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
		    <li><a data-target=".vehicle-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">交通管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="vehicle-menu nav nav-list collapse in">
		            <li onclick="vehicle();"><a><span class="fa fa-caret-right"></span>交通信息管理</a></li>
			    </ul>
		    </li>
		    <li><a data-target=".ticket-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">门票管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="ticket-menu nav nav-list collapse in">
		            <li onclick=""><a><span class="fa fa-caret-right"></span>门票信息管理</a></li>
		            <li onclick="tickettype();"><a><span class="fa fa-caret-right"></span>门票类型管理</a></li>
			    </ul>
		    </li>
		    <li><a data-target=".line-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">线路管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="line-menu nav nav-list collapse in">
		            <li onclick="linetype();"><a><span class="fa fa-caret-right"></span>线路类型管理</a></li>
		            <li onclick="line();"><a><span class="fa fa-caret-right"></span>线路信息管理</a></li>
			    </ul>
		    </li>
	    </ul>
    </div>

    <div class="content">
    	<div class="header">
            
            <h1 class="page-title">门票信息管理</h1>

        </div>
        <div class="main-content">
            
			<div class="btn-toolbar list-toolbar">
				<div>
	            	<table class="table" style="text-align:center;">
	            		<tr>
	            			<td width="10%" align="right" style="border-top:none;">名称:</td>
	            			<td align="left" style="border-top:none;">
	            				<input type="text" id="name" class="form-control">
            				</td>
            				<td width="30%" style="border-top:none;">星级</td>
	            			<td width="30%" style="border-top:none;">
	            				<select id="star" class="form-control">
	            					  <option value="" checked="checked">请选择</option>
						              <option value="1">1</option>
						              <option value="2">2</option>
						              <option value="3">3</option>
						              <option value="4">4</option>
						              <option value="5">5</option>
					        	</select>
	            			</td>
	            		</tr>
	            		<tr>
	            			<td width="10%" align="right" style="border-top:none;">类型名称:</td>
	            			<td align="left" style="border-top:none;">
	            				<select id="tickettypeid" class="form-control">
	            					  <option value="" checked="checked">请选择</option>
					        	</select>
            				</td>
            				<td width="30%" style="border-top:none;"></td>
	            			<td width="30%" style="border-top:none;"></td>
	            		</tr>
	            		<tr>
	            			<td rowspan="4" align="right" style="border-top:none;">
	            				<button onclick="dosearch(1);" class="btn btn-default">查询</button>
	            			</td>
	            		</tr>
	            	</table>
            	</div>
			    <button id = "addTicket" class="btn btn-primary"><i class="fa fa-plus"></i>&nbsp;添加门票类型</button>
		  		<div class="btn-group">
		  		</div>
			</div>
			
			  <div id="ticketsTable">
			  <script id="ticketsTemplateView" type="text/html">
			  <table class="table" style="text-align:center;">
			  <thead>
			    <tr>
			      <th style="width:3%;text-align: center;">#</th>
			      <th style="width:20%;text-align: center;">名称</th>
			      <th style="width:25%;text-align: center;">地址</th>
			      <th style="width:5%;text-align: center;">星级</th>
			      <th style="width:10%;text-align: center;">费用</th>
			      <th style="width:10%;text-align: center;">类型</th>
			      <th style="width:20%;text-align: center;">备注</th>
			      <th style="width:5%;text-align: center;">操作</th>
			    </tr>
			  </thead>
			  <tbody>
			  {{ each tickets as ticket i }}
			    <tr>
			      <td>{{i + 1}}</td>
			      <td>{{ticket.name}}</td>
			      <td>{{ticket.area}}</td>
				  <td>{{ticket.star}}</td>
			      <td>{{ticket.cost}}元</td>
				  <td>{{ticket.tickettype}}</td>
			      <td>{{ticket.remark}}</td>
				  <td><a class="deleteModelBtn" ticketid="{{ticket.id}}"><i class="fa fa-trash-o"></i></a></td>
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

			<div class="modal small fade" id="addTicketModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog" style="width:800px;">
			    <div class="modal-content">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalLabel">添加门票信息</h3>
			        </div>
			        <div class="modal-body">
			            <table class="table" style="text-align:center;">
		            		<tr>
		            			<td width="10%" align="right" style="border-top:none;">名称</td>
		            			<td align="left" style="border-top:none;">
		            				<input type="text" id="nname" class="form-control">
		            			</td>
		            			<td style="border-top:none;" align="right">类型</td>
		            			<td style="border-top:none;">
		            				<select id="ttickettypeid" class="form-control">
	            					  <option value="" checked="checked">请选择</option>
					        	</select>
		            			</td>
		            		</tr>
		            		<tr>
		            			<td width="10%" align="right" style="border-top:none;">地址:</td>
		            			<td width="30%" align="left" style="border-top:none;">
		            				<select id="province" class="form-control" ">
		            					  <option value="" code="" checked="checked">请选择</option>
						        	</select>
					        	<td width="30%" align="left" style="border-top:none;">
						        	<select id="city" class="form-control">
		            					  <option value="" code="" checked="checked">请选择</option>
						        	</select>
						        </td>
						        <td width="30%" align="left" style="border-top:none;">
						        	<select id="area" class="form-control">
		            					  <option value="" code="" checked="checked">请选择</option>
						        	</select>
		            			</td>
		            		</tr>
		            		<tr>
		            			<td width="10%" align="right" style="border-top:none;">费用（单位：元）</td>
		            			<td align="left" style="border-top:none;">
		            				<input type="text" id="cost" class="form-control">
		            			</td>
		            			<td style="border-top:none;" align="right">星级</td>
		            			<td style="border-top:none;">
		            				<select id="sstar" class="form-control">
	            					  <option value="" checked="checked">请选择</option>
	            					  <option value="1">1</option>
						              <option value="2">2</option>
						              <option value="3">3</option>
						              <option value="4">4</option>
						              <option value="5">5</option>
					        	</select>
		            			</td>
		            		</tr>
		            		<tr>
		            			<td align="right" style="border-top:none;">景点说明</td>
		            			<td align="left" style="border-top:none;">
		            				<textarea id="description" class="form-control" ></textarea>
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
			            <button class="btn btn-danger addTicketBtn" data-dismiss="modal">确定</button>
			        </div>
			      </div>
			    </div>
			</div>
			

			<div class="modal small fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalLabel">删除门票信息</h3>
			        </div>
			        <div class="modal-body">
			            <p class="error-text"><i class="fa fa-warning modal-icon"></i>确定删除该门票信息?<br>操作不可恢复。</p>
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
	<input type="hidden" id = "ticketid" value="" />

    <script type="text/javascript">
	    $(function(){
	    	var ticketsPage = ${ticketsPage};
	    	var arrayObj = new Array(ticketsPage.totalPage);
	    	for (var i=0; i<ticketsPage.totalPage; i++){
	    		arrayObj[i] = i;
	    	}
	    	data = {
	    			tickets : ticketsPage.pageInfoResult,
	    			length : ticketsPage.totalRecord,
	    			currentPage : ticketsPage.currentPage,
	    			totalPage : ticketsPage.totalPage,
	    			list : arrayObj
	    	};
	    	var ticketsViewHtml = template("ticketsTemplateView", data);
	    	$("#ticketsTable").html(ticketsViewHtml);
	    	
	    	var listType = ${listType};
	    	for (i = 0; i < listType.length; i++) {
	    		$('#tickettypeid').append('<option value="' + listType[i].id + '">'+ listType[i].name + '</option>');
	    		$('#ttickettypeid').append('<option value="' + listType[i].id + '">'+ listType[i].name + '</option>');
    		} 
	    	
	    	var provinces = ${provinces};
	    	var citys = ${citys};
	    	var areas = ${areas};
	    	for (i = 0; i < provinces.length; i++) {
	    		$('#province').append('<option value="' + provinces[i].id + '" code="' + provinces[i].code + '">'+ provinces[i].name + '</option>');
    		} 
	    	$("#province").change(function(){
	    		var code = $("#province").find("option:selected").attr("code");
	    		//console.log(code);
	    		if (code != null && code != ''){
	    			var cit = citys[code];
	    			//console.log(cit);
	    			$('#city').html('<option value="" code="" checked="checked">请选择</option>'); 
	    			$('#area').html('<option value="" code="" checked="checked">请选择</option>');
	    			for (var i = 0; i < cit.length; i++) { 
	    				//console.log(cit[i]);
	    				$('#city').append('<option value="' + cit[i].id + '" code="' + cit[i].code + '">'+ cit[i].name + '</option>');
	    			}
	    		}
	    	});
	    	$("#city").change(function(){
	    		var code = $("#city").find("option:selected").attr("code");
	    		if (code != null && code != ''){
	    			var are = areas[code];
	    			$('#area').html('<option value="" code="" checked="checked">请选择</option>'); 
	    			for (var i = 0; i < are.length; i++) { 
	    				$('#area').append('<option value="' + are[i].id + '" code="' + are[i].code + '">'+ are[i].name + '</option>');
	    			}
	    		}
	    	});
	    	
	    	$("#addTicket").click(function(){
	    		$("#addTicketModel").modal('show');
	    	});
	    	
	    	$(".addTicketBtn").on("click", function(){
	    		var name = $.trim($("#nname").val());
	    		var remark = $.trim($("#remark").val());
	    		var provinceid = $.trim($("#province").val());
	    		var cityid = $.trim($("#city").val());
	    		var areaid = $.trim($("#area").val());
	    		var tickettypeid = $.trim($("#ttickettypeid").val());
	    		var cost = $.trim($("#cost").val());
	    		var description = $.trim($("#description").val());
	    		var star = $.trim($("#sstar").val());
	    		
	    		if (name == null || name == '') {
	    			alert("名称不能为空");
	    			return ;
	    		}
	    		if (provinceid == null || provinceid == '') {
	    			alert("请选择地址");
	    			return ;
	    		}
	    		var area = $("#province").find("option:selected").text();
	    		if (cityid != null && cityid != ''){
	    			area += $("#city").find("option:selected").text();
	    		}
    			if (areaid != null && areaid != ''){
    				area += $("#area").find("option:selected").text();
    			}
    			
    			if (cost == null || cost == '') {
	    			alert("金额不能为空");
	    			return ;
	    		} else {
	    			var partn =/^[0-9]{0}([0-9]|[.])+$/; 
	    			if (!partn.test(cost)){
	    				alert("金额只允许输入数字和小数点");
		    			return ;
	    			}
	    		}
	    		
	    		$.ajax({
	    			url : "${ctx}/ticket/addticket.html",
	    			async : false,
	    			type : 'POST',
	    			cache:false,
	    			data : {
	    				name : name,
	    	    		remark : remark,
	    	    		provinceid : provinceid,
	    	    		cityid : cityid,
	    	    		areaid : areaid,
	    	    		tickettypeid : tickettypeid,
	    	    		cost : cost,
	    	    		description : description,
	    	    		star : star,
	    	    		area : area
	    			},
	    			dataType : 'json',
	    			timeout : 15000,
	    			beforeSend : function() {
    	    			$("#addTicketTypeModel").modal('hide');
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
    		var id = $(this).attr("ticketid");
    		$("#ticketid").val(id);
    		$("#deleteModal").modal('show');
    	});
    	
		$("#deleteBtn").click(function(){
    		
    		var id = $("#ticketid").val();
    		
    		$.ajax({
    			url : "${ctx}/ticket/deleteticket.html",
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
    		
    	var name = $.trim($('#name').val());
    	var star = $.trim($('#star').val());
    	var tickettypeid = $.trim($('#tickettypeid').val());
    	
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
   			url : "${ctx}/ticket/search.html",
   			async : false,
   			type : 'POST',
   			cache:false,
   			data : {
   				currentPage : i,
   				name : name,
   				star : star,
   				tickettypeid : tickettypeid
   			},
   			dataType : 'json',
   			timeout : 15000,
   			beforeSend : function() {
   			},
   			complete : function(XMLHttpRequest,textStatus) {
				$(".deleteModelBtn").on("click", function(){
		    		var id = $(this).attr("ticketid");
		    		$("#ticketid").val(id);
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
   			    			tickets : result.pageInfoResult,
   			    			length : result.totalRecord,
   			    			currentPage : result.currentPage,
   			    			totalPage : result.totalPage,
   			    			list : arrayObj
   			    	};
   			    	var ticketTypesViewHtml = template("ticketsTemplateView", data);
   			    	$("#ticketsTable").html(ticketTypesViewHtml);
   					
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
</script>
    
  
</body></html>
