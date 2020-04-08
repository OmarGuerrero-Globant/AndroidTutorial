package com.example.retrofittwitter

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private val credentials: String = okhttp3.Credentials.basic("aConsumerKey", "aSecret")
    private lateinit var twitterApi: TwitterApi
    private var token: OAuthToken? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createTwitterApi()
    }

    private fun createTwitterApi() {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val originalRequest: Request = chain.request()
            val builder: Request.Builder = originalRequest.newBuilder().header(
                "Authorization",
                if (token != null) token?.getAuthorization() else credentials
            )
            val newRequest: Request = builder.build()
            chain.proceed(newRequest)
        }.build()
        val retrofit = Retrofit.Builder()
            .baseUrl(TwitterApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        twitterApi = retrofit.create(TwitterApi::class.java)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.request_token_button -> twitterApi.postCredentials("client_credentials")
                .enqueue(tokenCallback)
            R.id.request_user_details_button -> {
                val editTextInput = username_edittext.text.toString()
                if (editTextInput.isNotEmpty()) {
                    twitterApi.getUserDetails(editTextInput).enqueue(userDetailsCallback)
                } else {
                    Toast.makeText(this, "Please provide a username", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private var tokenCallback: Callback<OAuthToken> = object : Callback<OAuthToken> {
        override fun onFailure(call: Call<OAuthToken>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(
            call: Call<OAuthToken>?,
            response: retrofit2.Response<OAuthToken>?
        ) {
            if (response!!.isSuccessful) {
                request_token_button.isEnabled = false
                request_user_details_button.isEnabled = true
                username_textview.isEnabled = true
                username_edittext.isEnabled = true
                token = response.body()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Failure while requesting token",
                    Toast.LENGTH_LONG
                ).show()
                Log.d(
                    "RequestTokenCallback",
                    "Code: " + response.code().toString() + "Message: " + response.message()
                )
            }
        }
    }

    private var userDetailsCallback: Callback<UserDetails> = object : Callback<UserDetails> {
        override fun onFailure(call: Call<UserDetails?>?, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(
            call: Call<UserDetails>?,
            response: retrofit2.Response<UserDetails>?
        ) {
            if (response!!.isSuccessful) {
                val userDetails: UserDetails = response.body()
                name_textview.text =
                    if (userDetails.name == null) "no value" else userDetails.name
                location_textview.text =
                    if (userDetails.location == null) "no value" else userDetails.location
                url_textview.text =
                    if (userDetails.url == null) "no value" else userDetails.url
                description_textview.text = if (userDetails.description
                        .isEmpty()
                ) "no value" else userDetails.description
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Failure while requesting user details",
                    Toast.LENGTH_LONG
                ).show()
                Log.d(
                    "UserDetailsCallback",
                    "Code: " + response.code().toString() + "Message: " + response.message()
                )
            }
        }
    }
}