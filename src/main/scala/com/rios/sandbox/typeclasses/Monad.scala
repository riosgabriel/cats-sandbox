package com.rios.sandbox.typeclasses
import cats.Monad

trait Monad[F[_]] {

  def pure[A](value: A): F[A]

  def flatmap[A, B](value: F[A])(f: A ⇒ F[B]): F[B]

  def map[A, B](value: F[A])(f: A ⇒ B): F[B] =
    flatmap(value)(v ⇒ pure(f(v)))
}
