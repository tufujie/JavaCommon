columnDefs
设置列定义初始化属性
说明
和 columnsOption 参数很像，这个参数允许你给指定列设置选项，应用到一个或这多个列。而不像 columnsOption 需要每列都要定义
这个参数是一个列定义对象数组，通过使用 columnDefs.targetsDT 选项，告诉Datatables是定义的是那一列，他可以是下列情况：
0或者正整数-列从左到右是从0开始
一个负数-列从右到左的索引(-1代表最后一列)
一个字符串-将字符串和类名匹配列
字符串"_all"-所有列
另外， targets可以同时指定多列，接受一个数组（比如 targets: [ -1, -2 ] ）
数据类型
这个选项可以在以下类型:
array Type
示例
禁止第一列参与搜索
$('#example').dataTable( {
  "columnDefs": [ {
      "targets": 0,
      "searchable": false,
      "visible", false
    } ]
} );


columnDefs.targets
起始版本号: 1.10.0
指定一列或者多列

说明
使用 columnDefsOption 参数给列定义，用 columnDefs.targets告诉这个定义是指向那一列或者那几列

他可以是下列情况：

0或者正整数-列从左到右是从0开始
一个负数-列从右到左的索引(-1代表最后一列)
一个字符串-将字符串和类名匹配列
字符串"_all"-所有列

另外， targets可以同时指定多列，接受一个数组（比如 targets: [ -1, -2 ] ）
注意 columnDefsOption 需要和columnDefs.targets 搭配使用，而 columnsOption 可以单独使用，这是两者的不同处

数据类型
这个选项可以在以下类型:
array Type | string JS | integer JS


示例
禁止第一列参与搜索
$('#example').dataTable( {
  "columnDefs": [ {
      "targets": 0,
      "searchable": false
    } ]
} );

禁止第一列和第三列不能排序
$('#example').dataTable( {
  "columnDefs": [ {
      "targets": [ 0, 2 ],
      "orderable": false
    } ]
} );

列包含‘nosort’类名的不排序
$('#example').dataTable( {
"columnDefs": [ {
"targets": 'nosort',
"orderable": false
} ]
} );



设置某一行不显示：
.fnSetColumnVis(0, false, false);
同理，显示为
.fnSetColumnVis(18, true, false);

1、DataTables的默认配置
$(document).ready(function() {

$('#example').dataTable({

});

} );

2、DataTables的一些基础属性配置
"bPaginate": true, //翻页功能，是否显示（应用）分页器
"bLengthChange": true, //改变每页显示数据数量
"bFilter": true, //过滤功能
"bSort": false, //排序功能，是否启动各个字段的排序功能
"bInfo": true,//页脚信息，是否显示页脚信息，DataTables插件左下角显示记录数
"bAutoWidth": true//自动宽度
"bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示
"bStateSave" : false, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态
"bServerSide": true,
"aLengthMenu" : [20, 40, 60], //更改显示记录数选项
"iDisplayLength" : 50, //默认显示的记录数
"bAutoWidth" : false, //是否自适应宽度
"bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
"sDom": "t<'row-fluid'<'span12'ir><'span123 center'p>>",
"sPaginationType": "bootstrap",//详细分页组，可以支持直接跳转到某页


3、数据排序

$(document).ready(function() {
$('#example').dataTable( {
"aaSorting": [
[ 4, "desc" ]
]
} );
} );

从第0列开始，以第4列倒序排列

4、多列排序

5、隐藏某些列

$(document).ready(function() {
$('#example').dataTable( {
"aoColumnDefs": [
{ "bSearchable": false, "bVisible": false, "aTargets": [ 2 ] },
{ "bVisible": false, "aTargets": [ 3 ] }
] } );
} );


6、改变页面上元素的位置

$(document).ready(function() {
$('#example').dataTable( {
"sDom": '<"top"fli>rt<"bottom"p><"clear">'
} );
} );
//l- 每页显示数量
//f - 过滤输入
//t - 表单Table
//i - 信息
//p - 翻页
//r - pRocessing
//< and > - div elements
//<"class" and > - div with a class
//Examples: <"wrapper"flipt>, <lf<t>ip>

7、状态保存，使用了翻页或者改变了每页显示数据数量，会保存在cookie中，下回访问时会显示上一次关闭页面时的内容。

$(document).ready(function() {
$('#example').dataTable( {
"bStateSave": true
} );
} );

8、显示数字的翻页样式

$(document).ready(function() {
$('#example').dataTable( {
"sPaginationType": "full_numbers"  // 也可以是直接为=>bootstrap
} );
} );

9、水平限制宽度

