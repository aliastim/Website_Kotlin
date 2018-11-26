<html>

    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    </head>

    <body>

    <div class="container">
         <nav class="nav" style="background: #F2F2F2; padding-right:10px; max-height:50px; padding-top:5px;">

             <div class="d-flex mr-auto"></div>
             <a class="nav-link active" href="/article/1">Article 1</a>
             <a class="nav-link" href="/articles">Articles</a>
             <a class="nav-link" href="/article/delete" style="color:red;">Suppression d'Article</a>
             <a href="/article/nouveau" style="padding-right:10px;"><button type="submit" class="btn btn-outline-success">Créer un article</button></a>

             <a href="/admin/connection"><button type="submit" class="btn btn-outline-info">Plateforme</button></a>

        </nav>

        <div class="text-center">

            <h1>Article n°${id}!</h1>
            <p>${text}!</p>

            <hr/>

            <#list commentaires as commentaire>

                <div class="row">

                    <div class="col-6 text-right">
                         <p>${commentaire}</p>
                    </div>

                    <div class="col-6 text-left">

                        <form method="post" action="/article/${id}/deleteComment" enctype="multipart/form-data">
                            <input type="hidden" name="text" value="${commentaire}"/>
                            <input type="submit" class="btn btn-outline-danger" value="Delete"/>
                        </form>

                    </div>

                </div>

             </#list>


            <form method="post" action="/article/${id}/comment" enctype="multipart/form-data">
                <input name="text" placeholder="votre commentaire"/><br><br>
                <input type="submit" value="envoyer"/>
            </form>

        </div>

       </div>
    </body>


</html>