什么是Freemarker
Freemarker是一款模板引擎
Freemarker不是Web框架

数据模型+模板=输出(HTML)
Java Object
...
name = "Jef"

Templete File
<html>
Hello,${name}
</html>

通过Freemarker

Output
<html>
Hello,Jef
</html>

前端设计师和程序员的学习侧重点
MVC设计（Model、View、Controler）
前端设计师侧重于View(模板设计)
后端程序员需要全面掌握MVC

如何开始？
一点心得
先划一个范围
再定一个目标
创建可行计划
边玩边学

Maven构建Sprign+Freemarker项目
Maven以来Spring和Freemarker的jar包
Spring配置文件和Freemarker Servlet配置文件


配置文件介绍
Spring配置文件介绍:applicationContext.xml
Spring Freemarker配置文件：spring-servlet.xml


运行实例：列表展示



一、对象判读与显示
两种方式，一种是通用插值，另外一种是数字格式化插值，比较常用的是通用插值
freemarker中显示某对象使用${name}.
但如果name为null，freemarker就会报错。如果需要判断对象是否为空：
<#if name??>
……
</#if>
当然也可以通过设置默认值${name!''}来避免对象为空的错误。如果name为空，就以默认值（"!"后的字符）显示。
对象user，name为user的属性的情况，user，name都有可能为空，那么可以写成${(user.name)!''}，表示user或者name为null，都显示为空。判断为空
<#if (user.name)??>
……
</#if>

对于null，或者miss value，freemarker会报错
<#if letVo.manageScore != "">  
   ${html("${(letVo.manageScore)!''}")}   
</#if> 
当letVo有值，manageScore为""时（不是null），必须使用<#if letVo.manageScore!="">来判读，之前在项目中使用了<#if letVo.manageScore??> 结果
 ${html("${(letVo.manageScore)!''}")} 页面一样
会显示，使用 ${html('${(letVo.manageScore)!"0"}')}时，页面也不会显示0，主要是原因如下：
!：default value operator，语法结构为：unsafe_expr!default_expr，比如 ${mouse!"No mouse."} 当mouse不存在时，返回default value；
而product.color!"red"将只处理color为miss value的情况，如果是这样的写法，那么在 product 中，当 color 不存在时（返回 ”red” ）将会
被处理， 但是如果连 product 都不存在时将不会处理。 也就是说这样写时变量 product必须存在，否则模板就会报错。
(product.color)!"red" 这种方式，能够处理product或者color为miss value的情况；这时，如果当不存在时也会被处理，那就是说如果 product 不存在或者 product
存在而 color 不存在，都能显示默认值 ”red” 而不会报错。本例和上例写法的重要区别在于用括号时，就允许其中表达式的任意部分可以未定义。
??: Missing value test operator ,测试是否为missing value
unsafe_expr?? ：product.color??将只测试color是否为null
(unsafe_expr)??：(product.color)??将测试product和color是否存在null
?exists:旧版本的用法
比如：<#if mouse??>
Mouse found
<#else>
No mouse found
</#if>
Creating mouse...
<#assign mouse = "Jerry">
<#if mouse??>
Mouse found
<#else>
No mouse found
</#if>

<#--加个感叹号可以解决为空的问题-->
${(emp.group)!}
<#--加上括号，感叹号解决对象导航为空的问题-->
${(emp.group.name)!"group为空或者name为空"}
<br/>
<#--感叹号还可以解决未定义为空的问题-->
${(a.b)!("a.b未定义")}
<br/>
<#--(a.b)?? 判断a.b是否为空-->
<#if (a.b)??>
    不为空
    <#else>
    为空
</#if>
<br/>
${(a.b)???string}



二、判断list是否为空以及list的使用
<#if orgList?? && (orgList?size > 0) >
<#list orgList as org>
<!-- list中 对象_index 表示该对象的位置，位置从1开始 -->
<!-- list中 对象_has_next 表示是否还有下一个对象，如果有为true，否则为false -->
<#if org_index % 2 == 1>
</#if>
</#list>
<#else>
     <li class="T-creat">您还没有企业，请创建新企业<a href="javascript:void(0)" id="add-org-a" >立即创建</a></li>
</#if>


三、数字的处理
方法一
    #{number ;m1M2}    m 最少几位小数，M最多几位小数
eg：保留两位小数：<b>¥</b><em>#{goods.spCost! ;m2M2}</em>  原本是${goods.spCost!''}，在这里去掉$了。

方法二
  ${x?string("0.##")}    
 --- ${(ccgood.spCost!)?string('#.##')}

在开发中很容易忽视一点，输入一个值(可能是小数)，输出时如果不做处理，就很容易出现 
隐形的BUG。比如，如果从数据库取出一个0.22的数值，一般的输出${x?if_exists?html}, 
这时是显示0，而不是0.22。 
应该写成${x?if_exists?string.number} 或者 ${x?if_exists.toString()?html} 

freemarker数字格式化可以在两个地方设置，一个是全局的，即在freemarker.properties文件中设置number_format，还有一个是使用string指令来控制数据的输出格式。例如：

