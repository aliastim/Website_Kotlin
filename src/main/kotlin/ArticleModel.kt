package com.timothee.corrado

interface ArticleModel {

    fun getArticleText(article_id: Int): String?

    fun getArticleComments(article_id: Int): List<String>

    fun postComment(article_id: Int, text: String)

    fun getArticles(): List<String>

    fun postArticle(name: String)

    fun deleteArticle(id: Int): String?

    fun deleteComments(article_id: Int): String?

    fun deleteComments2(text: String): String?
}