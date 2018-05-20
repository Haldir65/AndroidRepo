package com.me.harris.textviewtest.model

data class News(val name: String, val id: Long, val creater: Customer)

data class Customer(val id: Long, val name: String)


data class User(val name: String, val title: String)


data class MainPage(val error:Boolean = false,
                    val results:List<Topic>)

data class Topic(val _id: String, val createdAt: String, val desc: String,
                 val images: List<String>,
                 val publishedAt: String
                 , val source: String,
                 val type: String,
                 val url: String,
                 val used: Boolean,
                 val who: String)


class FoodStore : Production<Food> {
    override fun produce(): Food {
        println("Produce food")
        return Food()
    }
}

class FastFoodStore : Production<FastFood> {
    override fun produce(): FastFood {
        println("Produce food")
        return FastFood()
    }
}

class InOutBurger : Production<Burger> {
    override fun produce(): Burger {
        println("Produce burger")
        return Burger()
    }
}

open class Food
open class FastFood : Food()
class Burger : FastFood()

interface Production<out T> {
    fun produce(): T
}