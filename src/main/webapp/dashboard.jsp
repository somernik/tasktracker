<%@include file="templates/taglib.jsp"%>
<c:set var="title" value="Dashboard" />
<%@include file="templates/header.jsp"%>
<html>
<%@include file="templates/dataTableSetup.jsp"%>
<body>
<div class="mainContent">
    <div class="container-fluid">
      <div class="row">
        <%@include file="templates/sideNav.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">${email} Dashboard</h1>

          <div class="row placeholders">
            <div class="col-xs-6 col-sm-3 placeholder">
              <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
          </div>

          <h2 class="sub-header">Active Tasks</h2>
          <c:if test="${empty tasks}">
            <h3>You do not have any tasks, get started by <a href="addTask.jsp">adding a task</a>!</h3>
          </c:if>
          <c:if test="${not empty tasks}">
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Type</th>
                  <th>Category</th>
                  <th>Estimated Time Left</th>
                  <th>Time Entry</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="task" items="${tasks}">
                  <c:if test="${task.completed == false}">
                  <tr>
                    <td>${task.taskName}</td>
                    <td>${task.taskType}</td>
                    <td>${task.taskCategory}</td>
                    <td><c:out value="${task.estimateTimeLeft()}" />
                    </td>
                    <td>
                      <form method="post" action="/editTask">
                        <input type="hidden" name="id" value="${task.taskId}">
                        <input type="text" class="form-control table-input dashboard-input" name="timeAdded" placeholder="Minutes" required />
                        <button type="submit" name="submit" value="addTime" class="btn btn-success">Add Time</button>
                      </form>
                    </td>
                    <td>
                      <form method="post" action="/editTask">
                        <input type="hidden" name="id" value="${task.taskId}">
                        <button type="submit" name="submit" value="details" class="btn btn-success">View Details</button>
                      </form>
                    </td>
                  </tr>
                </c:if>
                </c:forEach>
              </tbody>
            </table>
          </div>
          </c:if>
        </div>
      </div>
    </div>
</div>
  </body>
</html>
