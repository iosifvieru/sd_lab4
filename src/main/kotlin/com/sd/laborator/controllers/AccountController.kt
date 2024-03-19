package com.sd.laborator.controllers

import com.sd.laborator.interfaces.AccountInterface
import com.sd.laborator.interfaces.EncryptionInterface
import com.sd.laborator.pojo.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController {
    @Autowired
    private lateinit var accountService: AccountInterface

    // CREATE

    // DELETE
    @RequestMapping(value=["/deleteaccount/{id}"], method=[RequestMethod.DELETE])
    fun deleteAccount(@PathVariable id: Int): ResponseEntity<Unit>{
        accountService.deleteAccount(id)
        return ResponseEntity(Unit, HttpStatus.OK)
    }

    // READ
    // TODO

    // UPDATE
    // TODO
}