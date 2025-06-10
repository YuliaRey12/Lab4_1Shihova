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

    // Правильные ответы
    private val answers = listOf(true, true, true, true, false)

    // Текущий индекс вопроса
    private var currentIndex = 0

    // Количество правильных ответов
    private var correctAnswers = 0

    // UI элементы
    private lateinit var questionTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация View
        questionTextView = findViewById(R.id.questionTextView)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)

        // По умолчанию кнопка Next выключена
        nextButton.isEnabled = false

        // Установка обработчиков событий
        trueButton.setOnClickListener { checkAnswer(true) }
        falseButton.setOnClickListener { checkAnswer(false) }
        nextButton.setOnClickListener { showNextQuestion() }

        // Восстановление состояния
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("current_index", 0)
            correctAnswers = savedInstanceState.getInt("correct_answers", 0)
        }

        // Обновление текста вопроса
        updateQuestion()
    }

    // Сохранение состояния активности
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("current_index", currentIndex)
        outState.putInt("correct_answers", correctAnswers)
    }

    // Обновление текста вопроса
    private fun updateQuestion() {
        questionTextView.text = questions[currentIndex]
    }

    // Проверка ответа пользователя
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = answers[currentIndex]

        val message = if (userAnswer == correctAnswer) {
            correctAnswers++
            "Correct!"
        } else {
            "Incorrect!"
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        // Блокировка кнопок True/False
        trueButton.isEnabled = false
        falseButton.isEnabled = false
        nextButton.isEnabled = true

        // Если это последний вопрос — показываем результат
        if (currentIndex == questions.size - 1) {
            nextButton.isEnabled = false
            nextButton.visibility = View.GONE

            Toast.makeText(
                this,
                "You scored $correctAnswers out of ${questions.size}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    // Переход к следующему вопросу
    private fun showNextQuestion() {
        currentIndex = (currentIndex + 1) % questions.size
        updateQuestion()

        // Восстанавливаем возможность выбора
        trueButton.isEnabled = true
        falseButton.isEnabled = true
        nextButton.isEnabled = false

        // При повторном прохождении — обнуляем счётчик
        if (currentIndex == 0) {
            correctAnswers = 0
            nextButton.visibility = View.VISIBLE
        }
    }
}