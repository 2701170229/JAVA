
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车登录页面</title>
</head>
<body>
<div style="padding-left: 30%">
    <fieldset  style="margin-top: 20px; width: 20%" >
        <legend>登录</legend>
        用户名：<input id="username"  required="required"><br>
        密&nbsp;&nbsp;&nbsp; 码：<input id="pwd" required="required"><br>
        <input id="login"   type="button" value="登录" style="color: #00aced" onclick="login();">
        <input id="register"   type="button" value="去注册" style="color: indianred" onclick="register();">
        <input     type="button" value="找回密码" style="color: cadetblue" onclick="findPwd();">
    </fieldset>

</div>

</body>
<script type="text/javascript" src="static/js/layui/layui.js"></script>
<script >
    layui.use('layer', function() {
        var $ = layui.jquery, layer = layui.layer;
    });
    function login() {
        var $ = layui.jquery, layer = layui.layer;
        var username=$("#username").val();
        var pwd=$("#pwd").val();
        if(null==username||''==username){
            alert("用户名不能为空");
            return;
        }else if(null==pwd||''==pwd){
            alert("密码不能为空");
            return;
        }else{
            $.ajax({
                url:"login?action=login",
                type:'post',
                data:{username:username,pwd:pwd},
                success:function(data){
                    if(data==0){
                        window.location.href="goodsList";
                    }else{
                        alert("登录失败，账号或者密码错误");
                    }
                },
                complete: function () {
                    layer.close(this.layerIndex);
                },
            });
        }
    }

    //注册跳转
    function register() {
        window.location.href="register";
    }

    function findPwd() {
        window.location.href="login?action=findPwd";
    }
</script>
</html>
