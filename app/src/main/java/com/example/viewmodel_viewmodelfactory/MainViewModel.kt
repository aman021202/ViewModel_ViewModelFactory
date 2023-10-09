package com.example.viewmodel_viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.io.InputStream

class MainViewModel(val context: Context): ViewModel() {
// WAY TO MANAGE THE QOUTE LIST
    //DEFINES THE EMPTY ARRAY FOR THE QUOTE LIST
    private var quoteList : Array<Quote> = emptyArray()
    private var index = 0

    init {
        //TO DEFINE QUOTE LIST WE NEED TO KNOW ALL ABOUT OUR JSON FILE WHICH IS DEFINED IN THE ASSETS FOLDER & TO READ WE NEED TO PASS THE CONTEXT
        // FROM MAIN ACTIVITY
        quoteList = loadQuoteFromAssets()
    }
//SIMPLE FUNCTION WHICH RETURNS THE ARRAY OF QUOTES a
    private fun loadQuoteFromAssets(): Array<Quote> {

        val inputStream = context.assets.open("quotes.json")
    val size : Int = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    //ALL THE JSON THE WE RECEIVED IS IN THE UTF 8 FORMAT ONLY
    val json = String(buffer, Charsets.UTF_8)
    val gson = Gson()
    return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote() = quoteList[++index]

    fun previousQuote() = quoteList[--index]
}