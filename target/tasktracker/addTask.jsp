<%@include file="templates/taglib.jsp"%>
<c:set var="title" value="PROject Add Task" />
<%@include file="templates/header.jsp"%>
<html>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $( function() {
        $( ".datepicker" ).datepicker({dateFormat: 'yy-mm-dd'});
    } );
</script>
    <body>
        <%@include file="templates/sideNav.jsp"%>
        <form action="/addTask" id="newTask" class="form-signin form" method="post">
            <h2 class="form-signin-heading formHeader">Add New Task</h2>
            <div class="form-group">

                <input id="taskName" class="form-control" name="taskName" type="text" placeholder="Name" required autofocus /><br />

                <input id="taskCategory" class="form-control" name="taskCategory" type="text" placeholder="Category" required /><br />

                <input id="taskType" class="form-control" name="taskType" type="text" placeholder="Type" required /><br />

                <input id="taskDescription" class="form-control" name="taskDescription" type="text" placeholder="Description" required /><br />

                <input id="taskDueDate" class="form-control datepicker" name="taskDueDate" aria-describedby="searchTermHelp" type="text" required placeholder="Due Date" /><br />

                <button type="submit" name="submit" value="addTask" class="btn btn-success">Add Task</button>
            </div>
        </form>
    </body>
</html>