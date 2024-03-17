package com.sd.laborator.interfaces

import java.sql.Connection
import java.sql.ResultSet

interface DatabaseInterface {
    fun getConnection(): Connection
    fun executeQuery(query: String) : ResultSet?
    fun closeConnection()
}