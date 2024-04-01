package com.example.game

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val status: TextView = findViewById(R.id.textView)
        status.text =intent.getStringExtra("title")

    }


    //1=o
    //0=x

    private var gameactive = true
    private var activeplayer = 0
    private var count=0
    var xcount = 0;
    var ocount = 0;


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

    fun tap(view: View)
    {
        val status: TextView = findViewById(R.id.statusBarBackground)
        val img = view as ImageView
        val tapimage = img.tag.toString().toInt()
        if (gameposition[tapimage] == 2)
        {
            gameposition[tapimage] = activeplayer


             if (activeplayer == 1) {
                img.setImageResource(R.drawable.o)
                 activeplayer = 0
                count++
                status.text = "X's Turn - Tap to play"

            } else {
                img.setImageResource(R.drawable.x)
                activeplayer = 1
                count++
                status.text = "O's Turn - Tap to play"

            }

        }

        for (winpositions in winpositions)
        {
            val status: TextView = findViewById(R.id.statusBarBackground)


            if (gameposition[winpositions[0]] == gameposition[winpositions[1]] &&
                gameposition[winpositions[1]] == gameposition[winpositions[2]] &&
                gameposition[winpositions[0]] != 2)
            {
                    gameactive = false

               if(gameposition[winpositions[0]] == 0)
                {status.text= "X has won"
                    xcount++
                    val xscore: TextView = findViewById(R.id.Xwin)
                    xscore.text=(xcount.toString());
                }
                else{status.text= "O has won"
                    ocount++
                   val oscore: TextView = findViewById(R.id.Owin)
                   oscore.text =(ocount.toString());
                }
                playagain()


            }

            if(gameactive && (count == 9)) {
                gameReset(view)
                status.text = "DRAW"
            }

        }
    }
    fun playagain() {
        gameactive = true
        activeplayer = 0
        count=0
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
        xcount = 0
        ocount = 0

        val xscore: TextView = findViewById(R.id.Xwin)
        xscore.text = (xcount.toString())

        val oscore: TextView = findViewById(R.id.Owin)
        oscore.text = (ocount.toString())
    }




}




