package com.sd.laborator.services

import com.sd.laborator.interfaces.DatabaseInterface
import org.springframework.stereotype.Service
import java.sql.*

@Service
class DatabaseService : DatabaseInterface{
    private val path = "jdbc:sqlite:sdlab4.db"

    private var connection: Connection? = null
    override fun getConnection(): Connection? {
        this.connection = DriverManager.getConnection(path)
        return this.connection
    }

    override fun executeQuery(query: String): ResultSet? {
        if(this.connection == null){
            getConnection()
        }

        val statement = connection?.createStatement()
        var result: ResultSet? = null

        if(query.contains("SELECT")){
            result = statement?.executeQuery(query)
        }
        else {
            statement?.executeUpdate(query)
        }
        return result
    }

    override fun closeConnection() {
       if(this.connection != null){
           connection?.close()
       }
    }
}