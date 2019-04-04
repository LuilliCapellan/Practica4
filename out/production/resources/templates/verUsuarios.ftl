<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog - Practica 4 - 2014-0984</title>

    <!-- Bootstrap core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="../css/blog-post.css" rel="stylesheet">

</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/inicio/1">Inicio</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/agregarPost">Agregar Post</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Post Content Column -->
        <div class="col-lg-8">


            <table class="table">
                <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Usuario</th>
                    <th>Admin</th>
                    <th>Autor</th>
                    <th>Eliminar</th>
                    <th>Modificar</th>
                    <th>Ver</th>
                </tr>
                </thead>
                <tbody>
                <#assign x = 0>
                <#list usuarios as usuario>
                    <tr>

                        <td>${usuario.nombre}</td>
                        <td>${usuario.username}</td>
                        <td>${usuario.administrator?then("Si", "No")}</td>
                        <td>${usuario.autor?then("Si", "No")}</td>


                        <td><a href="/eliminarUsuario/${x}" class="btn"><i class="far fa-trash-alt"></i></a></td>
                        <td><a href="/editarUsuario/${x}" class="btn"><i class="fas fa-pencil-alt"></i></a></td>
                        <td><a href="verUsuario/${x}" class="btn"><i class="fas fa-eye"></i></a></td>

                    </tr>
                    <#assign x++>
                </#list>
                </tbody>
            </table>


        </div>


    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<!-- Footer -->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Luis Capellan - 2014-0984</p>
    </div>
    <!-- /.container -->
</footer>

<!-- Bootstrap core JavaScript -->
<script src="../vendor/vendor/jquery/jquery.min.js"></script>
<script src="../vendor/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
