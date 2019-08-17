package com.example

object ForDemo {

  def main(args: Array[String]): Unit = {
    //二重循环,只能用在for yield, 单for不行
    val result = for{
      x <- 1 to 2
      y <- 1 to 3
    } yield x + y

    println(result)
  }
}
