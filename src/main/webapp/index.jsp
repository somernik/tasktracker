<%@include file="templates/taglib.jsp"%>
<c:set var="title" value="PROject Home Page" />
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
            <img class="first-slide" src="images/cat1.jpg" alt="First slide">
            <div class="container">
                <div class="carousel-caption">
                    <h1>Accurate Task Estimation</h1>
                    <p>Every task you complete makes the estimation more accurate!</p>
                    <p><a class="btn btn-lg btn-primary btn-success" href="signUp.jsp" role="button">Sign up today</a></p>
                </div>
            </div>
        </div>
        <div class="item">
            <img class="second-slide" src="images/cat2.jpg" alt="Second slide">
            <div class="container">
                <div class="carousel-caption">
                    <h1>Another example headline.</h1>
                    <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                    <p><a class="btn btn-lg btn-primary btn-success" href="addTask.jsp" role="button">Add a Task</a></p>
                </div>
            </div>
        </div>
        <div class="item">
            <img class="third-slide" src="images/cat3.jpg" alt="Third slide">
            <div class="container">
                <div class="carousel-caption">
                    <h1>Cool Dashboard</h1>
                    <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                    <p><a class="btn btn-lg btn-primary btn-success" href="dashboard-sample.jsp" role="button">Check it Out!</a></p>
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
</div>
</body>
</html>