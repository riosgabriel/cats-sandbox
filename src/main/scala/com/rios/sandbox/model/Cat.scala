package com.rios.sandbox.model

import cats.{ Eq, Show }
import cats.instances.int._
import cats.instances.string._
import cats.instances.option._
import cats.syntax.show._
import cats.syntax.eq._
import com.rios.sandbox.Printable

final case class Cat(name: String, age: Int, color: String)

object Cat {

  implicit val printCat: Printable[Cat] = (cat: Cat) ⇒ s"${cat.name} is a ${cat.age} year-old ${cat.color} cat"

  implicit val catShow: Show[Cat] = Show.show { cat ⇒
    s"${cat.name.show} is a ${cat.age.show} year-old ${cat.color.show} cat"
  }

  implicit val catEq: Eq[Cat] = Eq.instance[Cat] { (cat1, cat2) ⇒
    (cat1.name === cat2.name) &&
    (cat1.age == cat2.age) &&
    (cat1.color == cat2.color)
  }

}

object CatMain extends App {

  val tom = Cat("Tom", 2, "Blue")
  val garfield = Cat("Garfield", 38, "Ginger")

  Printable.print(tom)

  import com.rios.sandbox.PrintableSintax._

  tom.print

  // Using cats instead

  println(garfield.show)

  val optionTom = Option(tom)
  val optionGarfield = Option(garfield)
  val optionEmptyCat = Option.empty[Cat]

  println(optionTom === optionTom)
  println(optionTom === optionGarfield)
  println(optionGarfield =!= optionEmptyCat)
}
