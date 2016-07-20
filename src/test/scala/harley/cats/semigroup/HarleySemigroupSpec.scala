package harley.cats

import cats.implicits._
import cats.kernel.Semigroup
import org.scalatest.{Matchers, WordSpec}

/**
  * https://github.com/typelevel/cats/blob/master/docs/src/main/tut/semigroup.md
  * Created by Harley on 2016. 7. 20..
  */
class HarleySemigroupSpec extends WordSpec with Matchers {

  "나만의 세미 그룹을 만들어 보겠습니다." should {

    "그럼.." in {
      case class Foo(a: Int, b: String)
      implicit val fooSemigroup = new Semigroup[Foo] {
        override def combine(x: Foo, y: Foo): Foo = Foo(x.a + y.a, x.b + y.b)
      }

      val foo1 = Foo(1, "a")
      val foo2 = Foo(2, "b")
      Semigroup[Foo].combine(foo1, foo2) should be(Foo(3, "ab"))
    }

    "이건 cats에서 제공해주는 것" in {
      Semigroup[String].combine("a", "b") should be("ab")
      "a" |+| "b" should be("ab")
    }
  }
}
