package com.rios.sandbox.typeclasses

trait Printable[A] {
  self ⇒

  def format(value: A): String

  def contramap[B](f: B ⇒ A): Printable[B] = (value: B) ⇒ self.format(f(value))

}

object PrintableInstances {

  implicit val printableString = new Printable[String] {
    override def format(value: String): String = s"The value $value is a String"
  }

  implicit val optPrintableString = new Printable[Option[String]] {
    override def format(value: Option[String]): String = value match {
      case Some(s) ⇒ s"The value $s is a Option[String]"
      case None    ⇒ s"The value is a None"
    }
  }

  implicit val printableInt = new Printable[Int] {
    override def format(value: Int): String = s"The value $value is an Int"
  }
}

object Printable {

  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)

  def print[A](value: A)(implicit p: Printable[A]): Unit = println(format(value))

}

object PrintableSintax {

  implicit class PrintableOps[A](value: A) {

    def format(implicit p: Printable[A]): String = Printable.format(value)

    def print(implicit p: Printable[A]): Unit = Printable.print(value)

  }

}

object PrintableMain extends App {

  val intValue = 123

  val stringValue = "A String"

  val someStringValue = Option("Option String")
  val noneValue = Option.empty[String]

  import PrintableInstances._

  Printable.print(intValue)
  Printable.print(stringValue)
  Printable.print(someStringValue)
  Printable.print(noneValue)

}
