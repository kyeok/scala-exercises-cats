package harley.cats.monoid

import cats.Monoid
import cats.implicits._
import org.scalatest.{Matchers, WordSpec}

/**
  * Created by Harley on 2016. 7. 20..
  */
class HarleyMonoidSpec extends WordSpec with Matchers {

  "나만의 모노이드를 만들어 보겠소" should {
    "그럼.. " in {
      case class Harley(a: Int, b: String)
      implicit val harleyMonoid = new Monoid[Harley] {
        override def empty: Harley = new Harley(0, "")

        override def combine(x: Harley, y: Harley): Harley = Harley(x.a + y.a, x.b + y.b)
      }

      var h1 = Harley(1, "HARLEY")
      var h2 = Harley(2, "harley")

      h1 |+| h2 should be(Harley(3, "HARLEYharley"))
    }
  }
}
