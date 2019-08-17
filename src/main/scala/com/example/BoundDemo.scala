package com.example

import scala.reflect.ClassTag

/**
 * upper bound 上界 [U <: T],U必须是T的子类或等于T
 * lower bound 下届 [U >: T],U必须是T的超类或等于T
 * context bound 上下文界定，看起来很像上下界语法，但其实是一种隐式参数的简写方式
 */
object BoundDemo {

  def test[C: ClassTag]() : Unit = {
    println(reflect.classTag[C].runtimeClass.getName)
  }

  def test2[C]()(implicit ct: ClassTag[C]): Unit = {
    println(Option(reflect.classTag[C]).map(_.runtimeClass.getName))
  }

  def main(args: Array[String]): Unit = {
    test[Int]() //int
    test() //scala.runtime.Nothing$
    test()(reflect.classTag[String])//java.lang.String
  }
}
