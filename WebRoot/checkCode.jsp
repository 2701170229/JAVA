
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>邮箱验证码页面</title>
</head>
<body>
<div style="padding-left: 30%">
    <fieldset  style="margin-top: 20px; width: 20%" >
        <legend>邮箱验证码</legend>
        用户名：<input id="username"  required="required"><br>
        验证码：<input id="authCode" required="required"><br>
        <input id="register"   type="button" value="验证" style="color: indianred" onclick="register();">
        <input id="login"   type="button" value="去登录" style="color: #00aced" onclick="login();">
    </fieldset>

</div>

</body>
<script type="text/javascript" src="static/js/layui/layui.js"></script>
<script >
    layui.use('layer', function() {
        var $ = layui.jquery, layer = layui.layer;
    });
    function register() {
        var $ = layui.jquery, layer = layui.layer;
        var username=$("#username").val();
        var authCode=$("#authCode").val();
        if(null==username||''==username){
            alert("用户名不能为空");
            return;
        }else if(null==authCode||''==authCode){
            alert("验证码不能为空");
            return;
        } else{
            $.ajax({
                url:"login?action=checkCode",
                type:'post',
                data:{username:username,authCode:authCode},
                success:function(data){
                    if(data==0){
                        alert("动态码验证成功");
                        window.location.href="login";
                    }else{
                        alert("动态码验证失败，请稍后重试");
                    }
                }
            });
        }
    }

    //登录跳转
    function login() {
        window.location.href="login";
    }
</script>
</html>
