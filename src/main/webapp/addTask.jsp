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

    function addNewTask() {
        if (document.getElementById("addNewType").checked) {
            document.getElementById("newTypeLocation").className = "not-hidden";
            document.getElementById("typeSelection").value = "new";
        } else {
            document.getElementById("newTypeLocation").className = "hidden";
        }
    }
</script>
    <body>
    <c:if test="${loggedIn}">
        <%@include file="templates/sideNav.jsp"%>

        <form action="/addTask" id="newTask" class="form-signin form" method="post">
            <h2 class="form-signin-heading formHeader">Add New Task</h2>
            <div class="form-group">

                <input id="taskName" class="form-control" name="taskName" type="text" placeholder="Name" required autofocus /><br />

                <input id="taskCategory" class="form-control" name="taskCategory" type="text" placeholder="Category" required /><br />
                <select name="type" class="form-control" id="typeSelection">
                    <option name="taskType" value="new">Select Type</option>
                    <c:forEach var="entry" items="${types}">
                        <option name="taskType" value="${entry.key}">${entry.value}</option>
                    </c:forEach>
                </select>

                <label for="addNewType">Add New Type?</label>
                <input type="checkbox" id="addNewType" name="addNewType" onclick="addNewTask();" />

                <div id="newTypeLocation" class="hidden">
                    <input id="taskType" class="form-control" name="newType" type="text" placeholder="New Type" value="New Type" required /><br />
                </div>

                <input id="taskDescription" class="form-control" name="taskDescription" type="text" placeholder="Description" required /><br />

                <input id="taskDueDate" class="form-control datepicker" name="taskDueDate" aria-describedby="searchTermHelp" type="text" required placeholder="Due Date" /><br />

                <button type="submit" name="submit" value="addTask" class="btn btn-success btn-block">Add Task</button>
            </div>
        </form>
    </c:if>
    <c:if test="${loggedIn != true}">
        <h3>Please <a href="logIn.jsp">log in</a> to view page.</h3>
    </c:if>
    </body>
</html>