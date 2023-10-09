package com.example.viewmodel_viewmodelfactory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    private val quoteText:TextView
        get() = findViewById(R.id.quoteText)

    private val quoteAuthor: TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //ACTIVITIES CAN BE DESTROYED BY USING SCREEN ROTATION OR BY CHANGING ANY OTHER CONFIGRATION SO ACTIVITY IS NOT BEING PASSED IN MainViewModelFactory()
        //i.e. WE ARE PASSING THE APPLICATION CONTEXT & IT IS USE TO CREATE THE OBJECT FOR MAIN VIEW MODEL
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
       //THIS IS BEING USED TO CALLTHE FUN SET QUOTE FROM VIEW MODEL & IT WILL RETURN THE QUOTE WHICH IS PASSED DOWNWARDS i.e. AFTER THE CURLY BRACES
        setQuote(mainViewModel.getQuote())
    }

    fun setQuote(quote: Quote){
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.previousQuote())
    }
    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote())
    }
    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(intent)
    }



}