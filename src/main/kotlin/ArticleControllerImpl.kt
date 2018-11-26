package com.timothee.corrado

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import freemarker.cache.*
import io.ktor.freemarker.*
//import io.ktor.freemarker.FreeMarker
import io.ktor.http.*
import io.ktor.request.receiveParameters

import java.sql.*
import java.sql.ResultSet
import java.util.concurrent.ConcurrentLinkedQueue

class ArticleControllerImpl(val model: ArticleModel) : ArticleController {

    override fun displayArticle(id: Int): ArticleData? {
        val text = model.getArticleText(id) ?: return null
        val comments = model.getArticleComments(id)
        return ArticleData(id, text, comments)
    }

    override fun displayArticles() : ArticlesData? {

        val articles = model.getArticles()
        return ArticlesData(articles)

    }

    override fun postComment(article_id: Int, text:String) {
        if (text.isNotBlank())
            model.postComment(article_id, text)
    }

    override fun postArticle(name: String) {
        if (name.isNotBlank())
            model.postArticle(name)
    }

    override fun byebyeArticle(id: Int) {
        model.deleteArticle(id)
        model.deleteComments(id)
    }

    override fun deleteComment(text: String) {
        model.deleteComments2(text)
    }

}