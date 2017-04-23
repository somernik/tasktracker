<%--
  Created by IntelliJ IDEA.
  User: sarah
  Date: 4/2/2017
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="templates/taglib.jsp"%>
<c:set var="title" value="User Profile" />
<%@include file="templates/header.jsp"%>
<html>
<body>
<c:if test="${loggedIn}">
<div class="mainContent">
    <div class="container-fluid">
        <div class="row">
            <%@include file="templates/sideNav.jsp"%>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <form action="/editUser" id="editUser" class="form-signin form" method="post">

                    <h2 class="form-signin-heading formHeader">Edit User</h2>

                    <label for="username">Username</label>
                    <input id="username" class="form-control" name="username" type="text" placeholder="Username"
                           pattern="^[A-Za-z]{1}[A-Za-z0-9_-]+$" title="Username must start with a letter and contain only letters, numbers, or dashes"
                           required autofocus value="${user.username}" /><br />

                    <label for="email">Email</label>
                    <input id="email" class="form-control" name="email" type="email" placeholder="Email" value="${user.email}" required /><br />

                    <label for="firstName">First Name</label>
                    <input id="firstName" class="form-control" name="firstName" type="text" placeholder="First Name"
                           pattern="^([A-Za-z]{1}[A-Za-z-]+[ ]?)+$" title="First Name must contain only letters or dashes"
                            value="${user.firstName}" required /><br />

                    <label for="lastName">Last Name</label>
                    <input id="lastName" class="form-control" name="lastName" type="text" placeholder="Last Name"
                           pattern="^([A-Za-z]{1}[A-Za-z-]+[ ]?)+$" title="Last Name must contain only letters or dashes"
                           value="${user.lastName}" required  /><br />

                    <label for="password">Change Password</label>
                    <input type="password" class="form-control" id="password" name="password" aria-describedby="searchTermHelp" placeholder="New Password"><br />
                    <input type="password" class="form-control" id="passwordCheck" name="passwordCheck" aria-describedby="searchTermHelp" placeholder="New Password Check"><br />

                    <label for="passwordOld">Enter Password to Confirm Changes</label>
                    <input type="password" class="form-control" id="passwordOld" name="passwordOld" aria-describedby="searchTermHelp" placeholder="Password" required><br />

                    <!-- TODO: make cancel button work -->
                    <input type="button" href="profile.jsp" value="Cancel" class="btn btn-success btn-block" /><br />
                    <button type="submit" name="submit" value="editUser" class="btn btn-success btn-block">Save Changes</button>
                </form>
            </div>
        </div>
    </div>
</div>
</c:if>
<c:if test="${loggedIn != true}">
    <h3>Please <a href="logIn.jsp">log in</a> to view page.</h3>
</c:if>
</body>
</html>