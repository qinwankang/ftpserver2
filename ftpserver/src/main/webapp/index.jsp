<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="plug-in.jsp"></jsp:include>
</head>
<body>     
	<center>
	<form method="post" action="<%=request.getContextPath() %>/ftpserver/uploadmsg.do" enctype="multipart/form-data">
		<input type="submit" value="测试ftp按钮" style="background-color: #B8E7F9;width: 150px;height: 30px;algin:center">
	</form>
	<hr>
		<input type="button" value="测试OSS上传" style="background-color: #B8E7F9;width: 150px;height: 30px;algin:center" onclick="testOSS()">
	<hr>
	<div class="container-fluid">
		<div class="row clearfix">
			<div class="col-md-12  col-xl-12 column">
				<!-- 展示表格 -->
				<table id="stu-table-id"></table>
			</div>	
		</div>
	</div>
	<form id="vip-date-form">
		<label>开始时间：  
		<span style="white-space:pre;"> </span><input  onClick="WdatePicker({lang:'zh-cn',maxDate:'#F{$dp.$D(\'EndTime\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd',skin:'twoer',readOnly:true,isShowClear:false})"  class="Wdate" id="BeginTime">  
		</label>  
		<label>结束时间  
		<span style="white-space:pre;"> </span><input  name="vipdate" onClick="WdatePicker({lang:'zh-cn',minDate:'#F{$dp.$D(\'BeginTime\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',skin:'twoer',readOnly:true,isShowClear:false})" class="Wdate" id="EndTime" >  
		</label>
		<input type="button" onclick="searchEcharsInfo()" value="搜索"/>
	</form>
	 <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    	<div id="echars-demo-id" style="width: 600px;height:400px;"></div>
	</center>
</body>
	<script type="text/javascript">
	$(function(){
		searchEcharsInfo();
	})
	function searchEcharsInfo(){
	//ajax发送请求 ，拿到需要展示的echars的数据
	$.ajax({
		url:"<%=request.getContextPath()%>/redisTest/queryEcharsTest.do",
		type:"post",
		//data:{"nowdate":nowdate},
		data:$("#vip-date-form").serialize(),
		dataType:"json",
		success:function (echarsList){
			var xInfo ;
			var yInfo ;
			xInfo = echarsList.xAxis;
			yInfo = echarsList.series;
			 // 基于准备好的dom，初始化echarts实例
		    var myChart = echarts.init(document.getElementById('echars-demo-id'));
		    // 指定图表的配置项和数据
		    var option = {
		        title: {
		            text: 'ECharts 入门示例'
		        },
		        tooltip: {},
		        legend: {
		            data:['销量']
		        },
		        xAxis: {
		        	type: 'category',
		            boundaryGap: false,
		            data: xInfo
		        },
		        yAxis: {type: 'value',},
		        series: [{
		            type: 'line',
		            symbol:'none',  //这句就是去掉点的  
		            smooth:true, 
		            data:yInfo,
		            areaStyle: {}
		        }]
		    };
		    
		    // 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
			
		},
		error:function (){
			alert("初始化加载展示的echars的数据");
		}	
	})
	
	}
    
    //my97时间设置
    function initDate(){  
        //设置结束日期为当前日期  
        var date = new Date();  
        var seperator1 = "-";  
        var month = date.getMonth() + 1;  
        var strDate = date.getDate();  
        if(month >= 1 && month <= 9) {  
            month = "0" + month;  
        }  
        if(strDate >= 0 && strDate <= 9) {  
            strDate = "0" + strDate;  
                }  
        var end = date.getFullYear() + seperator1 + month + seperator1 + strDate;  
        $("#EndTime").val(end);  
                  
         //设置开始日期为当前日期的前一个月  
        date.setMonth(date.getMonth()-1);  
        var begin = date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();  
        $("#BeginTime").val(begin);  
    }  
    initDate();  
    //测试OSS
	function testOSS(){
		location.href="<%=request.getContextPath() %>/ftpserver/uploadOSS.do";
	}
	
	<%-- $(function(){
	    $("#stu-table-id").bootstrapTable({
	    	url:'<%=request.getContextPath()%>/redisTest/queryStuList.do',
	        striped: true,//隔行变色
	        showColumns:false,//是否显示 内容列下拉框
	        showPaginationSwitch:false,//是否显示 数据条数选择框
	        minimumCountColumns:1,//最小留下一个
	        showRefresh:false,//显示刷新按钮
	        showToggle:false,//显示切换视图
			search:false,//是否显示搜索框
	        searchOnEnterKey:true,//设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
	         sidePagination:"server",//
	         method:'post',//发送请求的方式
	         contentType:"application/x-www-form-urlencoded",//必须的否则条件查询时会乱码
	       /*   queryParams:function(data){
	                return {
	                    roomno:data.search,
	                    page:this.pageNumber,//当前页
	                    rows:this.pageSize //每页条数
	                 }
	           }, */
	        columns: [
	         {
	            field: 'stuid',
	            title: '编号',
	            idField:true,
	            width: 100
	        },{
	            field: 'stuname',
	            title: '学生姓名',
	            width: 100
	        },{
	            field: 'stuage',
	            title: '学生年龄',
	            width: 100
	           
	        },{
	            field: 'act',
	            title: '操作',
	            width: 100,
	            formatter : function(value,row,index){
	        		return "<a href='javascript:deleteStu("+row.id+")'>删除</a>";
	            }
	        }]
	    })
	}) --%>
	
	</script>
</html>