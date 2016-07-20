package harley.cats.apply

import cats.Apply
import cats.std.all._
import cats.syntax.all._
import org.scalatest.{Matchers, WordSpec}

/**
  * 어랏. 이 테스트 제대로 안 돈다. 무조건 다 Passed라고 나옴
  * Created by Harley on 2016. 7. 20..
  */
class ApplySpec extends WordSpec with Matchers {

  var addArity2 = (a: Int, b: Int) => a + b
  var addArity3 = (a: Int, b: Int, c: Int) => a + b + c

  "Apply는 이럴 것이다~" should {
    "AP" in {
      val intToString: Int ⇒ String = _.toString
      val double: Int ⇒ Int = _ * 2
      val addTwo: Int ⇒ Int = _ + 2

      Apply[Option].ap(Some(intToString))(Some(1)) should be(Some("1"))
      Apply[Option].ap(Some(double))(Some(1)) shouldBe (Some(2))
      Apply[Option].ap(Some(double))(None) should be(None)
      Apply[Option].ap(None)(Some(1)) should be(None)
      Apply[Option].ap(None)(None) should be(None)
    }

    "variants AP" in {
      Apply[Option].ap2(Some(addArity2))(Some(1), Some(2)) should be(Some(3))
      Apply[Option].ap2(Some(addArity2))(Some(1), None) should be(None)
      Apply[Option].ap3(Some(addArity3))(Some(1), Some(2), Some(3)) should be(Some(6))
    }

    "variants MAP" in {
      Apply[Option].map2(Some(1), Some(2))(addArity2) should be(Some(3))
      Apply[Option].map3(Some(1), Some(2), Some(3))(addArity3) should be(Some(6))
      Apply[Option].map3(Some(1), Some(2), None)(addArity3) should be(None)
    }

    "tuple" in {
      Apply[Option].tuple2(Some(1), Some(2)) should be(Some((1, 2)))
      Apply[Option].tuple3(Some(1), Some(2), Some(3)) should be(Some((1, 2, 3)))
    }

    "|@|" in {
      val option2 = Option(1) |@| Option(2)
      val option3 = option2 |@| Option.empty[Int]

      option2 map addArity2 should be(Some(3))
      option3 map addArity3 should be(Some(3))

      option2 apWith Some(addArity2) should be(890)
      option3 apWith Some(addArity3) should be()

      option2.tupled should be()
      option3.tupled should be()

    }
  }
}
