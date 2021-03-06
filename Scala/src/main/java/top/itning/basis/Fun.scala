package top.itning.basis

object Fun {

  def main(args: Array[String]): Unit = {
    defFun()
    map()
    arr()
    method2fun()
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

  def methodOfFunNo(f: () => Unit): Unit = {
    f()
  }

  def methodOfFun(f: Int => Int): Unit = {
    f(3)
  }

  def methodOfFun2(f: Int => Int, f2: (Int, String) => Double): Unit = {
    f(3)
    f2(1, "2")
  }

  def method2fun(): Unit = {
    val mapfun = map _
    methodOfFunNo(mapfun)
    methodOfFunNo(map)
  }


  def arr(): Unit = {
    val a = Array(1, 2, 3)
    println(a.map(_ * 10).toBuffer)
  }
}
