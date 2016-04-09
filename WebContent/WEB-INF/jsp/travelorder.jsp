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
		    <li><a data-target=".vehicle-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">交通管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="vehicle-menu nav nav-list collapse in">
		            <li onclick="vehicle();"><a><span class="fa fa-caret-right"></span>交通信息管理</a></li>
			    </ul>
		    </li>
		    <li><a data-target=".ticket-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">门票管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="ticket-menu nav nav-list collapse in">
		            <li onclick="ticket();"><a><span class="fa fa-caret-right"></span>门票信息管理</a></li>
		            <li onclick="tickettype();"><a><span class="fa fa-caret-right"></span>门票类型管理</a></li>
			    </ul>
		    </li>
		    <li><a data-target=".line-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">线路管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="line-menu nav nav-list collapse in">
		            <li onclick="linetype();"><a><span class="fa fa-caret-right"></span>线路类型管理</a></li>
		            <li onclick=""><a><span class="fa fa-caret-right"></span>线路信息管理</a></li>
			    </ul>
		    </li>
		    <li><a data-target=".quote-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">报价管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="quote-menu nav nav-list collapse in">
		            <li onclick="quote();"><a><span class="fa fa-caret-right"></span>报价信息管理</a></li>
			    </ul>
		    </li>
		    <li><a data-target=".order-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">订单管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="order-menu nav nav-list collapse in">
		            <li onclick=""><a><span class="fa fa-caret-right"></span>订单信息管理</a></li>
			    </ul>
		    </li>
	    </ul>
    </div>
    
    <div class="content">
        <div class="main-content">
			<div class="btn-toolbar list-toolbar">
				<div>
	            	<table class="table" style="text-align:center;">
	            		<tr>
	            			<td width="10%" align="right" style="border-top:none;">订单状态</td>
	            			<td width="40%" align="left" style="border-top:none;">
	            				<select id="iscost" class="form-control">
	            					  <option value="" checked="checked">请选择</option>
	            					  <option value="1" checked="checked">审核中</option>
	            					  <option value="2" checked="checked">进行中</option>
	            					  <option value="3" checked="checked">已结束</option>
           					    </select>
            				</td>
            				<td width="20%" align="right" style="border-top:none;"></td>
	            			<td width="30%" align="left" style="border-top:none;">
            				</td>
	            		</tr>
	            		<tr>
	            			<td rowspan="4" align="right" style="border-top:none;">
	            				<button onclick="dosearch(1);" class="btn btn-default">查询</button>
	            			</td>
	            		</tr>
	            	</table>
            	</div>
		  		<div class="btn-group">
		  		</div>
			</div>
			
			  <div id="travelQuotesTable">
			  <script id="travelQuotesTemplateView" type="text/html">
			  <table class="table" style="text-align:center;">
			  <thead>
			    <tr>
			      <th style="width:3%;text-align: center;">#</th>
			      <th style="width:20%;text-align: center;">线路名称</th>
			      <th style="width:20%;text-align: center;">出发时间</th>
			      <th style="width:20%;text-align: center;">状态</th>
			      <th style="width:40%;text-align: center;">操作</th>
			    </tr>
			  </thead>
			  <tbody>
			  {{ each travelQuotes as travelQuote i }}
			    <tr>
			      <td>{{i + 1}}</td>
			      <td>{{travelQuote.linename}}</td>
			      <td>{{travelQuote.time}}</td>
			      <td>{{travelQuote.state}}</td>
				  <td>
					{{ if travelQuote.iscost == 1 }}
					<button class="btn btn-default publishModelBtn" linedetailid="{{travelQuote.linedetailid}}" time="{{travelQuote.time}}">提交用户名单</button>
					{{ /if }}
					{{ if travelQuote.iscost == 1 }}
					<button class="btn btn-default addTravelUser" linedetailid="{{travelQuote.linedetailid}}">添加游客</button>
					{{ /if }}
				  </td>
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
  				<li><a onclick="javascript:dosearch(-1)">&laquo;</a></li>
				{{ /if }}
				{{ each list as val i }}
				{{ if currentPage == i+1 }}
  				<li><a style="color:#444;">{{i+1}}</a></li>
				{{ /if}}
				{{ if currentPage != i+1 }}
  				<li><a onclick="javascript:dosearch({{i+1}})">{{i+1}}</a></li>
				{{ /if}}
				{{ /each }}
				{{ if currentPage == totalPage }}
  				<li><a style="display:none;">&raquo;</a></li>
				{{ /if }}
				{{ if currentPage != totalPage }}
  				<li><a onclick="javascript:dosearch(-2)">&raquo;</a></li>
				{{ /if }}
			</ul> 
			</div>
			{{/if}}
			</script>
			</div>
			
			<div class="modal small fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalLabel">删除订单</h3>
			        </div>
			        <div class="modal-body">
			            <p class="error-text"><i class="fa fa-warning modal-icon"></i>确定删除该订单?<br>操作不可恢复。</p>
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
	<input type="hidden" id = "travelquoteid" value="" />

    <script type="text/javascript">
	    $(function(){
			
	    	var listTravelQuotePage = ${listTravelQuotePage};
	    	var arrayObj = new Array(listTravelQuotePage.totalPage);
	    	for (var i=0; i<listTravelQuotePage.totalPage; i++){
	    		arrayObj[i] = i;
	    	}
	    	data = {
	    			travelQuotes : listTravelQuotePage.pageInfoResult,
	    			length : listTravelQuotePage.totalRecord,
	    			currentPage : listTravelQuotePage.currentPage,
	    			totalPage : listTravelQuotePage.totalPage,
	    			list : arrayObj
	    	};
	    	var travelQuotesViewHtml = template("travelQuotesTemplateView", data);
	    	$("#travelQuotesTable").html(travelQuotesViewHtml);
	    	
        $("[rel=tooltip]").tooltip();
        
        $(".travelQuoteModelBtn").click(function(){
        	var num = $(this).attr("num");
        	if (num < 1) {
        		alert("请先填加旅客信息！");
        		return ;
        	}
        	var id = $(this).attr("id");
    		$("#travelquoteid").val(id);
	    	$("#travelQuoteModel").modal("show");
    	});
        
        
        $("#deleteBtn").on("click", function(){
        	var id = $("#travelquoteid").val();
			$.ajax({
    			url : "${ctx}/travelquote/deltravelquote.html",
    			async : false,
    			type : 'POST',
    			cache:false,
    			data : {
    				id : id
    			},
    			dataType : 'json',
    			timeout : 15000,
    			beforeSend : function() {
    		    	$("#deleteModal").modal("hide");
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
        
        
        $(".addTravelUser").on("click", function(){
        	var linedetailid=$(this).attr("linedetailid");
        	window.location.href = "${ctx}/traveluser/index.html?linedetailid=" + linedetailid;
        });
        
    });
	    
	    function dosearch(i){
    		

			var iscost = $.trim($("#iscost").val());
	    	
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
	   			url : "${ctx}/travelorder/search.html",
	   			async : false,
	   			type : 'POST',
	   			cache:false,
	   			data : {
	   				currentPage : i,
	   				iscost : iscost
	   			},
	   			dataType : 'json',
	   			timeout : 15000,
	   			beforeSend : function() {
	   			},
	   			complete : function(XMLHttpRequest,textStatus) {
	   				$(".delTravelQuote").click(function(){
	   		        	var id = $(this).attr("id");
	   		    		$("#travelquoteid").val(id);
	   			    	$("#deleteModal").modal("show");
	   		    	});
	   				$(".travelQuoteModelBtn").click(function(){
	   		        	var num = $(this).attr("num");
	   		        	if (num < 1) {
	   		        		alert("请先填加旅客信息！");
	   		        		return ;
	   		        	}
	   		        	var id = $(this).attr("id");
	   		    		$("#travelquoteid").val(id);
	   			    	$("#travelQuoteModel").modal("show");
	   		    	});
	   				$(".addTravelUser").on("click", function(){
	   		        	var linedetailid=$(this).attr("linedetailid");
	   		        	window.location.href = "${ctx}/traveluser/index.html?linedetailid=" + linedetailid;
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
	   			    			travelQuotes : result.pageInfoResult,
	   			    			length : result.totalRecord,
	   			    			currentPage : result.currentPage,
	   			    			totalPage : result.totalPage,
	   			    			list : arrayObj
	   			    	};
	   			    	var travelQuotesViewHtml = template("travelQuotesTemplateView", data);
	   			    	$("#travelQuotesTable").html(travelQuotesViewHtml);
	   					
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
	    
	    function tickettype(){
			window.location.href = "${ctx}/tickettype/index.html";
	    }
		
		function vehicle(){
			window.location.href = "${ctx}/staff/index.html";
	    }

		function linetype(){
			window.location.href = "${ctx}/linetype/index.html";
	    }
		function line(){
			window.location.href = "${ctx}/line/index.html";
	    }
		function quote(){
			window.location.href = "${ctx}/outquote/index.html";
	    }
		function ticket(){
			window.location.href = "${ctx}/ticket/index.html";
	    }
	
</script>
    
  
</body>
</html>
