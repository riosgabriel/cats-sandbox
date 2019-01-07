package com.rios.sandbox

import cats.Show
import cats.instances.string._
import cats.syntax.semigroup._
import cats.instances.int._
import cats.syntax.show._
import cats.Eq
import cats.syntax.eq._
import cats.instances.option._
import cats.syntax.option._

object Main extends App {

  println("Hello " |+| "Cats!")

  implicit val welcome: String = "Welcome!"

  def give_welcome(implicit text: String): Unit =
    println(text)

  give_welcome("Hello!")
  give_welcome

  val showInt = Show.apply[Int]
  println(showInt.show(123))

  val showString = Show.apply[String]
  println(showString.show("ABC"))

  println(123.show)
  println("ABC".show)

  val eqInt = Eq[Int]
  println(eqInt.eqv(123, 123))

  println(123 === 123)
  println(123 =!= 321)

  println(Option(123) === Option(123))
  // Some(123) === None -> compile error

  println((Some(1): Option[Int]) === (None: Option[Int]))

  1.some === none[Int]

  1.some =!= none[Int]

  println("abc" ++ "def")
}
