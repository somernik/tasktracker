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
<div class="mainContent">
    <div class="container-fluid">
        <div class="row">
            <%@include file="templates/sideNav.jsp"%>
            <div class="col-sm-9 col-sm-offset-3 col-md-5 col-md-offset-2 main">
                <div class="form" id="profile">
                    <h2 class="form-signin-heading formHeader">User Profile</h2><hr />
                    <h3>Username: ${user.username}</h3>
                    <h3>Email: ${user.email}</h3>
                    <h3>First Name: ${user.firstName}</h3>
                    <h3>Last Name: ${user.lastName}</h3>
                    <a href="../editUser.jsp"><button type="submit" name="submit" value="editUser" class="btn btn-success">Edit Profile</button></a>
                </div>
            </div>
        </div>
    </div>
</div>
</div>