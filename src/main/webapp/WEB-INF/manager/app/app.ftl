<@ms.html5>
	<@ms.nav title="应用设置" back=true>
		<@ms.saveButton id="saveUpdate" postForm="appForm"/>
	</@ms.nav>
	<@ms.panel>
		<@ms.form isvalidation=true name="appForm" id="appForm" action="" redirect="${managerPath}/app/list.do">
			<@ms.hidden name="appId" value="${app.appId?default('')}"/>
			<@ms.text name="appName" width="500" label="网站标题" value="${app.appName?default('')}"  title="网站标题" placeholder="请输入网站标题" validation={"maxlength":"50","required":"true", "data-bv-notempty-message":"必填项目","data-bv-stringlength-message":"网站标题在50个字符以内!"}/>
  			<!--网站Logo,暂时不兼容-->
  			<@ms.formRow label="网站Logo" help="提示:文章缩略图,支持jpg,png格式">
  			  	<@ms.uploadImg path="upload/app/${app.appId}/" inputName="appLogo" size="1"  maxSize="1" imgs="${app.appLogo!('')}"/>
  			</@ms.formRow>
  			<@ms.checkbox name="appMobileStyle" width="200"  list=[{"id":"m","value":"启用"}] listKey="id" listValue="value" valueList=["${app.appMobileStyle!('')}"] label="启用移动端" 
  				help="启用后手机用户访问网站会显示手机版网页，前提是网站必需提供移动端皮肤，相关教程:<a href='http://ms.ming-soft.com/mbbs/13086/detail.do' target='_blank'>铭飞移动端开发教程</a>"/>
			<@ms.textarea name="appUrl" label="域 名" width="400"  rows="4" value="${app.appUrl?default('')}" 
						  placeholder="使用回车换行可以输入多个域名地址，必须要加http:// 域名不要以／\\符号结尾"
						  help="多个域名回车换行显示，必须以http[s]://打头，<br/>例如：http://www.a.com <br/> http://a.com 域名不要以／\\符号结尾"
						  validation={"maxlength":"150","required":"true","data-bv-notempty-message":"必填项目", "data-bv-stringlength-message":"长度在150个字符以内!"} />
			<@ms.select name="appStyle" width="300" id="appStyle" label="模板风格"  />
			<@ms.textarea name="appKeyword"  label="关键字" width="700" value="${app.appKeyword?default('')}" rows="4"  placeholder="请输入关键字"/>
			<@ms.textarea name="appDescription"  label="描述" width="700"  value="${app.appDescription?default('')}" rows="4"  placeholder="请输入站点描述"/>
			
			<@ms.textarea name="appCopyright"  label="版权信息" width="700" value="${app.appCopyright?default('')}" rows="4"  placeholder="请输入版权信息"/>
		</@ms.form>	
	</@ms.panel>
</@ms.html5>	  
<script>
	$(function(){
		<#if app.appId != 0>
				$("#appForm").attr("action","${managerPath}/app/update.do");
				ms.get("${managerPath}/template/queryAppTemplateSkin.do",null,function(msg){
						if(msg.fileName != null && msg.fileName.length != 0){
							//清空默认信息
							$("#appStyle").html("");
				   			for(var i=0; i<msg.fileName.length; i++){
				   				if ("${app.appStyle?default('')}"==msg.fileName[i]) {
				   					$("#appStyle").append("<option value="+msg.fileName[i]+" selected>"+msg.fileName[i]+"</option>")
				   				} else {
					   				$("#appStyle").append("<option value="+msg.fileName[i]+">"+msg.fileName[i]+"</option>")
					   			}
					   		}
				   		}else{
				   			$(".appStyle").append("<option>暂无模版</option>");
				   		}
				});
		<#else>
            $("#appForm").attr("action","${managerPath}/app/save.do");
		</#if>

        //保存提交事件
        $("#saveUpdate").click(function(){
			//alert("123567");
            <#--var btnWord = $(this).text();-->
            <#--if($("#columnListUrlModel").find("option:selected").text()=="暂无文件"){-->
                <#--$("#columnListUrlModel").find("option:selected").text("");-->
            <#--}-->
            <#--if($("#columnUrlModel").find("option:selected").text()=="暂无文件"){-->
                <#--$("#columnUrlModel").find("option:selected").text("");-->
            <#--}-->
            <#--//如果选择的是普通文章，则-->
            <#--if($("select[name=columnContentModelId]").find("option:selected").val()==""){-->
                <#--$("select[name=columnContentModelId]").find("option:selected").val(0)-->
            <#--}-->
            <#--var formdata = $("#columnForm").serialize();-->
            <#--var URL = "";-->
		<#--<#if column.categoryId==0>-->
            <#--URL =base+"${baseManager}/cms/column/save.do";-->
		<#--<#else>-->
            <#--URL = base+"${baseManager}/cms/column/update.do";-->
		<#--</#if>-->
            <#--if(isNaN($("input[name=categorySort]").val())){-->
                <#--alert("自定义排序必须是数字");-->
                <#--$("input[name=categorySort]").val(0);-->
                <#--$("#saveUpdate").css("disabled",false);-->
                <#--return;-->
            <#--}-->

            <#--$.ajax({-->
                <#--type: "post",-->
                <#--url: URL,-->
                <#--data: formdata,-->
                <#--dataType:"json",-->
                <#--beforeSend:function(){-->
                    <#--$("#saveUpdate").attr("disabled",true);-->
                    <#--$("#saveUpdate").text($("#saveUpdate").text()+"中");-->
                    <#--//console.log(formdata);-->
                <#--},-->
                <#--success: function(msg){-->
                    <#--if (msg.result) {-->
					<#--<#if column.categoryId==0>-->
                        <#--alert("保存成功");-->
					<#--<#else>-->
                        <#--alert("更新成功");-->
					<#--</#if>-->
                        <#--location.href=base+"${baseManager}/cms/column/list.do?categoryId="+msg.resultData;-->
                    <#--}else{-->
					<#--<#if column.categoryId==0>-->
                        <#--alert("保存失败");-->
					<#--<#else>-->
                        <#--alert("更新失败");-->
					<#--</#if>-->
                        <#--$("#saveUpdate").attr("disabled",false);-->
                        <#--$("#saveUpdate").text(btnWord)-->
                    <#--}-->
                <#--}-->
            <#--});-->
        });
	});
</script>

	