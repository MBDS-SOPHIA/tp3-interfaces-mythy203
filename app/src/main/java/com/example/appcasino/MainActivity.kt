package com.example.appcasino

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider

/**
 * MainActivity - Handles dice rolling and game logic.
 */
class MainActivity : AppCompatActivity() {

    private var targetNumber: Int = 2  // Default target number

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find UI elements
        val rollButton: Button = findViewById(R.id.button)
        val targetNumberSlider: Slider = findViewById(R.id.targetNumberSlider)
        val targetNumberTextView: TextView = findViewById(R.id.targetNumberTextView)

        // Disable the roll button initially
        rollButton.isEnabled = false

        // Listen for changes on the slider
        targetNumberSlider.addOnChangeListener { _, value, _ ->
            targetNumber = value.toInt()
            targetNumberTextView.text = "Target number: $targetNumber"
            rollButton.isEnabled = true // Enable the button once a number is selected
        }

        // Set click listener for the roll button
        rollButton.setOnClickListener { rollDice() }
    }

    /**
     * Rolls two dice and checks if the sum matches the target number.
     */
    private fun rollDice() {
        val dice1 = Dice(6)
        val dice2 = Dice(6)

        val dice1Roll = dice1.roll()
        val dice2Roll = dice2.roll()
        val total = dice1Roll + dice2Roll

        // Update UI with dice roll results
        val dice1TextView: TextView = findViewById(R.id.dice1TextView)
        val dice2TextView: TextView = findViewById(R.id.dice2TextView)
        val winMessageTextView: TextView = findViewById(R.id.winMessageTextView)

        dice1TextView.text = dice1Roll.toString()
        dice2TextView.text = dice2Roll.toString()

        // Check if the user wins
        if (total == targetNumber) {
            winMessageTextView.text = "🎉 You Win! 🎉"
            winMessageTextView.visibility = TextView.VISIBLE
        } else {
            winMessageTextView.text = "❌ You Lose! Try Again."
            winMessageTextView.visibility = TextView.VISIBLE
        }
    }
}

/**
 * Dice class representing a die with a fixed number of sides.
 */
class Dice(private val numSides: Int) {
    /**
     * Rolls the die and returns a random number between 1 and numSides.
     */
    fun roll(): Int {
        return (1..numSides).random()
    }
}
