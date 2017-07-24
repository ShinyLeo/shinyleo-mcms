<!DOCTYPE html>
<html lang="zh">
 <head>
<#include "${managerViewPath}/include/macro.ftl"/>
<#include "${managerViewPath}/include/meta.ftl"/>

</head>
<body style="overflow-x:hidden;overflow-y:scroll;">

<c:if test="${not empty listColumn}">
    <span><p>查询单号：</p></span>
    <table class="table table-bordered table-round">
        <thead>
        <tr>
            <th>序号</th>
            <th>标题</th>
            <th>属性</th>
            <th>链接地址</th>
            <th>列表地址</th>
            <th>内容地址</th>
            <th>封面地址</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="preOrder">
        <c:forEach items="${listColumn}" var="report" varStatus="status">
            <tr>
                <td>${report.categoryCategoryId}</td>
                <td>${report.categoryTitle}</td>
                <td>${report.columnType}</td>
                <td>${report.columnPath}</td>
                <td>${report.columnListUrl}</td>
                <td>${report.columnUrl}</td>
                <td>${report.columnUrl}</td>
                <td>操作</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</c:if>

<script>
var columnId="";
$(function(){
	$("#addButton").on("click",function(){
		location.href=base+"${baseManager}/cms/column/add.do";
	});
	//确认删除
	$(".rightDelete").on("click",function(){
		$(this).request({url: base+"${baseManager}/cms/column/"+columnId+"/delete.do",type:"json",method:"post",func:function(msg) {
			var columnJson = $.parseJSON(msg.resultMsg);
			if(columnJson==false){
				alert("请先删除子栏目");
				$(".delete").modal("hide");
			}else{
				alert("删除成功");
				location.reload();
			}
		}});
	});
});


function editclumnTree(obj){
	var categoryId = $(obj).attr("data-id");
	location.href=base+"${baseManager}/cms/column/"+categoryId+"/edit.do";
}

function deleteclumnTree(obj){
	columnId = $(obj).attr("data-id");
	$(".delete").modal();
}
</script>
</body>



</html>













