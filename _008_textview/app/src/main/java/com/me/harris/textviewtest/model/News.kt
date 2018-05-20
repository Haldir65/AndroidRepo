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