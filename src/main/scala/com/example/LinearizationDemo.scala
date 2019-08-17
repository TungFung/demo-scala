package com.example

/**
 * 线性化规则
 * 当new一个实例时，scala会把继承的类和特质都拿出来，将它们线性化的排列在一起。
 * 调用super时，是这个链条向上最近的那一个。
 */
object LinearizationDemo {

  class Animal
  trait Furry extends Animal
  trait HasLegs extends Animal
  trait FourLegged extends HasLegs
  class Cat extends Animal with Furry with FourLegged

  /**
   * Cat,FourLegged,HasLegs,Furry,Animal,AnyRef,Any
   *
   * Any
   * |
   * AnyRef
   * |
   * Animal  <- HasLegs
   * |   \  /       \
   * |   Furry  FourLegged
   * |          /
   * |       /
   * |    /
   * Cat
   */
}
