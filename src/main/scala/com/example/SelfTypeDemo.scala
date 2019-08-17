package com.example

/**
 * 自身类型
 * 自身类型是在类中提到this时，对于this的假定类型，就是假定这个对象会mixin其他trait在运行时，
 * 那么在类的定义里面就直接可以引用mixin进来的方法。
 */
object SelfTypeDemo {

  abstract class Food(val name: String) {
    override def toString: String = name
  }

  class Recipe(val name: String, val ingredients: List[Food], val instructions: String){
    override def toString: String = name
  }

  object Apple extends Food("apple")

  trait SimpleFoods {
    object Pear extends Food("pear")
  }

  trait SimpleRecipes {
    this: SimpleFoods => //不加这句话，下面的Pear就会引用不到
    object FruitSalad extends Recipe("salad", List(Apple, Pear), "Mix it all together")
  }

}
