package com.sd.laborator.interfaces

interface EncryptionInterface {
    fun encrypt(str: String): String
    fun decrypt(str: String): String
}