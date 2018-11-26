package com.timothee.corrado

import java.sql.*
import java.util.concurrent.ConcurrentLinkedQueue


object ConnectionPool {

    private val list = ConcurrentLinkedQueue<Connection>()

    fun getConnection(): Connection {
        return list.poll() ?: DriverManager.getConnection("jdbc:mysql://localhost:3306/DesignPattern", "root", "")
    }

    fun releaseConnection(c: Connection){
        list.add(c)
    }

    inline fun useConnection(f: (Connection) -> Unit) {
        val c = getConnection()
        try {
            f(c)
        }finally {
            releaseConnection(c)
        }
    }
}