package romannumerals

import romannumerals.Numeral.NotANumeral

class RomanNumber {
    private val MAX_DIGIT_FACE_VALUE = 9
    private val ONE_MAGNITUDE_TEN_POWER_ONE = 10

    private var digits: List<RomanDecimalDigitCognate>

    constructor(numeral: Numeral) {
        this.digits = listOf(RomanDecimalDigitCognate(listOf(numeral)))
    }

    private constructor(digits: List<RomanDecimalDigitCognate>) {
        this.digits = digits
    }

    fun append(numeral: Numeral): RomanNumber {
        val digits = this.digits.toMutableList()
        val lastDigit = digits.last()
        if (isInvalidOperation(lastDigit, numeral)) {
            return (RomanNumber(NotANumeral))
        }
        if (shouldItBeANewDigit(lastDigit, numeral)) {
            val newLastDigit = RomanDecimalDigitCognate(listOf(numeral))
            digits.add(newLastDigit)
        } else {
            val modifiedCurrentLastDigit = lastDigit.append(numeral)
            digits.remove(lastDigit)
            digits.add(modifiedCurrentLastDigit)
        }
        return RomanNumber(digits)
    }

    fun toDecimal(): Int {
        val total = digits.fold(0, { total, next -> total + next.placeValue() })
        return if (total > 0) {
            total
        } else {
            0
        }
    }

    private fun isInvalidOperation(lastDigit: RomanDecimalDigitCognate, numeral: Numeral): Boolean {
        val hasANotANumeralValue = lastDigit.equals(RomanDecimalDigitCognate(listOf(NotANumeral)))
                || numeral == NotANumeral
        val areTheseBothWithFaceValueOf5ExceptDecreasingMagnitude = (lastDigit.faceValue() == 5 && numeral.faceValue == 5)
                && lastDigit.placeValue() <= numeral.placeValue
        val isTheNumeralValueMoreThanAMagnitudeHigherThanPreviousDigit = numeral.placeValue / lastDigit.placeValue() > ONE_MAGNITUDE_TEN_POWER_ONE
        val isFiveTimesPreviousDigitExceedsTheNumeralValueIfTheNumeralIsAGreaterNumber = numeral.placeValue > lastDigit.placeValue()
                && lastDigit.placeValue() * 5 > numeral.placeValue
        val isTheNumeralNegatingThePreviousDigit = lastDigit.placeValue() / numeral.placeValue == MAX_DIGIT_FACE_VALUE
        return (
                hasANotANumeralValue
                        || areTheseBothWithFaceValueOf5ExceptDecreasingMagnitude
                        || isTheNumeralValueMoreThanAMagnitudeHigherThanPreviousDigit
                        || isFiveTimesPreviousDigitExceedsTheNumeralValueIfTheNumeralIsAGreaterNumber
                        || isTheNumeralNegatingThePreviousDigit
                )
    }

    private fun shouldItBeANewDigit(lastDigit: RomanDecimalDigitCognate, numeral: Numeral): Boolean {
        val isTheLastDigitValueMoreThanAMagnitudeHigherThanTheNumeralValue = lastDigit.placeValue() / numeral.placeValue >= ONE_MAGNITUDE_TEN_POWER_ONE
        val isTheSumOfFaceValueMoreThanTheMaximumAllowedFaceValue = lastDigit.faceValue() + numeral.faceValue > MAX_DIGIT_FACE_VALUE
        return isTheLastDigitValueMoreThanAMagnitudeHigherThanTheNumeralValue
                || isTheSumOfFaceValueMoreThanTheMaximumAllowedFaceValue
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RomanNumber

        if (digits != other.digits) return false

        return true
    }

    override fun hashCode(): Int {
        return digits.hashCode()
    }

    override fun toString(): String {
        return "RomanNumber(digits=$digits)"
    }

}