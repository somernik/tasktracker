<%@include file="templates/taglib.jsp"%>
<c:set var="title" value="Sign Up" />
<%@include file="templates/header.jsp"%>

<html>
<body>
<div class="mainContent">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <form action="/addUser" class="form-signin form" method="post">
                    <h2 class="form-signin-heading formHeader">Sign Up</h2>
                    <div class="form-group">
                        <input type="text" class="form-control" id="username" name="username"
                               aria-describedby="searchTermHelp" placeholder="Username" pattern="^[A-Za-z]{1}[A-Za-z0-9_-]+$"
                               title="Username must start with a letter and contain only letters, numbers, or dashes" required><br />

                        <input type="text" class="form-control" id="firstName" name="firstName" aria-describedby="searchTermHelp"
                               placeholder="First Name" pattern="^([A-Za-z]{1}[A-Za-z-]+[ ]?)+$" title="First Name must contain only letters or dashes" required><br />
                        <input type="text" class="form-control" id="lastName" name="lastName" aria-describedby="searchTermHelp"
                               placeholder="Last Name" pattern="^([A-Za-z]{1}[A-Za-z-]+[ ]?)+$" title="Last Name must contain only letters or dashes" required><br />

                        <input type="email" class="form-control" id="email" name="email" aria-describedby="searchTermHelp"
                               placeholder="Email" title="Please enter a valid email address" required><br />

                        <input type="password" class="form-control" id="password" name="password"
                               aria-describedby="searchTermHelp" placeholder="Password" title="Password must contain at least 1 lowercase letter, 1 uppercase letter, and 1 number" required><br />

                        <input type="password" class="form-control" id="passwordCheck" name="passwordCheck"
                               aria-describedby="searchTermHelp" placeholder="Password Check" title="Password must contain at least 1 lowercase letter, 1 uppercase letter, and 1 number" required><br />

                        <button type="submit" name="submit" value="addUser" class="btn btn-lg btn-primary btn-block btn-success">Sign Up</button>
                    </div>

                    <p>Already have an account? <a href="logIn.jsp">Sign In</a></p>

                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>