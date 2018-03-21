// Create map of test values

/*
Note:
x to y creates a tuple of (x, y)
x to y to z -> (x, y) to z -> ((x, y), z)
which in this case is: ((message, testvalue), expected value)
 */

val testValues = mapOf(
        "Regular phone number" to "905-678-9473" to true,
        "Single number" to "6" to false,
        "Negative number" to "-1" to false,
        "Regular phone number" to "289-400-8354" to true,
        "Phone number with ()" to "(678) 567-9873" to true,
        "Invalid phone number with ()" to "(905)-456-8734" to false,
        "Floating point" to "0.234" to false,
        "Area code out of range" to "(100) 400-7890" to false,
        "Section A out of range" to "289-100-2000" to false,
        "Edge case test" to "200-201-0000" to true,
        "Letters" to "abc-abc-abcd" to false
)

// Program entry point

fun main(args: Array<String>) {

    var pass: Int = 0

    // Destructuring loop and string interpolation

    for ((k, v) in testValues) {

        if (isValidPhone(k.second) == v) {
            println("${k.first}: PASS")
            pass++
        } else {
            println("${k.first}: FAIL")
        }

    }

    // Ternary statement of sorts (inline if) and more string interpolation

    println(if (pass == testValues.size) "All $pass tests passed" else "$pass out of ${testValues.keys.size} tests passed")

}