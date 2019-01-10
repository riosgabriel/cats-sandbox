package com.rios.sandbox.worksheet
import cats.Functor
import cats.instances.function._
import cats.syntax.functor._
import cats.instances.list._
import cats.instances.option._
import com.rios.sandbox.model.Cat

/*
  Using an object instead of a worksheet,
  because the sc files do not seem to work properly,
  even with the `-Ypartial-unification` option in the sbt configuration.
 */
object CatsFunctor {

  def main(args: Array[String]): Unit = {

    val f = (x: Int) ⇒ x.toDouble

    val v = (x: Double) ⇒ x * 2

    val doubleInt = f map v

    println(doubleInt(2))

    val toDouble = (x: Int) ⇒ x.toDouble

    println(toDouble(2))

    println(toDouble.map(_ + 1).map(_ * 2).map(_ + "!")(2))

    val list1 = List(1, 2, 3)
    Functor[List].map(list1)(_ * 2)

    val option1 = Option(123)
    Functor[Option].map(option1)(_.toString)

    val op = Option(100)

    println(Functor[Option].lift((x: Int) ⇒ x + 10)(op))

    val list = List(1, 2, 3)

    println(Functor[List].lift((x: Int) ⇒ x * 2)(list))
    println(Functor[List].map(list)(_ * 2))

    doMath(Option(20))
    doMath(List(1, 2, 3))

  }

  def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] = start.map(n ⇒ n + 1 * 2)

}
