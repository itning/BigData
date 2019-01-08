package top.itning.basis

class Fun {

}

object Fun {

  def main(args: Array[String]): Unit = {
    defFun()
    map()
    arr()
  }

  def defFun(): Unit = {
    val f = (x: Int, y: Int) => {
      x + y
    }
    println(f)
    println(f(1, 3))
  }

  def map(): Unit = {
    println((1 to 10).map((r: Int) => r * 10))
    println((1 to 10).map(r => r * 10))
    println((1 to 10).map(_ * 10))
  }

  def methodOfFun(f: Int => Int): Unit = {
    f(3)
  }

  def arr(): Unit = {
    val a = Array(1, 2, 3)
    println(a.map(_ * 10).toBuffer)
  }
}
