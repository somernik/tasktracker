<%@include file="templates/taglib.jsp"%>
<c:set var="title" value="PROject | Sign In" />
<%@include file="templates/header.jsp"%>
<html>
<body>
    <div class="container">

        <form class="form-signin form" action="/LoginServlet" method="post">
            <div class="divHeader">
            <h2 class="form-signin-heading formHeader">Please sign in</h2>
            </div>
            <div class="form-group">
                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" <c:if test="${not empty email}">value="${email}"</c:if> required autofocus><br />
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
                <c:if test="${not empty errorMessage}">
                    <p class="error">Incorrect username or password.</p>
                </c:if>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block btn-success" type="submit">Sign in</button>
            </div>
            <p>Need an account? <a href="signUp.jsp">Sign Up</a></p>
        </form>
    </div> <!-- /container -->
</body>
</html>
