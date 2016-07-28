<@ms.html5>
    <@ms.nav title="${modelTitle}编辑">
		<@ms.saveButton id="saveFormButton" postForm="categoryForm"/>
    </@ms.nav>
    <@ms.panel>
   			<@ms.form isvalidation=true name="categoryForm"   action="${managerPath}/category/${autoCURD}.do"  redirect="${managerPath}/category/list.do?modelId=${modelId}&modelTitle=${modelTitle}&categoryLevel=${category.categoryLevel}">
				    		<input type="hidden" name="categoryId" id="categoryId"  value="${category.categoryId}"/>
				    		<@ms.text name="categoryTitle" width="300" label="名称:" title="类别名称" maxlength="30"  placeholder="类别名称"  
				    		validation={"required":"true", "data-bv-notempty-message":"请填写栏目类别"} value="${category.categoryTitle?default('')}"/>
				    		<@ms.textarea id="description"  name="categoryDescription" label="描述:"  title="栏目描述" placeholder="类别描述" maxlength="150" value="${category.categoryDescription?default('')}"/>
				    		
				    		<@ms.formRow label="缩略图">
								<@ms.uploadImg path="upload/${manager_session.basicId?default('0')}/app/" inputName="categorySmallImg" size="15" filetype="" msg=""  maxSize="1" imgs="${category.categorySmallImg?default('')}" />
				    		</@ms.formRow>
				    		<#if category.categoryLevel gt 0>
					    		<@ms.formRow label="缩略图" width="300">
					            	<@ms.inputTree  
					            		treeId="inputTree" 
					            		json="${listCategory?default('')}" 
					            		jsonId="categoryId" 
					            		jsonPid="categoryCategoryId" 
					            		jsonName="categoryTitle"
					            		name="categoryCategoryId"
					            		rootName="顶级节点"
					            		required=false
					            		text="${modelName?default('请选择关联节点')}"
					            		value="${category.categoryCategoryId?default('0')}"
					            		onclick="isSelf"
					            		parent=true
					            	/>				    		
					    		</@ms.formRow>
				    		</#if>
	    	</@ms.form>	
    </@ms.panel>
</@ms.html5>
<script> 
	function isSelf(event,treeId,treeNode) {
		if (treeNode.categoryId==${category.categoryId}) {
			<@ms.notify msg="不能选择当前节点！" />
			return false;
		}
		
		//检查编辑的时候是否将父节点移动到子节点下面
		var nodes = zTreeObjinputTree.getNodesByParam("categoryId", treeNode.categoryId, zTreeObjinputTree.getNodeByParam("categoryId", ${category.categoryId}, null));
		if (nodes.length > 0) {
			<@ms.notify msg="不支持父节点移动到子节点！" />
			return false;
		}
		return true;
	}
</script>