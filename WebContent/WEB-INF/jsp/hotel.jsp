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
		            <li onclick=""><a><span class="fa fa-caret-right"></span>宾馆信息管理</a></li>
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
		    <li><a data-target=".order-menu" class="nav-header" data-toggle="collapse" style="padding-left: 20px;">订单管理<i class="fa fa-collapse"></i></a></li>
		    <li>
			    <ul class="order-menu nav nav-list collapse in">
		            <li onclick="order();"><a><span class="fa fa-caret-right"></span>订单管理</a></li>
			    </ul>
		    </li>
	    </ul>
    </div>

    <div class="content">
    	<div class="header">
            
            <h1 class="page-title">宾馆信息管理</h1>

        </div>
        <div class="main-content">
            
			<div class="btn-toolbar list-toolbar">
				<div>
	            	<table class="table" style="text-align:center;">
	            		<tr>
	            			<td width="10%" align="right" style="border-top:none;">宾馆名称:</td>
	            			<td width="40%" align="left" style="border-top:none;">
	            				<select id="hoteltypeid" class="form-control">
	            					  <option value="" checked="checked">请选择</option>
           					    </select>
            				</td>
            				<td width="10%" style="border-top:none;">规格</td>
	            			<td width="40%" style="border-top:none;">
	            				<select id="format" class="form-control">
	            					  <option value="" checked="checked">请选择</option>
						              <option value="0">标准间</option>
						              <option value="1">大床房</option>
						              <option value="2">单人间</option>
						              <option value="3">三人间</option>
						              <option value="4">商务套房</option>
						              <option value="5">豪华套房</option>
						              <option value="6">总统套房</option>
						              <option value="7">海景房</option>
					        	</select>
	            			</td>
	            		</tr>
	            		<tr>
	            			<td rowspan="4" align="right" style="border-top:none;">
	            				<button onclick="dosearch(1);" class="btn btn-default">查询</button>
	            			</td>
	            		</tr>
	            	</table>
            	</div>
			    <button id = "addHotel" class="btn btn-primary"><i class="fa fa-plus"></i>&nbsp;添加宾馆信息</button>
		  		<div class="btn-group">
		  		</div>
			</div>
			
			  <div id="hotelsTable">
			  <script id="hotelsTemplateView" type="text/html">
			  <table class="table" style="text-align:center;">
			  <thead>
			    <tr>
			      <th style="width:3%;text-align: center;">#</th>
			      <th style="width:10%;text-align: center;">宾馆名称</th>
			      <th style="width:10%;text-align: center;">规格</th>
			      <th style="width:25%;text-align: center;">地址</th>
			      <th style="width:10%;text-align: center;">价格</th>
			      <th style="width:25%;text-align: center;">备注</th>
			      <th style="width:10%;text-align: center;">操作</th>
			    </tr>
			  </thead>
			  <tbody>
			  {{ each hotels as hotel i }}
			    <tr>
			      <td>{{i + 1}}</td>
			      <td>{{hotel.hoteltype}}</td>
			      <td>{{hotel.formatname}}</td>
			      <td>{{hotel.area}}</td>
			      <td>{{hotel.cost}}元</td>
			      <td>{{hotel.remark}}</td>
				  <td><a class="deleteModelBtn" hotelid="{{hotel.id}}"><i class="fa fa-trash-o"></i></a></td>
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

			<div class="modal small fade" id="addHotelModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog" style="width:800px;">
			    <div class="modal-content">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalLabel">添加宾馆信息</h3>
			        </div>
			        <div class="modal-body">
			            <table class="table" style="text-align:center;">
		            		<tr>
		            			<td align="right" style="border-top:none;">宾馆名称</td>
		            			<td align="left" style="border-top:none;">
		            				<select id="hhoteltypeid" class="form-control">
	            					  <option value="" checked="checked">请选择</option>
           					    </select>
		            			</td>
		            			<td align="right" style="border-top:none;">规格</td>
		            			<td style="border-top:none;">
		            				<select id="fformat" class="form-control">
	            					  <option value="" checked="checked">请选择</option>
						              <option value="0">标准间</option>
						              <option value="1">大床房</option>
						              <option value="2">单人间</option>
						              <option value="3">三人间</option>
						              <option value="4">商务套房</option>
						              <option value="5">豪华套房</option>
						              <option value="6">总统套房</option>
						              <option value="7">海景房</option>
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
			            <button class="btn btn-danger addHotelBtn" data-dismiss="modal">确定</button>
			        </div>
			      </div>
			    </div>
			</div>
			

			<div class="modal small fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            <h3 id="myModalLabel">删除宾馆信息</h3>
			        </div>
			        <div class="modal-body">
			            <p class="error-text"><i class="fa fa-warning modal-icon"></i>确定删除该宾馆信息?<br>操作不可恢复。</p>
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
	<input type="hidden" id = "hotelid" value="" />

    <script type="text/javascript">
	    $(function(){
			
			
	    	var hotelsPage = ${hotelsPage};
	    	var arrayObj = new Array(hotelsPage.totalPage);
	    	for (var i=0; i<hotelsPage.totalPage; i++){
	    		arrayObj[i] = i;
	    	}
	    	data = {
	    			hotels : hotelsPage.pageInfoResult,
	    			length : hotelsPage.totalRecord,
	    			currentPage : hotelsPage.currentPage,
	    			totalPage : hotelsPage.totalPage,
	    			list : arrayObj
	    	};
	    	var hotelsViewHtml = template("hotelsTemplateView", data);
	    	$("#hotelsTable").html(hotelsViewHtml);
	    	
	    	var listType = ${listType};
	    	for (i = 0; i < listType.length; i++) {
	    		$('#hoteltypeid').append('<option value="' + listType[i].id + '">'+ listType[i].name + '</option>');
	    		$('#hhoteltypeid').append('<option value="' + listType[i].id + '">'+ listType[i].name + '</option>');
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
	    	
	    	
	    	$("#addHotel").click(function(){
	    		$("#addHotelModel").modal('show');
	    	});
	    	
	    	$(".addHotelBtn").on("click", function(){
	    		var hoteltypeid = $.trim($("#hhoteltypeid").val());
	    		var format = $.trim($("#fformat").val());
	    		var provinceid = $.trim($("#province").val());
	    		var cityid = $.trim($("#city").val());
	    		var areaid = $.trim($("#area").val());
	    		var cost = $.trim($("#cost").val());
	    		var remark = $.trim($("#remark").val());
	    		
	    		if (hoteltypeid == null || hoteltypeid == '') {
	    			alert("请选则宾馆类型");
	    			return ;
	    		}
	    		if (format == null || format == '') {
	    			alert("请选则宾馆规格");
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
	    			url : "${ctx}/hotel/addhotel.html",
	    			async : false,
	    			type : 'POST',
	    			cache:false,
	    			data : {
	    				hoteltypeid : hoteltypeid,
	    	    		format : format,
	    	    		provinceid : provinceid,
	    	    		cityid : cityid,
	    	    		areaid : areaid,
	    	    		cost : cost,
	    	    		remark : remark,
	    	    		area : area
	    			},
	    			dataType : 'json',
	    			timeout : 15000,
	    			beforeSend : function() {
    	    			$("#addHotelModel").modal('hide');
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
    		var id = $(this).attr("hotelid");
    		$("#hotelid").val(id);
    		$("#deleteModal").modal('show');
    	});
    	
		$("#deleteBtn").click(function(){
    		
    		var id = $("#hotelid").val();
    		
    		$.ajax({
    			url : "${ctx}/hotel/deletehotel.html",
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
    		

		var hoteltypeid = $.trim($("#hoteltypeid").val());
		var format = $.trim($("#format").val());
    	
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
   			url : "${ctx}/hotel/search.html",
   			async : false,
   			type : 'POST',
   			cache:false,
   			data : {
   				currentPage : i,
   				hoteltypeid : hoteltypeid,
   				format : format
   			},
   			dataType : 'json',
   			timeout : 15000,
   			beforeSend : function() {
   			},
   			complete : function(XMLHttpRequest,textStatus) {
				$(".deleteModelBtn").on("click", function(){
		    		var id = $(this).attr("hotelid");
		    		$("#hotelid").val(id);
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
   			    			hotels : result.pageInfoResult,
   			    			length : result.totalRecord,
   			    			currentPage : result.currentPage,
   			    			totalPage : result.totalPage,
   			    			list : arrayObj
   			    	};
   			    	var hotelsViewHtml = template("hotelsTemplateView", data);
   			    	$("#hotelsTable").html(hotelsViewHtml);
   					
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
	function linedetail(){
		window.location.href = "${ctx}/linedetail/index.html";
    }
	function innerquote(){
		window.location.href = "${ctx}/innerquote/index.html";
    }
	function order(){
		window.location.href = "${ctx}/travelorderls/index.html";
    }
</script>
    
  
</body></html>
