package com.rios.sandbox.exercises
import cats.Functor
import cats.syntax.functor._

sealed trait Tree[+A]

object Tree {

  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](tree: Tree[A])(f: A ⇒ B): Tree[B] = tree match {
      case Branch(left, right) ⇒ Branch(map(left)(f), map(right)(f))
      case Leaf(value)         ⇒ Leaf(f(value))
    }
  }

  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)

  def leaf[A](value: A): Tree[A] = Leaf(value)

}

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object BranchingOutFunctors extends App {

  val tree = Tree.branch(Tree.leaf(1), Tree.leaf(2))

  println(tree.map(_ * 2))

  print(Tree.leaf(100).map(_ + 50))
}
