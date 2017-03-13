<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
    <body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" id="logo" href="../index.jsp">PROject</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="../index.jsp">Home</a></li>
                    <li><a href="#about">About</a></li>
                    <li><a href="#contact">Contact</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">My Tasks</a></li>
                            <li><a href="#">My Classes</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li class="dropdown-header">Nav header</li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-right">
                    <c:choose>
                    <c:when test="${loggedIn}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle btn btn-primary btn-success" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${email} <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="../dashboard.jsp">Dashboard</a></li>
                                <li><a href="#">Tasks</a></li>
                                <li><a href="#">My Classes</a></li>
                                <li role="separator" class="divider"></li>
                                <li class="dropdown-header">Nav header</li>
                                <li><a href="editUser.jsp">Edit Account</a></li>
                                <li><a class="btn btn-primary btn-success" href="\LogoutServlet" role="button">Log Out</a></li>
                            </ul>
                        </li>

                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-primary btn-success" href="../logIn.jsp" role="button">Log In</a>
                        <a class="btn btn-primary btn-success" href="../signUp.jsp" role="button">Sign Up</a>
                    </c:otherwise>
                    </c:choose>
                </form>
            </div>
        </div>
    </nav>
    <!--
    <div class="navbar-wrapper">
        <div class="container">

            <nav class="navbar navbar-inverse navbar-static-top">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" id="logo" href="#">PROject</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="index.jsp">Home</a></li>
                            <li><a href="#about">About</a></li>
                            <li><a href="#contact">Contact</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">My Tasks</a></li>
                                    <li><a href="#">My Classes</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li class="dropdown-header">Nav header</li>
                                    <li><a href="#">Separated link</a></li>
                                    <li><a href="#">One more separated link</a></li>
                                </ul>
                            </li>
                        </ul>
                        <form class="navbar-form navbar-right">
                            <a class="btn  btn-primary btn-success" href="logIn.jsp" role="button">Sign In</a>
                            <a class="btn btn-primary btn-success" href="signUp.jsp" role="button">Sign Up</a>
                        </form>
                    </div>

                </div>
            </nav>

        </div>
    </div>-->

    </body>
</html>