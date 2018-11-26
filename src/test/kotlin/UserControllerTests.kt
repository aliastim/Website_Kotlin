package com.timothee.corrado

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import junit.framework.TestCase
import org.junit.Test

class UserControllerTests {

    @Test
    fun getValidUserPasswordTest() {
        val model = mock<UserModel> {
            on { getUserPassword("test@test.fr") } doReturn "Text"
        }

        val controller = UserControllerImpl(model)

        val data = controller.displayUser("test@test.fr")
        TestCase.assertEquals(UserData("test@test.fr", "Text"), data)

        verify(model).getUserPassword("test@test.fr")
        verifyNoMoreInteractions(model)
    }
}