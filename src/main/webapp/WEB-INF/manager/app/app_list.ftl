<!DOCTYPE html>
<html lang="zh">
 <head>
<#include "${managerViewPath}/include/macro.ftl"/>
<#include "${managerViewPath}/include/meta.ftl"/>
</head>
<body>	
<@ms.content>
<@ms.contentBody>
	<@ms.contentNav title="站点管理" ></@ms.contentNav >
	<@ms.contentPanel>
		<@ms.panelNav >
			<@ms.panelNavBtnGroup>
				<@ms.panelNavBtnAdd/>
			</@ms.panelNavBtnGroup>
		</@ms.panelNav>
		<@ms.table head=['网站标题','域名','描述',"<th class='text-center'>操作</th>"]>
			<#if list?has_content>
				<#list list as item>
					<tr>
			   			<td style="width:30%"><a href="javascript:;" class="editModel" data-id="${item.appId?c?default(0)}">${item.appName?default("")}</a></td>
						<td style="width:30%">${item.appUrl?default("")}</td>
						<td style="width:30%">${item.appDescription?default("")}</td>
						<td class="text-center">
							<a class="btn btn-xs red tooltips del-btn" data-toggle="tooltip" data-id="${item.appId?c?default(0)}"  data-original-title="删除">
								<i class="glyphicon glyphicon-trash"></i>
							</a>
							<a class="btn btn-xs red tooltips editModel" data-toggle="tooltip" data-id="${item.appId?c?default(0)}"  data-original-title="编辑" >
								<i class="glyphicon glyphicon-pencil"></i>
							</a>
						</td>
			   		</tr>
				</#list>
			<#else>
			<tr>
	       	<td colspan="4" class="text-center">
	          		<@ms.nodata content="暂无站点！"/>
				</td>
	       </tr>
			</#if>
		</@ms.table>

    <!--删除的模态框-->
		<@ms.modal id="delete" title="删除提示!">
			<@ms.modalBody>
			  		确认删除？
			</@ms.modalBody>
			<@ms.modalButton>
				<@ms.button value="确认" id="rightDelete" class="btn btn-danger" value="删除"/>
			</@ms.modalButton>
		</@ms.modal>	

	</@ms.contentPanel>
</@ms.contentBody>
</@ms.content>        

<script>

var tmplModelId;
var postUrl;
$(function(){

	//添加模块
    $("#addButton").on("click",function(){
        location.href=base+"${baseManager}/app/add.do";
    });
	
	//编辑模块
	$(".editModel").on("click",function(){
		tmplModelId=$(this).attr("data-id");	//获取点击的id
        location.href=base+"${baseManager}/app/"+tmplModelId+"/edit.do";	//编辑请求地址
	});
	
	//删除模块
	$(".del-btn").on("click",function(){
		tmplModelId=$(this).attr("data-id");
		$(".delete").modal();
	});
	
	//确认删除
	$("#rightDelete").on("click",function(){
		var actionUrl=base+"${baseManager}/app/"+tmplModelId+"/delete.do";
		$(this).text("删除中");
		$(this).attr("disabled",true);
		$("#rightDelete").request({url:actionUrl,type:"json",method:"post",func:function(data) {
			alert("删除成功");
			location.reload();
		}});
	});
	
});


</script>


</body>
</html>
