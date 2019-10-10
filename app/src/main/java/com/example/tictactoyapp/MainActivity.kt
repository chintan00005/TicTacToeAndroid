package com.example.tictactoyapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view :View){

        when(view.id)
        {
            R.id.btn1->{
                Toast.makeText(this,"Yeah Science 1", Toast.LENGTH_SHORT).show()
            }
            R.id.btn2->{
                Toast.makeText(this,"Yeah Science 2", Toast.LENGTH_SHORT).show()
            }

        }
    }
}