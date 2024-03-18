package com.sd.laborator.controllers

import com.sd.laborator.interfaces.ICheltuieli
import com.sd.laborator.pojo.Cheltuiala
import com.sd.laborator.pojo.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CheltuieliController {
    @Autowired
    private lateinit var cheltuialaService: ICheltuieli

    @RequestMapping(value = ["/addcheltuiala"], method=[RequestMethod.POST])
    fun createCheltuiala(@RequestBody cheltuiala: Cheltuiala): ResponseEntity<Unit> {
        println(cheltuiala)
        cheltuialaService.add(cheltuiala)
        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @RequestMapping(value = ["/cheltuieli"], method = [RequestMethod.GET])
    fun getCheltuieli() :  ResponseEntity<List<Cheltuiala>> {
        val cheltuieli = cheltuialaService.afisare()

        var httpStatus = HttpStatus.OK
        if (cheltuieli.isEmpty()) {
            httpStatus = HttpStatus.NO_CONTENT
        }
        return ResponseEntity(cheltuieli, httpStatus)
    }

    @RequestMapping(value = ["/deletecheltuiala/{id}"], method = [RequestMethod.DELETE])
    fun deleteCheltuieli(@PathVariable id: Int): ResponseEntity<Unit> {
        cheltuialaService.delete(id)
        return ResponseEntity(Unit, HttpStatus.OK)
    }
}