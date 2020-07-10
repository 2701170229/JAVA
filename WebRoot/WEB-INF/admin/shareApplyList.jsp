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
<title>MyShare</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="static/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<link href="static/css/style.css" rel='stylesheet' type='text/css' />
<link href="static/css/font-awesome.css" rel="stylesheet">
<link href="static/css/custom.css" rel="stylesheet">

<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/js/layui/layui.js"></script>
<script type="text/javascript">
	function cancelShare(id){
		$("#cancelModal").modal('show');
		$("#share_id").val(id);
	}
</script>
</head>
<body>
	<div id="wrapper">
		<%@include file="common/nav.jsp" %>
		<div id="page-wrapper">
			<div class="col-md-12 graphs">
				<div class="xs">
					<h3>用户共享申请列表</h3>
					<div class="bs-example4" data-example-id="contextual-table">
						<table class="table">
							<thead>
								<tr>
									<th>文件名</th>
									<th>大小</th>
									<th>申请日期</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${fileList }" var="userFile">
								<tr>
									<td>${userFile.filename }</td>
									<td>${userFile.fileSize }</td>
									<td>${userFile.sharedTime }</td>
									<td>
                                        <a class="btn btn-primary" href="javascript:audit('${userFile.id}',1);">通过</a>
                                        <a class="btn btn-danger" href="javascript:audit('${userFile.id}',3);">拒绝</a>
                                    </td>
								</tr>
							</c:forEach>
							</tbody>

						</table>
					</div>
				</div>
			</div>
		</div>
       
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
    <script >
        layui.use('layer', function() { 
            var $ = layui.jquery, layer = layui.layer; 
        });
        
        function audit(fileId,status) {
            if(status==1){
                layer.confirm("确认审核通过吗？",function () {
                    $.post('${pageContext.request.contextPath}/admin', {fileId:fileId,status:1,sharedReason:"",action:'auditShare'},
                        function (data) {
                            layer.msg('审核操作成功！', {icon:6,time:3000},function () {
                                location.reload();
                            });
                        }
                    );
                })
            }else if(status==3){
                layer.prompt({title: '请输入审核拒绝原因'}, function(pass, index){
                    layer.close(index);
                    $.post('${pageContext.request.contextPath}/admin',
                        {fileId:fileId,status:3,sharedReason:pass,action:'auditShare'},
                        function (data) {
                            layer.msg('审核操作成功！', {icon:6,time:3000},function () {
                                location.reload();
                            });
                        }
                    );
                });
            }
        }
    </script>
</body>
</html>
