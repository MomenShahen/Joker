package com.messages.jokes.model

data class Joke(
    var category: String,
    var type: String,
    var setup: String,
    var delivery: String,
    var lang: String,
    var joke: String,
    var id: Int,
    var safe: Boolean,
    var flags: Flags
){

}