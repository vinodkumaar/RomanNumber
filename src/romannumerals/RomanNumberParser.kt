package romannumerals

class RomanNumberParser(inputString: String) {
    private var romanNumber: RomanNumber? = null

    init {
        buildRomanNumber(inputString)
    }

    private fun buildRomanNumber(inputString: String) {
        val listOfRomanNumerals = inputString.toCharArray().map {
            try {
                Numeral.valueOf(it.toString())
            } catch (e: Exception) {
                Numeral.NotANumeral
            }
        }
        listOfRomanNumerals.forEach {
            if (romanNumber != null) {
                romanNumber = romanNumber?.append(it)
            } else {
                romanNumber = RomanNumber(it)
            }
        }
    }

    fun toRomanNumber(): RomanNumber {
        return romanNumber!!
    }

}