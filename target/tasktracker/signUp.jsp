<%@include file="templates/taglib.jsp"%>
<c:set var="title" value="PROject Sign Up" />
<%@include file="templates/header.jsp"%>

<html>
<body>
    <form action="/addUser" class="form-signin form" method="post">
        <h2 class="form-signin-heading formHeader">Sign Up</h2>
        <div class="form-group">
            <input type="text" class="form-control" id="username" name="username" aria-describedby="searchTermHelp" placeholder="Username"><br />

            <input type="text" class="form-control" id="firstName" name="firstName" aria-describedby="searchTermHelp" placeholder="First Name"><br />
            <input type="text" class="form-control" id="lastName" name="lastName" aria-describedby="searchTermHelp" placeholder="Last Name"><br />

            <input type="text" class="form-control" id="email" name="email" aria-describedby="searchTermHelp" placeholder="Email"><br />

            <input type="password" class="form-control" id="password" name="password" aria-describedby="searchTermHelp" placeholder="Password"><br />

            <input type="password" class="form-control" id="passwordCheck" name="passwordCheck" aria-describedby="searchTermHelp" placeholder="Password Check"><br />
            <button type="submit" name="submit" value="addUser" class="btn btn-lg btn-primary btn-block btn-success">Sign Up</button>
        </div>

        <p>Already have an account? <a href="logIn.jsp">Sign In</a></p>

    </form>
</body>
</html>