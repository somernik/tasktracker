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
<c:if test="${loggedIn}">
<div class="mainContent">
    <div class="container-fluid">
        <div class="row">
        <%@include file="templates/sideNav.jsp"%>
            <c:if test="${not empty singleTask}">
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <c:choose>
                    <c:when test="${singleTask.estimatedCompletionTime <= 0}">
                        Estimated Time Left not available. Please add an estimate of how long this task will take.
                        <form method="post" action="/addEstimation">
                            <input type="hidden" name="id" value="${task.taskId}">
                            <input type="text" class="form-control table-input dashboard-input" name="estimation"
                                   pattern="^([0-9]+)[/.]?([0-9]+)$" title="Please only enter numbers" placeholder="Minutes" required />
                            <button type="submit" name="submit" value="addEstimation" class="btn btn-success">Add Estimation</button>
                        </form>
                    </c:when>
                    <c:when test="${singleTask.completed == true}">
                        <h2>This task has been completed!</h2>
                    </c:when>
                    <c:otherwise>
                        <h1>${numberOfDays} Days To Completion!</h1>
                    </c:otherwise>
                </c:choose>
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h2 class="formHeader">Time Entry Burn Up</h2>
                    </div>
                    <div class="panel-body">
                        <p>This graph shows the estimated amount of time for completion and how much the time you work is bringing you to your goal!</p>
                        <div id="burnUpChart"></div>
                    </div>
                </div>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-5 col-md-offset-2 main">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h2 class="form-signin-heading formHeader">Task Detail</h2>
                    </div>
                    <div class="panel-body">
                        <h3>Name: ${singleTask.taskName}</h3>
                        <h3>Category: ${singleTask.taskCategory}</h3>
                        <h3>Type: ${singleTask.taskType}</h3>
                        <h3>Description: ${singleTask.taskDescription}</h3>
                        <h3>Start Date: ${singleTask.startDate}</h3>
                        <h3>Due Date: ${singleTask.taskDueDate}</h3>
                        <h3>Total Time Spent: ${singleTask.timeSpent} minutes </h3>
                        <h3>Estimated Completion Time: ${singleTask.estimatedCompletionTime} minutes</h3>
                        <h3>Completion:
                            <c:if test="${singleTask.completed == true}">Completed</c:if>
                            <c:if test="${singleTask.completed == false}">Not Completed</c:if></h3>
                    </div>
                    <div class="panel-footer">
                        <form method="post" action="/editTask">
                            <input type="hidden" name="id" value="${singleTask.taskId}">
                            <button type="submit" name="submit" value="editTask" class="btn btn-success">Edit Task</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-5 col-md-offset-0 main">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h2 class="formHeader">Percentage Completed</h2>
                    </div>
                    <div class="panel-body">
                        <div id="gaugeChart"></div>
                    </div>
                </div>

                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h2 class="formHeader">Time Entries</h2>
                    </div>
                    <div class="panel-body">
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
            </c:if>
            <c:if test="${empty singleTask}">
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <h3>Please a select a task from the <a href="/dashboard">Dashboard</a> or <a href="/searchTasks">search</a> for a task.</h3>
            </div>
            </c:if>
        </div>
    </div>
</div>
</div>
<script>
    var percentage = (${singleTask.timeSpent} / ${singleTask.estimatedCompletionTime}) * 100;

    var entries = ['${singleTask.taskName}'];
    entries.push(0);
    <c:forEach var="point" items="${plotPoints}">
        entries.push(${point});
    </c:forEach>

    var dates = ['x'];
    dates.push('${singleTask.startDate}');
    <c:forEach var="entry" items="${taskEntries}">
        dates.push('${entry.dateEntered}');
    </c:forEach>

    var estimatedCompletion = ['Estimated Completion Time'];
    for (index = 1; index < entries.length; index++) {
        estimatedCompletion.push(${singleTask.estimatedCompletionTime});
    }

    var chart = c3.generate({
        bindto: '#burnUpChart',
        data: {
            x: 'x',
//        xFormat: '%Y%m%d', // 'xFormat' can be used as custom format of 'x'
            columns: [
                dates,
//            ['x', '20130101', '20130102', '20130103', '20130104', '20130105', '20130106'],
                entries,
                estimatedCompletion
            ]
        },
        axis: {
            x: {
                type: 'timeseries',
                tick: {
                    format: '%Y-%m-%d',
                    rotate: 85,
                    multiline: false,
                    height: 130
                }
            },
            y: {
                min: 0
            }
        }
    });

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
            pattern: ['#FF0000', '#F97600', '#F6C600', '#60B044'], // the four color levels for the percentage values.
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
</c:if>
<c:if test="${loggedIn != true}">
    <h3>Please <a href="logIn.jsp">log in</a> to view page.</h3>
</c:if>
</body>
</html>