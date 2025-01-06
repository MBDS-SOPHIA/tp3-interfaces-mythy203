package com.example.appcasino

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows the user to roll two dice and view the results.
 */
class MainActivity : AppCompatActivity() {

    /**
     * This method is called when the Activity is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the Button in the layout
        val rollButton: Button = findViewById(R.id.button)

        // Set a click listener on the button to roll the dice when the user taps the button
        rollButton.setOnClickListener { rollDice() }
    }

    /**
     * Rolls two dice and updates the screen with the results.
     */
    private fun rollDice() {
        // Create two dice with 6 sides
        val dice1 = Dice(6)
        val dice2 = Dice(6)

        val dice1Roll = dice1.roll()
        val dice2Roll = dice2.roll()

        // Update the screen with the dice rolls
        val dice1TextView: TextView = findViewById(R.id.dice1TextView)
        val dice2TextView: TextView = findViewById(R.id.dice2TextView)
        val winMessageTextView: TextView = findViewById(R.id.winMessageTextView)

        dice1TextView.text = dice1Roll.toString()
        dice2TextView.text = dice2Roll.toString()

        // Check if both dice have the same number (player wins)
        if (dice1Roll == dice2Roll) {
            winMessageTextView.text = "🎉 You Win! 🎉"
            winMessageTextView.visibility = TextView.VISIBLE
        } else {
            winMessageTextView.visibility = TextView.GONE
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
