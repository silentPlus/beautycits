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

    <link href='http://fonts.useso.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="${ctx}/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${ctx}/lib/font-awesome/css/font-awesome.css">

    <script src="${ctx}/lib/jquery-1.11.1.min.js" type="text/javascript"></script>

    

    <link rel="stylesheet" type="text/css" href="${ctx}/stylesheets/theme.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/stylesheets/premium.css">

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
        .navbar-default .navbar-brand .navbar-default .navbar-brand:hover { 
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
          <a class="" href="">
	          <span class="navbar-brand" style="color:#FAFAFA;">
		          <!-- <span class="fa fa-paper-plane"></span> -->
		                    旅游管理系统
	          </span>
          </a>
        </div>

        <div class="navbar-collapse collapse" style="height: 1px;">

        </div>
    </div>
    <div class="dialog">
	    <div class="panel panel-default">
	        <p class="panel-heading no-collapse">登录</p>
	        <div class="panel-body">
                <div>
                    <label>用户名</label>
                    <input id="username" type="text" class="form-control span12">
                </div>
                <div>
                	<label>密码</label>
                    <input id="password" type="password" class="form-control span12 form-control">
                </div>
                <br/>
                <button id="loginbtn" class="btn btn-primary pull-right">登录</button>
                <div class="clearfix"></div>
	        </div>
	    </div>
	    <!-- 先屏蔽，后期有时间可作为拓展功能 -->
	    <!-- <p><a href="reset-password.html">Forgot your password?</a></p> -->
	</div>
    <script src="${ctx}/lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    <script type="text/javascript">
    
    $($("#loginbtn").click(function(){
    		var username = $("#username").val().trim();
    		var password = $("#password").val().trim();
    		if (username == null || username == '') {
    			alert("用户名不能为空！");
    			$("#username").val("");
    			return ;
    		}
    		if (password == null || password == '') {
    			alert("密码不能为空！");
    			$("#password").val("");
    			return ;
    		}
    		
    		$.ajax({
    			url : "${loginUrl}",
    			async : false,
    			type : 'POST',
    			cache:false,
    			data : {
    				username : username,
    				password : username
    			},
    			dataType : 'json',
    			timeout : 15000,
    			beforeSend : function() {
    			},
    			complete : function(XMLHttpRequest,textStatus) {
    				/* if(textStatus=="parsererror"){
    		               $.messager.alert('提示信息', "登陆超时！请重新登陆！", 'info',function(){
    		                   window.location.href = 'login.jsp';
    		               });
    		          } else if(textStatus=="error"){
    		              $.messager.alert('提示信息', "请求超时！请稍后再试！", 'info');
    		          } */
    			},
    			error : function() {
    				alert("error");
    			},
    			success : function(response) {
    				var json = eval(response);
    				if (0===json.status){
    					url = json.result;
    					window.location.href = "${ctx}/aaa/bbb.html";
                    } else if (1===json.status){
                        alert(json.message);
                        window.location.reload();
                    }
    			}
    		});
    	})
    	);
    </script>
</body>
</html>
