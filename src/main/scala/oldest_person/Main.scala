package oldest_person

import scala.collection.mutable.ListBuffer
import scala.io.StdIn

object Main extends App {
  val numbers = (('0' to '9')).toSet
  def containsNumbersOnly(inputString: String) = inputString.forall(numbers.contains(_))

  var ages = new ListBuffer[Int]()
  var ageRead: String = ""

  do {
    print("Please, input the age of a new person or input an empty line to calculate the maximum: ")
    ageRead = StdIn.readLine()

    if(containsNumbersOnly(ageRead) && ageRead != "") {
      ages += ageRead.toInt
    } else {
      print("Looks like something went wrong. Please, make sure that you input numbers only. ")
    }
  } while(ageRead != "")

  print("\n\n---\n\n")

  println(s"You inputted ${ages.length} ages. The maximum was ${ages.max}.")
  println(s"That means that the oldest person is ${ages.max} years old.")
}