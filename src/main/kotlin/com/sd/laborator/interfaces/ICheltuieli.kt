package com.sd.laborator.interfaces

import com.sd.laborator.pojo.Cheltuiala

interface ICheltuieli {
    fun search(nume: String): Cheltuiala
    fun afisare(): List<Cheltuiala>
    fun add(cheltuiala: Cheltuiala)
    fun delete(id: Int)
    fun update(id:Int, cheltuiala: Cheltuiala)
}