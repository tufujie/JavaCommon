var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '环形图';
// 方式1
var projectNames = [];
projectNames.push("泰富");
projectNames.push("泰然");
projectNames.push("沙河");
var showDatas = [];
var showData = {};
showData.value = 1;
showData.name = "泰富";
showDatas.push(showData);
var showData2 = {};
showData2.value = 2;
showData2.name = "泰然";
showDatas.push(showData2);
var showData3 = {};
showData3.value = 3;
showData3.name = "沙河";
showDatas.push(showData3);

// 方式2，建议使用
projectNameList = JSON.parse(projectNameList);
showDataList = JSON.parse(showDataList);

option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data: projectNameList
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
            data: showDataList
        }
    ]
};
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}