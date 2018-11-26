package com.timothee.corrado

object UserMysqlModel: UserModel {

    override fun getUserPassword(mail: String) : String? {
        ConnectionPool.useConnection { connection ->
            connection.prepareStatement("SELECT `password` FROM `users` WHERE `mail` = ? ").use { stmt ->
                stmt.setString(1, mail)
                stmt.executeQuery().use { results ->
                    if (results.next()) {
                        return results.getString(1)
                    }
                }
            }
        }
        return null
    }

}