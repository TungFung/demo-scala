package com.example

/**
 * scala中的型变注解
 * 不变
 * 协变 -- 对于Queue[+T]， Queue[String] 是 Queue[Any] 的子类，协变类型参数不能用做方法参数,可以作为返回值
 * 逆变 -- 用于方法入参
 */
object VariantDemo {

  class Publication(val title: String)

  class Book(title: String, author: String) extends Publication(title)

  object Library {
    val books: Set[Book] = Set(
      new Book("C++", "Eric"),
      new Book("Java", "Tom")
    )

    //这里是 Book => AnyRef
    def printBookList(info: Book => AnyRef): Unit = {
      for(book <- books) println(info(book))
    }
  }

  def main(args: Array[String]): Unit = {
    //这里是Publication => String
    def getTile(p: Publication): String = {
      p.title
    }

    //Publication => String 是 Book => AnyRef 的子类, 所以可以这样传进去
    Library.printBookList(getTile)
  }
}
