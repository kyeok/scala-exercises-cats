Option(1).map(_ + 1)
List(1, 2, 3).map(_ + 1)
Vector(1, 2, 3).map(_.toString)

import cats._

implicit val optionFunctor: Functor[Option] = new Functor[Option] {
  def map[A, B](fa: Option[A])(f: A => B) = fa map f
}

implicit val listFunctor: Functor[List] = new Functor[List] {
  def map[A, B](fa: List[A])(f: A => B) = fa map f
}

Functor[List].map(List("harley", "juliet", "liam"))(_.length)

val source = List("Cats", "is", "awesome")
val product = Functor[List].fproduct(source)(_.length).toMap

product.get("Cats").getOrElse(0)

val listOpt = Functor[List] compose Functor[Option]
listOpt.map(List(Some(1), None, Some(3)))(_ + 1)