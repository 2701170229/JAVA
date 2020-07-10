<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>edit</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">   
<link href="static/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<link href="static/css/style.css" rel='stylesheet' type='text/css' />
<link href="static/css/font-awesome.css" rel="stylesheet">
<script src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/layui/layui.js"></script>
<link href="static/css/custom.css" rel="stylesheet">
<link href="static/css/templatemo_style.css" rel="stylesheet" type="text/css">	
<script type="text/javascript">
	function submit_form(){
		document.forms["search_form"].submit();
	}
</script>
</head>

<body>
	<div id="wrapper">
		<%@include file="common/nav.jsp" %>
		<div id="page-wrapper">
			<div class="">
		<div class="col-md-12">
			<h1 class="margin-bottom-15">以下是微信付款码，扫码即可支付</h1>
            <div class="form-group">
                <div class="col-md-12">
                    <div class="control-wrapper">
                        <div class="text-center" id="div1">
                            <input type="submit" value="扫码付款"  class="btn btn-success">
                        </div>
                        <div class="text-center" id="div2" style="display: none;">
                            <input type="submit" value="恭喜您，完成扫码支付！"class="btn btn-warning">
                        </div>
                    </div>
                    <div class="text-center">
                        <font color="green">${msgSuccess }</font>
                        <font color="red">${msgFail }</font>
                    </div>
                </div>
            </div>
			<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form" action="user" method="post">
				<input type="hidden" name="action" value="editSubmit">
		        <div class="form-group">
		          <img src="static/image/pay.png" alt="平台赞赏码" style="width: 81%; padding-left: 24%;">
		        </div>
		      </form>
		</div>
	</div>
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
</body>
<script >
    layui.use('layer', function() { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

    });
    var test1 = setTimeout(function(){
        $("#div1").css("display","none");
        $("#div2").css("display","block");
        layer.msg('恭喜您，完成扫码支付！', {time: 4000, icon:6},function () {
            window.location.href="user?action=doTopUp";
        });
        clearTimeout(test1);
    },5000);

</script>
</html>
