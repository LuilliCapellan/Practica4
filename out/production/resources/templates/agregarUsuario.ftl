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
    <link rel="stylesheet" type="text/css" href="../css/my-login.css">
    <link href="../css/blog-login.css" rel="stylesheet">
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

                <#if usuario??>
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
                <form method="post" action="/guardarUsuario" style="width: 500px">

                    <div class="form-group">
                        <h1 class="mt-4">Agregar Usuario</h1>

                        <label>Nombre de usuario</label>
                        <input type="text" class="form-control" placeholder="username" aria-label="Username"
                               name="usuario" required>
                    </div>

                    <div class="form-group">
                        <label>Nombre</label>
                        <input type="text" class="form-control" rows="3" placeholder="John Doe" name="nombre" required>
                    </div>

                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" id="password" class="form-control" rows="3" name="pass" required
                               data-eye>
                    </div>
                    <div class="form-check-inline">
                        <input class="toggle" type="checkbox" name="admin"> Administrador

                    </div>
                    <div class="form-inline">
                        <input class="toggle" style="padding: 5px" type="checkbox" name="autor"> Autor
                    </div>


                    <hr>

                    <button style="float: right" type="submit" class="btn btn-primary">Guardar</button>
                </form>
            </div>


        </div>
        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

            <!-- Search Widget -->
            <div class="card my-4">
                <h5 class="card-header">Search</h5>
                <div class="card-body">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search for...">
                        <span class="input-group-btn">
                  <button class="btn btn-secondary" type="button">Go!</button>
                </span>
                    </div>
                </div>
            </div>

            <!-- Side Widget -->
            <div class="card my-4">
                <h5 class="card-header">Blog</h5>
                <div class="card-body">
                    Blog de prueba para la materia de Programacion WEB dada por el profesor Carlos Camacho, tarea
                    presentada por el estudiante, Luis Capellan
                    Matricula 2014-0984
                </div>
            </div>

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
