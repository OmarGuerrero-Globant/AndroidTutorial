package com.example.retrofittwitter

import retrofit2.Call
import retrofit2.http.*


interface TwitterApi {
    @FormUrlEncoded
    @POST("oauth2/token")
    fun postCredentials(@Field("grant_type") grantType: String): Call<OAuthToken>

    @GET("/1.1/users/show.json")
    fun getUserDetails(@Query("screen_name") name: String): Call<UserDetails>

    companion object {
        const val BASE_URL = "https://api.twitter.com/"
    }
}