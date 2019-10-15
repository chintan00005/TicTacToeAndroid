package com.example.tictactoyapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.login_view.*

class LoginActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var database : FirebaseDatabase?=null;
    private var mRef:DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_view)
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance()
        mRef = database!!.reference
    }

    fun login(view: View) {

        if (!edEmail.text.toString().equals("") &&
                !edPassword.text.toString().equals("")) {
            loginToFirebase(edEmail.text.toString(),
                    edPassword.text.toString())
        } else {
            Toast.makeText(this, "Enter Email/Pwd", Toast.LENGTH_SHORT).show()
        }
    }

    fun loginToFirebase(email: String, password: String) {
        mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener {

            task ->

            if (task.isSuccessful) {
                Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show()
                goToGame(false)
            } else {

                mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show()
                        goToGame(true)
                    } else {
                        Toast.makeText(this, "" + task.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        goToGame(false)
    }

    fun goToGame(registered:Boolean) {
        val currentUser = mAuth!!.currentUser;
        if (currentUser != null) {
            if(registered)
            {
                mRef!!.child("Users").
                        child(splitNameFromEmail(currentUser.email)).child("Request").setValue(true)
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("id", currentUser.uid)
            intent.putExtra("email",splitNameFromEmail(currentUser.email))
            startActivity(intent)
        }
    }

    fun splitNameFromEmail(email:String?):String{
        return email!!.split("@")[0]
    }

}