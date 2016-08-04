package harley.cats.apply

import cats._
import cats.std.all._
import cats.syntax.all._
import org.scalatest.{Matchers, WordSpec}

/**
  * 어랏. 이 테스트 제대로 안 돈다. 무조건 다 Passed라고 나옴
  *
  * apply는 함수제공자, 도구
  *
  * f : box or function
  * ff : box에 들어있는 함수
  * fa : box에 들어있는 A
  *
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
      option3 map addArity3 should be(Option.empty[Int])

      option2 apWith Some(addArity2) should be(Some(3))
    }


  }

  "apply" should {
    "test" in {
      println(Functor[List].map(List(1, 2, 3))(x => List(x)))
      //map A => B
    }
  }

  "harley ap" should {
    "test" in {
      def foo = (a: Int, b: Int) => a + b
      println(Apply[Option].ap2(Some(foo))(Some(1), Some(2)))

      //def ap2[A, B, Z](ff: F[(A, B) => Z])(fa: F[A], fb: F[B]): F[Z] =
      //Apply[Int].ap2(Int(foo))(Int(1), Int(2))
    }
  }

}