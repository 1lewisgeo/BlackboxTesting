// Create map of test values

/*
Note:
x to y creates a tuple of (x, y)
x to y to z -> (x, y) [Pair<A, B>.]to z -> (x, y, z)
 */

// The second to now creates a triple instead of ((x, y), z) -> (x, y, z)
// If you want to know more look into Kotlin generics, infix functions, and extension functions
infix fun <A, B, C> Pair<A, B>.to(value: C): Triple<A, B, C> {
    return Triple(this.first, this.second, value)
}

val testValues = listOf(

        // Good input

        "Regular phone number" to "905-678-9473" to true,
        "Regular phone number" to "905 678 9473" to true,
        "Regular phone number" to "(905) 678-9473" to true,
        "Regular phone number" to "(905) 678 9473" to true,
        "Regular phone number" to "678-9473" to true,
        "Regular phone number" to "678 9473" to true,

        // Range testing

        "Area out of range" to "100-555-5555" to false,
        "Area out of range" to "000-666-4444" to false,
        "Area edge case" to "199-555-5555" to false,
        "Area edge case" to "199-888-8888" to false,
        "Area edge" to "200-555-5555" to true,
        "Area edge" to "200-667-3464" to true,

        "Prefix out of range" to "905-000-6666" to false,
        "Prefix edge case" to "905-200-7777" to false,
        "Prefix edge case" to "478-200-4563" to false,
        "Prefix edge" to "905-201-8888" to true,
        "Prefix edge" to "555-201-4523" to true,

        "Too few digits" to "90-55-7788" to false,
        "Too few digits" to "908-555-788" to false,
        "Too few digits" to "90-55-788" to false,
        "Too few digits" to "1-2-3" to false,
        "Too few digits" to "903-0-9" to false,

        "Too many digits" to "4444-4444-55555" to false,
        "Too many digits" to "2222-3333-44444" to false,

        "Phone number with ()" to "(678) 567-9873" to true,
        "Invalid phone number with ()" to "(905)-456-8734" to false,
        "Invalid phone number with ()" to "(846)-777-4444" to false,
        "Wrong type of parenthesis" to "[555] 555-5555" to false,
        "Wrong type of parenthesis" to "{678} 789 1123" to false,
        "Wrong type of parenthesis with hyphen" to "[678]-346-7777" to false,
        "Wrong type of parenthesis with hyphen" to "{456}-223-4444" to false,

        "Incorrect separator" to "555=666-8888" to false,
        "Incorrect separator" to "333-666=7777" to false,
        "Incorrect separator" to "555:773:6474" to false,

        "Unclosed bracket" to "(905 555 5555" to false,
        "Unopened bracket" to "905) 555 5555" to false,
        "Unclosed bracket with dash" to "(905-666-6666" to false,
        "Unopened bracket with dash" to "905)-555-5555" to false,

        // Bizarre input

        "Single number" to "6" to false,
        "Single number" to "2" to false,
        "Negative number" to "-1" to false,
        "Negative number" to "-7" to false,
        "Floating point" to "0.234" to false,
        "Floating point" to "2.68" to false,
        "Negative floating point" to "-6.47" to false,
        "Letters" to "abc-abc-abcd" to false,
        "Letters" to "1-800-pizza-nova" to false,
        "Special symbols" to "*&^%$#$%^&*(" to false,
        "Special symbols" to "****&*((*((*" to false,
        "Sentence" to "Hey Mr. Rivard how are you" to false

)

// Program entry point

fun main(args: Array<String>) {

    var pass: Int = 0

    // Destructuring loop and string interpolation

    for ((msg, value, res) in testValues) {

        if (isValidPhone(value) == res) {
            println("$msg: PASS")
            pass++
        } else {
            println("$msg: FAIL")
        }

    }

    // Ternary statement of sorts (inline if) and more string interpolation

    println(if (pass == testValues.size) "All $pass tests passed" else "$pass out of ${testValues.size} tests passed")

}