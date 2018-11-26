<html>

    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    </head>

    <body>

    <div class="container text-center">
        <nav class="nav" style="background: #F2F2F2; padding-right:10px; max-height:50px; padding-top:5px;">
              <p style="font-size:20px; margin-bottom: -5px; padding-top: 5px; padding-left: 10px;">Connecté sous : <B>${content}!</B></p>
              <div class="d-flex mr-auto"></div>
              <a class="nav-link active" href="/article/1">Article 1</a>
              <a class="nav-link" href="/articles">Articles</a>
              <a class="nav-link" href="/article/delete" style="color:red;">Suppression d'Article</a>
              <a href="/article/nouveau" style="padding-right:10px;"><button type="submit" class="btn btn-outline-success">Créer un article</button></a>
              <form method="post" action="/admin/deconnection" enctype="multipart/form-data">
                  <button type="submit" class="btn btn-outline-danger">Déconnexion</button>
              </form>
        </nav>

        <br><br>
              <h1 class="text-center">Suppression d'article</h1>
        <br><br>

        <form method="post" action="/article/byebye" enctype="multipart/form-data">
              <div class="form-group">
                <label for="exampleInputEmail1">Quel article voulez vous supprimer (mettre l'id) ?</label>
                    <input name="article_delete" class="form-control" id="exampleInputEmail1" placeholder="Enter id article">
              </div>

              <button type="submit" class="btn btn-primary">Submit</button>
        </form>



    </div>
    </body>

</html>