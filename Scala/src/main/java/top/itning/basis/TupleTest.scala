package top.itning.basis

object TupleTest {
  def main(args: Array[String]): Unit = {
    val tuple = ("a", 2, 3.4)
    println(tuple._1)
    val t, (x, y, z) = ("a", 2, 3.4)
    println(t, x, y, z)
  }
}
