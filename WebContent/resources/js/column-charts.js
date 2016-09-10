/**
 * Created by Trung on 3/30/2016 3:02 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
function drawColumnChart(container, seriesData) {

    $(function () {
        $('#column-chart').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Errors Statistic'
            },
            xAxis: {
                categories: ['OPERABLE', 'PERCEIVABLE', 'ROBUST', 'UNDERSTANDABLE', 'SQL INJECTION', 'XSS', 'XML INJECTION', 'XPATH INJECTION'],
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Total error'
                },
                stackLabels: {
                    enabled: true,
                    style: {
                        fontWeight: 'bold',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    }
                }
            },
            legend: {
                align: 'right',
                x: -30,
                verticalAlign: 'top',
                y: 25,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
            tooltip: {
                headerFormat: '<b>{point.x}</b><br/>',
                pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
            },
            credits: {
                enabled: false
            },
            plotOptions: {
                column: {
                    stacking: 'normal',
                    dataLabels: {
                        enabled: true,
                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                        style: {
                            textShadow: '0 0 3px black'
                        }
                    }
                }
            },
            series: seriesData
        });
    });
}

function getRemoteColumnDataDrawChart(container, url) {
    console.log("Starting...!");

    $.ajax({
        url: url,
        dataType: 'json',
        success: function(data) {
            var seriesData = [];

            $.each(data.series, function(i, seriesItem) {
                console.log(seriesItem) ;
                var series = {
                    data: []
                };
                series.name = seriesItem.name;
                series.color = seriesItem.color;

                $.each(seriesItem.data, function(j, seriesItemData) {
                    console.log("Data (" + j +"): "+seriesItemData) ;
                    series.data.push(parseFloat(seriesItemData));
                });

                seriesData[i] = series;
                console.log(seriesData[i]);
            });

            //draw the chart
            drawColumnChart(container, seriesData);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        },
        cache: false
    });
} //function end