package com.cheeseocean;

fun main(){
    println("hello kotlin")
    hello("wuxc", 23)
    var customer = Customer("wuxc");
    println(customer.lowerName);
}

fun hello(name: String, age: Int){
    println("name$name, age: $age")
}

class Customer(name: String){
    val lowerName = name.toUpperCase();
}