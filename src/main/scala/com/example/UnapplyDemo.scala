package com.example

object UnapplyDemo {

  object Twice {
    def unapply(s: String): Option[String] = {
      val length = s.length / 2
      val half = s.substring(0, length)
      if(half == s.substring(length)) Some(half) else None
    }
  }

  object UpperCase {
    def unapply(s: String): Boolean = s.toUpperCase == s
  }

  object Email {
    def unapply(s: String): Option[(String, String)] = {
      val parts = s split "@"
      if(parts.length == 2) Some(parts(0), parts(1)) else None
    }
  }

  def userTwiceUpper(s: String) = s match {
    case Email(Twice(x @ UpperCase()), domain) => "match:" + x + " in domain " + domain
    case _ => "no match"
  }

  object Domain {
    //unapplySeq 是变长参数匹配提取
    def unapplySeq(s: String): Option[Seq[String]] = {
      Some(s.split("\\.").reverse)
    }
  }

  def isTomInDotCom(s: String): String = s match {
    case Email("tom", Domain("com", lastTwo, _*)) => s"match, lastTwo is ${lastTwo}"
    case _ => "no match"
  }

  object ExpandedEmail {
    def unapplySeq(s: String): Option[(String, Seq[String])] = {
      val parts = s.split("@")
      if (parts.length == 2)
        Some(parts(0), parts(1).split("\\.").reverse)
      else
        None
    }
  }

  def main(args: Array[String]): Unit = {
    //字符串之所以能映射到Email类型，并且被拆解出两部分的数据，是因为Email中定义了unapply方法
    "eric@163.com" match {
      case Email(user, domain) => println(s"user:${user}, domain:${domain}")
      case _ => println("no match")
    }

    //这是变量绑定模式，变量 @ 含有unapply返回Boolean的提取器
    //这种语法的意思是，如果unapply方法返回true,那么把输入的值赋值给变量
    "ABC" match {
      case x @ UpperCase() => println(x)
      case _ => println("no match")
    }

    //这里首先调用Email的unapply，拆解出第一部分数据DIDI,第二部分数据163.com
    //163.com 这部分赋值到domain变量中
    //DIDI 进入到Twice的unapply方法中被拆解出 DI
    //DI 在进入UpperCase的unapply方法中，返回true，所以把输入DI赋值给绑定变量x
    println(userTwiceUpper("DIDI@163.com"))

    //用unapplySeq提取出多个在Seq中
    println(isTomInDotCom("tom@sun.com")) //match, lastTwo is sun
    println(isTomInDotCom("tom@oracle.sun.com")) //match, lastTwo is sun

    //变量赋值时也可以使用提取器接收
    val ExpandedEmail(name, topdom, subdoms @ _*) = "tom@support.epfl.ch"
    println(name + ", " + topdom + ", " + subdoms) //tom, ch, WrappedArray(epfl, support)
  }
}
