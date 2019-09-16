package rock_paper_scissors

import scala.io.StdIn
import scala.util.Random

object Main extends App {
  val validMoves = Set('R', 'P', 'S')
  val validNameChars = (('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).toSet
  val swearWords = Set("cunt", "bitch", "html is a programming language")
  val rnd: Random = Random

  def clearScreen() = print("\u001b[2J")
  def isASwearWord(string: String) = swearWords.forall(string contains _)
  def isAValidMove(move: String) = validMoves.contains(move charAt 0)
  def isAValidCommand(move: String) = move.length == 1 && isAValidMove(move)
  def isAValidPlayerName(name: String) = name.length > 2 && name.forall(validNameChars contains _) && !isASwearWord(name.toLowerCase())
  def areScissorsWeaker(other: Char) = other == 'R'
  def isRockWeaker(other: Char) = other == 'P'
  def isPaperWeaker(other: Char) = other == 'S'
  def isTie(own: Char, other: Char) = own == other
  def havePlayerWon(own: Char, other: Char) = !isTie(own, other) && ((own == 'R' && !isRockWeaker(other)) || (own == 'P' && !isPaperWeaker(other)) || (own == 'S' && !areScissorsWeaker(other)))

  var playerName: String = ""
  var playerPoints: Int = 0
  var playerChoice: String = ""
  var aiChoice: String = ""
  var aiPoints: Int = 0
  var matchNumber: Int = 0

  println("Welcome to Rock-Paper-Scissors made in Scala!")
  println("\nRead the following information carefully, so you can play without an issue:")
  println("1.\tAnswer with R for Rock, P for Paper and S for Scissors")
  println("2.\tAnswer with bye to close the game")
  println("3.\tHave fun!")
  print("\nThat's all. Before I go... What's your name?\n")

  do {
    print("Make sure that your name doesn't contain any swear words, that contains alphanumeric chars only and it is at least 2 char long! So, what is it? ");
    playerName = StdIn.readLine().trim();
  } while(!isAValidPlayerName(playerName));

  println(s"\nNice, ${playerName}! Just press ENTER key when you're ready!")
  StdIn.readLine()

  do {
    clearScreen()
    matchNumber += 1

    println(s"Welcome to the Match #${matchNumber}")
    println(s"AI ${aiPoints} - ${playerPoints} ${playerName}")

    aiChoice = validMoves.toVector(rnd.nextInt(validMoves.size)).toString;

    println("\nWhat do you want to use?")
    println("\nR for Rock")
    println("P for Paper")
    println("S for Scissors")
    println("bye for leaving the match")

    do {
      print("\nYour choice: ")
      playerChoice = StdIn.readLine().trim().toUpperCase()
    } while(!isAValidCommand(playerChoice) && !playerChoice.equals("BYE"))

    if(!playerChoice.equals("BYE")) {
      println(s"\nAI choice: ${aiChoice}")
      println(s"Your choice: ${playerChoice}")

      if (!havePlayerWon(playerChoice.charAt(0), aiChoice.charAt(0)) && !isTie(playerChoice.charAt(0), aiChoice.charAt(0))) {
        println("\nAI won!")
        aiPoints += 1
      } else if (havePlayerWon(playerChoice.charAt(0), aiChoice.charAt(0)) && !isTie(playerChoice.charAt(0), aiChoice.charAt(0))) {
        println(s"\nYou won! GJ, ${playerName}")
        playerPoints += 1
      } else if (isTie(playerChoice.charAt(0), aiChoice.charAt(0))) {
        println(s"\nNice tie. No points awarded.")
      }

      println(s"AI ${aiPoints} - ${playerPoints} ${playerName}")
      println("Press ENTER to continue...")
      StdIn.readLine()
    }

  } while(!playerChoice.equals("BYE"))
}
