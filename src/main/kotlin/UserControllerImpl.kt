package com.timothee.corrado

class UserControllerImpl(val model: UserModel): UserController {

    override fun displayUser(mail: String): UserData? {
        val password = model.getUserPassword(mail) ?: return null
        return UserData(mail, password)
    }
}