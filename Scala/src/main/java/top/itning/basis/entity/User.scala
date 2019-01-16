package top.itning.basis.entity

class User {
  var id: String = _
  //其它包无法访问
  private var password: String = _
}

object User {
  def apply(): User = new User()
}
