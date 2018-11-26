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

interface ArticleController {
    fun displayArticle(id: Int): ArticleData?

    fun displayArticles() : ArticlesData?

    fun postComment(article_id: Int, text:String)

    fun postArticle(name : String)

    fun byebyeArticle(id: Int)

    fun deleteComment(text: String)

}