package com.example.implici

/**
 * 隐式类型参数和隐式参数列表
 * 隐式参数列表即最后一个参数列表,参数列表中需要先声明一个关键字implicit 后面才是变量：类型，变量：类型
 * 隐式参数列表可以显示的传入，也可以让编译器来传入，需要把隐式参数存在当前作用域内，并且是单个标识符的
 */
object ImplicitParamDemo {

  class PreferredPrompt(val preference: String) //首选的提示符

  class PreferredDrink(val preference: String) //首选的饮品

  object Greeter {
    def greet(name: String)(implicit prompt: PreferredPrompt, drink: PreferredDrink): Unit = {
      println(s"Welcom:${name}")
      println(s"Enjoy a cup of ${drink.preference} ?")
      println(prompt.preference)
    }
  }

  object JoesPrefs {
    implicit val prompt = new PreferredPrompt("Yes, master >")
    implicit val drink = new PreferredDrink("tea")
  }

  object ListUtil {
    /**
     * 这种[T: Ordering] 叫做Context Bound,并没有说T是什么类型，而是说T组合了Ordering,
     * 因为这样写会添加一个隐式列表，传入一个Ordering[T]
     */
    def maxList[T: Ordering](elements: List[T]): T = {
      elements match {
        case List() => throw new IllegalArgumentException("empty list!")
        case List(x) => x
        case x :: rest => {
          val maxRest = maxList(rest)
          if(implicitly[Ordering[T]].gt(x, maxRest)) x else maxRest
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    import JoesPrefs._
    Greeter.greet("Joe")
  }
}

