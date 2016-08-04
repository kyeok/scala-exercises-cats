package harley.cats.monad

import cats.Monad
import cats.std.all._
import org.scalatest.{FunSuite, Matchers}

/**
  * Created by Harley on 2016. 8. 4..
  */
class MonadSpec extends FunSuite with Matchers {
  test("Test") {
    println(Option(Option(1)).flatten)
    Option(Option(1)).flatten shouldBe Some(1)
    Option(None).flatten shouldBe None
    println(List(List(1), List(2, 3)).flatten)
    println(List(List(1), List(2, 3), List(4, 5, 1)).flatten)
    List(List(1), List(2, 3)).flatten shouldBe List(1, 2, 3)
  }

  test("Monad pure") {
    Monad[Option].pure(42) shouldBe Some(42)
    //Monad[List].flatMap(List(1, 2, 3))(x ⇒ List(x, x))

    Monad[List].flatMap(List(1, 2, 3))(x => List(x, x)) shouldBe List(1, 1, 2, 2, 3, 3)

    Monad[Option].ifM(Option(true))(Option("truthy"), Option("falsy")) shouldBe Option("truthy")
  }

}
