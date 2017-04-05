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

<link href="css/slider.css" rel="stylesheet" type="text/css">

<%@include file="templates/sideNav.jsp"%>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div>
        <h2 class="formHeader">Percentage of Time Spent by Type</h2>
        Category
            <label class="switch" onclick="changeDonutChart();">
                <input id="chartSwitch" type="checkbox" checked>
                <div class="slider round"></div>
            </label>Type
    </div>
    <div id="typeChart"></div>
    <div id="categoryChart" class="hide"></div>
    <div id="chart"></div>
    <div>
        <h2 class="formHeader">Time Spent Per Day of Week</h2>
        <p>${mostCommonDay} is the day you are most likely to work on tasks!</p>
    </div>
    <div id="barChart"></div>
</div>
<script>
    function changeDonutChart() {
        if (document.getElementById("chartSwitch").checked) {
            document.getElementById("categoryChart").className = "hide";
            document.getElementById("typeChart").className = "not-hidden";
        } else {
            document.getElementById("typeChart").className = "hide";
            document.getElementById("categoryChart").className = "not-hidden";
        }
    }
    // http://stackoverflow.com/questions/27659818/c3js-custom-date-for-each-line multiple  xs
    var types = [];
    var categories = [];
    var index_percent = 0;
    <c:forEach var="entry" items="${typePercentages}">
        var percentages = ['${entry.key}', ${entry.value}];
        types[index_percent] = percentages;
        index_percent++;
    </c:forEach>
    index_percent = 0;
    <c:forEach var="entry" items="${categoryPercentages}">
        var percentages = ['${entry.key}', ${entry.value}];
        categories[index_percent] = percentages;
        index_percent++;
    </c:forEach>
    var donutChart = c3.generate({
        bindto: '#typeChart',
        data: {
            columns: types,
            type : 'donut',
            onclick: function (d, i) { console.log("onclick", d, i); },
            onmouseover: function (d, i) { console.log("onmouseover", d, i); },
            onmouseout: function (d, i) { console.log("onmouseout", d, i); }
        },
        donut: {
            title: "% of Time Spent"
        }
    });
    var donutChart = c3.generate({
        bindto: '#categoryChart',
        data: {
            columns: categories,
            type : 'donut',
            onclick: function (d, i) { console.log("onclick", d, i); },
            onmouseover: function (d, i) { console.log("onmouseover", d, i); },
            onmouseout: function (d, i) { console.log("onmouseout", d, i); }
        },
        donut: {
            title: "% of Time Spent"
        }
    });

    var chart = c3.generate({
        bindto: '#chart',
        data: {
            x: 'x',
//        xFormat: '%Y%m%d', // 'xFormat' can be used as custom format of 'x'
            columns: [
                ['x', '2013-01-01', '2013-01-02', '2013-01-03', '2013-01-04', '2013-01-05', '2013-01-06'],
//            ['x', '20130101', '20130102', '20130103', '20130104', '20130105', '20130106'],
                ['data1', 30, 200, 100, 400, 150, 250],
                ['data2', 130, 340, 200, 500, 250, 350]
            ]
        },
        axis: {
            x: {
                type: 'timeseries',
                tick: {
                    format: '%Y-%m-%d'
                }
            }
        }
    });

    var dayColumns = [[],[],[],[],[],[],[]];
    var index = 0;
    <c:forEach var="entry" items="${timePerDay}">
        var dayValues = ['${entry.key}', ${entry.value}];
        dayColumns[index] = dayValues;
        index++;
    </c:forEach>
    var barChart = c3.generate({
        bindto: '#barChart',
        data: {
            columns: dayColumns,
            type: 'bar'
        },
        bar: {
            width: {
                ratio: 0.75 // this makes bar width 50% of length between ticks
            }
            // or
            //width: 100 // this makes bar width 100px
        }
    });

    function setGraph() {
        checkBox = document.getElementById('compliance_AA');
        if (checkBox.checked) {
            document.getElementById('ComplianceSummary').innerHTML = "A compliance concern is found for Key Element I-A.";
        } else {
            document.getElementById('ComplianceSummary').innerHTML = "Key Element I-A is met.";
        }
    }
</script>
</body>
</html>


