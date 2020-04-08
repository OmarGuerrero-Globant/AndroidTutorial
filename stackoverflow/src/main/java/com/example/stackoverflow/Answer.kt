package com.example.stackoverflow

import com.google.gson.annotations.SerializedName


data class Answer( @SerializedName("answer_id") var answerId: Int = 0,
                   @SerializedName("is_accepted") var accepted : Boolean = false,
                   var score : Int = 0
                   ){
    override fun toString(): String {
        return "$answerId - Score: $score - Accepted: ${if (accepted) "Yes" else "No"}"
    }
}