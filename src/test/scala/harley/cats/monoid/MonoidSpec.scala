package harley.cats.monoid

import cats._
import cats.std.all._
import cats.syntax.all._
import org.scalatest.{Matchers, WordSpec}

/**
  * Semigroup 의 기능에 empty가 추가로 들어간 구조
  *
  * 생각해보기!
  * sequence하기 진행 시킬 수 밖에 없는 것들... 왼쪽부터 오른쪽으로 순차적으로 더 한다고 하면? 느림
  * 어느쪽에서 더하건 결과가 같다면? 병렬처리가 가능함.
  *
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
