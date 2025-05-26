package com.example.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Review : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //assigns values
        val btnRestart = findViewById<Button>(R.id.btnRestart)
        val btnExit = findViewById<Button>(R.id.btnExit)
        val txtQ1 = findViewById<TextView>(R.id.txtQ1)

        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")

        //takes the copied answers from the the score page and displays them
        val txtReviewAnswer = StringBuilder()
        if (questions != null && answers != null && questions.size == answers.size) {
            for (i in questions.indices) {
                txtReviewAnswer.append("${i + 1}. ${questions[i]}\n")
                txtReviewAnswer.append("Answer: ${if (answers[i]) "True" else "False"}\n\n")
            }
            txtQ1.text = txtReviewAnswer.toString()
        } else {
            txtQ1.text = "Error loading review data."
        }



        btnRestart.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnExit.setOnClickListener{
            finishAffinity()
        }
    }
}