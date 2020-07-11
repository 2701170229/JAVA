
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>找回密码页面</title>
</head>
<body>
<div style="padding-left: 30%">
    <fieldset  style="margin-top: 20px; width: 25%" >
        <legend>密码找回</legend>
        所要找回的用户名：<input id="username"  required="required"><br>
        接收验证码的邮箱：<input id="email" required="required"><br>
        <input id="register"   type="button" value="发送验证码" style="color: indianred" onclick="send();">
        <input id="login"   type="button" value="去登录" style="color: #00aced" onclick="login();">
    </fieldset>

</div>

</body>
<script type="text/javascript" src="static/js/layui/layui.js"></script>
<script >
    layui.use('layer', function() {
        var $ = layui.jquery;
    });
    function send() {
        var $ = layui.jquery;
        var username=$("#username").val();
        var email=$("#email").val();
        if(null==username||''==username){
            alert("用户名不能为空");
            return;
        }else if(null==email||''==email){
            alert("邮箱不能为空");
            return;
        } else{
            $.ajax({
                url:"login?action=doFindPwd",
                type:'post',
                data:{username:username,email:email},
                success:function(data){
                    if(data==0){
                        alert("动态验证码发送成功");
                        window.location.href="login?action=checkCodePage";
                    }else{
                        alert("动态验证码发送失败，请稍后重试");
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
