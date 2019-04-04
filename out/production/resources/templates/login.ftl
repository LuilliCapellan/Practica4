<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Blog - Practica 4 - 2014-0984</title>
    <!-- Bootstrap core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <link href="../css/blog-login.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/my-login.css">


</head>
<body class="my-login-page">


<section class="h-100">
    <div class="container h-100">
        <div class="row justify-content-md-center h-100">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h4 class="card-title">Login</h4>
                        <form method="POST" action="/iniciarSesion">

                            <#assign x="">
                            <div class="form-group">
                                <label for="email">Usuario</label>

                                <input id="email" type="text" class="form-control" name="usuario"  required autofocus value=${user!x}>
                            </div>

                            <div class="form-group">
                                <label for="password">Password

                                </label>
                                <input id="password" type="password" class="form-control" name="password" required data-eye value=${pass!x}>
                            </div>

                            <div class="form-group">
                                <label>

                                    <input type="checkbox"  id="remember" name="remember"> Remember me
                                </label>
                            </div>

                            <div class="form-group no-margin">
                                <button type="submit" class="btn btn-primary btn-block">
                                    Login
                                </button>
                            </div>

                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>

<script src="../vendor/bootstrap/js/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="../vendor/bootstrap/js/my-login.js"></script>
</body>
</html>
