package com.example.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnExit = findViewById<Button>(R.id.btnExit)
        val btnImg = findViewById<ImageButton>(R.id.btnImg)
        var counter = 0

        // Intent to QuizPage
        val intent = Intent(this, QuizPage::class.java)

        btnStart?.setOnClickListener {
            startActivity(intent)
        }

        btnExit.setOnClickListener {
            val toast = Toast.makeText(this, "Click again to exit", Toast.LENGTH_SHORT)
            toast.show()
            counter += 1

            if (counter == 2) {
                finishAffinity()
                exitProcess(0)
            }
        }

        btnImg.setOnClickListener {
            Toast.makeText(this, "Answer questions by selecting True or False", Toast.LENGTH_SHORT)
                .show()
        }

    }
}