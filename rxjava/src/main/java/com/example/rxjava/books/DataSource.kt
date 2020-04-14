package com.example.rxjava.books

import android.os.SystemClock


class DataSource {

    fun getFavoriteBooks() : List<String>{
            SystemClock.sleep(8000)
            return createBooks()
    }

    fun getFavoriteBooksWithException() : List<String>{
            SystemClock.sleep(8000)
            throw RuntimeException("Failed to load")
    }

    private fun createBooks(): List<String> {
        val books: MutableList<String> = ArrayList()
        with(books) {
            add("Lord of the Rings")
            add("The dark elf")
            add("Eclipse Introduction")
            add("History book")
            add("Der kleine Prinz")
            add("7 habits of highly effective people")
            add("Other book 1")
            add("Other book 2")
            add("Other book 3")
            add("Other book 4")
            add("Other book 5")
            add("Other book 6")
        }
        return books
    }

}