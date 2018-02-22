package romannumerals

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import romannumerals.Numeral.*


class RomanNumberTest {
    @Test
    fun shouldBeAbleToRepresentRomanNumbers() {
        assertEquals(RomanNumber(I), RomanNumber(I))
    }

    @Test
    fun shouldBeAbleToAppendNumeralsToRomanNumbers() {
        assertEquals(2, RomanNumber(I).append(I).toDecimal())
        assertEquals(3, RomanNumber(I).append(I).append(I).toDecimal())
    }

    @Test
    fun shouldReturnNotANumeralIfAppendedMoreThanThreeSimilarNumerals() {
        assertEquals(RomanNumber(NotANumeral), RomanNumber(I).append(I).append(I).append(I))
    }

    @Test
    fun shouldNotBeAbleToAppendTwoConsecutiveNumeralsWithSameFaceValueof5() {
        assertEquals(RomanNumber(NotANumeral), RomanNumber(V).append(V))
        assertEquals(RomanNumber(NotANumeral), RomanNumber(L).append(L))
    }

    @Test
    fun shouldNotBeAbleToAppendTwoConsecutiveNumeralsOfDifferentFaceValueof5() {
        assertEquals(RomanNumber(NotANumeral), RomanNumber(V).append(L))
        assertEquals(RomanNumber(NotANumeral), RomanNumber(L).append(V))
    }

    @Test
    fun shouldNotBeAbleToAppendMoreThanOneMagnitudeToALowerPlaceValue() {
        assertEquals(RomanNumber(NotANumeral), RomanNumber(I).append(C))
        assertEquals(RomanNumber(NotANumeral), RomanNumber(X).append(M))
    }

    @Test
    fun shouldNotBeAbleToAppendLargerNumeralToFaceValue5Numeral() {
        assertEquals(RomanNumber(NotANumeral), RomanNumber(V).append(X))
        assertEquals(RomanNumber(NotANumeral), RomanNumber(D).append(M))
    }

    @Test
    fun shouldBeAbleToAppendNumeralsOfFaceValue5ToImmediateLowerNumeral() {
        assertEquals(4, RomanNumber(I).append(V).toDecimal())
        assertEquals(400, RomanNumber(C).append(D).toDecimal())
    }

    @Test
    fun shouldNotBeAbleToAppendNumeralsOfFaceValue5ToTwoConsecutiveLowerNumerals() {
        assertEquals(RomanNumber(NotANumeral), RomanNumber(I).append(I).append(V))
        assertEquals(RomanNumber(NotANumeral), RomanNumber(C).append(C).append(D))
    }

    @Test
    fun shouldNotBeAbleToAppendNumeralsHigherValueToThreeConsecutiveLowerNumerals() {
        assertEquals(RomanNumber(NotANumeral), RomanNumber(I).append(I).append(I).append(X))
        assertEquals(RomanNumber(NotANumeral), RomanNumber(C).append(C).append(C).append(D))
    }

    @Test
    fun shouldNotBeAbleToAppendToNegatePreviousNumerals() {
        assertEquals(RomanNumber(NotANumeral), RomanNumber(X).append(C).append(X))
        assertEquals(RomanNumber(NotANumeral), RomanNumber(C).append(M).append(C))
    }

    @Test
    fun shouldBeAbleToAbleToRepresentRomanNumberInDecimal() {
        assertEquals(1999, RomanNumber(M).append(C).append(M).append(X).append(C).append(I).append(X).toDecimal())
        assertEquals(3990, RomanNumber(M).append(M).append(M).append(C).append(M).append(X).append(C).toDecimal())
    }

    @Test
    fun shouldReturn0ForNotANumeral() {
        assertEquals(0, RomanNumber(NotANumeral).toDecimal())
    }

    @Test
    fun shouldReturnNotANumeralForAnyOperationThatContainsNotANumeral() {
        assertEquals(RomanNumber(NotANumeral), RomanNumber(I).append(I).append(NotANumeral))
        assertEquals(RomanNumber(NotANumeral), RomanNumber(NotANumeral).append(I).append(X))
    }
}