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
<div class="mainContent">
    <div class="container-fluid">
        <div class="row">
            <%@include file="templates/sideNav.jsp"%>

            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

                <form action="/saveTask" id="editTask" class="form-signin form" method="post">
                    <h2 class="form-signin-heading formHeader">Edit Task</h2>
                        <label for="taskName">Name</label>
                        <input id="taskName" class="form-control" name="taskName" type="text" placeholder="Name"
                               pattern="^([A-Za-z]{1}[A-Za-z-]+[ ]?)+$" title="Task Name must contain only letters or dashes"
                               autofocus value="${singleTask.taskName}" required /><br />


                        <div id="categoryDrop">
                            <select name="taskCategory" class="form-control" id="taskCategoryId">
                                <option name="taskCategory" value="new">Category</option>
                                <c:forEach var="entry" items="${categories}">
                                    <option name="taskCategory" value="${entry}" <c:if test="${singleTask.taskCategory == entry}">selected</c:if>>${entry}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <label for="addNewCategory">Add New Category?</label>
                        <input type="checkbox" id="addNewCategory" name="addNewCategory" onclick="test();" /><br/><br />

                        <div id="newCategoryLocation" class="hidden">
                            <input id="category" class="form-control" name="newCategory" type="text" placeholder="New Category"
                                   pattern="^([A-Za-z]{1}[A-Za-z-]+[ ]?)+$" title="Category must contain only letters or dashes"
                                   value="New Category" required /><br />
                        </div>


                    <div id="taskDrop">
                        <select name="type" class="form-control" id="typeSelection">
                            <option name="taskType" value="new">Select Type</option>
                            <c:forEach var="entry" items="${types}">
                                <option name="taskType" value="${entry.key}" <c:if test="${singleTask.taskType == entry.value}">selected</c:if>>${entry.value}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <label for="addNewType">Add New Type?</label>
                    <input type="checkbox" id="addNewType" name="addNewType" onclick="addNewTask();" /><br /><br />

                    <div id="newTypeLocation" class="hidden">
                        <input id="taskType" class="form-control" name="newType" type="text" placeholder="New Type"
                               pattern="^([A-Za-z]{1}[A-Za-z-]+[ ]?)+$" title="Type must contain only letters or dashes"
                               value="New Type" required /><br />
                    </div>

                        <label for="taskDescription">Description</label>
                        <textarea id="taskDescription" class="form-control" name="taskDescription" type="text"
                                  placeholder="Description" maxlength="1000" required>${singleTask.taskDescription}</textarea><br />

                        <label for="taskStartDate">Start Date</label>
                        <input id="taskStartDate" class="form-control datepicker" name="taskStartDate"
                               aria-describedby="searchTermHelp" type="text"  placeholder="Start Date"
                               pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
                               title="Please enter a valid date" value="${singleTask.startDate}" /><br />

                        <label for="taskDueDate">Due Date</label>
                        <input id="taskDueDate" class="form-control datepicker" name="taskDueDate"
                               aria-describedby="searchTermHelp" type="text"  placeholder="Due Date"
                               pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
                               title="Please enter a valid date" value="${singleTask.taskDueDate}" required /><br />

                        <label >Total Time : ${singleTask.timeSpent}</label><br />
                        <label for="timeAdded">Time To Add</label>
                        <input type="text" class="form-control table-input dashboard-input" id="timeAdded"
                               name="timeAdded" placeholder="Minutes" pattern="^([0-9]+)[/.]?([0-9]+)$"
                               title="Please only enter numbers" /><br />

                        <label for="completed">Completion</label><br />
                        <input id="completed" type="radio" class="form-control table-input dashboard-input"
                               name="completion" value="1" <c:if test="${singleTask.completed == true}">checked</c:if>>Completed</input>
                        <input id="notCompleted" type="radio" class="form-control table-input dashboard-input"
                               name="completion" value="0" <c:if test="${singleTask.completed == false}">checked</c:if>>Not Completed</input><br />

                        <input type="hidden" name="id" value="${singleTask.taskId}">

                        <button href="taskDetail.jsp" value="cancel" class="btn btn-success">Cancel</button>
                        <button type="submit" name="submit" value="saveEdits" class="btn btn-success">Save Changes</button>

                </form>
            </div>

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