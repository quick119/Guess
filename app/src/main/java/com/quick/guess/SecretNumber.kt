package com.quick.guess

import java.util.*

class SecretNumber {
    var secret: Int = Random().nextInt(10) + 1
    var count = 0

    fun validate(number : Int) : Int {
        count ++
        return number - secret
    }

    fun reset() {
        secret = Random().nextInt(10) + 1
        count = 0
    }
}