package romannumerals

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import romannumerals.Numeral.*

class RomanNumberParserTest {
    @Test fun shouldBeAbleToParseRomanLetter() {
        assertEquals(1, RomanNumberParser("I").toRomanNumber().toDecimal())
        assertEquals(2, RomanNumberParser("II").toRomanNumber().toDecimal())
        assertEquals(1997, RomanNumberParser("MCMXCVII").toRomanNumber().toDecimal())
        assertEquals(1234, RomanNumberParser("MCCXXXIV").toRomanNumber().toDecimal())
    }

    @Test fun shouldReturn0ForUnparseableChars() {
        assertEquals(0, RomanNumberParser("ABCD").toRomanNumber().toDecimal())
    }
}