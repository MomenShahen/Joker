package com.messages.jokes.model

data class JokeResponse(var error: Boolean, var amount: Int, var jokes: List<Joke>){

}