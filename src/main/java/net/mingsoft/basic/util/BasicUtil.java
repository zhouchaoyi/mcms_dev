package net.mingsoft.basic.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mingsoft.base.constant.e.BaseCookieEnum;
import com.mingsoft.base.constant.e.BaseEnum;
import com.mingsoft.base.constant.e.BaseSessionEnum;
import com.mingsoft.basic.biz.IAppBiz;
import com.mingsoft.basic.entity.AppEntity;
import com.mingsoft.basic.entity.ModelEntity;
import com.mingsoft.basic.interceptor.BaseInterceptor;
import com.mingsoft.util.StringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class BasicUtil {

	/**
	 * 根据模块编码获得模块编号
	 * 
	 * @param code
	 *            编码
	 * @return 返回模块编号，如果没有返回0
	 */
	public static int getModelCodeId(BaseEnum code) {
		com.mingsoft.basic.biz.IModelBiz modelBiz = (com.mingsoft.basic.biz.IModelBiz) SpringUtil
				.getBean(SpringUtil.getRequest().getServletContext(), "modelBiz");
		ModelEntity model = modelBiz.getEntityByModelCode(code);
		if (model != null) {
			return model.getModelId();
		}
		return 0;
	}

	/**
	 * 获取当前模块对应的appid , appid主要根据用户的请求地址获得
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @return 返回appId，找不到对应app,返回0
	 */
	public static int getAppId() {
		AppEntity app = BasicUtil.getApp();
		if (app != null) {
			return app.getAppId();
		}
		return 0;
	}

	/**
	 * 获取当前模块对应的appid , appid主要根据用户的请求地址获得
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @return 返回appId，找不到对应app,返回0
	 */
	public static AppEntity getApp() {

		// 获取用户所请求的域名地址
		IAppBiz websiteBiz = (IAppBiz) SpringUtil.getBean("appBiz");
		AppEntity website = websiteBiz.getByUrl(BasicUtil.getDomain());
		return website;
	}

	/**
	 * 获取项目路径
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @return 返回项目路径，例如：http://www.ip.com/projectName 后面没有反斜杠，后面接地址或参数必须加/
	 */
	public static String getUrl() {
		HttpServletRequest request = SpringUtil.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName();
		if (request.getServerPort() == 80) {
			basePath += path;
		} else {
			basePath += ":" + request.getServerPort() + path;
		}
		return basePath;
	}

	/**
	 * 获取请求域名，域名不包括http请求协议头
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @return 返回域名地址
	 */
	public static String getDomain() {
		String path = SpringUtil.getRequest().getContextPath();
		String domain = SpringUtil.getRequest().getServerName();
		if (SpringUtil.getRequest().getServerPort() == 80) {
			domain += path;
		} else {
			domain += ":" + SpringUtil.getRequest().getServerPort() + path;
		}
		return domain;
	}

	/**
	 * 默认页码参数
	 */
	private final static String PAGE_NO = "pageNo";

	/**
	 * 默认一页显示数量
	 */
	private final static String PAGE_SIZE = "pageSize";
	private final static String PAGE = "page";

	/**
	 * 分页开始方法，必须配合BasicUtil.endPage()一起使用
	 */
	public static void startPage() {
		PageHelper.startPage(BasicUtil.getInt(PAGE_NO, 1), BasicUtil.getInt(PAGE_SIZE, 10));
	}
	/**
	 * 分页开始方法，必须配合BasicUtil.endPage()一起使用
	 * @param count  是否统计总数 如果不需要分页使用false
	 */
	public static void startPage(boolean count) {
		BasicUtil.startPage(BasicUtil.getInt(PAGE_NO, 1), BasicUtil.getInt(PAGE_SIZE, 10),count);
	}
	
	/**
	 * 分页开始方法，必须配合BasicUtil.endPage()一起使用
	 * @param count  是否统计总数 如果不需要分页使用false
	 * @param pageNo  当前页码
	 * @param pageSize 一页显示数量
	 */
	public static void startPage(int pageNo,int pageSize,boolean count) {
		PageHelper.startPage(BasicUtil.getInt(PAGE_NO, pageNo), BasicUtil.getInt(PAGE_SIZE, pageSize),count);
	}

	@SuppressWarnings("rawtypes")
	public static void endPage(List list, String name) {
		@SuppressWarnings("unchecked")
		PageInfo page = new PageInfo(list);
		SpringUtil.getRequest().setAttribute(BaseInterceptor.PARAMS,
				BasicUtil.assemblyRequestUrlParams(new String[] { PAGE_NO }));
		SpringUtil.getRequest().setAttribute(name, page);
	}

	@SuppressWarnings("rawtypes")
	public static void endPage(List list) {
		BasicUtil.endPage(list, PAGE);
	}

	/**
	 * 获取整型值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param param
	 *            参数名称
	 * @param def
	 *            默认值，如果参数不存在或不符合规则就用默认值替代
	 * @return 返回整型值
	 */
	public static Integer getInt(String param, int def) {
		String value = SpringUtil.getRequest().getParameter(param);
		if (StringUtil.isInteger(value)) {
			return Integer.parseInt(value);
		} else {
			return def;
		}
	}

	public static Integer getInt(String param) {
		return BasicUtil.getInt(param, 0);
	}

	/**
	 * 获取字符串值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param param
	 *            参数名称
	 * @param def
	 *            默认值，如果参数不存在或不符合规则就用默认值替代
	 * @return 返回整型值
	 */
	public static String getString(String param, String def) {
		String value = SpringUtil.getRequest().getParameter(param);
		if (StringUtil.isBlank(value)) {
			value = def;
		}
		return value;
	}

	/**
	 * 获取字符串值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param param
	 *            参数名称
	 * @return 返回整型值
	 */
	public static String getString(String param) {
		return BasicUtil.getString(param, "");
	}

	/**
	 * 获取整型值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param param
	 *            参数名称
	 * @param def
	 *            默认值，如果参数不存在或不符合规则就用默认值替代
	 * @return 返回整型值
	 */
	public static int[] getInts(String param) {
		String[] value = SpringUtil.getRequest().getParameterValues(param);
		if (StringUtil.isIntegers(value)) {
			return StringUtil.stringsToInts(value);
		} else {
			return null;
		}
	}

	private final static String IDS = "ids";

	public static int[] getIds() {
		return BasicUtil.getInts(IDS);
	}

	/**
	 * 将请求的request的参数重新组装。主要是将空值的替换成null,因为requestMap空值是"",这样处理有利于外部判断,
	 * 同时将获取到的值映射到页面上
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @return 返回处理过后的数据
	 */
	public static Map<String, Object> assemblyRequestMap() {
		HttpServletRequest request = SpringUtil.getRequest();
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, String[]> map = request.getParameterMap();
		Iterator<String> key = map.keySet().iterator();
		while (key.hasNext()) {
			String k = (String) key.next().replace(".", "_");
			String[] value = map.get(k);

			if (value == null) {
				params.put(k, null);
				request.setAttribute(k, null);
			} else if (value.length == 1) {
				String temp = null;
				if (!StringUtil.isBlank(value[0])) {
					temp = value[0];
				}
				params.put(k, temp);
				request.setAttribute(k, temp);
			} else if (value.length == 0) {
				params.put(k, null);
				request.setAttribute(k, null);
			} else if (value.length > 1) {
				params.put(k, value);
				request.setAttribute(k, value);
			}
		}
		return params;
	}

	/**
	 * 将请求的request的参数重新组装。主要是将空值的替换成null,因为requestMap空值是"",这样处理有利于外部判断,
	 * 同时将获取到的值映射到页面上
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @return 返回处理过后的数据
	 */
	public static String assemblyRequestUrlParams() {
		return assemblyRequestUrlParams(null);
	}

	/**
	 * 将请求的request的参数重新组装。主要是将空值的替换成null,因为requestMap空值是"",这样处理有利于外部判断,
	 * 同时将获取到的值映射到页面上
	 * 
	 * @param filter
	 *            需要过滤的字段
	 * @return 返回处理过后的数据
	 */

	public static String assemblyRequestUrlParams(String[] filter) {
		Map<String, String[]> map = SpringUtil.getRequest().getParameterMap();
		Iterator<String> key = map.keySet().iterator();
		StringBuffer sb = new StringBuffer();

		while (key.hasNext()) {

			String k = (String) key.next();

			if (filter != null && Arrays.asList(filter).contains(k)) {
				continue;
			}

			String[] value = map.get(k);
			if (value.length == 1) {
				String temp = "";
				if (!StringUtil.isBlank(value[0])) {
					temp = value[0];
				}
				sb.append(k).append("=").append(temp).append("&");
			} else if (value.length > 1) {
				sb.append(k).append("=").append(value).append("&");
			}
		}
		if (sb.length() > 0) {

			return sb.substring(0, sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 移除url参数
	 * 
	 * @param fitlers
	 *            需要移除的字段名称
	 */
	public static void removeUrlParams(String[] fitlers) {
		SpringUtil.getRequest().setAttribute(BaseInterceptor.PARAMS, BasicUtil.assemblyRequestUrlParams(fitlers));
	}

	/**
	 * 获取session的值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param key
	 *            枚举类中的值
	 * @return session中获取的对象
	 */
	public static Object getSession(BaseSessionEnum key) {
		return SpringUtil.getRequest().getSession().getAttribute(key.toString());
	}

	/**
	 * 设置session的值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param key
	 *            枚举类中的值
	 */
	public static void setSession(BaseSessionEnum key, Object value) {
		SpringUtil.getRequest().getSession().setAttribute(key.toString(), value);
	}

	/**
	 * 移除session的值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param key
	 *            枚举类中的值
	 */
	public static void removeSession(BaseSessionEnum key) {
		SpringUtil.getRequest().getSession().removeAttribute(key.toString());
	}

	/**
	 * 获取Cookie的值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param key
	 *            枚举类中的值
	 * @return Cookie中获取的对象
	 */
	public static String getCookie(BaseCookieEnum key) {
		HttpServletRequest request = SpringUtil.getRequest();
		if (request.getCookies() != null) {
			for (Cookie c : request.getCookies()) {
				if (c.getName().equals(key.toString())) {
					return c.getValue();
				}
			}
		}
		return null;
	}
}
