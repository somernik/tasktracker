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

<%@include file="templates/sideNav.jsp"%>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div id="donutchart"></div>
    <div id="chart"></div>
    <div id="barChart"></div>
</div>

<script>

    var donutChart = c3.generate({
        bindto: '#donutchart',
        data: {
            columns: [
                ['data1', 30],
                ['data2', 120],
            ],
            type : 'donut',
            onclick: function (d, i) { console.log("onclick", d, i); },
            onmouseover: function (d, i) { console.log("onmouseover", d, i); },
            onmouseout: function (d, i) { console.log("onmouseout", d, i); }
        },
        donut: {
            title: "Iris Petal Width"
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
    setTimeout(function () {
        chart.load({
            columns: [
                ['data3', 400, 500, 450, 700, 600, 500]
            ]
        });
    }, 1000);

    var barChart = c3.generate({
        bindto: '#barChart',
        data: {
            columns: [
                ['data1', 30, 200, 100, 400, 150, 250],
                ['data2', 130, 100, 140, 200, 150, 50]
            ],
            type: 'bar'
        },
        bar: {
            width: {
                ratio: 0.5 // this makes bar width 50% of length between ticks
            }
            // or
            //width: 100 // this makes bar width 100px
        }
    });

    setTimeout(function () {
        barChart.load({
            columns: [
                ['data3', 130, -150, 200, 300, -200, 100]
            ]
        });
    }, 1000);
</script>
</body>
</html>


