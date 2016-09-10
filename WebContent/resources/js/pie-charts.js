/**
 * Created by Trung on 3/30/2016 9:07 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
function drawPieChart(container, seriesData) {

    $('#pie-chart').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Error Rate'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            name: "Brands",
            colorByPoint: true,
            data: seriesData
        }]
    });
}

function getRemotePieDataDrawChart(container, url) {
    console.log("Starting...!");

    $.ajax({
        url: url,
        dataType: 'json',
        success: function(data) {

            var aData = data.series;
            var arr = []

            console.log("test content" + aData);

            $.map(aData, function (item, index) {
                var obj = {};
                obj.name = item.name;
                obj.y = item.data;
                arr.push(obj);
            });

            var myJsonString = JSON.stringify(arr);
            var jsonArray = JSON.parse(JSON.stringify(arr));

            console.log("Starting...!");
            console.log(jsonArray);
            console.log("Ending...!");

            drawPieChart(container, jsonArray);

        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        },
        cache: false
    });
} //function end