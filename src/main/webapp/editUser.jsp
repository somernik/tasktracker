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
                    <input id="username" class="form-control" name="username" type="text" placeholder="Username" required autofocus value="${user.username}" /><br />

                    <label for="email">Email</label>
                    <input id="email" class="form-control" name="email" type="email" placeholder="Email" required value="${user.email}" /><br />

                    <label for="firstName">First Name</label>
                    <input id="firstName" class="form-control" name="firstName" type="text" placeholder="First Name" required value="${user.firstName}" /><br />

                    <label for="lastName">Last Name</label>
                    <input id="lastName" class="form-control" name="lastName" type="text" placeholder="Last Name" required value="${user.lastName}" /><br />

                    <label for="password">Change Password</label>
                    <input type="password" class="form-control" id="passwordOld" name="passwordOld" aria-describedby="searchTermHelp" placeholder="Old Password"><br />
                    <input type="password" class="form-control" id="password" name="password" aria-describedby="searchTermHelp" placeholder="New Password"><br />
                    <input type="password" class="form-control" id="passwordCheck" name="passwordCheck" aria-describedby="searchTermHelp" placeholder="New Password Check"><br />

                    <button href="profile.jsp" value="cancel" class="btn btn-success btn-block">Cancel</button><br />
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