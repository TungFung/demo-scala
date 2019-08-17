package com.example.implici

object ImplicitClassDemo {

  /**
   * 隐式类，只能是嵌套类而且不能是样例类，并且类参数必须有且只能有一个
   * 因为隐式类会自动生成一个从类参数类型转为隐式类类型的隐式方法
   */
  implicit class RectangleMaker(width: Int) {
    def x(height: Int) = Rectangle(width, height)
  }

  case class Rectangle(width: Int, height: Int)

  def main(args: Array[String]): Unit = {
    val rectangle: Rectangle = 3 x 4 //这里面应用的隐式类型转换函数是编译器自动添加的
  }
}
