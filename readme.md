# MAD - Exercise 01

## Tasks

* Watch the Kotlin Crashcourse Video in Moodle or complete the tutorials *
  *[Introduction to programming in Kotlin](https://developer.android.com/courses/pathways/android-basics-compose-unit-1-pathway-1)
  ** and *
  *[Kotlin fundamentals](https://developer.android.com/courses/pathways/android-basics-compose-unit-2-pathway-1
  )**.
* Answer the questions inside this Readme.md file and push it to your repository.
* Submit your coding solution of the Number Guessing Game inside the repository.
* Submit the link to your repository in Moodle.

## Questions

### Describe how Kotlin handles null safety. What are nullable types and non-null types in Kotlin? (0,5 points)

<span style="color:blue">Kotlin's approach to handling null safety is designed to minimize the risk
of null reference exceptions, a common issue in many programming languages, including Java. In Java,
accessing a member of a null reference results in a NullPointerException. Kotlin addresses this
problem by differentiating between types that can hold null values (nullable types) and those that
cannot (non-null types). Nullable types in Kotlin are declared by appending a question mark to the
type name. For instance, to declare a variable that can hold a null value, you would write:
</span>

```kotlin 
var b: String? = "abc" // can be set to null
b = null // This is allowed because b is a nullable type
print(b)
```

The variable 'b' is of type 'String?', which means it can hold either a string value or null. On the
other hand, the not-null assertion operator (!!) converts any value to a non-nullable type and
throws an exception if the value is null. If the value is null, using the !! operator will throw a
NullPointerException (NPE). For example:

```kotlin 
val l = b!!.length
```

In this case, b!! asserts that b is not null: if b is indeed not null, l will be assigned the length
of b. If b is null, however, a NullPointerException will be thrown. Thus, if you want an NPE, you
can have it, but Kotlin helps you avoid it occasionally.

### What are lambda expressions and higher order functions in Kotlin? Why would you store a function inside a variable? (0,5 points)

Lambdas Expressions are essentially anonymous functions that we can treat as values – we can, for
example, pass them as arguments to methods, return them, or do any other thing we could do with a
normal object. To define a lambda, we need to stick to the syntax, which involves enclosing the
lambda within curly braces, specifying its parameters (with optional type annotations) inside these
braces, followed by an arrow (->) and then the lambda's body. The return value of the lambda is the
last expression inside its body if it's not returning Unit. For example, a lambda expression to sum
two integers could be defined as:

```kotlin 
val sum = { x: Int, y: Int -> x + y }
```

In Kotlin, a function which can accept a function as parameter or can return a function is called
Higher-Order function. Instead of Integer, String or Array as a parameter to function, we will pass
anonymous function or lambdas. Frequently, lambdas are passed as parameter in Kotlin functions for
the convenience.

For instance, a higher-order function that accepts a lambda expression as a parameter and invokes it
could look like this:

```kotlin 
// lambda expression
var lambda = { println("Hello world!") }

// higher-order function
fun higherfunc(lmbd: () -> Unit) { // accepting lambda as parameter
    lmbd()                               //invokes lambda expression
}
fun main(args: Array<String>) {
//invoke higher-order function
    higherfunc(lambda)                 // passing lambda as parameter
}
```

Storing a function in a variable allows for flexible passing of behavior as data. This enables
defining functions that can be dynamically changed at runtime, passed as arguments to other
functions, or stored for later use, enabling highly dynamic and expressive programming patterns.
This approach is especially helpful in event-driven programming, callbacks, and when implementing
generic algorithms that operate on functions provided by the user.

### Provide a solution for the following number guessing game inside `App.kt`. (3 points)

## Number Guessing Game in Kotlin

The game is a simple number guessing game. The task is to generate a random, max 9-digit, number.
The number must **not contain repeating digits**. Valid digits are 1-9.
Ask the user to guess the max 9-digit number. The game is finished when the user guesses the correct
digits in the correct order.
In each round, the user gets feedback about the number of correct digits and the number of correct
digits in the correct position.
The output should be in the format "n:m", where "n" is the number of digits guessed correctly
regardless of their position,
and "m" is the number of digits guessed correctly at their correct position. Here are some examples:

This example shows the game flow with 4-digits to guess (the default argument)

Generated number: 8576

- User input: 1234, Output: 0:0
- User input: 5678, Output: 4:1
- User input: 5555, Output: 1:1
- User input: 3586, Output: 3:2
- User input: 8576, Output: 4:4 -> user wins

Take a look into the provided code structure in `src/main/kotlin/App.kt`. Implement the following
methods (lambda expressions):

- _playNumberGame(digitsToGuess: Int = 4)_ (1 point): The main game loop that handles user input and
  game state. Make use of _generateRandomNonRepeatingNumber_ and
  _checkUserInputAgainstGeneratedNumber_ here. This function also utilizes a default argument
- _generateRandomNonRepeatingNumber_ (1 point): A lambda expression that generates a random number
  with non-repeating digits of a specified length.
- _checkUserInputAgainstGeneratedNumber_ (1 point): A lambda expression that compares the user's
  input against the generated number and provides feedback.

``CompareResult.kt`` This class is a data structure which helps with bundling the result of the
comparison of the user input and the generated number. Look at the toSting() and use it in your
output.

Run the project with `./gradlew run` and test your implementation with the provided tests
in `src/test/kotlin/AppTest.kt` with `./gradlew test`.

# Project Structure

The project is structured into two main Kotlin files:

**App.kt**: Contains the main game logic and functions.

**AppTest.kt**: Contains unit tests for the various functions in App.kt.

