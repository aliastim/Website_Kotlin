package com.timothee.corrado

interface UserController {

    fun displayUser(mail: String): UserData?
}