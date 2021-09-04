var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '环形图';

var projectNames = [],
    pieData = [],
    showDatas = JSON.parse(showDatas);
for (var i = 0; i < showDatas.length; i++) {
    var showData = showDatas[i];
    projectNames.push(showData.projectName);
    pieData.push({value : showData.totalAmount, name : showData.projectName});
}
option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data: projectNames
    },
    series: [
        {
            name:'',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: pieData
        }
    ]
};
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}