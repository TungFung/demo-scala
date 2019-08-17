package com.example

object FieldDemo {

  class Person(name: String, val age: Int, var sex: String)

  abstract class Food {
    var name: String
    val days: Int
  }

  class Apple extends Food with Printable {
    override var name: String = "apple"
    override val days: Int = 30
    override val someValue: String = "I'm an apple"
  }

  trait Printable {
    var initValue: Int = _
    val initValue2: Int = _
    val someValue: String

    def print(): Unit = {
      println(someValue)
    }
  }

  trait Eatable {
    val v: String
  }

  def main(args: Array[String]): Unit = {
    val person: Person = new Person("Eric", 30, "male")
    person.age //不能重新赋值
    person.sex = "Female" //ok
    //person.name 没有这个字段,必须要有var或val

    val food = new Apple() with Eatable {
      override val v: String = "Can be eat"
    }
    food.days
    food.name
    food.print
    food.someValue
    food.initValue
    food.initValue2
    food.v

  }
}
