package com.example.tictactoyapp

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

val allAvailableIds = LinkedList<String>();
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        allAvailableIds.add(btn1.id.toString())
        allAvailableIds.add(btn2.id.toString())
        allAvailableIds.add(btn3.id.toString())
        allAvailableIds.add(btn4.id.toString())
        allAvailableIds.add(btn5.id.toString())
        allAvailableIds.add(btn6.id.toString())
        allAvailableIds.add(btn7.id.toString())
        allAvailableIds.add(btn8.id.toString())
    }

    fun onClick(view: View) {

//        when (view.id) {
//            R.id.btn1 -> {
//                Toast.makeText(this, "Yeah Science 1", Toast.LENGTH_SHORT).show()
//            }
//            R.id.btn2 -> {
//                Toast.makeText(this, "Yeah Science 2", Toast.LENGTH_SHORT).show()
//            }
//
//        }
        selectBox(view as Button)
    }

    fun onReset(view:View)
    {
        toggleGame(true)


    }

    var activePlayer = "1"
    val playerOneSelection = LinkedList<String>();
    val playerSecondSelection = LinkedList<String>();



    fun selectBox(button: Button) {

        if (activePlayer == "1") {
            button.text = "O"
            button.setBackgroundResource(R.color.blue_bg)
            playerOneSelection.add(button.id.toString())
            activePlayer = "2"
        } else {
            button.text = "X"
            button.setBackgroundResource(R.color.green_bg)
            playerSecondSelection.add(button.id.toString())
            activePlayer = "1"
        }

        button.isEnabled = false;
        selectWinner();
    }

    fun selectWinner() {
        var winner = -1;

        if ((playerOneSelection.contains(btn1.id.toString()) && playerOneSelection.contains(btn2.id.toString())
                        && playerOneSelection.contains(btn3.id.toString())) ||
                (playerOneSelection.contains(btn4.id.toString()) && playerOneSelection.contains(btn5.id.toString())
                        && playerOneSelection.contains(btn6.id.toString())) ||
                (playerOneSelection.contains(btn7.id.toString()) && playerOneSelection.contains(btn8.id.toString())
                        && playerOneSelection.contains(btn9.id.toString())) ||
                (playerOneSelection.contains(btn1.id.toString()) && playerOneSelection.contains(btn5.id.toString())
                        && playerOneSelection.contains(btn9.id.toString())) ||
                (playerOneSelection.contains(btn3.id.toString()) && playerOneSelection.contains(btn5.id.toString())
                        && playerOneSelection.contains(btn7.id.toString())) ||
                (playerOneSelection.contains(btn1.id.toString()) && playerOneSelection.contains(btn4.id.toString())
                        && playerOneSelection.contains(btn7.id.toString())) ||
                (playerOneSelection.contains(btn2.id.toString()) && playerOneSelection.contains(btn5.id.toString())
                        && playerOneSelection.contains(btn8.id.toString())) ||
                (playerOneSelection.contains(btn3.id.toString()) && playerOneSelection.contains(btn6.id.toString())
                        && playerOneSelection.contains(btn9.id.toString())))
        {
            winner = 1;
        }
        else         if ((playerSecondSelection.contains(btn1.id.toString()) && playerSecondSelection.contains(btn2.id.toString())
                        && playerSecondSelection.contains(btn3.id.toString())) ||
                (playerSecondSelection.contains(btn4.id.toString()) && playerSecondSelection.contains(btn5.id.toString())
                        && playerSecondSelection.contains(btn6.id.toString())) ||
                (playerSecondSelection.contains(btn7.id.toString()) && playerSecondSelection.contains(btn8.id.toString())
                        && playerSecondSelection.contains(btn9.id.toString())) ||
                (playerSecondSelection.contains(btn1.id.toString()) && playerSecondSelection.contains(btn5.id.toString())
                        && playerSecondSelection.contains(btn9.id.toString())) ||
                (playerSecondSelection.contains(btn3.id.toString()) && playerSecondSelection.contains(btn5.id.toString())
                        && playerSecondSelection.contains(btn7.id.toString())) ||
                (playerSecondSelection.contains(btn1.id.toString()) && playerSecondSelection.contains(btn4.id.toString())
                        && playerSecondSelection.contains(btn7.id.toString())) ||
                (playerSecondSelection.contains(btn2.id.toString()) && playerSecondSelection.contains(btn5.id.toString())
                        && playerSecondSelection.contains(btn8.id.toString())) ||
                (playerSecondSelection.contains(btn3.id.toString()) && playerSecondSelection.contains(btn6.id.toString())
                        && playerSecondSelection.contains(btn9.id.toString())))
        {
            winner = 2;
        }

        if(winner != -1)
        {
            Handler().postDelayed({   toggleGame(true);},1000)
            Toast.makeText(this, "Winner is Player $winner",Toast.LENGTH_SHORT).show()

        }
        else
        {
            if(activePlayer=="2")
            {
                Handler().postDelayed({
                     autoPlay()
                }, 200)

            }

        }
    }


    fun toggleGame(isEnable : Boolean){
        activePlayer="1";
        playerOneSelection.clear()
        playerSecondSelection.clear()

        btn1.isEnabled = isEnable
        btn2.isEnabled = isEnable
        btn3.isEnabled = isEnable
        btn4.isEnabled = isEnable
        btn5.isEnabled = isEnable
        btn6.isEnabled = isEnable
        btn7.isEnabled = isEnable
        btn8.isEnabled = isEnable
        btn9.isEnabled = isEnable

        btn1.setBackgroundResource(R.color.white_bg)
        btn2.setBackgroundResource(R.color.white_bg)
        btn3.setBackgroundResource(R.color.white_bg)
        btn4.setBackgroundResource(R.color.white_bg)
        btn5.setBackgroundResource(R.color.white_bg)
        btn6.setBackgroundResource(R.color.white_bg)
        btn7.setBackgroundResource(R.color.white_bg)
        btn8.setBackgroundResource(R.color.white_bg)
        btn9.setBackgroundResource(R.color.white_bg)

        btn1.text = ""
        btn2.text = ""
        btn3.text = ""
        btn4.text = ""
        btn5.text = ""
        btn6.text = ""
        btn7.text = ""
        btn8.text = ""
        btn9.text = ""

    }

    fun autoPlay(){

        val emptyList = LinkedList<String>();


        for(i in allAvailableIds)
        {

            if(!playerOneSelection.contains(i) &&
                    !playerSecondSelection.contains(i))
            {

                emptyList.add(i)
            }
        }

        val maxRange = emptyList.size -1;
        val minRange = 0;

        val btnIdRandomIndex: Int = ((Math.random() * (maxRange-minRange+1)) + minRange).toInt()
        if(emptyList.size>0)
        {
            val btnIdRandom = emptyList.get(btnIdRandomIndex)

            when(btnIdRandom)
            {
                btn1.id.toString()->{selectBox(btn1)}
                btn2.id.toString()->{selectBox(btn2)}
                btn3.id.toString()->{selectBox(btn3)}
                btn4.id.toString()->{selectBox(btn4)}
                btn5.id.toString()->{selectBox(btn5)}
                btn6.id.toString()->{selectBox(btn6)}
                btn7.id.toString()->{selectBox(btn7)}
                btn8.id.toString()->{selectBox(btn8)}
            }

        }



    }
}