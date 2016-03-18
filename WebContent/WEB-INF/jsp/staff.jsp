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
		    <li><a data-target=".vehicle-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">交通信息管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="vehicle-menu nav nav-list collapse in">
		            <li onclick=""><a><span class="fa fa-caret-right"></span>交通信息管理</a></li>
			    </ul>
		    </li>
		    <li><a data-target=".ticket-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">门票信息管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="ticket-menu nav nav-list collapse in">
		            <li onclick=""><a><span class="fa fa-caret-right"></span>门票信息管理</a></li>
			    </ul>
		    </li>
	    </ul>
    </div>

    <div class="content">
    	<div class="header">
            
            <h1 class="page-title">交通信息管理</h1>

        </div>
        <div class="main-content">
            
			<div class="btn-toolbar list-toolbar">
				<div>
	            	<table class="table" style="text-align:center;">
	            		<tr>
	            			<td width="10%" align="right" style="border-top:none;">始发地:</td>
	            			<td width="30%" align="left" style="border-top:none;">
	            				<select id="oprovince" class="form-control" ">
	            					  <option value="" checked="checked">请选择</option>
					        	</select>
				        	<td width="30%" align="left" style="border-top:none;" onchange="doOcity(this.selectedIndex);">
					        	<select id="ocity" class="form-control">
	            					  <option value="" checked="checked">请选择</option>
					        	</select>
					        </td>
					        <td width="30%" align="left" style="border-top:none;">
					        	<select id="oarea" class="form-control">
	            					  <option value="" checked="checked">请选择</option>
					        	</select>
	            			</td>
	            		</tr>
	            		<tr>
	            			<td width="10%" align="right" style="border-top:none;">目的地:</td>
	            			<td width="30%" align="left" style="border-top:none;">
	            				<select id="dprovince" class="form-control" onchange="doDprovince(this.selectedIndex);">
	            					  <option value="" checked="checked">请选择</option>
					        	</select>
				        	<td width="30%" align="left" style="border-top:none;" onchange="doDcity(this.selectedIndex);">
					        	<select id="dcity" class="form-control">
	            					  <option value="" checked="checked">请选择</option>
					        	</select>
					        </td>
					        <td width="30%" align="left" style="border-top:none;">
					        	<select id="darea" class="form-control">
	            					  <option value="" checked="checked">请选择</option>
					        	</select>
	            			</td>
	            		</tr>
	            		
	            		<tr>
	            			<td width="10%" align="right" style="border-top:none;">交通类别:</td>
	            			<td align="left" style="border-top:none;">
	            				<select id="vehicletype" class="form-control">
	            					  <option value="" checked="checked">请选择</option>
						              <option value="1">汽车</option>
						              <option value="2">火车</option>
						              <option value="3">飞机</option>
						              <option value="4">轮船</option>
					        	</select>
	            			</td>
	            			<td style="border-top:none;"></td>
	            			<td style="border-top:none;"></td>
	            		</tr>
	            		<tr>
	            			<td rowspan="4" align="right" style="border-top:none;">
	            				<button onclick = "getTypeUsers(-1,1);" class="btn btn-default">查询</button>
	            			</td>
	            		</tr>
	            	</table>
            	</div>
			    <button id = "addVehicle" class="btn btn-primary"><i class="fa fa-plus"></i>&nbsp;添加交通信息</button>
		  		<div class="btn-group">
		  		</div>
			</div>
			
			  <div id="vehiclesTable">
			  <script id="vehiclesTemplateView" type="text/html">
			  <table class="table" style="text-align:center;">
			  <thead>
			    <tr>
			      <th style="width:3%;text-align: center;">#</th>
			      <th style="width:25%;text-align: center;">始发地</th>
			      <th style="width:25%;text-align: center;">目的地</th>
			      <th style="width:10%;text-align: center;">类别</th>
			      <th style="width:10%;text-align: center;">金额</th>
			      <th style="width:25%;text-align: center;">备注</th>
			    </tr>
			  </thead>
			  <tbody>
			  {{ each vehicles as vehicle i }}
			    <tr>
			      <td>{{i + 1}}</td>
			      <td>{{vehicle.origin}}</td>
			      <td>{{vehicle.destination}}</td>
			      <td>{{vehicle.type}}</td>
			      <td>{{vehicle.cost}}元</td>
			      <td>{{vehicle.remark}}</td>
			    </tr>
			  {{ /each }}
			  </tbody>
			</table>
			
			{{if length != 0}}
			<div align = "right">
			<ul class="pagination">
				{{ if currentPage == 1 }}
  				<li><a style="display:none;">&laquo;</a></li>
				{{ /if }}
				{{ if currentPage != 1 }}
  				<li><a onclick="getTypeUsers(-1, -1)">&laquo;</a></li>
				{{ /if }}
				{{ each list as val i }}
				{{ if currentPage == i+1 }}
  				<li><a style="color:#444;">{{i+1}}</a></li>
				{{ /if}}
				{{ if currentPage != i+1 }}
  				<li><a onclick="getTypeUsers(-1, {{i+1}})">{{i+1}}</a></li>
				{{ /if}}
				{{ /each }}
				{{ if currentPage == totalPage }}
  				<li><a style="display:none;">&raquo;</a></li>
				{{ /if }}
				{{ if currentPage != totalPage }}
  				<li><a onclick="getTypeUsers(-1, -2)">&raquo;</a></li>
				{{ /if }}
			</ul> 
			</div>
			{{/if}}
			</script>
			</div>

			<!-- <div class="modal small fade" id="lockModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalLabel">锁定用户</h3>
			        </div>
			        <div class="modal-body">
			            <p class="error-text"><i class="fa fa-warning modal-icon"></i>确定锁定用户？<br>锁定后用户将无法进行正常操作。</p>
			        </div>
			        <div class="modal-footer">
			            <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			            <button class="btn btn-danger lockBtn" data-dismiss="modal">确定</button>
			        </div>
			      </div>
			    </div>
			</div>
			 -->
        </div>
    </div>
	<input type="hidden" id = "userid" value="" />

    <script type="text/javascript">
	    $(function(){
	    	$("#oprovince").change(function(){
	    		var index = $.trim($("#oprovince").val());
	    		if (index != null || index != ''){
	    			for (var city in citys[index]) { 
	    				console.log(city);
	    				$('#ocity').append('<option value="' + city[i].id + '">'+ city[i].name + '</option>');
	    			}
	    		}
	    	});
	    	
	    	function doOprovince(index){
	    		
	    		$('#ocity').append('<option value="' + citys[index][i].id + '">'+ provinces[i].name + '</option>');
	    	}
			function doOcity(index){
				$('#oarea').append('<option value="' + areas[index][i].id + '">'+ provinces[i].name + '</option>');		
	    	}
			function doDprovince(index){
				$('#ocity').append('<option value="' + citys[index][i].id + '">'+ provinces[i].name + '</option>');
			}
			function doDcity(index){
				$('#oarea').append('<option value="' + areas[index][i].id + '">'+ provinces[i].name + '</option>');
			}

	    	var vehicles = ${vehicles};
	    	var arrayObj = new Array(vehicles.totalPage);
	    	for (var i=0; i<vehicles.totalPage; i++){
	    		arrayObj[i] = i;
	    	}
	    	console.log(vehicles);
	    	data = {
	    			vehicles : vehicles.pageInfoResult,
	    			length : vehicles.totalRecord,
	    			currentPage : vehicles.currentPage,
	    			totalPage : vehicles.totalPage,
	    			list : arrayObj
	    	};
	    	console.log(data);
	    	var usersViewHtml = template("vehiclesTemplateView", data);
	    	$("#vehiclesTable").html(usersViewHtml);
	    	
	    	var provinces = ${provinces};
	    	var citys = ${citys};
	    	var areas = ${areas};
	    	console.log("provinces" + provinces);
	    	console.log("citys" + citys);
	    	console.log("areas" + areas);
	    	
	    	for (i = 0; i < provinces.length; i++) {
	    		$('#oprovince').append('<option value="' + provinces[i].id + '">'+ provinces[i].name + '</option>');
    		} 
	    	for (i = 0; i < provinces.length; i++) {
	    		$('#dprovince').append('<option value="' + provinces[i].id + '">'+ provinces[i].name + '</option>');
    		}
	    	
	    	/* $(".lockBtn").click(function(){
	    		
	    		var id = $("#userid").val();
	    		var ischecked = $("#ischecked").val();
	    		
	    		$.ajax({
	    			url : "${ctx}/admin/lock.html",
	    			async : false,
	    			type : 'POST',
	    			cache:false,
	    			data : {
	    				id : id,
	    				ischecked : ischecked
	    			},
	    			dataType : 'json',
	    			timeout : 15000,
	    			beforeSend : function() {
	    	    		if (ischecked == 1) 
	    	    			$("#lockModal").modal('hide');
	    	    		else 
	    	    			$("#unLockModal").modal('hide');
	    			},
	    			complete : function(XMLHttpRequest,textStatus) {
	    			},
	    			success : function(response) {
	    				var json = eval(response);
	    				if (0===json.status){
	    					if (ischecked == 1)
	    						alert("锁定成功！");
	    					else
	    						alert("解锁成功！")
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
	    	
    
	    function getTypeUsers(type){
	    	
    		$.ajax({
    			url : "${ctx}/admin/gettypeusers.html",
    			async : false,
    			type : 'POST',
    			cache:false,
    			data : {
    				type : type
    			},
    			dataType : 'json',
    			timeout : 15000,
    			beforeSend : function() {
    			},
    			complete : function(XMLHttpRequest,textStatus) {
    			},
    			success : function(response) {
    				var json = eval(response);
    				if (0===json.status){
    					
    					var result = json.result; 
    					var data = {
    							users:result,
    							length:result.length
    					}
    			    	var usersViewHtml = template("usersTemplateView", data);
    			    	$("#usersTable").html(usersViewHtml);
    			    	if (type == 0)
    			    		$(".page-title").html("游客信息管理");
    			    	if (type == 1)
    			    		$(".page-title").html("旅行社信息管理");
   			    		if (type == 2)
   			    			$(".page-title").html("公司人员信息管理");
   			    		
   			    		$("#usertype").val(type);
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
	    
	    $("#addUser").click(function(){
			window.location.href = "${ctx}/admin/adduser.html";
	    }); */
	    
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    });
   </script>
    
  
</body></html>