${num?string('0.00')}
如果小数点后不足两位，用 0 代替（一定是两位小数）

${num?string('#.##')}
如果小数点后多于两位，就只保留两位，否则输出实际值（自由，规范，实用性强）
输出为：1239765.46

${num?string(',###.00')}
输出为：1,239,765.46
整数部分每三位用 , 分割，并且保证小数点后保留两位，不足用 0 代替（用于银行等金额展示，统一性强）

${num?string(',###.##')}
输出为：1,239,765.46
整数部分每三位用 , 分割，并且小数点后多余两位就只保留两位，不足两位就取实际位数，可以不不包含小数点

${num?string('000.00')}
输出为：012.70
整数部分如果不足三位（000），前面用0补齐，否则取实际的整数位

${num?string('###.00')}
等价于
${num?string('#.00')}
输出为：12.70
整数取实际的位数

1. c 用于将数字转换为字符串  
${123?c} 结果为123  
 
2. string 用于将数字转换为字符串  
Freemarker 中预订义了三种数字格式：number,currency（货币）和percent(百分比)其中number为默认的数字格式转换
例如：   
<#assign tempNum=20>
${tempNum}   
${tempNum? string .number}或${tempNum? string ("number")} 结果为20  
${tempNum? string .currency}或${tempNum? string ("currency")} 结果为￥20.00  
${tempNum? string .percent}或${tempNum? string ("percent")} 结果为2,000%  

3.对于数字 int 取得数字的整数部分（如-1.9?int的结果是-1）

1， 小于1的数字在freemarker中相减错误
2，1000，2000这类的数字在freemarker中的格式错误，会被解释成1,000
解决方法设置number在freemarker的显示格式
<</span>#setting number_format="0.00">  
    <</span>#assign x="${goodsgroupt.memberprice}"/>  
    <</span>#assign y="${goodsgroupt.groupprice}"/>  
    ${(x?number-y?number)}  
重点就是这个number_format了
网上说让number_format="0"，这样只能解决第二种情况。

局部设置
特点：
如果在某个页面，或者某几个页面（其它页面可import进来），用到的数字格式化是相同的，可考虑统一配置数字的格式化形式
用法：
只需在用到需要格式化的数字之前 设置就可以了，格式如下： <#setting number_format=",##0.##"> 其中",##0.##"的内容是采用类似于Java中数字格式的语法形式，也就是以上的第三点。
注意：
如果用在一个页面，则整个页面默认就是那种格式，除非用string函数覆盖了默认的格式，同样的，如果放在一个公共的页面，其它页面只要include它，也会是同样的格式。
 用法：
 <#include "/admin/***.ftl" />
 导入其他页面元素 <#include filename options> options包含两个属性 encoding=”GBK” 编码格式 parse=true 是否作为ftl语法解析,默认是true，false就是以文本方式引入.注意在ftl文件里布尔值都是直接赋值的如parse=true,而不是

全局设置
特点：
在所有的页面都默认提供数字的格式化形式
用法
（针对spring）：在freemarker的配置文件中设置其默认的数字格式化形式，如下：
代码如下复制代码<property name = "freemarkerSettings" >
<props>
.....
<prop key = "number_format"> 0.## </prop >
......
</props>
</property>

FreeMarker类型转换：


四、操作字符串函数  
1. substring（start,end）从一个字符串中截取子串  
start:截取子串开始的索引，start必须大于等于0，小于等于end
end: 截取子串的长度，end必须大于等于0，小于等于字符串长度，如果省略该参数，默认为字符串长度。
例子：
${"str"?substring(0)} 结果为str  
${"str"?substring(1)} 结果为tr  
${"str"?substring(2)} 结果为r  
${"str"?substring(3)} 结果为  
 
${"str"?substring(0,0)} 结果为  
${"str"?substring(0,1)} 结果为s  
${"str"?substring(0,2)} 结果为st  
${"str"?substring(0,3)} 结果为str  
 
 
2. cap_first 将字符串中的第一个单词的首字母变为大写。  
${"str"？cap_first} 结果为Str  
 
3. uncap_first将字符串中的第一个单词的首字母变为小写。  
${"Str"？uncap_first} 结果为str  
 
4. capitalize将字符串中的所有单词的首字母变为大写  
${"str"？ capitalize} 结果为STR  
 
5. date,time，datetime将字符串转换为日期
<#assign>作用：
声明变量插入  
例如：
<#assign date1="2009-10-12"?date("yyyy-MM-dd")>
<#assign date2="9:28:20"?time("HH:mm:ss")>
<#assign date3=" 2009-10-12 9:28:20"?time("HH:mm:ss")>
 
${date1} 结果为2009-10-12  
${date2} 结果为9:28:20  
${date3} 结果为2009-10-12 9:28:20  
也可以
${date1?string('yyyy-MM-dd')}
 
注意：如果指定的字符串格式不正确将引发错误。
 
6. ends_with 判断某个字符串是否由某个子串结尾，返回布尔值。  
${"string "?ends_with("ing")?string } 返回结果为true
注意：布尔值必须转换为字符串才能输出
 
7. html 用于将字符串中的<、>、&和"替换为对应得&lt;&gt;&quot:&amp  
 
