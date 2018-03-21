fun isValidPhone(str: String): Boolean {

    // Destructure string into 3 parts of the phone number and check that all parts are present

    val (area, a, b) = str.split("-", " ").apply { if (this.size < 3) return@isValidPhone false }

    // Check if the area code has () and if it does that the character following is a space not a hyphen

    // I use an inefficient regex here but that doesn't really matter in this case, I could precompile it too but it isn't worth the effort
    if (area.contains(Regex("[()]")) && str[5] != ' ') {
        return false
    }

    // Check lengths

    if (area.length < 3 || a.length < 3 || b.length < 4) {
        return false
    }

    // replacing '(' and ')' just to be safe
    // using the negation of the in operator with ranges to test values

    try {
        if (area.replace("(", "").replace(")", "").toInt() !in 200..999 ||
                a.toInt() !in 201..999) {
            return false
        }
    } catch (e: NumberFormatException) {
        return false
    }

    // All tests passed

    return true

}