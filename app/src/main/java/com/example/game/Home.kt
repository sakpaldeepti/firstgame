package com.example.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class Home : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    }

    fun play(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        val text = "Welcome"

        intent.putExtra("title", text)
        startActivity(intent)
    }
    fun robot(view: View) {
        val intent = Intent(this, Robot::class.java)
        startActivity(intent)
    }


}
