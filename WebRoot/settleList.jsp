<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>结算订单列表</title>
    <style type="text/css">
        body, table {
            font-size: 12px;
        }
        table {
            table-layout: fixed;
            empty-cells: show;
            border-collapse: collapse;
            margin: 0 auto;
        }

        td {
            height: 30px;
        }

        h1, h2, h3 {
            font-size: 12px;
            margin: 0;
            padding: 0;
        }

        .table {
            border: 1px solid #cad9ea;
            color: #666;
        }

        .table th {
            background-repeat: repeat-x;
            height: 30px;
        }

        .table td, .table th {
            border: 1px solid #cad9ea;
            padding: 0 1em 0;
        }

        .table tr.alter {
            background-color: #f5fafe;
        }
    </style>
</head>
<body>


    <div style="padding-left: 5%; font-size: x-large;">结算列表<div style="float: right;padding-right: 5%"><a href="shopCart">点击进入购物车列表</a></div></div>
    <table width="90%" class="table">
        <tr>
            <th>购买数量</th>
            <th>商品总价</th>
            <th>结算时间</th>
        </tr>
        <c:forEach items="${settleList}" var="settle">
            <tr>
                <td style="text-align: center">${settle.num}</td>
                <td style="text-align: center">${settle.price}</td>
                <td style="text-align: center">${settle.createTime}</td>
            </tr>
        </c:forEach>
    </table>

</body>
<script type="text/javascript" src="static/js/layui/layui.js"></script>
<script>
    layui.use('layer', function () {
        var $ = layui.jquery, layer = layui.layer;
    });
</script>
</html>
