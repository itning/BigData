package top.itning.basis

object Basis {
  def main(args: Array[String]): Unit = {
    varVal()
    ifElse()
    forOfTo()
  }

  def varVal(): Unit = {
    //var 可以改变
    var i = 1
    //val 不可以改变
    val j = 2
    //指定类型
    val str: String = ""
  }

  def ifElse(): Unit = {
    var x = 1
    val y = if (x > 1) x else x - 1
    //缺失else
    val z = if (x != 1) x
    println(s"ifElse Method invoked: z==$z " + z.getClass.getSimpleName)
  }

  def forOfTo(): Unit = {
    println(1 to 10)
    for (i <- 1 to 10) {
      print(i + " ")
    }
    println()
    for (i <- 1 to 3; j <- 1 to 3 if i != j) {
      print(i + "" + j + " ")
    }
    println()
    val v = for (i <- 1 to 10) yield i * 10
    println(s"v is: $v")
    for (i <- 1 until 10) {
      print(i + " ")
    }
    println()
  }
}