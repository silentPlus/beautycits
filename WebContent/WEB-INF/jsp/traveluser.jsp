<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="zh-CN"><head>
    <meta charset="utf-8">
    <title>旅游系统</title>
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
        .content{
        	border-left: none;
        	margin-right: 240px;
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
          <span class="navbar-brand"> 旅游系统</span>
        </div>

        <div class="navbar-collapse collapse" style="height: 1px;">
        	<c:if test="${user != null }">
	          <ul id="main-menu" class="nav navbar-nav navbar-right">
	            <li class="dropdown hidden-xs">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	                    <span class="glyphicon glyphicon-user padding-right-small" style="position:relative;top: 3px;"></span> ${ user.username }
	                    <i class="fa fa-caret-down"></i>
	                </a>
	
	              <ul class="dropdown-menu">
	                <li><a tabindex="-1" href="${ url_editUser }">修改资料</a></li>
	                <li><a tabindex="-1" href="${ url_dingdan }">订单管理</a></li>
	                <li class="divider"></li>
	                <li><a tabindex="-1" href="${ url_logout }">注销</a></li>
	              </ul>
	            </li>
	          </ul>
			</c:if>
			<c:if test="${user == null }">
			  <ul onclick="login();"  id="main-menu" class="nav navbar-nav navbar-right">
	            <li class="dropdown hidden-xs">
	                <a class="dropdown-toggle" data-toggle="dropdown">
                   		登录
	                </a>
	            </li>
	          </ul>
			</c:if>
        </div>
    </div>
    
    <div class="content">
        <div class="main-content">
            <button onclick="lineshow();" class="btn btn-default">首页</button>
            <button onclick="back();" class="btn btn-default">返回</button>
			<div class="btn-toolbar list-toolbar">
				<div>
	            	<table class="table" style="text-align:center;">
	            		<tr>
	            			<td width="25%" align="left" style="border-top:none;">
	            				${lineShow.linetype}--${lineShow.linename}
            				</td>
	            			<td width="25%" align="left" style="border-top:none;">
	            				天数：${lineShow.day}天
            				</td>
           				</tr>
           				<tr>
            				<td align="left" style="border-top:none;">
            					去时交通：${lineShow.govehicle}
            				</td>
	            			<td align="left" style="border-top:none;">
	            				回时交通：${lineShow.backvehicle}
	            			</td>
	            		</tr>
	            		<tr>
	            			<td style="border-top:none;">
	            			</td>
	            			<td style="border-top:none;">
	            				<button id = "addTravelUser" class="btn btn-default">添加人员</button>
	            				<button id = "toTravelQuote" class="btn btn-default">去交费</button>
	            			</td>
	            		</tr>
	            	</table>
            	</div>
		  		<div class="btn-group">
		  		</div>
			</div>
			
			  <div id="travelUsersTable">
			  <script id="travelUsersTemplateView" type="text/html">
			  <table class="table" style="text-align:center;">
			  <thead>
			    <tr>
			      <th style="width:3%;text-align: center;">#</th>
			      <th style="width:20%;text-align: center;">姓名</th>
			      <th style="width:20%;text-align: center;">年龄</th>
			      <th style="width:20%;text-align: center;">操作</th>
			    </tr>
			  </thead>
			  <tbody>
			  {{ each travelUsers as travelUser i }}
			    <tr>
			      <td>{{i + 1}}</td>
			      <td>{{travelUser.name}}</td>
			      <td>{{travelUser.age}}</td>
				  <td><a class="delTravelUser" id="{{travelUser.id}}"><i class="fa fa-trash-o"></i></a></td>
			    </tr>
			  {{ /each }}
			  </tbody>
			</table>
			</script>
			</div>
			
			<div class="modal small fade" id="addTravelUserModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog" >
			    <div class="modal-content">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalLabel">添加人员信息</h3>
			        </div>
			        <div class="modal-body">
			            <table class="table" style="text-align:center;">
		            		<tr>
		            			<td align="right" style="border-top:none;">姓名</td>
		            			<td align="left" style="border-top:none;">
		            				<input type="text" id="name" class="form-control">
		            			</td>
		            			<td align="right" style="border-top:none;">年龄</td>
		            			<td style="border-top:none;">
		            				<input type="text" id="age" class="form-control">
		            			</td>
		            		</tr>
		            	</table>
			        </div>
			        <div class="modal-footer">
			            <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			            <button class="btn btn-danger addTravelUserBtn" data-dismiss="modal">确定</button>
			        </div>
			      </div>
			    </div>
			</div>
			
        </div>
    </div>
	<input type="hidden" id = "linedetailid" value="${lineShow.id }" />

    <script type="text/javascript">
	    $(function(){
			
	    	var travelUsers = ${listTravelUser};
	    	data = {
	    			travelUsers : travelUsers
	    	};
	    	//console.log(data);
	    	var travelUsersViewHtml = template("travelUsersTemplateView", data);
	    	$("#travelUsersTable").html(travelUsersViewHtml);
	    	
        $("[rel=tooltip]").tooltip();
	    
        $(".delTravelUser").on("click", function(){
        	var id = $(this).attr("id");
			$.ajax({
    			url : "${ctx}/traveluser/deletetravleuser.html",
    			async : false,
    			type : 'POST',
    			cache:false,
    			data : {
    				id : id
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
    					alert("删除成功");
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
        
        $("#addTravelUser").click(function(){
    		$("#addTravelUserModel").modal("show");
    	});
        
        $(".addTravelUserBtn").on("click", function(){
    		var name = $.trim($("#name").val());
    		var age = $.trim($("#age").val());
    		var linedetailid = $("#linedetailid").val();
    		
    		if (name == null || name == '') {
    			alert("请填写游客姓名");
    			return ;
    		}
    		if (age == null || age == '') {
    			alert("请填写游客年龄");
    			return ;
    		} else {
    			var partn =/^[0-9]*[1-9][0-9]*$/; 
    			if (!partn.test(age)){
    				alert("年龄只允许输入整数");
	    			return ;
    			}
    		}
    		
    		$.ajax({
    			url : "${ctx}/traveluser/addtraveluser.html",
    			async : false,
    			type : 'POST',
    			cache:false,
    			data : {
    				name : name,
    	    		age : age,
    	    		linedetailid : linedetailid
    			},
    			dataType : 'json',
    			timeout : 15000,
    			beforeSend : function() {
	    			$("#addTravelUserModel").modal('hide');
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
        
	    });
		function login(){
			window.location.href = "${ctx}/login/index.html";
		}
		
		function back(){
			window.history.back();
		}
		
		$("#toTravelQuote").on("click", function(){
			window.location.href = "${ctx}/travelquote/index.html";
		});
		
		function lineshow(){
			window.location.href = "${ctx}/lineshow/index.html";
		}
	
</script>
    
  
</body></html>
