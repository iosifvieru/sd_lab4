package com.sd.laborator.services

import com.sd.laborator.interfaces.DatabaseInterface
import org.springframework.stereotype.Service
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

@Service
class DatabaseService : DatabaseInterface{
    private val path = "jdbc:sqlite:sdlab4.db"
    private lateinit var connection: Connection

    override fun getConnection(): Connection {
        this.connection = DriverManager.getConnection(path)
        return connection
    }

    override fun executeQuery(query: String): ResultSet? {
        val connection = DriverManager.getConnection(path)
        val statement = connection.createStatement()

        if(query.contains("SELECT")){
            return statement.executeQuery(query)
        }

        statement.executeUpdate(query)
        connection.close()

        return null
    }

    override fun closeConnection() {
       this.connection.close()
    }
}