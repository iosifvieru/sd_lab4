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

    override fun updateAccount(id: Int, user: User) {
        val query = "UPDATE users SET firstname = '${user.firstName}', lastname = '${user.lastName}', username= '${user.username}', password = '${user.password}' WHERE id='$id';"
        storage.executeQuery(query)
    }

    override fun getAccount(username: String): User {
        val query = "SELECT * FROM users WHERE username='$username';"
        val results = storage.executeQuery(query)

        val user: User = User(-1, "", "", "")

        if(results == null){
            return user
        }

        if(results.next()){
            user.id = results.getInt("id")
            user.firstName = results.getString("firstname")
            user.lastName = results.getString("lastname")
            user.username = results.getString("username")
            user.password = results.getString("password")
        }
        return user
    }
}