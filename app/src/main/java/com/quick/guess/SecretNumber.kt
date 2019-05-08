package com.quick.guess

import java.util.*

class SecretNumber {
    val secret: Int = Random().nextInt(10) + 1
    var count = 0

    fun validate(number : Int) : Int {
        count ++
        return number - secret
    }
}