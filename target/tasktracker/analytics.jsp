<%@include file="templates/taglib.jsp"%>
<c:set var="title" value="Task Analytics" />
<%@include file="templates/header.jsp"%>
<html>
<body>
<!-- Load c3.css -->
<link href="c3/c3-0.4.11/c3.css" rel="stylesheet" type="text/css">

<!-- Load d3.js and c3.js -->
<!--<script src="/path/to/d3.v3.min.js" charset="utf-8"></script>-->
<script src="c3/d3.v3.js" charset="utf-8"></script>
<script src="c3/c3-0.4.11/c3.min.js"></script>

<div id="chart"></div>
<script>

    var chart = c3.generate({
        bindto: '#chart',
        data: {
            columns: [
                ['data1', 30, 200, 100, 400, 150, 250],
                ['data2', 50, 20, 10, 40, 15, 25]
            ]
        }
    });
</script>
</body>
</html>


