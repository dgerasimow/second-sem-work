<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />

    <title><@title></@title></title>

    <link rel="stylesheet" href="/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/style.css" />
    <link rel="stylesheet" href="/css/ionicons.min.css" />
    <link rel="stylesheet" href="/css/font-awesome.min.css" />
    <link href="/css/emoji.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,400i,700,700i" rel="stylesheet">

    <link rel="shortcut icon" type="image/png" href="/images/fav.png"/>

    <style>
        .center {
            margin: auto;
            width: 60%;
            padding: 10px;
        }
    </style>
</head>

<body>
<header id="header">
    <nav class="navbar navbar-default navbar-fixed-top menu">
        <div class="container">

            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/login"><img src="/images/logo.png" alt="logo" /></a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right main-menu">
                    <li class="dropdown">
                        <a href="/posts">Посты</a>
                    </li>
                    <#if user??>
                        <li class="dropdown">
                            <a href="/profile/${user.id}">Мой профиль</a>
                        </li>
                        <li class="dropdown"><a href="/logout">Выйти</a></li>
                    </#if>
                </ul>
                <form class="navbar-form navbar-right hidden-sm">
                    <div class="form-group">
                        <i class="icon ion-android-search"></i>
                        <input type="text" class="form-control" placeholder="Поиск людей">
                    </div>
                </form>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
    </nav>
</header>
<!--Header End-->

<div class="center" >
    <div class="content"><@content></@content></div>
</div>

<!-- Footer
================================================= -->
<footer>
    <div class="container">
        <div class="row">
            <div class="footer-wrapper">
                <div class="copyright">
                    <p>Danil Gerasimov. All rights reserved</p>
                </div>
            </div>
        </div>
    </div>
</footer>

<!--preloader-->
<div id="spinner-wrapper">
    <div class="spinner"></div>
</div>

<!-- Scripts
================================================= -->
<script src="/js/jquery-3.1.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.sticky-kit.min.js"></script>
<script src="/js/jquery.scrollbar.min.js"></script>
<script src="/js/script.js"></script>
<script src="/js/newPost.js"></script>
<script src="/js/newComment.js"></script>

</body>

</html>