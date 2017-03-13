<%@include file="templates/taglib.jsp"%>
<c:set var="title" value="Edit Task" />
<%@include file="templates/header.jsp"%>
<html>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- dateFormat: 'dd-mm-yy' -->
<script>
    $( function() {
        $( ".datepicker" ).datepicker({dateFormat: 'yy-mm-dd'});
    } );
</script>
<body>
<div class="mainContent">
    <div class="container-fluid">
        <div class="row">
            <%@include file="templates/sideNav.jsp"%>

            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

                <form action="/editTask" id="editTask" class="form-signin form" method="post">
                    <h2 class="form-signin-heading formHeader">Edit Task</h2>
                        <label for="taskName">Name</label>
                        <input id="taskName" class="form-control" name="taskName" type="text" placeholder="Name" required autofocus value="${singleTask.taskName}" /><br />

                        <label for="taskCategory">Category</label>
                        <input id="taskCategory" class="form-control" name="taskCategory" type="text" placeholder="Category" required value="${singleTask.taskCategory}" /><br />

                        <label for="taskType">Type</label>
                        <input id="taskType" class="form-control" name="taskType" type="text" placeholder="Type" required value="${singleTask.taskType}" /><br />

                        <label for="taskDescription">Description</label>
                        <input id="taskDescription" class="form-control" name="taskDescription" type="text" placeholder="Description" required value="${singleTask.taskDescription}" /><br />

                        <label for="taskStartDate">Start Date</label>
                        <input id="taskStartDate" class="form-control datepicker" name="taskStartDate" aria-describedby="searchTermHelp" type="text"  placeholder="Start Date" value="${singleTask.startDate}" /><br />

                        <label for="taskDueDate">Due Date</label>
                        <input id="taskDueDate" class="form-control datepicker" name="taskDueDate" aria-describedby="searchTermHelp" type="text"  placeholder="Due Date" required value="${singleTask.taskDueDate}"/><br />

                        <label >Total Time : ${singleTask.timeSpent}</label><br />
                        <label for="timeAdded">Time To Add</label>
                        <input type="text" class="form-control table-input dashboard-input" id="timeAdded" name="timeAdded" placeholder="Minutes" /><br />

                        <label for="completed">Completion</label><br />
                        <input id="completed" type="radio" class="form-control table-input dashboard-input" name="completion" value="1" <c:if test="${singleTask.completed == true}">checked</c:if>>Completed</input>
                        <input id="notCompleted" type="radio" class="form-control table-input dashboard-input" name="completion" value="0" <c:if test="${singleTask.completed == false}">checked</c:if>>Not Completed</input><br />

                        <input type="hidden" name="id" value="${singleTask.taskId}">
                        <button href="taskDetail.jsp" value="cancel" class="btn btn-success">Cancel</button>
                        <button type="submit" name="submit" value="saveEdits" class="btn btn-success">Save Changes</button>

                </form>
            </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>