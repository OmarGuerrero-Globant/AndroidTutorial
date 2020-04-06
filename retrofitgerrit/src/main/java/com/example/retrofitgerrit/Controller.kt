package com.example.retrofitgerrit

import android.os.Build
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.function.Consumer


class Controller : Callback<List<Change>> {
    fun start() {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val gerritAPI = retrofit.create(GerritAPI::class.java)
        val call: Call<List<Change>> = gerritAPI.loadChanges("status:open").also {
            it.enqueue(this)
        }
    }



    companion object {
        const val BASE_URL = "https://git.eclipse.org/r/"
    }

    override fun onFailure(call: Call<List<Change>>, t: Throwable) = t.printStackTrace()

    override fun onResponse(call: Call<List<Change>>?, response: Response<List<Change>>) {
        if (response.isSuccessful) {
            val changesList: List<Change> = response.body()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                with(changesList) {
                    forEach(Consumer { (subject) ->
                                println(subject)
                            })
                }
            }
        } else {
            println(response.errorBody())
        }
    }
}
