<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="zh-CN"><head>
    <meta charset="utf-8">
    <title>旅游管理系统</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<c:import url="importcommon.jsp"></c:import>
</head>
<body class=" theme-blue">

    <script type="text/javascript">
        $(function() {
            var match = document.cookie.match(new RegExp('color=([^;]+)'));
            if(match) var color = match[1];
            if(color) {
                $('body').removeClass(function (index, css) {
                    return (css.match (/\btheme-\S+/g) || []).join(' ')
                })
                $('body').addClass('theme-' + color);
            }

            $('[data-popover="true"]').popover({html: true});
            
        });
    </script>
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .navbar-default .navbar-brand, .navbar-default .navbar-brand:hover { 
            color: #fff;
        }
    </style>

    <script type="text/javascript">
        $(function() {
            var uls = $('.sidebar-nav > ul > *').clone();
            uls.addClass('visible-xs');
            $('#main-menu').append(uls.clone());
        });
    </script>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
   
  <!--<![endif]-->

    <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
          <span class="navbar-brand"> 旅游管理系统</span>
        </div>

        <div class="navbar-collapse collapse" style="height: 1px;">
          <ul id="main-menu" class="nav navbar-nav navbar-right">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user padding-right-small" style="position:relative;top: 3px;"></span> ${ user.username }
                    <i class="fa fa-caret-down"></i>
                </a>

              <ul class="dropdown-menu">
                <li><a tabindex="-1" href="${ url_editUser }">修改资料</a></li>
                <li class="divider"></li>
                <li><a tabindex="-1" href="${ url_logout }">注销</a></li>
              </ul>
            </li>
          </ul>

        </div>
    </div>
    

    <div class="sidebar-nav">
	    <ul>
		    <li><a data-target=".hotel-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">宾馆管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="hotel-menu nav nav-list collapse in">
		            <li onclick="hotel();"><a><span class="fa fa-caret-right"></span>宾馆信息管理</a></li>
		            <li onclick="hoteltype();"><a><span class="fa fa-caret-right"></span>宾馆类型管理</a></li>
			    </ul>
		    </li>
		    <li><a data-target=".restaurant-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">餐饮管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="restaurant-menu nav nav-list collapse in">
		            <li onclick="restaurant();"><a><span class="fa fa-caret-right"></span>饭店信息管理</a></li>
		            <li onclick="restauranttype();"><a><span class="fa fa-caret-right"></span>饭店类型管理</a></li>
			    </ul>
		    </li>
		    <li><a data-target=".bus-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">车辆管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="bus-menu nav nav-list collapse in">
		            <li onclick="bus();"><a><span class="fa fa-caret-right"></span>车辆信息管理</a></li>
			    </ul>
		    </li>
		    <li><a data-target=".guide-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">导游管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="guide-menu nav nav-list collapse in">
		            <li onclick="guide();"><a><span class="fa fa-caret-right"></span>导游信息管理</a></li>
			    </ul>
		    </li>
		    <li><a data-target=".line-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">线路管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="line-menu nav nav-list collapse in">
		            <li onclick="linedetail();"><a><span class="fa fa-caret-right"></span>线路规划管理</a></li>
		            <li onclick="innerquote();"><a><span class="fa fa-caret-right"></span>线路报价管理</a></li>
			    </ul>
		    </li>
	    </ul>
    </div>

    <div class="content">
    	<div class="header">
            
            <h1 class="page-title">
            	<button onclick="back();" class="btn btn-default">&#xe611;返回</button>线路日程安排
            </h1>

        </div>
        <div class="main-content">
            
			<div class="btn-toolbar list-toolbar">
				<div>
	            	<table class="table" style="text-align:center;">
	            		<tr>
	            			<td width="10%" align="right" style="border-top:none;">线路名称</td>
	            			<td align="left" style="border-top:none;">${ linename }</td>
	            		</tr>
	            		<tr>
	            			<td align="right" style="border-top:none;">天数</td>
	            			<td align="left" style="border-top:none;"><span id="day">${ day }</span></td>
	            		</tr>
	            	</table>
            	</div>
            	<div align="right">
			    	<button id = "addSchedule" class="btn btn-primary"><i class="fa fa-plus"></i>&nbsp;添加日程安排</button>
		  		</div>
		  		<div class="btn-group">
		  		</div>
			</div>
			
			  <div id="schedulesTable">
			  <script id="schedulesTemplateView" type="text/html">
			  <table class="table" style="text-align:center;">
			  <thead>
			    <tr>
			      <th style="width:3%;text-align: center;">天数</th>
			      <th style="width:10%;text-align: center;">宾馆</th>
			      <th style="width:10%;text-align: center;">早饭</th>
			      <th style="width:10%;text-align: center;">午饭</th>
			      <th style="width:10%;text-align: center;">晚饭</th>
			      <th style="width:10%;text-align: center;">用车</th>
			      <th style="width:25%;text-align: center;">备注</th>
			      <th style="width:10%;text-align: center;">操作</th>
			    </tr>
			  </thead>
			  <tbody>
			  {{ each schedules as schedule i }}
			    <tr>
			      <td>{{schedule.day}}</td>
			      <td>{{schedule.hotel}}</td>
			      <td>{{schedule.morestaurant}}</td>
			      <td>{{schedule.lurestaurant}}</td>
			      <td>{{schedule.direstaurant}}</td>
			      <td>{{schedule.bus}}</td>
			      <td>{{schedule.remark}}</td>
				  <td><a class="deleteModelBtn" scheduleid="{{schedule.id}}"><i class="fa fa-trash-o"></i></a></td>
			    </tr>
			  {{ /each }}
			  </tbody>
			</table>
			</script>
			</div>

			<div class="modal small fade" id="addScheduleModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog" style="width:800px;">
			    <div class="modal-content">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalLabel">添加日程安排</h3>
			        </div>
			        <div class="modal-body">
			            <table class="table" style="text-align:center;">
		            		<tr>
		            			<td align="left" style="border-top:none;">
		            				第<input type="text" id="dday" class="form-control">天
		            			</td>
		            			<td style="border-top:none;"></td>
		            			<td style="border-top:none;"></td>
		            			<td style="border-top:none;"></td>
		            		</tr>
		            		<tr>
		            			<td width="10%" align="right" style="border-top:none;">住宿</td>
		            			<td width="40%" align="left" style="border-top:none;">
		            				<select id="hotelid" class="form-control" ">
		            					  <option value="" cost="" checked="checked">请选择</option>
						        	</select>
					        	<td width="10%" align="right" style="border-top:none;">用车</td>
						        <td width="40%" align="left" style="border-top:none;">
		            				<select id="busid" class="form-control" ">
		            					  <option value="" cost="" checked="checked">请选择</option>
						        	</select>
		            			</td>
		            		</tr>
		            		<tr>
		            			<td width="10%" align="right" style="border-top:none;">早饭</td>
		            			<td width="40%" align="left" style="border-top:none;">
		            				<select id="morestaurantid" class="form-control" ">
		            					  <option value="" cost="" checked="checked">请选择</option>
						        	</select>
					        	<td width="10%" align="right" style="border-top:none;">午饭</td>
						        <td width="40%" align="left" style="border-top:none;">
		            				<select id="lurestaurantid" class="form-control" ">
		            					  <option value="" cost="" checked="checked">请选择</option>
						        	</select>
		            			</td>
		            		</tr>
		            		<tr>
		            			<td align="right" style="border-top:none;">晚饭</td>
		            			<td align="left" style="border-top:none;">
		            				<select id="direstaurantid" class="form-control" ">
		            					  <option value="" cost="" checked="checked">请选择</option>
						        	</select>
		            			</td>
		            			<td style="border-top:none;"></td>
		            			<td style="border-top:none;"></td>
		            		</tr>
		            		<tr>
		            			<td align="right" style="border-top:none;">备注</td>
		            			<td align="left" style="border-top:none;">
		            				<textarea id="remark" class="form-control" ></textarea>
		            			</td>
		            			<td style="border-top:none;"></td>
		            			<td style="border-top:none;"></td>
		            		</tr>
		            	</table>
			        </div>
			        <div class="modal-footer">
			            <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			            <button class="btn btn-danger addScheduleBtn" data-dismiss="modal">确定</button>
			        </div>
			      </div>
			    </div>
			</div>
			

			<div class="modal small fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalLabel">删除日程安排</h3>
			        </div>
			        <div class="modal-body">
			            <p class="error-text"><i class="fa fa-warning modal-icon"></i>确定删除该日程安排信息?<br>操作不可恢复。</p>
			        </div>
			        <div class="modal-footer">
			            <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			            <button id="deleteBtn" class="btn btn-danger" data-dismiss="modal">删除</button>
			        </div>
			      </div>
			    </div>
			</div>
			
        </div>
    </div>
	<input type="hidden" id = "currentPage" value="1" />
	<input type="hidden" id = "linedetailid" value="${ linedetailid }" />

    <script type="text/javascript">
	    $(function(){
			
	    	var schedules = ${schedules};
	    	var SchedulesViewHtml = template("SchedulesTemplateView", schedules);
	    	$("#SchedulesTable").html(SchedulesViewHtml);
	    	
	    	var listHotel = ${listHotel};
	    	for (i = 0; i < listHotel.length; i++) {
	    		$('#hotelid').append('<option value="' + listHotel[i].id + '" cost="' + listHotel[i].cost + '">'+ listHotel[i].hoteltype + '</option>');
    		} 
	    	var listBus = ${listBus};
	    	for (i = 0; i < listBus.length; i++) {
	    		$('#busid').append('<option value="' + listBus[i].id + '" cost="' + listBus[i].cost + '">' + listBus[i].name + '</option>');
    		} 
	    	
	    	var listRestaurant = ${listRestaurant};
	    	for (i = 0; i < listRestaurant.length; i++) {
	    		$('#morestaurant').append('<option value="' + listRestaurant[i].id + '" cost="' + listRestaurant[i].cost + '">'+ listRestaurant[i].restaurant + '</option>');
	    		$('#lurestaurant').append('<option value="' + listRestaurant[i].id + '" cost="' + listRestaurant[i].cost + '">'+ listRestaurant[i].restaurant + '</option>');
	    		$('#direstaurant').append('<option value="' + listRestaurant[i].id + '" cost="' + listRestaurant[i].cost + '">'+ listRestaurant[i].restaurant + '</option>');
    		} 
	    	
	    	$("#addSchedule").click(function(){
		    	$("#addSchedulModel").modal("show");
	    	});

	    	// 后面的还没有修改
	    	$(".addLineDetailBtn").on("click", function(){
	    		var lineid = $.trim($("#llineid").val());
	    		var guideid = $.trim($("#guideid").val());
	    		var govehicleid = $.trim($("#govehicleid").val());
	    		var backvehicleid = $.trim($("#backvehicleid").val());
	    		var insurance = $.trim($("#insurance").val());
	    		var remark = $.trim($("#remark").val());
	    		var gocost = $("#govehicleid").find("option:selected").attr("cost");
	    		var backcost = $("#backvehicleid").find("option:selected").attr("cost");
	    		var guidecost = $("#guideid").find("option:selected").attr("cost");
	    		
	    		if (lineid == null || lineid == '') {
	    			alert("请选则线路");
	    			return ;
	    		}
	    		if (guideid == null || guideid == '') {
	    			alert("请选则导游");
	    			return ;
	    		}
	    		if (govehicleid == null || govehicleid == '') {
	    			alert("请选择去时交通");
	    			return ;
	    		}
	    		if (backvehicleid == null || backvehicleid == '') {
	    			alert("请选择回时交通");
	    			return ;
	    		}
    			if (insurance == null || insurance == '') {
	    			alert("保险金额不能为空");
	    			return ;
	    		} else {
	    			var partn =/^[0-9]{0}([0-9]|[.])+$/; 
	    			if (!partn.test(insurance)){
	    				alert("金额只允许输入数字和小数点");
		    			return ;
	    			}
	    		}
	    		
	    		$.ajax({
	    			url : "${ctx}/linedetail/addlinedetail.html",
	    			async : false,
	    			type : 'POST',
	    			cache:false,
	    			data : {
	    				lineid : lineid,
	    	    		guideid : guideid,
	    	    		govehicleid : govehicleid,
	    	    		backvehicleid : backvehicleid,
	    	    		insurance : insurance,
	    	    		remark : remark,
	    	    		gocost : gocost,
	    	    		backcost : backcost,
	    	    		guidecost : guidecost
	    			},
	    			dataType : 'json',
	    			timeout : 15000,
	    			beforeSend : function() {
    	    			$("#addLineDetailModel").modal('hide');
	    			},
	    			complete : function(XMLHttpRequest,textStatus) {
	    				
	    				
	    			},
	    			success : function(response) {
	    				var json = eval(response);
	    				if (0===json.status){
	    					alert("添加成功");
	                    } else if (1===json.status){
	                        alert(json.message);
	                    }
	    				window.location.reload();
	    			},
	    			error : function(XMLHttpRequest, textStatus, errorThrown) {
	    				alert("系统错误！status:[" + XMLHttpRequest.status + "]errorThrown:]" + errorThrown + "]");
	    				window.location.reload();
	    			}
	    		});
	    	});
	    	
        $("[rel=tooltip]").tooltip();
    /*     $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        }); */
        $(".deleteModelBtn").on("click", function(){
    		var id = $(this).attr("linedetailid");
    		$("#linedetailid").val(id);
    		$("#deleteModal").modal('show');
    	});
    	
		$("#deleteBtn").click(function(){
    		
    		var id = $("#linedetailid").val();
    		
    		$.ajax({
    			url : "${ctx}/linedetail/deletelinedetail.html",
    			async : false,
    			type : 'POST',
    			cache:false,
    			data : {
    				id : id
    			},
    			dataType : 'json',
    			timeout : 15000,
    			beforeSend : function() {
    	    		$("#deleteModal").modal('hide');
    			},
    			complete : function(XMLHttpRequest,textStatus) {
    			},
    			success : function(response) {
    				var json = eval(response);
    				if (0===json.status){
    					alert("删除成功！")
                    } else if (1===json.status){
                        alert(json.message);
                    }
    				window.location.reload();
    			},
    			error : function(XMLHttpRequest, textStatus, errorThrown) {
    				alert("系统错误！status:[" + XMLHttpRequest.status + "]errorThrown:]" + errorThrown + "]");
    				window.location.reload();
    			}
    		});
	});
    });
	    
	    
	    
	function dosearch(i){
    		

		var lineid = $.trim($("#lineid").val());
    	
    	if (i == null || i == '') {
    		i = 0;
    	}
    	if (i == -1) {
    		i = parseInt($("#currentPage").val())-1;
    	}
    	if (i == -2) {
    		i = parseInt($("#currentPage").val())+1;
    	}
   		$.ajax({
   			url : "${ctx}/linedetail/search.html",
   			async : false,
   			type : 'POST',
   			cache:false,
   			data : {
   				currentPage : i,
   				lineid : lineid
   			},
   			dataType : 'json',
   			timeout : 15000,
   			beforeSend : function() {
   			},
   			complete : function(XMLHttpRequest,textStatus) {
				$(".deleteModelBtn").on("click", function(){
		    		var id = $(this).attr("linedetailid");
		    		$("#linedetailid").val(id);
		    		$("#deleteModal").modal('show');
		    	});
				
   			},
   			success : function(response) {
   				var json = eval(response);
   				if (0===json.status){
   					
   					var result = json.result; 
   					
   			    	var arrayObj = new Array(result.totalPage);
   			    	for (var j=0; j<result.totalPage; j++){
   			    		arrayObj[j] = j;
   			    	}
   			    	data = {
   			    			lineDetails : result.pageInfoResult,
   			    			length : result.totalRecord,
   			    			currentPage : result.currentPage,
   			    			totalPage : result.totalPage,
   			    			list : arrayObj
   			    	};
   			    	var lineDetailsViewHtml = template("lineDetailsTemplateView", data);
   			    	$("#lineDetailsTable").html(lineDetailsViewHtml);
   					
		    		$("#currentPage").val(i);
                   } else if (1===json.status){
                       alert(json.message);
      				   window.location.reload();
                   }
   			},
   			error : function(XMLHttpRequest, textStatus, errorThrown) {
   				alert("系统错误！status:[" + XMLHttpRequest.status + "]errorThrown:]" + errorThrown + "]");
   				window.location.reload();
   			}
   		});
   	}
	
	function hoteltype(){
		window.location.href = "${ctx}/hoteltype/index.html";
    }
	
	function restaurant(){
		window.location.href = "${ctx}/restaurant/index.html";
    }
	function restauranttype(){
		window.location.href = "${ctx}/restauranttype/index.html";
    }
	function bus(){
		window.location.href = "${ctx}/bus/index.html";
    }
	function guide(){
		window.location.href = "${ctx}/guide/index.html";
    }
	function hotel(){
		window.location.href = "${ctx}/hotel/index.html";
    }
	function innerquote(){
		window.location.href = "${ctx}/innerquote/index.html";
    }
	function linedetail(){
		window.location.href = "${ctx}/linedetail/index.html";
	}
	
</script>
    
  
</body></html>
