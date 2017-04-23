<%--
  Created by IntelliJ IDEA.
  User: Sarah Omernik
  Date: 4/18/2017
  Time: 5:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="templates/taglib.jsp"%>
<body>

    <%@ page isErrorPage="true" import="java.io.*" contentType="text/html"%>
    <h3>Error Code: <%=response.getStatus()%></h3>
    <p>Message: There was a problem, requested resource may not be available. Please try again later.</p>
    <p><a href="index.jsp">Home Page</a></p>
</body>
