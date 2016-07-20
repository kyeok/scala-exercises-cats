package harley.cats.functor

import cats._
import cats.implicits._
import org.scalatest.{Matchers, WordSpec}

/**
  * Created by Harley on 2016. 7. 20..
  */
class FunctorSpec extends WordSpec with Matchers {

  "Functor" should {
    "map length" in {
      Functor[Option].map(Option("Hello"))(_.length) should be(Some(5))
      Functor[Option].map(None: Option[String])(_.length) should be(None)
    }

    "lift" in {
      val lenOption: Option[String] => Option[Int] = Functor[Option].lift(_.length)
      lenOption(Some("harley")) shouldBe (Some(6))
    }

    "fproduct" in {
      val source = List("Cats", "is", "awesome")
      val product = Functor[List].fproduct(source)(_.length).toMap

      product.get("Cats").getOrElse(0) should be(4)
      product.get("is").getOrElse(0) should be(2)
      product.get("awesome").getOrElse(0) should be(7)
    }

    "compose" in {
      val listOpt = Functor[List] compose Functor[Option]
      listOpt.map(List(Some(1), None, Some(3)))(_ + 1) should be(List(Some(2), None, Some(4)))
    }
  }
}
