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
			<span class="navbar-brand" style="color:#FAFAFA;"> 旅游管理系统</span>
        	<div class="navbar-collapse collapse" style="height: 1px;"></div>
    	</div>
   	</div>
    
        <div class="dialog">
    <div class="panel panel-default">
        <p class="panel-heading no-collapse" style="font-size:17px;">注册</p>
        <div class="panel-body">
        	<div class="form-group">
        		<label>用户名<span style="color:red;">*</span></label>
        		<input type="text" id="username" class="form-control span12">
	        </div>
	        <div class="form-group">
	        	<label>密码<span style="color:red;">*</span></label>
	        	<input type="password" id="password" class="form-control">
	        </div>
	        <div class="form-group">
	        	<label>确认密码<span style="color:red;">*</span></label>
	        	<input type="password" id="cpassword" class="form-control" onmouseover="">
	        </div>
	        <div class="form-group">
		        <label>真实姓名<span style="color:red;">*</span></label>
		        <input type="text" id="realname" class="form-control span12">
	        </div>
	        <div class="form-group">
		        <label>性别</label>
		        <select id="sex" class="form-control">
		              <option value="1">男</option>
		              <option value="2">女</option>
		        </select>
	        </div>
	        <div class="form-group">
		        <label>用户类别</label>
		        <select id="usertype" class="form-control">
		              <option value="0">游客</option>
		              <option value="1">旅行社</option>
		        </select>
	        </div>
	        <div class="form-group">
	        	<label>手机号</label>
	        	<input type="text" id="telephone" class="form-control span12">
	        </div>
	        <div class="form-group">
		        <label>qq</label>
		        <input type="text" id="qq" class="form-control span12">
	        </div>
	        <div class="form-group">
		        <label>Email</label>
		        <input type="email" id="email" class="form-control span12">
	        </div>
	        <div class="form-group">
		        <label>remark</label>
		        <textarea id="remark" class="form-control" ></textarea>
	        </div>
            <div class="form-group">
                <a id="registerBtn" class="btn btn-primary pull-right">注册</a>
            </div>
        </div>
    </div>
</div>

    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        	}
        );
        
      	//给文本框绑定一个失去焦点事件
	    $("#username").focusout(function() {
		    var name = $("#username").val().trim();
		    if(name != null && name != ''){
		    	checkName(name);
	    	}
	    });
	    //发ajax请求到后台判断用户名是否重复
	    function checkName(name){
	    	var type = $("#usertype option:selected").val();
		    $.ajax({
			    url : "${ctx}/admin/checkusername.html",
			    type : "post",
			    dataType : 'JSON',
			    data : {username:name,usertype:type},
			    success : function(response) {
			    	var result = response.status;
				    //已经存在该名字提示用户
				    if(result == 1){
				    	alert("该用户名已经存在");
				    	$("#username").val('');
				    }
			    },
			    error : function(XMLHttpRequest, textStatus, errorThrown) {
    				alert("系统错误！status:[" + XMLHttpRequest.status + "]errorThrown:]" + errorThrown + "]");
    				window.location.reload();
    			}
		    });
	    } 
	    // 密码
	    $("#cpassword").focusout(function() {
		    var password = $("#password").val().trim();
		    var cpassword = $("#cpassword").val().trim();
		    if(!checkpwd(password, cpassword)){
		    	alert("两次输入的密码不一致");
	    	}
	    });
	    
	    function checkpwd(pwd, cpwd) {
	    	if(pwd != cpwd){
		    	return false;
	    	}else{
	    		return true;
	    	}
	    }
	 	// qq
	    $("#qq").focusout(function() {
		    var qq = $("#qq").val().trim();
		    var re = /^[1-9]*[1-9][0-9]*$/;
		    if(telephone != '' && telephone != null && !(re.test(qq))){
		      alert('qq号格式错误');
		      $("#qq").val('');
		    }
	    });
	    // 手机号
	    $("#telephone").focusout(function() {
		    var telephone = $("#telephone").val().trim();
		    var re = /^1[3|4|5|7|8]\d{9}$/;
		    if(telephone != '' && telephone != null && !(re.test(telephone))){
		      alert('手机号格式错误');
		      $("#telephone").val('');
		    }
	    });
	    
	 	// email
	    $("#email").focusout(function() {
		    var email = $("#email").val().trim();
		    var re= /\w@\w*\.\w/;
		    if (email != '' && email != null && !re.test(email)){
		    	alert('email格式错误');
		    	$("#email").val('');
		    }
	    });
        
        $("#registerBtn").click(function(){
        	var username = $("#username").val().trim();
	    	if(username == null || username == ''){
	    		alert("用户名不能为空！");
	    		return;
	    	}
	    	
	    	var password = $("#password").val().trim();
	    	var cpassword = $("#cpassword").val().trim();
	    	if(password == null || password == ''){
	    		alert("密码不能为空！");
	    		return;
	    	}
	    	if(!checkpwd(password, cpassword)){
		    	alert("两次输入的密码不一致");
		    	return;
	    	}
	    	
	    	var realname = $("#realname").val().trim();
	    	if (realname == null || realname == '') {
	    		alert("真实姓名不能为空！");
		    	return;
	    	}
	    	var usertype = $("#usertype option:selected").val();
	    	var ischecked = 1;
	    	if (usertype == 1) {
	    		ischecked = 0;
	    	}
	    	var sex = $("#sex option:selected").val();
	    	var telephone = $("#telephone").val().trim();
	    	var qq = $("#qq").val().trim();
	    	var email = $("#email").val().trim();
	    	var remark = $.trim($("#remark").val());
	    	
	    	$.ajax({
			    url : "${ctx}/register/doregister.html",
			    type : "post",
			    dataType : 'JSON',
			    data : {
			    	username:username,
			    	password:password,
			    	realname:realname,
			    	sex:sex,
			    	telephone:telephone==null?"":telephone,
			    	qq:qq==null?"":qq,
			    	email:email==null?"":email,
			    	remark:remark==null?"":remark,
			    	ischecked:ischecked,
			    	usertype:usertype
			    	},
			    success : function(response) {
			    	var data = eval(response);
				    
				    if(data.status == 0){
				    	alert("保存成功！");
				    	window.location.href = "${ctx}/login/index.html";
				    } else {
				    	alert(data.message);
				    	window.location.reload();
				    }
			    },
			    error : function(XMLHttpRequest, textStatus, errorThrown) {
    				alert("系统错误！status:[" + XMLHttpRequest.status + "]errorThrown:]" + errorThrown + "]");
    				window.location.reload();
    			}
	    	});
        });
    </script>
    
  
</body></html>
