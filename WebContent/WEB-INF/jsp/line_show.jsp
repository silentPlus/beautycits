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
            
			<div class="btn-toolbar list-toolbar">
				<div>
	            	<table class="table" style="text-align:center;">
	            		<tr>
	            			<td width="10%" align="right" style="border-top:none;">线路类型</td>
	            			<td width="40%" align="left" style="border-top:none;">
	            				<select id="llinetypeid" class="form-control">
	            					  <option value="" checked="checked">请选择</option>
           					    </select>
            				</td>
            				<td align="right" style="border-top:none;">线路名称</td>
	            			<td align="left" style="border-top:none;">
	            				<input id="linename" class="form-control" />
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
			
			  <div id="lineShowsTable">
			  <script id="lineShowsTemplateView" type="text/html">
			  <div id="lineShow">
				<table width="100%">
			  {{ each lineShows as lineShow i }}
				<tr>
					<td style="border-bottom: 1px solid;padding-top:5px;">
			    	  <div class="post-summary">
						<h2 style="margin-top:0px;">
							<a onclick="getDetail({{lineShow.id}});">{{lineShow.linetype}}--{{lineShow.linename}}</a>
						</h2>
						<p class="text-sm">发布时间：{{lineShow.publishtime}}</p>
						<p>
							天数：{{lineShow.day}}天
						</p>
						<p>
							去时交通：{{lineShow.govehicle}}<br>
							回时交通：{{lineShow.backvehicle}}
						</p>
						<p>
							<a class="btn btn-default btn-sm" onclick="getDetail({{lineShow.id}});">了解更多</a>
							<a class="btn btn-default btn-sm" onclick="baoming({{lineShow.id}});">报名</a>
						</p>
					  </div>
					</td>
				</tr>
			  {{ /each }}
				</table>
			  </div>
			
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

        </div>
    </div>
	<input type="hidden" id = "currentPage" value="1" />
	<input type="hidden" id = "linetypeid" value="" />

    <script type="text/javascript">
	    $(function(){
			
	    	var lineShowsPage = ${lineShowsPage};
	    	var arrayObj = new Array(lineShowsPage.totalPage);
	    	for (var i=0; i<lineShowsPage.totalPage; i++){
	    		arrayObj[i] = i;
	    	}
	    	data = {
	    			lineShows : lineShowsPage.pageInfoResult,
	    			length : lineShowsPage.totalRecord,
	    			currentPage : lineShowsPage.currentPage,
	    			totalPage : lineShowsPage.totalPage,
	    			list : arrayObj
	    	};
	    	var lineShowsViewHtml = template("lineShowsTemplateView", data);
	    	$("#lineShowsTable").html(lineShowsViewHtml);
	    	
	    	var listLineType = ${listLineType};
	    	for (i = 0; i < listLineType.length; i++) {
	    		$('#llinetypeid').append('<option value="' + listLineType[i].id + '">'+ listLineType[i].name + '</option>');
    		} 
	    	
        $("[rel=tooltip]").tooltip();
    /*     $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        }); */
    });
	    
	    
	    
	function dosearch(i){
    		
		var linetypeid = $.trim($("#llinetypeid").val());
		var linename = $.trim($("#linename").val());
    	
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
   			url : "${ctx}/lineshow/search.html",
   			async : false,
   			type : 'POST',
   			cache:false,
   			data : {
   				currentPage : i,
   				linetypeid : linetypeid,
   				linename : linename
   			},
   			dataType : 'json',
   			timeout : 15000,
   			beforeSend : function() {
   			},
   			complete : function(XMLHttpRequest,textStatus) {
   				function getDetail(id) {
   					window.location.href = "${ctx}/lineshow/detail.html?linedetailid=" + id;
   				}
   				function baoming(id){
   					window.location.href = "${ctx}/travelquote/index.html?linedetailid=" + id;
   				}
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
   			    			lineShows : result.pageInfoResult,
   			    			length : result.totalRecord,
   			    			currentPage : result.currentPage,
   			    			totalPage : result.totalPage,
   			    			list : arrayObj
   			    	};
   			    	var lineShowsViewHtml = template("lineShowsTemplateView", data);
   			    	$("#lineShowsTable").html(lineShowsViewHtml);
   					
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
	
	function getDetail(id) {
		window.location.href = "${ctx}/lineshow/detail.html?linedetailid=" + id;
	}
	
	function login(){
		window.location.href = "${ctx}/login/index.html";
	}
	
	function baoming(id){
		window.location.href = "${ctx}/traveluser/index.html?linedetailid=" + id;
	}
	
</script>
    
  
</body></html>
