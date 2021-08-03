package com.delug3.marvelapp.common.utilities

import java.math.BigInteger
import java.security.MessageDigest

class Constants {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com/"
        const val PUBLIC_KEY = "3bdb4c120b096da4fc94c7c938a9b33f"
        const val PRIVATE_KEY = "ac93aa83c707d957155ab6a1c2c7ca13f63e9b4f"
        val timeStamp = System.currentTimeMillis().toString()


        fun hashKey(): String {
            val input = timeStamp + PRIVATE_KEY + PUBLIC_KEY
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

    }


}