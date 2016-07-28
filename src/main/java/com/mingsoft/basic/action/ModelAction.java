package com.mingsoft.basic.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingsoft.basic.action.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.biz.IManagerBiz;
import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.constant.ModelCode;
import com.mingsoft.basic.entity.ManagerEntity;
import com.mingsoft.basic.entity.ModelEntity;
import com.mingsoft.util.StringUtil;

/**
 * 模块控制层
 * @author 史爱华
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2014-6-29<br/>
 * 历史修订：<br/>
 */
@Controller
@RequestMapping("/${managerPath}/model")
public class ModelAction extends BaseAction {

	/**
	 * 注入模块业务层
	 */
	@Autowired
	private IModelBiz modelBiz;
	
	@Autowired
	private IManagerBiz managerBiz;



	
	/**
	 * 根据管理员ID查询模块集合
	 * @param managerId 管理员id
	 * @param request 请求对象
	 * @param response 响应对象
	 */
	@RequestMapping("/{managerId}/queryModelByRoleId")
	public void queryModelByRoleId(@PathVariable int managerId,HttpServletRequest request, HttpServletResponse response) {
		ManagerEntity manager =(ManagerEntity) managerBiz.getEntity(managerId);
		if(manager==null){
			return;
		}
		List<BaseEntity> modelList = new ArrayList<BaseEntity>();
		modelList = modelBiz.queryModelByRoleId(manager.getManagerRoleID());
		this.outJson(response, null,true, JSONObject.toJSONString(modelList));

	}
}
