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
		    <li><a data-target=".dashboard-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">基础信息管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="dashboard-menu nav nav-list collapse in">
		            <li onclick="getTypeUsers(2)"><a><span class="fa fa-caret-right"></span>交通信息管理</a></li>
		            <li onclick="getTypeUsers(1)"><a><span class="fa fa-caret-right"></span>门票信息管理</a></li>
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
	            			<td width="90%" align="left" style="border-top:none;">
	            				<input id="username" />&nbsp;&nbsp;<input id="username" />&nbsp;&nbsp;<input id="username" />
	            			</td>
	            		</tr>
	            		<tr>
	            			<td width="10%" align="right" style="border-top:none;">目的地:</td>
	            			<td width="90%" align="left" style="border-top:none;">
	            				<input id="username" />&nbsp;&nbsp;<input id="username" />&nbsp;&nbsp;<input id="username" />
	            			</td>
	            		</tr>
	            		
	            		<tr>
	            			<td width="10%" align="right" style="border-top:none;">交通类别:</td>
	            			<td width="90%" align="left" style="border-top:none;">
	            				<input id="username" />
	            			</td>
	            		</tr>
	            		<tr>
	            			<td rowspan="4" align="right" style="border-top:none;">
	            				<button onclick = "getTypeUsers(-1,1);" class="btn btn-default">查询</button>
	            			</td>
	            		</tr>
	            	</table>
            	</div>
			    <button id = "addUser" class="btn btn-primary"><i class="fa fa-plus"></i>&nbsp;添加网站工作用户</button>
		  		<div class="btn-group">
		  		</div>
			</div>
			
			  <div id="usersTable">
			  <script id="usersTemplateView" type="text/html">
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
			  {{ each users as user i }}
			    <tr>
			      <td>{{i + 1}}</td>
			      <td>{{user.username}}</td>
			      <td>{{user.realname}}</td>
			      <td>{{if user.sex == 1}}男{{/if}}{{if user.sex == 2}}女{{/if}}</td>
			      <td>{{user.telephone}}</td>
			      <td>{{user.qq}}</td>
			    </tr>
			  {{ /each }}
			  </tbody>
			</table>
			
			{{if length != 0}}
			<ul class="pagination">
			  <li><a href="#">&laquo;</a></li>
			  <li><a href="#">1</a></li>
			  <li><a href="#">2</a></li>
			  <li><a href="#">3</a></li>
			  <li><a href="#">4</a></li>
			  <li><a href="#">5</a></li>
			  <li><a href="#">&raquo;</a></li>
			</ul>
			{{/if}}
			</script>
			</div>

			<div class="modal small fade" id="lockModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
			
			<div class="modal small fade" id="unLockModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalLabel">解锁用户</h3>
			        </div>
			        <div class="modal-body">
			            <p class="error-text"><i class="fa fa-warning modal-icon"></i>解锁用户？</p>
			        </div>
			        <div class="modal-footer">
			            <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			            <button class="btn btn-danger lockBtn" data-dismiss="modal">确定</button>
			        </div>
			      </div>
			    </div>
			</div>
			
			<div class="modal small fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalLabel">删除用户</h3>
			        </div>
			        <div class="modal-body">
			            <p class="error-text"><i class="fa fa-warning modal-icon"></i>确定删除用户?<br>操作不可恢复。</p>
			        </div>
			        <div class="modal-footer">
			            <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			            <button id="deleteBtn" class="btn btn-danger" data-dismiss="modal">删除</button>
			        </div>
			      </div>
			    </div>
			</div>
			
			<div class="modal small fade" id="checkModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalLabel">用户审核</h3>
			        </div>
			        <div class="modal-body">
			            <p class="error-text"><i class="fa fa-warning modal-icon"></i>确定用户通过审核?</p>
			        </div>
			        <div class="modal-footer">
			            <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			            <button id="checkBtn" class="btn btn-danger" data-dismiss="modal">确定</button>
			        </div>
			      </div>
			    </div>
			</div>

        </div>
    </div>
	<input type="hidden" id = "userid" value="" />
	<input type="hidden" id = "ischecked" value="" />
	<input type="hidden" id = "usertype" value="2" />

    <script type="text/javascript">
	    $(function(){

	    	var users = ${users};
	    	data = {
	    			users : users,
	    			length : users.length
	    	};
	    	console.log('${ctx}');
	    	var usersViewHtml = template("usersTemplateView", data);
	    	$("#usersTable").html(usersViewHtml);
	    	
	    	$(".checkModelBtn").click(function(){
	    		var id = $(this).attr("userid");
	    		$("#userid").val(id);
	    		$("#checkModal").modal('show');
	    	});
	    	
	    	$(".lockModelBtn").click(function(){
	    		var id = $(this).attr("userid");
	    		var ischecked = $(this).attr("ischecked");
	    		$("#userid").val(id);
	    		$("#ischecked").val(ischecked);
	    		if (ischecked == 1) 
	    			$("#lockModal").modal('show');
	    		else 
	    			$("#unLockModal").modal('show');
	    	});
	    	
	    	$(".deleteModelBtn").click(function(){
	    		var id = $(this).attr("userid");
	    		$("#userid").val(id);
	    		$("#deleteModal").modal('show');
	    	});
	    	
	    	$(".lockBtn").click(function(){
	    		
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
	    	
	    	$("#checkBtn").click(function(){
	    		
	    		var id = $("#userid").val();
	    		$.ajax({
	    			url : "${ctx}/admin/check.html",
	    			async : false,
	    			type : 'POST',
	    			cache:false,
	    			data : {
	    				id : id
	    			},
	    			dataType : 'json',
	    			timeout : 15000,
	    			beforeSend : function() {
	    	    		$("#checkModal").modal('hide');
	    			},
	    			complete : function(XMLHttpRequest,textStatus) {
	    			},
	    			success : function(response) {
	    				var json = eval(response);
	    				if (0===json.status){
	    					alert("审核成功！")
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
	    	
	    	$("#deleteBtn").click(function(){
	    		
	    		var id = $("#userid").val();
	    		
	    		$.ajax({
	    			url : "${ctx}/admin/delete.html",
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
	    });
	    
	    function download() {
	    	var usertype = $("#usertype").val();
	    	window.location.href = "${ctx}/export/users.html?type=" + usertype;
	    }
	    
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  
</body></html>
