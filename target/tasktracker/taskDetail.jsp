<%@include file="templates/taglib.jsp"%>
<c:set var="title" value="Task Detail" />
<%@include file="templates/header.jsp"%>
<html>
<%@include file="templates/dataTableSetup.jsp"%>
<!-- Load c3.css -->
<link href="c3/c3-0.4.11/c3.css" rel="stylesheet" type="text/css">

<!-- Load d3.js and c3.js -->
<!--<script src="/path/to/d3.v3.min.js" charset="utf-8"></script>-->
<script src="c3/d3.v3.js" charset="utf-8"></script>
<script src="c3/c3-0.4.11/c3.min.js"></script>
<body>
<div class="mainContent">
    <div class="container-fluid">
        <div class="row">
        <%@include file="templates/sideNav.jsp"%>

            <div class="col-sm-9 col-sm-offset-3 col-md-4 col-md-offset-2 main">
                <h2 class="form-signin-heading formHeader">Task Detail</h2><hr />
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
                <h2 class="formHeader">Percentage Completed</h2><hr />
                <div id="gaugeChart"></div>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-5 col-md-offset-1 main">
                <h2 class="formHeader">Time Entries</h2><hr />
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
<script>
    var percentage = (${singleTask.timeSpent} / ${singleTask.estimatedCompletionTime}) * 100;
    var chart = c3.generate({
        bindto: '#gaugeChart',
        data: {
            columns: [
                ['data', percentage]
            ],
            type: 'gauge',
            onclick: function (d, i) { console.log("onclick", d, i); },
            onmouseover: function (d, i) { console.log("onmouseover", d, i); },
            onmouseout: function (d, i) { console.log("onmouseout", d, i); }
        },
        gauge: {
        //        label: {
        //            format: function(value, ratio) {
        //                return value;
        //            },
        //            show: false // to turn off the min/max labels.
        //        },
        //    min: 0, // 0 is default, //can handle negative min e.g. vacuum / voltage / current flow / rate of change
        //    max: 100, // 100 is default
        //    units: ' %',
        //    width: 39 // for adjusting arc thickness
        },
        color: {
            pattern: ['#FF0000', '#F97600', '#F6C600', '#60B044'], // the three color levels for the percentage values.
            threshold: {
                //            unit: 'value', // percentage is default
                //            max: 200, // 100 is default
                values: [30, 60, 90, 100]
            }
        },
        size: {
            height: 180
        }
    });
</script>
</body>
</html>