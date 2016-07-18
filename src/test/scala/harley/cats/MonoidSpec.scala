package harley.cats

import cats._
import cats.std.all._
import cats.syntax.all._
import org.scalatest.{Matchers, WordSpec}

/**
  * Created by Harley on 2016. 7. 18..
  */
class MonoidSpec extends WordSpec with Matchers {

  "Monoid String" should {
    Monoid[String].empty shouldBe ""
    Monoid[String].combineAll(List("a", "b", "c")) shouldBe "abc"
    Monoid[String].combineAll(List()) shouldBe ""
  }

  "Monoid List" should {
    Monoid[Map[String, Int]].combineAll(List(Map("a" -> 1, "b" -> 2), Map("a" -> 3))) should be(Map("a" -> 4, "b" -> 2))
    Monoid[Map[String, Int]].combineAll(List()) should be(Map())
  }

  "Monoid foldMap" should {
    val l = List(1, 2, 3, 4, 5)
    l.foldMap(identity) should be(15)
    l.foldMap(i => i.toString) should be("12345")
  }

  "Monoid tuple" should {
    val l = List(1, 2, 3, 4, 5)
    l.foldMap(i => (i, i.toString)) should be(15, "12345")
  }
}
