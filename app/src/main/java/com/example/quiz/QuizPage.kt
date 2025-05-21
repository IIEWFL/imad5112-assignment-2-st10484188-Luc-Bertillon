package com.example.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizPage : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button
    private lateinit var feedbackTextView: TextView

    private var scoreCounter = 0
    private var trueCounter = 0
    private lateinit var scorePage: Intent

    companion object {
        val questions: Array<String> = arrayOf(
            "Motorcycles run on air",
            "Motorcycles are lighter than cars",
            "Motorcycles have 2 wheels",
            "Motorcycles protect you from rain",
            "You can only carry 2 people on a bike at once"
        )

        val answers: BooleanArray = booleanArrayOf(false, true, true, false, true)
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        questionTextView = findViewById(R.id.QuestionText)
        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        btnNext = findViewById(R.id.btnNext)
        feedbackTextView = findViewById(R.id.txtfeedback)




        // Initialize intent properly inside onCreate
        scorePage = Intent(this, Score::class.java)

        // Display the first question
        questionTextView.text = questions[scoreCounter]

        // Set up listeners
        btnTrue.setOnClickListener { checkAnswer(true) }
        btnFalse.setOnClickListener { checkAnswer(false) }

        btnNext.setOnClickListener {
            scoreCounter++
            if (scoreCounter < questions.size) {
                questionTextView.text = questions[scoreCounter]
                feedbackTextView.text = ""
                btnTrue.isEnabled = true
                btnFalse.isEnabled = true
                btnNext.isEnabled = false
            } else {
                scorePage.putExtra("score", scoreCounter)
                scorePage.putExtra("questions", questions)
                scorePage.putExtra("answers", answers)
                scorePage.putExtra("score", trueCounter)
                startActivity(scorePage)
                finish()
            }
        }

        btnNext.isEnabled = false // Disable next button until an answer is selected
    }

    @SuppressLint("SetTextI18n")
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = answers[scoreCounter]

        if (userAnswer == correctAnswer) {
            feedbackTextView.text = "Correct!"
            feedbackTextView.setTextColor(Color.GREEN)
            trueCounter++
        } else {
            feedbackTextView.text = "Incorrect :("
            feedbackTextView.setTextColor(Color.RED)
        }

        // Disable answer buttons and enable Next
        btnTrue.isEnabled = false
        btnFalse.isEnabled = false
        btnNext.isEnabled = true
    }


}