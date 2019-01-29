import cats.Show

import scala.util.Try

def parseInt(str: String): Option[Int] = Try(str.toInt).toOption

def divide(a: Int, b: Int): Option[Int] = if (b == 0) None else Some(a / b)

def stringDivideBy(str1: String, str2: String): Option[Int] = {
//  parseInt(str1).flatMap { int1 ⇒
//    parseInt(str2).flatMap { int2 ⇒
//      divide(int1, int2)
//    }
//  }

  for {
    x ← parseInt(str1)
    y ← parseInt(str2)
    r ← divide(x, y)
  } yield r
}

stringDivideBy("2", "1")
stringDivideBy("1", "0")

import cats.Monad
import cats.instances.option._
import cats.instances.list._
import cats.instances.int._
import cats.syntax.show._

// Option
val opt1 = Monad[Option].pure(10)

val opt2 = Monad[Option].flatMap(opt1)(o ⇒ Some(o + 1))

val opt3 = Monad[Option].map(opt2)(o ⇒ 10 * o)

// List
val list1 = Monad[List].pure(3)

val list2 = Monad[List].flatMap(List(1, 2, 3))(l ⇒ List(l, l * 10))

val list3 = Monad[List].map(list2)(v ⇒ v + 123)

println(list3.show)

import cats.syntax.applicative._

1.pure[Option]
Option(1)

import cats.syntax.functor._
import cats.syntax.flatMap._

def sumSquare[F[_] : Monad](a: F[Int], b: F[Int]): F[Int] =
  a.flatMap(x ⇒ b.map(y ⇒ x*x + y*y))

sumSquare(List(1, 2, 3), List(3, 2, 1))

def sumSquareRefac[F[_] : Monad](a: F[Int], b: F[Int]): F[Int] =
  for {
    x ← a
    y ← b
  } yield x*x + y*y

sumSquare(Option(2), Option(3))
