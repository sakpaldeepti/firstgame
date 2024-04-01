package com.example.game

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Suppress("NAME_SHADOWING")
class Robot : AppCompatActivity() {
    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_robot)
    }
    //1=robot=x
    //0=player=o

    private var gameactive = true
    private var activeplayer = 0
    private var count = 0
    private var rcount = 0
    private var pcount = 0


    private var gameposition = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    private var winpositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )


     @SuppressLint("SetTextI18n")
     fun tap(view: View) {
        val status: TextView = findViewById(R.id.statusBarBackground)
        val img = view as ImageView
        val tapimage = img.tag.toString().toInt()
        @SuppressLint("SetTextI18n")
        fun checkwinner(){

            for (winpositions in winpositions) {
                val status: TextView = findViewById(R.id.statusBarBackground)
                if (gameposition[winpositions[0]] == gameposition[winpositions[1]] &&
                    gameposition[winpositions[1]] == gameposition[winpositions[2]] &&
                    gameposition[winpositions[0]] != 2
                ) {
                    gameactive = false

                    if (gameposition[winpositions[0]] == 0) {
                        status.text = "Player has won"
                        pcount++
                        val pscore: TextView = findViewById(R.id.Xwin)
                        pscore.text = (pcount.toString())
                    } else {
                        status.text = "Robot has won"
                        rcount++
                        val rscore: TextView = findViewById(R.id.Owin)
                        rscore.text = (rcount.toString())
                    }
                    playagain()
                }
                if (gameactive && (count == 9)) {
                    playagain()
                    status.text = "DRAW"
                }
            }

        }

        if (gameposition[tapimage] == 2) {
            gameposition[tapimage] = activeplayer

            if (activeplayer == 1) {
                img.setImageResource(R.drawable.x)
                activeplayer = 0
                count++
                status.text = "Player's Turn - Tap to play"

                checkwinner()
                } else {
                    img.setImageResource(R.drawable.o)
                    activeplayer = 1
                    count++
                    status.text = "Computer's Turn - Tap to play"
                    checkwinner()
                }
            if (activeplayer == 1) {
                robot()

            }


        }
    }


    private fun playagain() {
        gameactive = true
        activeplayer = 0
        count = 0
        for (i in gameposition.indices) {
            gameposition[i] = 2
        }
        (findViewById<View>(R.id.imageView0) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView1) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView2) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView3) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView4) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView5) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView6) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView7) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView8) as ImageView).setImageResource(0)

    }

    fun gameReset(view: View) {

        playagain()
        pcount = 0
        rcount = 0

        val pscore: TextView = findViewById(R.id.Xwin)
        pscore.text = (pcount.toString())

        val rscore: TextView = findViewById(R.id.Owin)
        rscore.text = (rcount.toString())
    }

    private  fun robot() {
        // Call the ViewModel function to perform the robot's move using Kotlin coroutines
        CoroutineScope(Dispatchers.Main).launch {
            // Call the ViewModel function to perform the robot's move
            val robotMove = gameViewModel.performRobotMove(gameposition)

            // Update the UI on the main thread with the robot's move.
            if (robotMove != -1 && gameposition[robotMove] == 2) {
                val tapimage = when (robotMove) {
                        0 -> findViewById<ImageView>(R.id.imageView0)
                        1 -> findViewById(R.id.imageView1)
                        2 -> findViewById<ImageView>(R.id.imageView2)
                        3 -> findViewById(R.id.imageView3)
                        4 -> findViewById(R.id.imageView4)
                        5 -> findViewById(R.id.imageView5)
                        6 -> findViewById<ImageView>(R.id.imageView6)
                        7 -> findViewById<ImageView>(R.id.imageView7)
                        8 -> findViewById<ImageView>(R.id.imageView8)
                        else -> null
                    }
                    if (tapimage != null) {
                        tap(tapimage) // Call tap function with the appropriate ImageView
                    }
                }
            }
        }
    }

















