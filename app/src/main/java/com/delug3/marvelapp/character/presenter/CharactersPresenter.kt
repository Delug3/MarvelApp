package com.delug3.marvelapp.character.presenter

import com.delug3.marvelapp.character.view.Characters
import com.delug3.marvelapp.common.utilities.Constants
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and
import kotlin.experimental.or

class CharactersPresenter() : Characters.Presenter {

    private var md5Key: String? = null

    fun getCharacters() {
        if (md5Key == null) {
            getMd5Key()
        }

    }

    private fun getMd5Key(): String? {
        val input = Constants().TIME_STAMP + Constants().PRIVATE_KEY + Constants().PUBLIC_KEY
        try {
            val md = MessageDigest.getInstance("MD5")
            val md5Bytes = md.digest(input.toByteArray())
            val md5 = StringBuilder()
            for (md5Byte in md5Bytes) {
                md5.append(
                    Integer.toHexString((md5Byte and 0xFF.toByte() or 0x100.toByte()).toInt())
                        .substring(1, 3)
                )
            }
            md5Key = md5.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return md5Key
    }

}