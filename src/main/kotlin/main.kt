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

import io.ktor.sessions.*

import java.sql.*
import java.sql.ResultSet
import java.util.concurrent.ConcurrentLinkedQueue


    //OpenClassroom : comprendre le lien avec la BDD :
    //https://openclassrooms.com/fr/courses/626954-creez-votre-application-web-avec-java-ee/624392-communiquez-avec-votre-bdd

data class Session1(val name: String, val value: Int)

fun registerRoutes(routing : Routing) {

    //Articles
    val model : ArticleModel = ArticleMysqlModel
    val controller : ArticleController = ArticleControllerImpl(model)

    routing.get("/article/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val content = controller.displayArticle(id)
        if (content != null)
        {
            call.respond(FreeMarkerContent("article.ftl", content))
        }else{
            call.respond(HttpStatusCode.NotFound)
        }
    }

    routing.post("article/{id}/comment") {
        val article_id = call.parameters["id"]!!.toInt()
        val params = call.receiveParameters()
        val text = params["text"]!!
        controller.postComment(article_id, text)
        call.respondRedirect("/article/$article_id")
    }

    routing.post("article/{id}/deleteComment") {
        val article_id = call.parameters["id"]!!.toInt()
        val params = call.receiveParameters()
        val text = params["text"]!!
        controller.deleteComment(text)
        call.respondRedirect("/article/$article_id")
    }


    routing.post("article/create") {
        val params = call.receiveParameters()
        val text = params["article_new"]!!
        controller.postArticle(text)
        call.respondRedirect("/article/nouveau")
    }

    routing.post("/article/byebye") {
        val params = call.receiveParameters()
        val text = (params["article_delete"]!!).toInt()
        controller.byebyeArticle(text)
        call.respondRedirect("/article/delete")
    }

    //User Connexion
    val model2 : UserModel = UserMysqlModel
    val controller2 : UserController = UserControllerImpl(model2)

    routing.post("admin/connection") {
        /*if (call.sessions.get<Session1>() != null) {
            call.respondRedirect("/admin")
        }
        else {*/
            val params = call.receiveParameters()
            val mail = params["mail"]!!
            val password = params["password"]!!
            val content = controller2.displayUser(mail)
            if (password == content!!.password) {
                //call.respondRedirect("/")
                call.sessions.set(Session1(name = mail, value = 1))
                //call.respond(FreeMarkerContent("plateform.ftl", content))
                call.respondRedirect("/admin/connection")
            } else {
                val erreur = "erreur de connexion"
                //call.respondRedirect("/admin")
                call.respond(FreeMarkerContent("connect.ftl", erreur))
            }
        //}
    }

    routing.post("admin/deconnection") {
        call.sessions.clear<Session1>()
        call.respondRedirect("/admin")
    }
}

fun main()
{
    //val pool = ConnectionPool()
    embeddedServer(Netty, port = 8080) {
        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        }
        install ( Sessions ) { cookie <Session1>( "Session1" ) }

        routing {
            get("/") {
                call.respond(FreeMarkerContent("index.ftl", mapOf("name" to "Timothée")))
            }
            registerRoutes(this)
        }
        //SESSION
        routing {
            get("/admin") {
                // If the session was not set, or is invalid, the returned value is null.
                //val mySession: Session1? = call.sessions.get<Session1>()
                //call.sessions.clear<Session1>()

                /*if (call.sessions.get<Session1>() != null)
                {
                    val model2 : UserModel = UserMysqlModel
                    val controller2 : UserController = UserControllerImpl(model2)
                    val mySession: Session1? = call.sessions.get<Session1>()

                    val mail = mySession!!.name
                    val content = controller2.displayUser(mail)

                    call.respond(FreeMarkerContent("plateform.ftl", content))

                }else{*/

                    call.respond(FreeMarkerContent("connect.ftl", mapOf("name" to "Timothée")))
                //}

            }
        }

        routing {
            get("/admin/connection") {

                val model2 : UserModel = UserMysqlModel
                val controller2 : UserController = UserControllerImpl(model2)

                val model : ArticleModel = ArticleMysqlModel
                val controller : ArticleController = ArticleControllerImpl(model)

                val mySession: Session1? = call.sessions.get<Session1>()

                val mail = mySession!!.name
                val content = controller2.displayUser(mail)

                val content1 = controller.displayArticles()

                val content0 = mapOf("mail" to mail, "password" to content, "articles" to content1)

                if (content0 != null)
                {
                    call.respond(FreeMarkerContent("plateform.ftl", content0))
                }else{
                    call.respond(HttpStatusCode.NotFound)
                }


            }
        }

        routing {
            get("/articles") {
                val model : ArticleModel = ArticleMysqlModel
                val controller : ArticleController = ArticleControllerImpl(model)

                val content = controller.displayArticles()
                if (content != null)
                {
                    call.respond(FreeMarkerContent("articles.ftl", content))
                }else{
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }

        routing {
            get("/article/nouveau") {
                val model2 : UserModel = UserMysqlModel
                val controller2 : UserController = UserControllerImpl(model2)

                val mySession: Session1? = call.sessions.get<Session1>()

                val mail = mySession!!.name
                val content = controller2.displayUser(mail)

                call.respond(FreeMarkerContent("article_nouveau.ftl", mapOf("content" to mail)))
            }
        }

        routing {
            get("/article/delete") {
                val model2 : UserModel = UserMysqlModel
                val controller2 : UserController = UserControllerImpl(model2)

                val mySession: Session1? = call.sessions.get<Session1>()

                val mail = mySession!!.name
                val content = controller2.displayUser(mail)

                call.respond(FreeMarkerContent("article_delete.ftl", mapOf("content" to mail)))
            }
        }


    }.start(wait = true)
}

