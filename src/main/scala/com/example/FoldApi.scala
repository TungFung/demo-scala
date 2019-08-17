package com.example

object FoldApi {

  def testFold(): Unit = {
    val iter: Iterator[Int] = Iterator(100,200,300)
    val result: Int = iter.fold(1)((f1: Int, f2: Int) => {f1 + f2})
    println(result)
  }

  def testFoldLeft(): Unit = {
    val iter: Iterator[Int] = Iterator(100,200,300)
    val result: Int = iter.foldLeft(1)((f1: Int, f2: Int) => {f1 + f2})
    println(result)
  }

  def main(args: Array[String]): Unit = {
    testFoldLeft()
  }

}
