package com.example.stackoverflow

class FakeDataProvider {
    fun getQuestions(): List<Question>? {
        val questions: MutableList<Question> = ArrayList()
        (0..9).forEach { i ->
            val question = Question().apply {
                questionId = "$i"
                title = "$i"
                body = "${i}Body"
                questions.add(this)
            }
        }
        return questions
    }

    fun getAnswers(): List<Answer>? {
        val answers: MutableList<Answer> = ArrayList()
        (0..9).forEach { i ->
            val answer = Answer().apply {
                answerId = i
                accepted = false
                score = i
                answers.add(this)
            }
        }
        return answers
    }
}