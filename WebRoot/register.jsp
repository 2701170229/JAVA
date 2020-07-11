
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车注册页面</title>
</head>
<body>
<div style="padding-left: 30%">
    <fieldset  style="margin-top: 20px; width: 20%" >
        <legend>注册</legend>
        用户名：<input id="username"  required="required"><br>
        密&nbsp;&nbsp;&nbsp; 码：<input id="pwd" required="required"><br>
        确认密码：<input id="pwd2" required="required"><br>
        <input id="register"   type="button" value="注册" style="color: indianred" onclick="register();">
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
        var pwd=$("#pwd").val();
        var pwd2=$("#pwd2").val();
        if(null==username||''==username){
            alert("用户名不能为空");
            return;
        }else if(null==pwd||''==pwd){
            alert("密码不能为空");
            return;
        }else if(null==pwd2||''==pwd2){
            alert("确认密码不能为空");
            return;
        }else if(pwd2!=pwd){
            alert("两次输入密码不一致");
            return;
        }else{
            $.ajax({
                url:"register?action=register",
                type:'post',
                data:{username:username,pwd:pwd},
                success:function(data){
                    if(data==0){
                        alert("注册成功");
                        window.location.href="login";
                    }else{
                        alert("注册失败，账号已经存在");
                    }
                },
                complete: function () {
                    layer.close(this.layerIndex);
                },
            });
        }
    }

    //登录跳转
    function login() {
        window.location.href="login";
    }
</script>
</html>
