import cats.Monoid
import cats.instances.string._
import cats.instances.int._
import cats.instances.option._
import cats.Semigroup

Monoid[String].combine("Hi ", "there")
// Same as
Monoid.apply[String].combine("Hello ", "World")

Monoid[String].empty
// Same as
Monoid.apply[String].empty

Semigroup[String].combine("Hi ", "man")

Monoid[Int].combine(1, 2)

val opt1 = Option.empty[String]
val opt2 = Option.apply("Hey Ho")

Monoid[Option[String]].combine(opt1, opt2)


// Monoid Syntax
import cats.syntax.semigroup._

val combinedString = "Hi " |+| "there!" |+| Monoid[String].empty

combinedString

val combinedInt = 1 |+| 2 |+| Monoid[Int].empty

combinedInt

combinedInt |+| 4


//def add(items: List[Int]): Int = items.foldLeft(Monoid[Int].empty)(_ |+| _)

//def add[A](items: List[A])(implicit m: Monoid[A]): A =
//  items.foldLeft(m.empty)(_ |+| _)

def add[A : Monoid](items: List[A]): A = items.foldLeft(Monoid[A].empty)(_ |+| _)

val listOfInts = List(1, 2, 3, 4, 5, 6)

add(listOfInts)

val listOfOptInts = List(Some(1), None, Some(3), None, Some(5))

add(listOfOptInts)

val listOfSomeInts = List(Some(1), Some(2), Some(3))

//Error could not find a implicit value for type List[Some[Int]]
// add(listOfSomeInts)

case class Order(totalCost: Double, quantity: Double)

implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    override def empty = Order(0, 0)
    override def combine(o1: Order, o2: Order) = Order(
      o1.totalCost + o2.totalCost,
      o1.quantity + o2.quantity
    )
  }

val listOfOrder = List(Order(2, 4), Order(5, 3))

add(listOfOrder)

