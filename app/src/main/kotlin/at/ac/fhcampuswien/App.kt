/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package at.ac.fhcampuswien

import java.util.Scanner

class App {
    // Game logic for a number guessing game
    fun playNumberGame(digitsToGuess: Int = 4) {
        val generatedNumber = generateRandomNonRepeatingNumber(digitsToGuess)

        val scanner = Scanner(System.`in`)

        println("Welcome to the Number Guessing Game in Kotlin! The number has been generated. Try to guess it.")
        println("Enter your guess with $digitsToGuess unique digits, or type 'exit' to quit the game.")

        while (true) {
            print("Your guess: ")
            val input = scanner.nextLine()

            if (input.equals("exit", ignoreCase = true)) {
                println("The number was $generatedNumber. Bye!")
                break
            }

            try {
                val guess = input.toInt()

                if (input.length != digitsToGuess || input.toSet().size != digitsToGuess) {
                    println("Invalid input. Make sure you enter $digitsToGuess unique digits.")
                    continue
                }

                val result = checkUserInputAgainstGeneratedNumber(guess, generatedNumber)
                println(result)

                if (result.m == digitsToGuess) {
                    println("Congratulations! You've guessed the number correctly!")
                    break
                } else {
                    println("You have ${result.n} correct digit(s), with ${result.m} in the correct position. Try again!")
                }
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    /**
     * Generates a non-repeating number of a specified length between 1-9.
     *
     * Note: The function is designed to generate a number where each digit is unique and does not repeat.
     * It is important to ensure that the length parameter does not exceed the maximum possible length
     * for non-repeating digits (which is 9 excluding 0 for base-10 numbers).
     *
     * @param length The length of the non-repeating number to be generated.
     *               This dictates how many digits the generated number will have.
     * @return An integer of generated non-repeating number.
     *         The generated number will have a number of digits equal to the specified length and will
     *         contain unique, non-repeating digits.
     * @throws IllegalArgumentException if the length is more than 9 or less than 1.
     */
    val generateRandomNonRepeatingNumber: (Int) -> Int = { length ->
        if (length < 1 || length > 9) {
            throw IllegalArgumentException("Length must be between 1 and 9.")
        }

        val pool = ('1'..'9').toList()
        val remainingPool = ('0'..'9').toList()
        val digits = mutableListOf<Char>()

        //first digit
        digits.add(pool.random())

        //remaining digits
        (2..length).forEach { _ ->
            digits.add(remainingPool.filterNot { it in digits }.random())
        }

        val result = digits.joinToString("").toInt()
        result
    }

    /**
     * Compares the user's input integer against a generated number for a guessing game.
     * This function evaluates how many digits the user guessed correctly and how many of those
     * are in the correct position. The game generates number with non-repeating digits.
     *
     * Note: The input and the generated number must both be numbers.
     * If the inputs do not meet these criteria, an IllegalArgumentException is thrown.
     *
     * @param input The user's input integer. It should be a number with non-repeating digits.
     * @param generatedNumber The generated number with non-repeating digits to compare against.
     * @return [CompareResult] with two properties:
     *         1. `n`: The number of digits guessed correctly (regardless of their position).
     *         2. `m`: The number of digits guessed correctly and in the correct position.
     *         The result is formatted as "Output: m:n", where "m" and "n" represent the above values, respectively.
     * @throws IllegalArgumentException if the inputs do not have the same number of digits.
     */
    val checkUserInputAgainstGeneratedNumber: (Int, Int) -> CompareResult = { input, generatedNumber ->
        val inputString = input.toString()
        val generatedString = generatedNumber.toString()

        if (inputString.length != generatedString.length) {
            throw IllegalArgumentException("The inputs do not have the same number of digits.")
        }

        // eliminating duplicates
        val uniqueDigitsFromInput = inputString.toSet()

        // Count how many unique digits from the input are present in the generated number
        val m = uniqueDigitsFromInput.count { it in generatedString }
        // Count how many digits in the input string match exactly with the generated string
        val n = inputString.indices.count { inputString[it] == generatedString[it] }

        CompareResult(m, n)
    }
}

fun main() {
    val app = App()
    app.playNumberGame()
    app.playNumberGame(3)
}
