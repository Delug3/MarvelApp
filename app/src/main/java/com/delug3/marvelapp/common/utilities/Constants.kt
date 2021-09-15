package com.delug3.marvelapp.common.utilities

import java.math.BigInteger
import java.security.MessageDigest
/**Utility class to add constants that we can use across the entire app, reducing the necessary code*/
class Constants {
    companion object {
        val timeStamp = System.currentTimeMillis().toString()
        const val BASE_URL = "https://gateway.marvel.com/"
        const val PUBLIC_KEY = "3bdb4c120b096da4fc94c7c938a9b33f"
        const val PRIVATE_KEY = "ac93aa83c707d957155ab6a1c2c7ca13f63e9b4f"
        const val CHARACTER_ID = "CHARACTER_ID"
        const val NO_CONNECTION = "No internet connection"
        const val NO_DATA = "No data"
        const val NO_NAME = "Name not available"
        const val NO_DESCRIPTION = "Sorry, but there is no description available for this character yet"
        const val NO_COMICS = "No appearances for this character"

        fun hashKey(): String {
            val input = timeStamp + PRIVATE_KEY + PUBLIC_KEY
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

    }


}