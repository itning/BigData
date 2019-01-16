package top.itning.basis

import top.itning.basis.entity.{P, User}

class ObjectTest {

}

object ObjectTest {
  def main(args: Array[String]): Unit = {
    val user = new User
    println(user)
    val p = new P("")
    println(p)

  }
}
