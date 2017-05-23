package com.me.harris.androidanimations._36_fun_kt

/**
 * Created by Harris on 2017/5/23.
 */
sealed class UserResult //open

// in kyt , all class are final , no extend allowed ,unless you make it open
// so if i remove the sealed declaration ,warning
data class Success(val Users: List<User>) : UserResult()

data class Failure(val Users: List<User>) : UserResult()

fun retrieveUsers(): UserResult {
    return Success(ArrayList<User>(3))
}


fun main(array: Array<String>) {
    val results = retrieveUsers()

    val values = generateSequence(1) { //generate a infinite sequence
        it * 10
    }
    values.take(10).forEach { println(it) } //so i just want first ten of them ,1,10,100,1000,etc

    when (results) {
        is Success -> results.Users.forEach { println(it.name) } // smart casting
        is Failure -> results.Users.forEach {
            println("this is the failure user id ${it.id} \n")
        }
    }

    val list = ArrayList<String>(5)

    list.asSequence().forEach { println("value$it") }

}

