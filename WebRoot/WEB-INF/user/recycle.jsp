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
<title>MyDisk</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="static/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<link href="static/css/style.css" rel='stylesheet' type='text/css' />
<link href="static/css/font-awesome.css" rel="stylesheet">
<link href="static/css/custom.css" rel="stylesheet">

<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function share(id){
		$("#share_id").val(id);
		$("#shareModal").modal('show');
	}

    function dele(){
        var ids = $('input[name="ids"]');
        var haveDeleFile = false;
        ids.each(function(){
            if($(this).prop('checked')==true){
                haveDeleFile = true;
            }
        });
        if(haveDeleFile == true){
            $("#deleModal").modal('show');
        }else{
            alert("未选择要删除的文件！");
        }
    }
    function reduction(){
        var ids = $('input[name="ids"]');
        var haveDeleFile = false;
        ids.each(function(){
            if($(this).prop('checked')==true){
                haveDeleFile = true;
            }
        });
        if(haveDeleFile == true){
            $("#reductionModal").modal('show');
        }else{
            alert("未选择要还原的文件！");
        }
    }
	function delete_submit_form(){
		document.forms["dele_form"].submit();
	}
	function reduction_submit_form(){
	    $("#action").val("reductionFile");
        document.forms["dele_form"].submit();
        <%--var ids = $('input[name="ids"]');--%>
        <%--console.log(ids);--%>
        <%--$.ajax({--%>
            <%--type:"post",--%>
            <%--url:"<%=path%>/user",--%>
            <%--data:{ action:'reductionFile',ids:ids},--%>
            <%--dataType:"json",--%>
            <%--success:function(data) {--%>
                <%--if(data==0) {--%>
                    <%--alert('操作成功');--%>
                <%--} else {--%>
                    <%--alert('系统异常，请稍候重试');--%>
                <%--}--%>
                <%--location.reload();--%>
            <%--},--%>
            <%--error:function(msg) {--%>
                <%--alert(msg);--%>
            <%--}--%>
        <%--});--%>
	}
	function select_all(){
		var check = $("#chk_all");
		var ids = $('input[name="ids"]');
			if(check.prop('checked'))
			{
				ids.prop("checked", true);
			}
			else
			{
				ids.prop("checked", false);
				check.prop("checked", false);
			}
	}
</script>
</head>
<body>
	<div id="wrapper">
		<%@include file="common/nav.jsp" %>
		<div id="page-wrapper">
			<div class="col-md-12 graphs">
				<div class="xs">
					<h3>我的回收站</h3>
					<div class="bs-example4" data-example-id="contextual-table">
						<button class="btn btn-danger" onclick="dele()">彻底删除所选项</button>
						<button class="btn btn-primary" onclick="reduction()">还原</button>
						
						<table class="table">
							<thead>
								<tr>
									<th><input type="checkbox" id="chk_all" onchange="select_all()"></th>
									<th>文件名</th>
									<th>大小</th>
									<th>删除日期</th>
								</tr>
							</thead>
							<form action="user" id="dele_form" method="post">
							<input type="hidden" id="action" name="action" value="recycleDel">
							<tbody>
							<c:forEach items="${fileList }" var="userFile">
								<tr>
									<th scope="row"><input type="checkbox" name="ids" value="${userFile.id }"></th>
									<td>${userFile.filename }</td>
									<td>${userFile.fileSize }</td>
									<td>${userFile.deleteTime }</td>
								</tr>
							</c:forEach>
							</tbody>
							</form>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
<!--删除 模态框（Modal） -->
<div class="modal fade" id="deleModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
          		   文件删除
            </h4>
         </div>
         <div class="modal-body">
    		       确定要删除？
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <a class="btn btn-danger" onclick="delete_submit_form()">确定删除</a>
         </div>
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
    <div class="modal fade" id="reductionModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="reductionLabel">
                        文件还原
                    </h4>
                </div>
                <div class="modal-body">
                    确定要还原？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <a class="btn btn-danger" onclick="reduction_submit_form()">确定还原</a>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</body>
</html>
