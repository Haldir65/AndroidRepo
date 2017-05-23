package com.me.harris.androidanimations._36_fun_kt

import java.math.BigDecimal

/**
 * Created by Harris on 2017/5/23.
 */
data class Money(var amount: Int, val currency: String) {
    operator fun plus(popcon: Money) = popcon.amount
}

data class DecMoney(var amount: BigDecimal, val currency: String)


fun sendPayment(m: Money, message: String? = "default message"): Unit {
    println("sending ouy money ${m.amount} currency is${m.currency}")
}


fun main(args: Array<String>) {  // Top Level entry Point
    val tickets = Money(100, "$")
    val popcon = tickets.copy(500, "EUR")

    if (tickets !== popcon) {
        println("stuff not equal")
    }

    val costs = tickets + popcon

    val train : DecMoney = DecMoney(100.bd, "$")


    val users: List<User> = ArrayList<User>(3)
    // 1. high order function
    findEmails(users,{each -> each.endsWith(".com")}) //just like lambda

    // 2.
    findEmails(users,{it.endsWith(".com")}) //one parameter like in groovy

    //3.
    findEmails(users){
        it.endsWith(".com")
    }


    // Like stream
    val dotUser = users.filter { it.id ==10L }.sortedBy { it.id }.map {  Pair(it.id,it.name)}.first()

    val (id,_) = users.filter { it.id ==10L }.sortedBy { it.id }.map {  Pair(it.id,it.name)}.first()

    print(id)

//    sendPayment(tickets,"good luck")
//
//    sendPayment(popcon)  // default parameter
//
//    val popMoney = DecMoney(BigDecimal(100),"$")
//
//    7.percentof(popMoney)// 7 Percent of popMoney
//
//    7 percentof popMoney // any extension function with on parameter can add in fix
//
//
//    val bd2 = 100.bd
//    val decM :DecMoney  = popMoney.copy(bd2,"$")
//
//    print(decM.amount)

}



fun sum(x: Int, y: Int) = x + y

fun convertToDollars(m: Money,o:Money) :Money{
    o.amount = (m.amount * 7.5).toInt()
    return o
}

//use nullable
fun printMoneyAmount(m: Money?) {
//    print(m?.amount)
    print(m?.amount)

}


//high order function,  a function that takes function or return a function

data class User(val id: Long,val name:String)


fun findEmails(user: List<User>, prdicate: (String) -> (Boolean)) :List<User> {
    TODO("Later!") // filtering users
}






fun convertVerboseRealMoney(m: DecMoney): DecMoney {
    when (m.currency) {
        "$" -> return m
        "EUR" -> return DecMoney(m.amount * BigDecimal(1.10),"$")
       else -> TODO()
    }
}

// a much more simple expression
fun convertRealMoney(m: DecMoney) =when (m.currency) {
    "$" ->  m
    "EUR" ->  DecMoney(m.amount * BigDecimal(1.10),"$")
    else -> TODO()
}


// extension Function
fun BigDecimal.percent(percentage:Int) = this.multiply(BigDecimal(percentage)).divide(BigDecimal(1000))

infix fun Int.percentof(money: DecMoney) = money.amount.multiply(BigDecimal(this)).divide(BigDecimal(100))

private val Int.bd: BigDecimal
    get() {
        return BigDecimal(this) //return the instance
    }