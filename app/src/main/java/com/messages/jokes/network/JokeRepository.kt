package com.messages.jokes.network

import com.messages.jokes.model.JokeResponse
import retrofit2.Response
import javax.inject.Inject

class JokeRepository @Inject constructor(var jokeServices: JokeServices){
    suspend fun getJokes(
        id: Int
    ): Response<JokeResponse> {
        return jokeServices.getJokes(
            id
        )
    }

}