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
		            <li onclick=""><a><span class="fa fa-caret-right"></span>交通信息管理</a></li>
			    </ul>
		    </li>
		    <li><a data-target=".ticket-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">门票管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="ticket-menu nav nav-list collapse in">
		            <li onclick="ticket();"><a><span class="fa fa-caret-right"></span>门票信息管理</a></li>
		            <li onclick="ticketType();"><a><span class="fa fa-caret-right"></span>门票类型管理</a></li>
			    </ul>
		    </li>
		    <li><a data-target=".line-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">线路管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="line-menu nav nav-list collapse in">
		            <li onclick="linetype();"><a><span class="fa fa-caret-right"></span>线路类型管理</a></li>
		            <li onclick="line();"><a><span class="fa fa-caret-right"></span>线路信息管理</a></li>
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
		            <li onclick="order();"><a><span class="fa fa-caret-right"></span>订单信息管理</a></li>
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
	            					  <option value="" code="" checked="checked">请选择</option>
					        	</select>
				        	<td width="30%" align="left" style="border-top:none;">
					        	<select id="ocity" class="form-control">
	            					  <option value="" code="" checked="checked">请选择</option>
					        	</select>
					        </td>
					        <td width="30%" align="left" style="border-top:none;">
					        	<select id="oarea" class="form-control">
	            					  <option value="" code="" checked="checked">请选择</option>
					        	</select>
	            			</td>
	            		</tr>
	            		<tr>
	            			<td width="10%" align="right" style="border-top:none;">目的地:</td>
	            			<td width="30%" align="left" style="border-top:none;">
	            				<select id="dprovince" class="form-control">
	            					  <option value="" code="" checked="checked">请选择</option>
					        	</select>
				        	<td width="30%" align="left" style="border-top:none;">
					        	<select id="dcity" class="form-control">
	            					  <option value="" code="" checked="checked">请选择</option>
					        	</select>
					        </td>
					        <td width="30%" align="left" style="border-top:none;">
					        	<select id="darea" class="form-control">
	            					  <option value="" code="" checked="checked">请选择</option>
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
	            				<button id="search" class="btn btn-default">查询</button>
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
			      <th style="width:20%;text-align: center;">备注</th>
				  <th style="width:5%;text-align: center;">操作</th>
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
				  <td><a class="deleteModelBtn" vehicleid="{{vehicle.id}}"><i class="fa fa-trash-o"></i></a></td>
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

			<div class="modal small fade" id="addVehicleModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog" style="width:800px;">
			    <div class="modal-content">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalLabel">添加交通信息</h3>
			        </div>
			        <div class="modal-body">
			            <table class="table" style="text-align:center;">
		            		<tr>
		            			<td width="10%" align="right" style="border-top:none;">始发地:</td>
		            			<td width="30%" align="left" style="border-top:none;">
		            				<select id="ooprovince" class="form-control" ">
		            					  <option value="" code="" checked="checked">请选择</option>
						        	</select>
					        	<td width="30%" align="left" style="border-top:none;">
						        	<select id="oocity" class="form-control">
		            					  <option value="" code="" checked="checked">请选择</option>
						        	</select>
						        </td>
						        <td width="30%" align="left" style="border-top:none;">
						        	<select id="ooarea" class="form-control">
		            					  <option value="" code="" checked="checked">请选择</option>
						        	</select>
		            			</td>
		            		</tr>
		            		<tr>
		            			<td width="10%" align="right" style="border-top:none;">目的地:</td>
		            			<td width="30%" align="left" style="border-top:none;">
		            				<select id="ddprovince" class="form-control">
		            					  <option value="" code="" checked="checked">请选择</option>
						        	</select>
					        	<td width="30%" align="left" style="border-top:none;">
						        	<select id="ddcity" class="form-control">
		            					  <option value="" code="" checked="checked">请选择</option>
						        	</select>
						        </td>
						        <td width="30%" align="left" style="border-top:none;">
						        	<select id="ddarea" class="form-control">
		            					  <option value="" code="" checked="checked">请选择</option>
						        	</select>
		            			</td>
		            		</tr>
		            		
		            		<tr>
		            			<td width="10%" align="right" style="border-top:none;">交通类别:</td>
		            			<td align="left" style="border-top:none;">
		            				<select id="vvehicletype" class="form-control">
		            					  <option value="" checked="checked">请选择</option>
							              <option value="1">汽车</option>
							              <option value="2">火车</option>
							              <option value="3">飞机</option>
							              <option value="4">轮船</option>
						        	</select>
		            			</td>
		            			<td width="10%" align="right" style="border-top:none;">金额（单位：元）</td>
		            			<td align="left" style="border-top:none;">
		            				<input type="text" id="cost" class="form-control">
		            			</td>
		            		</tr>
		            		<tr>
		            			<td width="10%" align="right" style="border-top:none;">备注</td>
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
			            <button class="btn btn-danger addVehicleBtn" data-dismiss="modal">确定</button>
			        </div>
			      </div>
			    </div>
			</div>
			

			<div class="modal small fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalLabel">删除交通信息</h3>
			        </div>
			        <div class="modal-body">
			            <p class="error-text"><i class="fa fa-warning modal-icon"></i>确定删除该交通信息?<br>操作不可恢复。</p>
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
	<input type="hidden" id = "vehicleid" value="" />

    <script type="text/javascript">
	    $(function(){
			
			
	    	var vehicles = ${vehicles};
	    	var arrayObj = new Array(vehicles.totalPage);
	    	for (var i=0; i<vehicles.totalPage; i++){
	    		arrayObj[i] = i;
	    	}
	    	data = {
	    			vehicles : vehicles.pageInfoResult,
	    			length : vehicles.totalRecord,
	    			currentPage : vehicles.currentPage,
	    			totalPage : vehicles.totalPage,
	    			list : arrayObj
	    	};
	    	var vehiclesViewHtml = template("vehiclesTemplateView", data);
	    	$("#vehiclesTable").html(vehiclesViewHtml);
	    	
	    	var provinces = ${provinces};
	    	var citys = ${citys};
	    	var areas = ${areas};
	    	
	    	for (i = 0; i < provinces.length; i++) {
	    		$('#oprovince').append('<option value="' + provinces[i].id + '" code="' + provinces[i].code + '">'+ provinces[i].name + '</option>');
    		} 
	    	for (i = 0; i < provinces.length; i++) {
	    		$('#dprovince').append('<option value="' + provinces[i].id + '" code="' + provinces[i].code + '">'+ provinces[i].name + '</option>');
    		}
	    	for (i = 0; i < provinces.length; i++) {
	    		$('#ooprovince').append('<option value="' + provinces[i].id + '" code="' + provinces[i].code + '">'+ provinces[i].name + '</option>');
    		} 
	    	for (i = 0; i < provinces.length; i++) {
	    		$('#ddprovince').append('<option value="' + provinces[i].id + '" code="' + provinces[i].code + '">'+ provinces[i].name + '</option>');
    		}
	    	$("#oprovince").change(function(){
	    		var code = $("#oprovince").find("option:selected").attr("code");
	    		//console.log(code);
	    		if (code != null && code != ''){
	    			var cit = citys[code];
	    			//console.log(cit);
	    			$('#ocity').html('<option value="" code="" checked="checked">请选择</option>'); 
	    			$('#oarea').html('<option value="" code="" checked="checked">请选择</option>');
	    			for (var i = 0; i < cit.length; i++) { 
	    				//console.log(cit[i]);
	    				$('#ocity').append('<option value="' + cit[i].id + '" code="' + cit[i].code + '">'+ cit[i].name + '</option>');
	    			}
	    		}
	    	});
	    	$("#ocity").change(function(){
	    		var code = $("#ocity").find("option:selected").attr("code");
	    		if (code != null && code != ''){
	    			var are = areas[code];
	    			$('#oarea').html('<option value="" code="" checked="checked">请选择</option>'); 
	    			for (var i = 0; i < are.length; i++) { 
	    				$('#oarea').append('<option value="' + are[i].id + '" code="' + are[i].code + '">'+ are[i].name + '</option>');
	    			}
	    		}
	    	});
	    	$("#dprovince").change(function(){
	    		var code = $("#dprovince").find("option:selected").attr("code");
	    		if (code != null && code != ''){
	    			var cit = citys[code];
	    			$('#dcity').html('<option value="" code="" checked="checked">请选择</option>'); 
	    			$('#darea').html('<option value="" code="" checked="checked">请选择</option>'); 
	    			for (var i = 0; i < cit.length; i++) { 
	    				$('#dcity').append('<option value="' + cit[i].id + '" code="' + cit[i].code + '">'+ cit[i].name + '</option>');
	    			}
	    		}
	    	});
	    	$("#dcity").change(function(){
	    		var code = $("#dcity").find("option:selected").attr("code");
	    		if (code != null && code != ''){
	    			var are = areas[code];
	    			$('#darea').html('<option value="" code="" checked="checked">请选择</option>'); 
	    			for (var i = 0; i < are.length; i++) { 
	    				$('#darea').append('<option value="' + are[i].id + '" code="' + are[i].code + '">'+ are[i].name + '</option>');
	    			}
	    		}
	    	});
	    	$("#ooprovince").change(function(){
	    		var code = $("#ooprovince").find("option:selected").attr("code");
	    		if (code != null && code != ''){
	    			var cit = citys[code];
	    			$('#oocity').html('<option value="" code="" checked="checked">请选择</option>'); 
	    			$('#ooarea').html('<option value="" code="" checked="checked">请选择</option>'); 
	    			for (var i = 0; i < cit.length; i++) { 
	    				$('#oocity').append('<option value="' + cit[i].id + '" code="' + cit[i].code + '">'+ cit[i].name + '</option>');
	    			}
	    		}
	    	});
	    	$("#oocity").change(function(){
	    		var code = $("#oocity").find("option:selected").attr("code");
	    		if (code != null && code != ''){
	    			var are = areas[code];
	    			$('#ooarea').html('<option value="" code="" checked="checked">请选择</option>'); 
	    			for (var i = 0; i < are.length; i++) { 
	    				$('#ooarea').append('<option value="' + are[i].id + '" code="' + are[i].code + '">'+ are[i].name + '</option>');
	    			}
	    		}
	    	});
	    	$("#ddprovince").change(function(){
	    		var code = $("#ddprovince").find("option:selected").attr("code");
	    		if (code != null && code != ''){
	    			var cit = citys[code];
	    			$('#ddcity').html('<option value="" code="" checked="checked">请选择</option>'); 
	    			$('#ddarea').html('<option value="" code="" checked="checked">请选择</option>'); 
	    			for (var i = 0; i < cit.length; i++) { 
	    				$('#ddcity').append('<option value="' + cit[i].id + '" code="' + cit[i].code + '">'+ cit[i].name + '</option>');
	    			}
	    		}
	    	});
	    	$("#ddcity").change(function(){
	    		var code = $("#ddcity").find("option:selected").attr("code");
	    		if (code != null && code != ''){
	    			var are = areas[code];
	    			$('#ddarea').html('<option value="" code="" checked="checked">请选择</option>'); 
	    			for (var i = 0; i < are.length; i++) { 
	    				$('#ddarea').append('<option value="' + are[i].id + '" code="' + are[i].code + '">'+ are[i].name + '</option>');
	    			}
	    		}
	    	});
	    	
	    	$("#addVehicle").click(function(){
	    		$("#addVehicleModel").modal('show');
	    	});
	    	
	    	$(".addVehicleBtn").on("click", function(){
	    		var oareaid = $.trim($("#ooarea").val());
	    		var ocityid = $.trim($("#oocity").val());
	    		var oprovinceid = $.trim($("#ooprovince").val());
	    		var dareaid = $.trim($("#ddarea").val());
	    		var dcityid = $.trim($("#ddcity").val());
	    		var dprovinceid = $.trim($("#ddprovince").val());
	    		var vehicletype = $.trim($("#vvehicletype").val());
	    		var cost = $.trim($("#cost").val());
	    		var remark = $.trim($("#remark").val());
	    		
	    		if (oprovinceid == null || oprovinceid == '') {
	    			alert("请选则始发地城市");
	    			return ;
	    		}
	    		if (dprovinceid == null || dprovinceid == '') {
	    			alert("请选则目的地城市");
	    			return ;
	    		}
	    		if (vehicletype == null || vehicletype == '') {
	    			alert("请选则交通类型");
	    			return ;
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
	    		
	    		var origin = $("#ooprovince").find("option:selected").text();
	    		if (ocityid != null && ocityid != ''){
	    			origin += $("#oocity").find("option:selected").text();
	    		}
    			if (oareaid != null && oareaid != ''){
    				origin += $("#ooarea").find("option:selected").text();
    			}
	    		var destination = $("#ddprovince").find("option:selected").text();
	    		if (dcityid != null && dcityid != ''){
	    			destination += $("#ddcity").find("option:selected").text();
	    		}
    			if (dareaid != null && dareaid != ''){
    				destination += $("#darea").find("option:selected").text();
    			}
    			
	    		$.ajax({
	    			url : "${ctx}/staff/addvehicle.html",
	    			async : false,
	    			type : 'POST',
	    			cache:false,
	    			data : {
	    				oareaid : oareaid,
	    	    		ocityid : ocityid,
	    	    		oprovinceid : oprovinceid,
	    	    		dareaid : dareaid,
	    	    		dcityid : dcityid,
	    	    		dprovinceid : dprovinceid,
	    	    		vehicletype : vehicletype,
	    	    		cost : cost,
	    	    		remark : remark,
	    	    		origin : origin,
	    	    		destination : destination
	    			},
	    			dataType : 'json',
	    			timeout : 15000,
	    			beforeSend : function() {
    	    			$("#addVehicleModel").modal('hide');
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
	    	
	    	$(".deleteModelBtn").click(function(){
	    		var id = $(this).attr("vehicleid");
	    		$("#vehicleid").val(id);
	    		$("#deleteModal").modal('show');
	    	});
	    	
			$("#deleteBtn").click(function(){
	    		
	    		var id = $("#vehicleid").val();
	    		
	    		$.ajax({
	    			url : "${ctx}/staff/delete.html",
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
			
		
		
		$("#search").on("click", function(){
			dosearch(1);
		});
		$(".dosearch").on("click", function(){
			var page = $(this).attr("page");
			dosearch(page);
		});
	    
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    });
	function dosearch(i){
    		
    	var oprovinceid = $.trim($('#oprovince').val());
    	var dprovinceid = $.trim($('#dprovince').val());
    	var ocityid = $.trim($('#ocityid').val());
    	var dcityid = $.trim($('#dcityid').val());
    	var oareaid = $.trim($('#oareaid').val());
    	var dareaid = $.trim($('#dareaid').val());
    	var vehicletype = $.trim($('#vehicletype').val());
    	
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
   			url : "${ctx}/staff/search.html",
   			async : false,
   			type : 'POST',
   			cache:false,
   			data : {
   				currentpage : i,
   				oprovinceid : oprovinceid,
   				dprovinceid : dprovinceid,
   				ocityid : ocityid,
   				dcityid : dcityid,
   				oareaid : oareaid,
   				dareaid : dareaid,
   				vehicletype : vehicletype
   			},
   			dataType : 'json',
   			timeout : 15000,
   			beforeSend : function() {
   			},
   			complete : function(XMLHttpRequest,textStatus) {
   				$(".deleteModelBtn").click(function(){
   		    		var id = $(this).attr("vehicleid");
   		    		$("#vehicleid").val(id);
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
   			    			vehicles : result.pageInfoResult,
   			    			length : result.totalRecord,
   			    			currentPage : result.currentPage,
   			    			totalPage : result.totalPage,
   			    			list : arrayObj
   			    	};
   			    	var vehiclesViewHtml = template("vehiclesTemplateView", data);
   			    	$("#vehiclesTable").html(vehiclesViewHtml);
   					
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
	
	function ticket(){
		window.location.href = "${ctx}/ticket/index.html";
    }
	
	function ticketType(){
		window.location.href = "${ctx}/tickettype/index.html";
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
	function order(){
		window.location.href = "${ctx}/travelorder/index.html";
    }
</script>
    
  
</body></html>
