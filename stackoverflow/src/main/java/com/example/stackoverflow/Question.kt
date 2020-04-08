package com.example.stackoverflow

import com.google.gson.annotations.SerializedName


data class Question(var title: String = "",
                    var body : String = "",
                    @SerializedName("question_id")  var questionId: String = "")