8. index_of(substring,start)在字符串中查找某个子串，返回找到子串的第一个字符的索引，如果没有找到子串，则返回-1。  
  Start参数用于指定从字符串的那个索引处开始搜索，start为数字值。
  如果start大于字符串长度，则start取值等于字符串长度，如果start小于0， 则start取值为0。
  ${" string "?index_of("in") 结果为3  
${" string "?index_of("ab") 结果为-1  
 
9. length返回字符串的长度 ${" string "?length} 结果为6  
 
10. lower_case将字符串转为小写  
${" STRING "?lower_case} 结果为 string  
 
11. upper_case将字符串转为大写  
${" string "?upper_case} 结果为 STRING  
 
12. contains 判断字符中是否包含某个子串。返回布尔值  
  ${" string "?contains("ing")? string } 结果为true  
  注意：布尔值必须转换为字符串才能输出
 
13. number将字符串转换为数字  
${"111.11"?number} 结果为111.11  
 
14. replace用于将字符串中的一部分从左到右替换为另外的字符串。  
${"strabg"?replace("ab","in")} 结果为 string  
 
15. split使用指定的分隔符将一个字符串拆分为一组字符串  
<#list "This|is|split"?split("|") as s>
  ${s}
</#list>
结果为:
This  
is
split
 
16. trim 删除字符串首尾空格 ${" String "?trim} 结果为 String  
 
 
五、 操作布尔值  
 
string 用于将布尔值转换为字符串输出
true转为"true"，false转换为"false"
 
foo?string ("yes","no")如果布尔值是true,那么返回"yes",否则返回no


六、其他
1、大小比较符号使用需要注意:(xml的原因),可以用于比较数字和日期 使用lt、lte、gt和gte来替代<、<=、>和>= 也可以使用括号<#if (x>y)>
2、逻辑判断: if................
<#if condition>... <#elseif condition2>... <#elseif condition3>...... <#else>... Boolean类型的空值判断 空值判断可以写成<#if book.name?? > //注意${}为变量的渲染显示,而<>为定义等操作符的定义
witch............ <#switch value> <#case refValue1> ... <#break> <#case refValue2> ... <#break> ... <#case refValueN> ... <#break> <#default> ... </#switch>
3、对于Sequences(序列) size－获得序列中元素的数目，集合长度判断 <#if student?size != 0></#if> 判断=的时候,注意只要一个=符号,而不是==
4、宏/模板 初步了解: 使用更像一个闭包closure,可以定义后,在脚本中任意地方引用,并原地起作用 <#macro greet> <font size="+2">Hello Joe!</font> </#macro> 使用的方式为: <@greet></@greet> //同xml可以简写成<@greet/>
5、外部导入的使用,可以用于模块化,并且提供公用性 如:lib/my_lib.ftl文件 <#macro copyright date> <p>Copyright (C) ${date} Julia Smith. All rights reserved. <br>Email: ${mail}</p> </#macro> <#assign mail = "jsmith@acme.com">
lib/my_inc.ftl文件 <#import "/lib/my_test.ftl" as my> <#assign mail="fred@acme.com"> <@my.copyright date="1999-2002"/> ${my.mail} ${mail} 输出结果将不会出现冲突
对于库中的变量修改,使用in关键字 <#assign mail="jsmith@other.com" in my>
函数定义:区别于宏对象,带返回值 <#function name param1 param2><#return val></#function>函数，有返回参数
6、hash与list的定义 <#assign c= {"a":"orz","b":"czs"}> ${c.a}
7、List片段可以采用： products[10..19] or products[5..] 的格式进行定义,当只局限于数字 <#assign c= [1,2,3,4,5,6,6,7]> <#list c[1..3] as v> ${v} </#list>
8、用compress directive或者transform来处理输出。 <#compress>...</#compress>：消除空白行。 <@compress single_line=true>...</@compress>将输出压缩为一行。都需要包裹所需文档
freemarker可用"["代替"<".在模板的文件开头加上[#ftl].
注释部分 <#-- 注释部分 -->
9、oparse指令指定FreeMarker不处理该指定里包含的内容,该指令的语法格式如下: <#noparse>...</#noparse>
10、定义全局变量的方式 <#assign name1=value1 name2=value2 / > // 可以同时定义多个变量,也可以使用循环来给变量赋值 <#assign x> <#list ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"] as n> ${n} </#list> </#assign> ${x}
11、etting指令,用于动态设置freeMarker的运行环境:
该指令用于设置FreeMarker的运行环境,该指令的语法格式如下:<#setting name=value>,在这个格式中,name的取值范围包含如下几个: locale:该选项指定该模板所用的国家/语言选项 number_format:指定格式化输出数字的格式 boolean_format:指定两个布尔值的语法格式,默认值是true,false date_format,time_format,datetime_format:指定格式化输出日期的格式 time_zone:设置格式化输出日期时所使用的时区
<#return> 用于退出宏的运行
?html 用于将字符串中可能包含的html字符,进行过滤.
调用Java方法,需要使用实现TemplateMethodModel接口,但是好像会覆盖掉属性的访问