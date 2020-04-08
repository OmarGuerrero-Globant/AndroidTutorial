package com.example.stackoverflow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var stackoverflowAPI: StackOverflowAPI
    private var token : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        questions_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>){}

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val question = parent.adapter.getItem(position) as Question
                stackoverflowAPI.getAnswersForQuestion(question.questionId).enqueue(answersCallback)
            }
        }
        list.setHasFixedSize(true)
        list.layoutManager = LinearLayoutManager(this@MainActivity)
        createStackoverflowAPI()
        stackoverflowAPI.getQuestions().enqueue(questionsCallback)
    }

    override fun onResume() {
        super.onResume()
        if (token != null) {
            authenticate_button.isEnabled = false
        }
    }

    private fun createStackoverflowAPI() {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(StackOverflowAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        stackoverflowAPI = retrofit.create<StackOverflowAPI>(StackOverflowAPI::class.java)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.text1 -> if (token != null) {

            } else {
                Toast.makeText(this, "You need to authenticate first", Toast.LENGTH_LONG).show()
            }
            R.id.authenticate_button -> {
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1) {
            token = data?.getStringExtra("token")!!
        }
    }

    private var questionsCallback: Callback<ListWrapper<Question>> =
        object : Callback<ListWrapper<Question>>{
            override fun onResponse(
                call: Call<ListWrapper<Question>>,
                response: Response<ListWrapper<Question>>
            ) {
                if (response.isSuccessful) {
                    val questions: ListWrapper<Question> = response.body()
                    val arrayAdapter: ArrayAdapter<Question> = ArrayAdapter<Question>(
                        this@MainActivity,
                        R.layout.simple_spinner_dropdown_item,
                        questions.items
                    )
                    questions_spinner.adapter = arrayAdapter
                } else {
                    Log.d(
                        "QuestionsCallback",
                        "Code: ${response.code()} Message: ${response.message()}"
                    )
                }
            }

            override fun onFailure(call: Call<ListWrapper<Question>>, t: Throwable) {
                t.printStackTrace()
            }
        }
    var answersCallback: Callback<ListWrapper<Answer>> =
        object : Callback<ListWrapper<Answer>> {
            override fun onResponse(
                call: Call<ListWrapper<Answer>>,
                response: Response<ListWrapper<Answer>>
            ) {
                if (response.isSuccessful) {
                    val data: MutableList<Answer> = ArrayList()
                    data.addAll(response.body().items)
                    list.adapter = RecyclerViewAdapter(data)
                } else {
                    Log.d(
                        "QuestionsCallback",
                        "Code: ${response.code()} Message: ${response.message()}"
                    )
                }
            }

            override fun onFailure(call: Call<ListWrapper<Answer>>, t: Throwable) {
                t.printStackTrace()
            }
        }
    var upvoteCallback: Callback<ResponseBody> = object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody>) =
            if (response.isSuccessful) {
                Toast.makeText(this@MainActivity, "Upvote successful", Toast.LENGTH_LONG).show()
            } else {
                Log.d(
                    "QuestionsCallback",
                    "Code: ${response.code()} Message: ${response.message()}"
                )
                Toast.makeText(
                    this@MainActivity,
                    "You already upvoted this answer",
                    Toast.LENGTH_LONG
                ).show()
            }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
        }
    }
}