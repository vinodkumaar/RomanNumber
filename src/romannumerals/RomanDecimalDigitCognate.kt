package romannumerals

class RomanDecimalDigitCognate(numerals: List<Numeral>) {
    private var numerals: List<Numeral>

    init {
        this.numerals = numerals
    }

    fun append(numeral: Numeral): RomanDecimalDigitCognate {
        if (!canAppend(numeral)) {
            return RomanDecimalDigitCognate(listOf(Numeral.NotANumeral))
        }
        val numerals = this.numerals.toMutableList()
        numerals.add(numeral)
        return RomanDecimalDigitCognate(numerals)
    }

    private fun canAppend(numeral: Numeral): Boolean {
        return numerals.count { it.equals(numeral) } < 3 && numerals.size < 4
    }

    fun faceValue() = this.numerals.fold(0, { total, next -> total + next.faceValue })

    fun placeValue() = this.numerals.fold(0, { total, next ->
        if (total < next.placeValue) {
            next.placeValue - total
        } else {
            next.placeValue + total
        }
    })

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RomanDecimalDigitCognate

        if (numerals != other.numerals) return false

        return true
    }

    override fun hashCode(): Int {
        return numerals.hashCode()
    }

    override fun toString(): String {
        return "RomanDecimalDigitCognate(numerals=$numerals)"
    }

}