$(document).ready(function() {
$('#example').dataTable( {
"sScrollX": "100%",
"sScrollXInner": "110%",
"bScrollCollapse": true
} );
} );

10、垂直限制高度

11、水平和垂直两个方向共同限制

12、改变语言

$(document).ready(function() {
$('#example').dataTable( {
"oLanguage": {
"sLengthMenu": "每页显示 _MENU_ 条记录",
"sZeroRecords": "抱歉， 没有找到",
"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
"sInfoEmpty": "没有数据",
"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
"oPaginate": {
"sFirst": "首页",
"sPrevious": "前一页",
"sNext": "后一页",
"sLast": "尾页"
},
"sZeroRecords": "没有检索到数据",
"sProcessing": "<img src='./loading.gif' />"
}
} );
} );
这个可以配置在zh_CN.txt文件中
{
    "sProcessing" : "正在获取数据，请稍后...",  
    "sLengthMenu" : "显示 _MENU_ 条",  
    "sZeroRecords" : "没有您要搜索的内容",  
    "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",  
    "sInfoEmpty" : "记录数为0",  
    "sInfoFiltered" : "(全部记录数 _MAX_ 条)",  
    "sInfoPostFix" : "",  
    "sSearch" : "搜索",  
    "sUrl" : "",  
    "oPaginate": {  
        "sFirst" : "第一页",  
        "sPrevious" : "上一页",  
        "sNext" : "下一页",  
        "sLast" : "最后一页"  
    }
}
用法：
"oLanguage": {
                    "sUrl": "/resources/js/zh_CN.txt"
                }


13、click事件

14、配合使用tooltip插件

15、定义每页显示数据数量
$(document).ready(function() {
$('#example').dataTable( {
"aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]]
} );
} );

16、row callback
最后一列的值如果为“A”则加粗显示

17、排序控制

$(document).ready(function() {
$('#example').dataTable( {
"aoColumns": [
null,
{ "asSorting": [ "asc" ] },
{ "asSorting": [ "desc", "asc", "asc" ] },
{ "asSorting": [ ] },
{ "asSorting": [ ] }
]
} );
} );

说明：第一列点击按默认情况排序，第二列点击已顺序排列，第三列点击一次倒序，二三次顺序，第四五列点击不实现排序

18、从配置文件中读取语言包

$(document).ready(function() {
$('#example').dataTable( {
"oLanguage": {
"sUrl": "cn.txt"
}
} );
} );

19、是用ajax源

$(document).ready(function() {
$('#example').dataTable( {
"bProcessing": true,
"sAjaxSource": './arrays.txt'
} );
} );

20、使用ajax，在服务器端整理数据

$(document).ready(function() {
$('#example').dataTable( {
"bProcessing": true,
"bServerSide": true,
"sPaginationType": "full_numbers",

"sAjaxSource": "./server_processing.PHP",
/*如果加上下面这段内容，则使用post方式传递数据
"fnServerData": function ( sSource, aoData, fnCallback ) {
$.ajax( {
"dataType": 'json',
"type": "POST",
"url": sSource,
"data": aoData,
"success": fnCallback
} );
}*/
"oLanguage": {
"sUrl": "cn.txt"
},
"aoColumns": [
{ "sName": "platform" },
{ "sName": "version" },
{ "sName": "engine" },
{ "sName": "browser" },
{ "sName": "grade" }
]//$_GET['sColumns']将接收到aoColumns传递数据
} );
} );

21、为每行加载id和class
服务器端返回数据的格式：

{
"DT_RowId": "row_8",
"DT_RowClass": "gradeA",
"0": "Gecko",
"1": "Firefox 1.5",
"2": "Win 98+ / OSX.2+",
"3": "1.8",
"4": "A"
},

22、为每行显示细节，点击行开头的“+”号展开细节显示

23、选择多行

24、集成jquery插件jEditable

三、遇到的问题

3.1“Cannot reinitialise DataTable.
To retrieve the DataTables object for this table, pass no arguments or see the docs for bRetrieve and bDestroy ”
1、Cannot call method ’fnSetData‘ of undefined

原因：表格中有class为hidden的列，使得内容部分的列数多于表头

2、Data Tables warning（table id = 'xxxx'）:Cannot reinitialize Data Table。

解决办法 使用"bRetrieve": true选项即可

3.2 排序时指定某列不可排序

$(".datatable").dataTable( {  
        "aoColumnDefs": [ { "bSortable": false, "aTargets": [ 0 ] }]  
    });
注意： "bSort": true, //排序功能 要注释掉
3.3 确定选择每页展示个数列表和默认每页展示个数设置
"aLengthMenu": [[4, 10, 20, -1], [4, 10, 20, "所有"]],  
"iDisplayLength":4  