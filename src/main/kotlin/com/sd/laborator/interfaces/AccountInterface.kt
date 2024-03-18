package com.sd.laborator.interfaces

import com.sd.laborator.pojo.User

interface AccountInterface {
    fun addAccount(user: User)
    fun deleteAccount(id: Int)
    fun searchAccount()
    fun updateAccount(id: Int, user: User)

    //?
    fun getAccount(username: String): User
}