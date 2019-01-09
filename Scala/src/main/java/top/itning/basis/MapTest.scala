package top.itning.basis

import scala.collection.mutable

object MapTest {
  def main(args: Array[String]): Unit = {
    val map = Map("a" -> 1, "b" -> 2)
    println(map)
    val m = mutable.Map[String, Int]("1" -> 1)
    m("2") = 2
    m += ("3" -> 3)
    println(m)
    println(("3" -> 3).getClass.getSimpleName)
  }
}
