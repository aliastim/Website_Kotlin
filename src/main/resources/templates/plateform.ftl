<html>

    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    </head>

    <body>

<div class="container text-center">
    <nav class="nav" style="background: #F2F2F2; padding-right:10px; max-height:50px; padding-top:5px;">
      <p style="font-size:20px; margin-bottom: -5px; padding-top: 5px; padding-left: 10px;">Connecté sous : <B>${mail}!</B></p>
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
    <h1 class="text-center">Formulaire de connexion</h1>
    <br><br>



        <h1>Mail : ${mail}!</h1>
        <p>Info Utilisateur: ${password}!</p>
        <p>Mot de passe: ${password.password}!</p>


        <p>Articles: ${articles}!</p>


<!--
        <h2>Liste d'articles</h2>

        <table class="table">
          <thead class="thead-dark">
            <tr>
              <th scope="col">#</th>
              <th scope="col">Texte</th>
              <th scope="col">Supprimer</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">1</th>
              <td>Mark</td>
              <td>Otto</td>
            </tr>
            <tr>
              <th scope="row">2</th>
              <td>Jacob</td>
              <td>Thornton</td>
            </tr>
            <tr>
              <th scope="row">3</th>
              <td>Larry</td>
              <td>the Bird</td>
            </tr>
          </tbody>
        </table>

        <#list articles as article, value>
                <p>${article}</p>
        </#list>-->


    </div>
    </body>

</html>