<!DOCTYPE html>
<html lang="en">
<head>

    <link href='https://fonts.googleapis.com/css?family=Anton|Passion+One|PT+Sans+Caption' rel='stylesheet'
          type='text/css'>
    <title>Error</title>

    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <link href="../css/blog-login.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/my-login.css">
</head>

<body>

<!-- Error Page -->
<section class="h-100">
    <div class="container h-100">
        <div class="row justify-content-md-center h-100">
            <div class="error">
                <div class="container-floud">
                    <div class="col-xs-12 ground-color text-center">
                        <div class="container-error-404">
                            <div class="clip">
                                <div class="shadow">${tercero}<span class="digit thirdDigit"></span></div>
                            </div>
                            <div class="clip">
                                <div class="shadow"><span class="digit secondDigit">${segundo}</span></div>
                            </div>
                            <div class="clip">
                                <div class="shadow"><span class="digit firstDigit">${primero}</span></div>
                            </div>
                            <div class="msg">OH!<span class="triangle"></span></div>
                        </div>
                        <h2 class="h1">${mensaje}</h2>
                    </div>
                    <div class="form-group no-margin" style="display: inline-block;text-align: center; horiz-align: center; vertical-align: center">

                        <form method="get" action="/inicio" style="display: inline-block;text-align: center; horiz-align: center; vertical-align: center">

                            <button type="submit" class="btn btn-primary btn-block">
                                Volver atras
                            </button>
                        </form>

                        <hr>
                        <form method="get" action="/" style="display: inline-block;text-align: center; horiz-align: center; vertical-align: center">

                            <button type="submit"
                                    class="btn btn-primary btn-block">
                                Log in
                            </button>

                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>
<script src="../vendor/bootstrap/js/index.js"></script>
<!-- Error Page -->
</body>
</html>