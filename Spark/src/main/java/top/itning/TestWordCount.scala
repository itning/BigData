package top.itning

import org.apache.spark.{SparkConf, SparkContext}

/**
  * -Dspark.master=local[*] -Dhadoop.home.dir=G:\\winutils\\hadoop-2.8.3
  */
object TestWordCount {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("wordCount")
    val sc = new SparkContext(sparkConf)
    val array = sc.parallelize(Array(1, 4, 6, 3, 2, 7)).map(_ * 10).collect()
    println("start")
    println(array.toBuffer)
    println("end")
    sc.stop()
  }
}