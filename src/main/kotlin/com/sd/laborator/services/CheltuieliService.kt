package com.sd.laborator.services

import com.sd.laborator.interfaces.DatabaseInterface
import com.sd.laborator.interfaces.ICheltuieli
import com.sd.laborator.pojo.Cheltuiala
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CheltuieliService : ICheltuieli {
    @Autowired
    private lateinit var storage: DatabaseInterface

    override fun search(nume: String): Cheltuiala {
        TODO("Not yet implemented")
    }

    override fun getCheltuiala(id: Int): Cheltuiala {
        val query = "SELECT * FROM cheltuieli WHERE id=$id;"
        val results = storage.executeQuery(query)

        val returnValue = Cheltuiala(-1, -1, -1.0f, "none")

        if(results == null){
            return returnValue
        }

        if(results.next()){
            returnValue.id = results.getInt("id")
            returnValue.userID = results.getInt("user_id")
            returnValue.suma = results.getFloat("suma")
            returnValue.descriere = results.getString("descriere")
        }

        return returnValue
    }

    override fun afisare(): List<Cheltuiala> {
        val results = storage.executeQuery("SELECT * FROM cheltuieli")
        val lista : MutableList<Cheltuiala> = mutableListOf()

        if (results != null) {
            while(results.next()){
                val tempCheltuiala = Cheltuiala(
                    results.getInt("id"),
                    results.getInt("user_id"),
                    results.getFloat("suma"),
                    results.getString("descriere")
                )
                lista.add(tempCheltuiala)
            }
        }

        return lista
    }

    override fun add(cheltuiala: Cheltuiala) {
        val query = "INSERT INTO cheltuieli (user_id, suma, descriere) VALUES (${cheltuiala.userID}, ${cheltuiala.suma}, '${cheltuiala.descriere}');"
        storage.executeQuery(query)
    }

    override fun delete(id: Int) {
        val query = "DELETE FROM cheltuieli WHERE id=${id};"
        storage.executeQuery(query)
    }

    override fun update(id: Int, cheltuiala: Cheltuiala) {
        //val query = "UPDATE cheltuieli SET user_id = '${cheltuiala.userID}', suma = '${cheltuiala.suma}', descriere = '${cheltuiala.descriere}' WHERE id='${id}';"
        //val query = "UPDATE cheltuieli SET user_id = '${cheltuiala.userID}', suma = '${cheltuiala.suma}', descriere = '${cheltuiala.descriere}' WHERE id='$id';"

        val userID = cheltuiala.userID
        val suma = cheltuiala.suma
        val descriere = cheltuiala.descriere

        println("$id $userID $suma $descriere")

        val query = "UPDATE cheltuieli SET user_id=$userID, suma=$suma, descriere='$descriere' WHERE id=$id;"
        storage.executeQuery(query)
    }
}