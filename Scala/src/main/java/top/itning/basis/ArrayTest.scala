package top.itning.basis

import scala.collection.mutable.ArrayBuffer

object ArrayTest {
  def main(args: Array[String]): Unit = {
    val array = new Array[Int](10)
    array(0) = 1
    array(2) = 3
    println(array.toBuffer)
    println()
    //泛型 []
    //下标 ()
    val a = Array[Int](1, 2, 3)
    println(a.toBuffer)
    //变长数组
    //mutable 可变
    val arrayBuffer = new ArrayBuffer[Int]()
    arrayBuffer += 1
    //追加元组
    arrayBuffer += (2, 3, 4)
    //追加Array
    arrayBuffer ++= a
    arrayBuffer.insert(0, 9, 8, 7)
    arrayBuffer remove 0
    println("arrayBuffer: " + arrayBuffer)
    a.sortBy(x => x)
    a.sortWith(_ > _)
    a.sortWith((a, b) => a > b)
  }
}
