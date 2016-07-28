package com.mingsoft.basic.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingsoft.basic.action.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ms-basic
 * 
 * @author 小小
 * @version 版本号：<br/>
 *          创建日期：2016年4月23日<br/>
 *          历史修订：<br/>
 */
@Controller
@RequestMapping("/${managerPath}/basicType")
public class BasicTypeAction extends BaseAction {

	public void query(HttpServletRequest request,HttpServletResponse response) {
		
	}
}
