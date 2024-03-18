package com.sd.laborator.services

import com.sd.laborator.interfaces.EncryptionInterface
import org.springframework.stereotype.Service
import java.security.MessageDigest

@Service
class SHA256Service : EncryptionInterface{
    private val algorithm: String = "SHA-256"

    override fun encrypt(str: String): String {
        val digest = MessageDigest.getInstance(algorithm)
        val encodedHash = digest.digest(str.toByteArray(charset("UTF-8")))

        val hexString = StringBuilder(2* encodedHash.size)

        for (byte in encodedHash){
            hexString.append(String.format("%02X", byte))
        }
        return hexString.toString()
    }

    override fun decrypt(str: String): String {
        return ""
    }


}