<%@include file="templates/taglib.jsp"%>
<c:set var="title" value="Task Search" />
<%@include file="templates/header.jsp"%>
<html>
<%@include file="templates/dataTableSetup.jsp"%>
<body>
<div class="mainContent">
    <div class="container-fluid">
        <div class="row">
        <%@include file="templates/sideNav.jsp"%>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">Search Tasks</h2>
            <c:if test="${empty tasks}">
                <h3>No tasks matched your search or you do not have any tasks. <a href="addTask.jsp">Add a task?</a></h3>
            </c:if>
            <form method="get" action="/searchSpecificTasks" class="form" id="searchForm">
                <h3 class="form-signin-heading formHeader">Search Filters</h3>

                <label for="completion">Completion</label>
                <select name="completion" id="completion" class="form-control filterForm">
                    <option value="all" <c:if test="${completion == 'all'}">selected</c:if>>All</option>
                    <option value="completed" <c:if test="${completion == 'completed'}">selected</c:if>>Completed</option>
                    <option value="notCompleted" <c:if test="${completion == 'notCompleted'}">selected</c:if>>Not Completed</option>
                </select>

                <label for="category">Category</label>
                <select name="category" id="category" class="form-control filterForm">
                    <option value="all" >All</option>
                    <option value="completed">populated values here</option>
                    <option value="notCompleted">populated values here</option>
                </select>
                <br />
                <br />
                <label for="type">Type</label>
                <select name="type" id="type" class="form-control filterForm">
                    <option value="all">All</option>
                    <option value="completed">populated values here</option>
                    <option value="notCompleted">populated values here</option>
                </select>

                <label for="timeOperator">Time Spent</label>
                <select name="timeOperator" id="timeOperator" class="form-control filterForm">
                    <option value="greaterThan" <c:if test="${timeOperator == 'greaterThan'}">selected</c:if>>Greater than or equal to</option>
                    <option value="lessThan" <c:if test="${timeOperator == 'lessThan'}">selected</c:if>>Less than or equal to</option>
                </select>
                <input type="text" class="form-control filterForm" id="minutes" name="timeSpent" value="${time}" /> minutes
                <br /><br />
                <button type="submit" name="submit" value="searchInfo" class="btn btn-success btn-block">Search</button>
            </form>
            <br />
            <div class="table-responsive">
                <table class="table table-striped" id="searchTable">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Category</th>
                        <th>Due Date</th>
                        <th>Time Spent</th>
                        <th>Time Entry</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="task" items="${tasks}">
                        <tr>
                            <td>${task.taskName}</td>
                            <td>${task.taskType}</td>
                            <td>${task.taskCategory}</td>
                            <td>${task.taskDueDate}</td>
                            <td>${task.timeSpent}</td>
                            <td>
                                <c:if test="${task.completed == true}">Completed</c:if>
                                <c:if test="${task.completed == false}">
                                    <form method="post" action="/saveTask">
                                        <input type="hidden" name="id" value="${task.taskId}">
                                        <input type="text" class="form-control table-input dashboard-input" name="timeAdded" placeholder="Minutes" required />
                                        <button type="submit" name="submit" value="addTime" class="btn btn-success">Add Time</button>
                                    </form>
                                </c:if>
                            </td>
                            <td>
                                <form method="post" action="/taskDetails">
                                    <input type="hidden" name="id" value="${task.taskId}">
                                    <button type="submit" name="submit" value="details" class="btn btn-success">View Details</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>