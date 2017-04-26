<%@include file="templates/taglib.jsp"%>
<c:set var="title" value="Dashboard" />
<%@include file="templates/header.jsp"%>
<html>
<body>
<div class="mainContent">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li><a href="#">Dashboard</a></li>
                    <li><a href="#">Analytics</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="#">Add New Task</a></li>
                    <li><a href="#">Search Tasks</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="#">View Profile</a></li>
                    <li><a href="#">Edit Account</a></li>
                </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <h1 class="page-header">Your Dashboard</h1>

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
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Type</th>
                            <th>Category</th>
                            <th>Estimated Time Left</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Final Project</td>
                            <td>Project</td>
                            <td>Java Class</td>
                            <td>30</td>
                            <td><a class="btn btn-primary btn-success" href="" role="button">Add Time</a></td>
                        </tr>
                        <tr>
                            <td>Persuasive Speech</td>
                            <td>Assignment</td>
                            <td>Speech</td>
                            <td>70</td>
                            <td><a class="btn btn-primary btn-success" href="" role="button">Add Time</a></td>
                        </tr>
                        <tr>
                            <td>Vacuum</td>
                            <td>Weekly Chores</td>
                            <td>Housework</td>
                            <td>20</td>
                            <td><a class="btn btn-primary btn-success" href="" role="button">Add Time</a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
