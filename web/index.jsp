
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/7/10
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <div class="box">
    <form action="登录练习.html" method="get">
      <h1>登录</h1>
      <div class="box-item">
        <input type="text" placeholder="请输入您的邮箱" id="emailname" required>
      </div>

      <div class="box-item">
        <input type="password" name="" placeholder="请输入您的电话" required pattern="^1[0-9]+{10}$">
      </div>
      <input class="btn1" type="submit" name="" value="登录">
    </form>
  </div>
  </body>

  </html>

