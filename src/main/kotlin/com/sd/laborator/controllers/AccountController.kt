package com.sd.laborator.controllers

import com.sd.laborator.interfaces.AccountInterface
import com.sd.laborator.pojo.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController {
    @Autowired
    private lateinit var accountService: AccountInterface

    @RequestMapping(value=["/register"], method=[RequestMethod.POST])
    fun register(@RequestBody user: User): ResponseEntity<Unit> {
        accountService.addAccount(user)

        return ResponseEntity(Unit, HttpStatus.CREATED)
    }
}