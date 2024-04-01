package com.example.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.airbnb.lottie.LottieAnimationView

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val view =LottieAnimationView(this)
        view.setAnimation(R.raw.main)
        view.playAnimation()
        view.loop(true)


        Handler().postDelayed( {


            var intent=Intent(this,Home::class.java)
            startActivity(intent)

        },1000L)

        }
}
