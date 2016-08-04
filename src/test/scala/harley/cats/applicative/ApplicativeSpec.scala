package harley.cats.applicative

import cats._
import cats.implicits._
import org.scalatest.{FunSuite, Matchers}

/**
  * Created by Harley on 2016. 8. 2..
  */
class ApplicativeSpec extends FunSuite with Matchers {

  test("pure") {
    Applicative[Option].pure(1) shouldBe Some(1)
    Applicative[List].pure(1) shouldBe List(1)

    //Applicative[List] compose Applicative[Option].pure(1) shouldBe List(1)
  }

}
