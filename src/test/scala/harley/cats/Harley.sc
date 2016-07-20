import cats.Apply
import cats.implicits._

var addArity2 = (a: Int, b: Int) => a + b
var addArity3 = (a: Int, b: Int, c: Int) => a + b + c

val option2 = Option(1) |@| Option(2)
val option3 = option2 |@| Option.empty[Int]

option2 map addArity2
option3 map addArity3

option2 apWith Some(addArity2)
option3 apWith Some(addArity3)

option2.tupled
option3.tupled