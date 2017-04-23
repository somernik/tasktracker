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
            document.getElementById("taskDrop").className = "hidden";
        } else {
            document.getElementById("newTypeLocation").className = "hidden";
            document.getElementById("taskDrop").className = "not-hidden";

        }
    }

    function test() {
        if (document.getElementById("addNewCategory").checked) {
            document.getElementById("newCategoryLocation").className = "not-hidden";
            document.getElementById("taskCategoryId").value = "new";
            document.getElementById("categoryDrop").className = "hidden";
        } else {
            document.getElementById("newCategoryLocation").className = "hidden";
            document.getElementById("categoryDrop").className = "not-hidden";
        }
    }

</script>
    <body>
    <c:if test="${loggedIn}">
        <%@include file="templates/sideNav.jsp"%>

        <form action="/addTask" id="newTask" class="form-signin form" method="post">
            <h2 class="form-signin-heading formHeader">Add New Task</h2>
            <div class="form-group">

                <input id="taskName" class="form-control" name="taskName" type="text" placeholder="Name"
                       pattern="^([A-Za-z0-9]{1}[A-Za-z-0-9.]*[ ]?)+$" title="Task Name must contain only letters, dashes or numbers"
                       required autofocus /><br />

                <div id="categoryDrop">
                    <select name="taskCategory" class="form-control" id="taskCategoryId">
                        <option name="taskCategory" value="new">Select Category</option>
                        <c:forEach var="entry" items="${categories}">
                            <option name="taskCategory" value="${entry}">${entry}</option>
                        </c:forEach>
                    </select>
                </div>

                <label for="addNewCategory">Add New Category?</label>
                <input type="checkbox" id="addNewCategory" name="addNewCategory" onclick="test();" /><br/><br />

                <div id="newCategoryLocation" class="hidden">
                    <input id="category" class="form-control" name="newCategory" type="text" placeholder="New Category"
                           pattern="^([A-Za-z0-9]{1}[A-Za-z-0-9.]*[ ]?)+$" title="Category must contain only letters, dashes, or numbers"
                           value="New Category" required /><br />
                </div>

                <div id="taskDrop">
                    <select name="type" class="form-control" id="typeSelection">
                        <option name="taskType" value="new">Select Type</option>
                        <c:forEach var="entry" items="${types}">
                            <option name="taskType" value="${entry.key}">${entry.value}</option>
                        </c:forEach>
                    </select>
                </div>

                <label for="addNewType">Add New Type?</label>
                <input type="checkbox" id="addNewType" name="addNewType" onclick="addNewTask();" /><br /><br />

                <div id="newTypeLocation" class="hidden">
                    <input id="taskType" class="form-control" name="newType" type="text" placeholder="New Type"
                           pattern="^([A-Za-z0-9]{1}[A-Za-z-0-9.]*[ ]?)+$" title="Type must contain only letters, dashes, or numbers"
                           value="New Type" required /><br />
                </div>

                <textarea id="taskDescription" class="form-control" name="taskDescription" type="text"
                          placeholder="Description" maxlength="1000" required></textarea><br />

                <input id="taskDueDate" class="form-control datepicker" name="taskDueDate" aria-describedby="searchTermHelp"
                       placeholder="Due Date" type="text"
                       pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
                       title="Please enter a valid date" required  /><br />

                <button type="submit" name="submit" value="addTask" class="btn btn-success btn-block">Add Task</button>
            </div>
        </form>
    </c:if>
    <c:if test="${loggedIn != true}">
        <h3>Please <a href="logIn.jsp">log in</a> to view page.</h3>
    </c:if>
    </body>
</html>