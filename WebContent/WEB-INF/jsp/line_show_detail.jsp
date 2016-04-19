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
	            				<button onclick="baoming(${lineShow.id});" class="btn btn-default">报名</button>
	            			</td>
	            		</tr>
	            	</table>
            	</div>
		  		<div class="btn-group">
		  		</div>
			</div>
			
			  <div id="lineShowDetailsTable">
			  <script id="lineShowDetailsTemplateView" type="text/html">
			  <div id="lineShow">
				<table width="100%">
			  {{ each listSchedule as schedule i }}
				<tr>
					<td style="border-bottom: 1px solid;padding-top:5px;">
			    	  <div class="post-summary">
						<h3 style="margin-top:0px;">
							第{{schedule.day}}天
						</h3>
						<p>
							{{ if schedule.morestaurant != null && schedule.morestaurant != '' }}
							早饭：{{schedule.morestaurant}}<br>
							{{/if}}
							{{ if schedule.lurestaurant != null && schedule.lurestaurant != '' }}
							午饭：{{schedule.lurestaurant}}<br>
							{{/if}}
							{{ if schedule.direstaurant != null && schedule.direstaurant != '' }}
							晚饭：{{schedule.direstaurant}}<br>
							{{/if}}
						</p>
						<p>
							{{ if schedule.hotel != null && schedule.hotel != '' }}
							住宿：{{schedule.hotel}}<br>
							{{/if}}
							{{ if schedule.bus != null && schedule.bus != '' }}
							用车：{{schedule.bus}}
							{{/if}}
						</p>
						<p>
						{{ if mapTicket[schedule.id].length > 0 }}
							景点：
						{{ each mapTicket[schedule.id] as scheduleTicket }}
							{{scheduleTicket.ticket}}&nbsp;&nbsp;&nbsp;&nbsp;
						{{/each}}
						{{ /if }}
						</p>
					  </div>
					</td>
				</tr>
			  {{ /each }}
				</table>
			  </div>
			
			</script>
			</div>

        </div>
    </div>
	<input type="hidden" id = "linedetailid" value="${lineShow.id }" />

    <script type="text/javascript">
	    $(function(){
			
	    	var listSchedule = ${listSchedule};
	    	var mapTicket = ${mapTicket};
	    	data = {
	    			listSchedule : listSchedule,
	    			mapTicket : mapTicket
	    	};
	    	console.log(data);
	    	var lineShowDetailsViewHtml = template("lineShowDetailsTemplateView", data);
	    	$("#lineShowDetailsTable").html(lineShowDetailsViewHtml);
	    	template.helper('getTicketList', function (map,id) {
				return	map[id];
			});
        $("[rel=tooltip]").tooltip();
	    
	    });
		function login(){
			window.location.href = "${ctx}/login/index.html";
		}
		
		function back(){
			window.history.back();
		}
		
		function baoming(id){
			window.location.href = "${ctx}/traveluser/index.html?linedetailid=" + id;
		}
	
</script>
    
  
</body></html>
