package com.example.retrofittwitter

import com.google.gson.annotations.SerializedName

data class OAuthToken(@SerializedName("access_token") private val accessToken : String,
                 @SerializedName("token_type") private val tokenType : String){

    fun getAuthorization() : String = "$tokenType $accessToken"
}