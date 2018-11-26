package com.timothee.corrado

object ArticleMysqlModel : ArticleModel {

    override fun getArticleText(article_id: Int): String? {
        ConnectionPool.useConnection { connection ->
            connection.prepareStatement("SELECT `name` FROM `articles` WHERE `id` = ? ").use { stmt ->
                stmt.setInt(1, article_id)
                stmt.executeQuery().use { results ->
                    if (results.next()) {
                        return results.getString(1)
                    }
                }

            }
        }

        return null
    }

    override fun getArticleComments(article_id: Int): List<String> {
        val comments = ArrayList<String>()
        ConnectionPool.useConnection { connection ->
            connection.prepareStatement("SELECT text From commentaires WHERE article_id = ?").use { stmt ->
                stmt.setInt(1, article_id)
                stmt.executeQuery().use { results ->
                    while (results.next())
                        comments += results.getString(1)
                }
            }
        }
        return comments
    }

     override fun postComment(article_id: Int, text: String) {
        ConnectionPool.useConnection { connection ->
            connection.prepareStatement("INSERT INTO commentaires (article_id, text) VALUES ( ?, ?)").use { stmt ->
                stmt.setInt(1, article_id)
                stmt.setString(2, text)
                stmt.execute()
            }
        }
    }

    override fun postArticle(name: String) {
        ConnectionPool.useConnection { connection ->
            connection.prepareStatement("INSERT INTO articles (name) VALUES ( ? )").use { stmt ->
                stmt.setString(1, name)
                stmt.execute()
            }
        }
    }

    override fun getArticles(): List<String> {
        val articles = ArrayList<String>()
        ConnectionPool.useConnection { connection ->
            connection.prepareStatement("SELECT name From articles").use { stmt ->
                stmt.executeQuery().use { results ->
                    while (results.next())
                        articles += results.getString(1)
                }
            }
        }
        return articles
    }

    override fun deleteArticle(id: Int): String? {
        ConnectionPool.useConnection { connection ->
            connection.prepareStatement("DELETE FROM `articles` WHERE `id` = ? ").use { stmt ->
                stmt.setInt(1, id)
                stmt.execute()
            }
        }

        return null
    }

    override fun deleteComments(article_id: Int): String? {
        ConnectionPool.useConnection { connection ->
            connection.prepareStatement("DELETE FROM `commentaires` WHERE `id` = ? ").use { stmt ->
                stmt.setInt(1, article_id)
                stmt.execute()
            }
        }

        return null
    }

    override fun deleteComments2(text: String): String? {
        ConnectionPool.useConnection { connection ->
            connection.prepareStatement("DELETE FROM `commentaires` WHERE `text` = ? ").use { stmt ->
                stmt.setString(1, text)
                stmt.execute()
            }
        }

        return null
    }
}