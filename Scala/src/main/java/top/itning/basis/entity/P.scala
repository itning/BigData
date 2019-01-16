package top.itning.basis.entity

import scala.io.Source

class P(val id: String) {
  try {
    Source.fromFile("").mkString
  } catch {
    case e: Exception => e.printStackTrace()
  }
}
