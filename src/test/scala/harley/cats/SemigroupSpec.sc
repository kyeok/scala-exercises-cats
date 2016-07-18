
import cats.implicits._

Map("foo" -> Map("bar" -> 5)).combine(Map("foo" -> Map("bar" -> 6), "baz" -> Map()))
Map("foo" -> List(1, 2)).combine(Map("foo" -> List(3, 4), "bar" -> List(42)))

Map("foo" -> Map("bar" -> 5)) ++ Map("foo" -> Map("bar" -> 6), "baz" -> Map())
Map("foo" -> List(1, 2)) ++ Map("foo" -> List(3, 4), "bar" -> List(42))

Map("foo" -> Map("bar" -> 5)) |+| Map("foo" -> Map("bar" -> 6), "baz" -> Map())
Map("foo" -> List(1, 2)) |+| Map("foo" -> List(3, 4), "bar" -> List(42))
