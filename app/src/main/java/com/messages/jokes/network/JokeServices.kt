package com.messages.jokes.network

import com.messages.jokes.model.JokeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeServices {
    @GET("joke/Any")
    suspend fun getJokes(
        @Query("amount") amount: Int
    ): Response<JokeResponse>
}