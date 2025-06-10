package com.example.lab4_1shihova

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Список вопросов
    private val questions = listOf(
        "Canberra is the capital of Australia.",
        "The current president of the United States is Joe Biden.",
        "Mars is a planet.",
        "The Earth revolves around the Sun.",
        "The capital of France is Berlin."
    )


    private val answers = listOf(true, true, true, true, false)


    private var currentIndex = 0


    private var correctAnswers = 0

    private lateinit var questionTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}