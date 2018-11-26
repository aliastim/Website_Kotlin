package com.timothee.corrado

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

class ArticleControllerTests {

    @Test
    fun getValidArticleTextTest() {
        /* Given */
        val model = mock<ArticleModel> {
            on { getArticleText(42) } doReturn "Text"
            on { getArticleComments(42) } doReturn listOf("Comment")
        }

        val controller = ArticleControllerImpl(model)

        val data = controller.displayArticle(42)
        assertEquals(ArticleData(42, "Text", listOf("Comment")), data)

        verify(model).getArticleText(42)
        verify(model).getArticleComments(42)
        verifyNoMoreInteractions(model)
    }

    @Test
    fun getInvalidArticleTextTest() {
        /* Given */
        val model = mock<ArticleModel> {
            on { getArticleText(42) } doReturn null
        }

        val controller = ArticleControllerImpl(model)

        val data = controller.displayArticle(42)
        assertNull(data)

        verify(model).getArticleText(42)
        verifyNoMoreInteractions(model)
    }

    @Test
    fun postInvalidComment() {
        val model = mock<ArticleModel>()

        val controller = ArticleControllerImpl(model)

        controller.postComment(42, "")
        controller.postComment(42, "   ")


    }

}