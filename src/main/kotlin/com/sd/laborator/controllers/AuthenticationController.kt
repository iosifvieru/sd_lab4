package com.sd.laborator.controllers

import com.sd.laborator.interfaces.AccountInterface
import com.sd.laborator.interfaces.EncryptionInterface
import com.sd.laborator.pojo.LoginRequest
import com.sd.laborator.pojo.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController {
    @Autowired
    private lateinit var accountService: AccountInterface

    @Autowired
    private lateinit var passwordHash: EncryptionInterface

    @RequestMapping(value=["/login"], method=[RequestMethod.POST])
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Unit> {

        println(loginRequest.username)
        println(loginRequest.password)

        val user: User = accountService.getAccount(loginRequest.username)

        if(passwordHash.encrypt(loginRequest.password) != user.password){
            return ResponseEntity(Unit, HttpStatus.NOT_FOUND)
        }

        /*
        if(loginRequest.password != user.password){
            return ResponseEntity(Unit, HttpStatus.NOT_FOUND)
        }*/

        return ResponseEntity(Unit, HttpStatus.OK)
    }


}