<%@include file="templates/taglib.jsp"%>
<c:set var="title" value="Task Detail" />
<%@include file="templates/header.jsp"%>
<html>
<%@include file="templates/dataTableSetup.jsp"%>
<body>
<div class="mainContent">
    <div class="container-fluid">
        <div class="row">
        <%@include file="templates/sideNav.jsp"%>

            <div class="col-sm-9 col-sm-offset-3 col-md-4 col-md-offset-2 main">
                <h2 class="form-signin-heading formHeader">Task Detail</h2>
                <h3>Name: ${singleTask.taskName}</h3>
                <h3>Category: ${singleTask.taskCategory}</h3>
                <h3>Type: ${singleTask.taskType}</h3>
                <h3>Description: ${singleTask.taskDescription}</h3>
                <h3>Start Date: ${singleTask.startDate}</h3>
                <h3>Due Date: ${singleTask.taskDueDate}</h3>
                <h3>Total Time Spent: ${singleTask.timeSpent} minutes </h3>
                <h3>Estimated Completion Time: ${singleTask.estimatedCompletionTime}</h3>
                <h3>Completion:
                    <c:if test="${singleTask.completed == true}">Completed</c:if>
                    <c:if test="${singleTask.completed == false}">Not Completed</c:if></h3>

                <form method="post" action="/editTask">
                    <input type="hidden" name="id" value="${singleTask.taskId}">
                    <button type="submit" name="submit" value="editTask" class="btn btn-success">Edit Task</button>
                </form>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-5 col-md-offset-1 main">
                <h2 class="formHeader">Time Entries</h2>
                <c:if test="${empty taskEntries}">
                    <h5>This task does not have any time entries, get started by <a href="editTask.jsp">editing your task</a>!</h5>
                </c:if>
                <c:if test="${not empty taskEntries}">
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Date Entered</th>
                            <th>Time Entered</th>
                            <th>Time (in minutes)</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="taskEntry" items="${taskEntries}">
                                <tr>
                                    <td>${taskEntry.dateEntered}</td>
                                    <td>${taskEntry.timeEntered}</td>
                                    <td>${taskEntry.timeAdded}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                </c:if>

            </div>

        </div>
    </div>
</div>
</div>
</body>
</html>