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
                <#if autor?? && autor == true >
                    <li class="nav-item">
                        <a class="nav-link" href="/agregarPost">Agregar Post</a>
                    </li>
                <#elseif admin?? && admin==true>
                    <li class="nav-item">
                        <a class="nav-link" href="/agregarPost">Agregar Post</a>
                    </li>
                </#if>

                <#if (usuario??)>
                    <li class="nav-item">
                        <a class="nav-link" href="/logOut">Salir</a>
                    </li>
                <#else>
                    <li class="nav-item">
                        <a class="nav-link" href="/">Log In</a>
                    </li>
                </#if>


            </ul>
        </div>
    </div>
</nav>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Post Content Column -->
        <div class="col-lg-8">

            <div class="lead">
                <!-- Title -->
                <form method="post" action="/editarPost/${articulo.id}">

                    <div class="form-group">
                        <h1 class="mt-4">Editar Post</h1>
                        <hr>
                        <textarea class="form-control" rows="1" placeholder="${articulo.titulo}"
                                  name="titulo">${articulo.titulo}</textarea>
                    </div>

                    <div class="form-group">
                        <h4 class="mt-4">Contenido</h4>
                        <textarea class="form-control" rows="3" placeholder="Cuerpo del post"
                                  name="cuerpo">${articulo.cuerpo}</textarea>
                    </div>

                    <div class="form-group">
                        <h4 class="mt-4">Etiquetas</h4>
                        <textarea class="form-control" rows="1" placeholder="${articulo.titulo}"
                                  name="etiquetas">${etiquetas}</textarea>
                    </div>
                    <button style="float: right" type="submit" class="btn btn-primary">Editar</button>
                </form>
            </div>


        </div>
        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

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
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
