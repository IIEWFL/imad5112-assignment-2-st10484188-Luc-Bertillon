package com.example.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class Score : AppCompatActivity() {

    private var counter = 0

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnexit = findViewById<Button>(R.id.btnExit)
        val txtscore = findViewById<TextView>(R.id.txtTitle)
        val txtmessage = findViewById<TextView>(R.id.txtMessage)
        val btnReview = findViewById<Button>(R.id.btnReview)

        val score = intent.getIntExtra("score", 0)
        txtscore.text = "Final Score:  $score/5"

        txtmessage.text = if (score >= 3) {
            "Great job!"
        } else {
            "Keep practising!"
        }

        btnexit.setOnClickListener {
            val toast = Toast.makeText(this, "Click again to exit", Toast.LENGTH_SHORT)
            toast.show()
            counter += 1

            if (counter == 2) {
                finishAffinity()
            }
        }
    }
}