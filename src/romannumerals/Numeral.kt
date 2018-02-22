package romannumerals

enum class Numeral(val char: Char, val placeValue: Int, val faceValue: Int) {
    I('I',1,1),
    V('V',5,5),
    X('X',10,1),
    L('L',50,5),
    C('C',100,1),
    D('D',500,5),
    M('M',1000,1),
    NotANumeral('U', Int.MIN_VALUE, Int.MIN_VALUE)
}