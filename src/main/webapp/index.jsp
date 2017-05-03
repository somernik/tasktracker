<%@include file="templates/taglib.jsp"%>
<c:set var="title" value="Home Page" />
<%@include file="templates/header.jsp"%>
<html>
<body>
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img class="first-slide" src="images/estimation.PNG" alt="First slide">
            <div class="container">
                <div class="carousel-caption">
                    <h1 class="dark">Accurate Task Estimation</h1>
                    <p class="dark">Every task you complete makes the estimation more accurate!</p>
                    <p><a class="btn btn-lg btn-primary btn-success" href="signUp.jsp" role="button">Sign up today!</a></p>
                </div>
            </div>
        </div>
        <div class="item">
            <img class="second-slide" src="images/search.PNG" alt="Second slide">
            <div class="container">
                <div class="carousel-caption">
                    <h1 class="dark">Search Through Tasks</h1>
                    <p class="dark">Search by type, name, amount of time spent and other options.</p>
                    <p><a class="btn btn-lg btn-primary btn-success" href="signUp.jsp" role="button">Sign up today!</a></p>
                </div>
            </div>
        </div>
        <div class="item">
            <img class="third-slide" src="images/detail.PNG" alt="Third slide">
            <div class="container">
                <div class="carousel-caption">
                    <h1 class="dark">Detailed Task Information</h1>
                    <p class="dark">Shows time entries, percentage completion and more!</p>
                    <p><a class="btn btn-lg btn-primary btn-success" href="signUp.jsp" role="button">Sign up Today!</a></p>
                </div>
            </div>
        </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div><!-- /.carousel -->

</body>
</html>