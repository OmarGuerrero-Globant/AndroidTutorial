package com.example.stackoverflow

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface StackOverflowAPI {
    @GET("/2.2/questions?order=desc&sort=votes&site=stackoverflow&tagged=android&filter=withbody")
    fun getQuestions(): Call<ListWrapper<Question>>

    @GET("/2.2/questions/{id}/answers?order=desc&sort=votes&site=stackoverflow")
    fun getAnswersForQuestion(@Path("id") questionId: String): Call<ListWrapper<Answer>>

    companion object {
        const val BASE_URL = "https://api.stackexchange.com"
    }
}