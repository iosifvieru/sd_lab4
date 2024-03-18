package com.sd.laborator.services

import com.sd.laborator.interfaces.AccountInterface
import com.sd.laborator.interfaces.DatabaseInterface
import com.sd.laborator.pojo.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountService: AccountInterface{
    @Autowired
    private lateinit var storage : DatabaseInterface
    override fun addAccount(user: User) {
        val query = "INSERT INTO users (firstname, lastname, username, password) VALUES ('${user.firstName}', '${user.lastName}', '${user.username}', '${user.password}');"
        storage.executeQuery(query)
    }

    override fun deleteAccount(id: Int) {
        val query = "DELETE FROM users WHERE id=$id;"
        storage.executeQuery(query)
    }

    override fun searchAccount() {
        TODO("Not yet implemented")
    }

    override fun updateAccount() {
        TODO("Not yet implemented")
    }

}