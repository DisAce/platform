package org.coiol.platform.common.utils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;


public final class SessionUtil {
	protected static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(SessionUtil.class);
	private static final String USER = "user";    //用户
	private static String _user = "Anonymous";
	private static String userAgent;  //客户端
	private static String ip;  //IP
	private static String msg; //信息
	private static Integer status; //状态
	private static final String VERIFY_CODE = "KAPTCHA_SESSION_KEY";//验证码
   // private static final List<String> authUrls;
  
	/**
	  * 设置session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static void setAttr(HttpServletRequest request,String key,Object value){
		 request.getSession(true).setAttribute(key, value);
	 }
	 
	 
	 /**
	  * 获取session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static Object getAttr(HttpServletRequest request,String key){
		 return request.getSession(true).getAttribute(key);
	 }
	 
	 /**
	  * 删除Session值
	  * @param request
	  * @param key
	  */
	 public static void removeAttr(HttpServletRequest request,String key){
		 request.getSession(true).removeAttribute(key);
	 }
	 
	 /**
	  * 设置用户信息 到session
	  * @param request
	  * @param user
	  */
	 public static void setUser(HttpServletRequest request,Object user){
		 request.getSession(true).setAttribute(USER, user);
	 }
	 
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return SysUser
	  */
	 public static Object getUser(HttpServletRequest request){
		return request.getSession(true).getAttribute(USER);
	 }
	 
	 
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return SysUser
	  */
	 public static void removeUser(HttpServletRequest request){
		removeAttr(request, USER);
	 }
	 
	 
	 /**
	  * 设置验证码 到session
	  * @param request
	  * @param user
	  */
	 public static void setVerifyCode(HttpServletRequest request,String sessionVerifyCode){
		 request.getSession(true).setAttribute(VERIFY_CODE, sessionVerifyCode);
	 }
	 
	 
	 /**
	  * 从session中获取验证码
	  * @param request
	  * @return SysUser
	  */
	 public static String getVerifyCode(HttpServletRequest request){
		return (String)request.getSession(true).getAttribute(VERIFY_CODE);
	 }
	 
	 
	 /**
	  * 从session中获删除验证码
	  * @param request
	  * @return SysUser
	  */
	 public static void removeVerifyCode(HttpServletRequest request){
		removeAttr(request, VERIFY_CODE);
	 }

	/**
	 * 
	 * <br>
	 * <b>功能：</b>清除所有Session<br>
	 * <b>作者：</b>EingXin<br>
	 * <b>日期：</b> 2014-10-15 <br>
	 * 
	 * @param request
	 */
	public static void removeSessionAll(HttpSession session) {
		if (session != null) {
			java.util.Enumeration<?> e = session.getAttributeNames();
			while (e.hasMoreElements()) {
				String sessionName = (String) e.nextElement();
				System.out.println("removeSessionName:" + sessionName);
				session.removeAttribute(sessionName);
			}
		}
	}

	 /**
	  * 获取登录用户名的值
	  * @param request
	  * @param key
	  * @param value
	  */
	public static String get_user() {
		return _user;
	}


	public static void set_user(String _user) {
		SessionUtil._user = _user;
	}


	 /**
	  * 获取登录用户客户端信息的值
	  * @param request
	  * @param key
	  * @param value
	  */
	public static String getUserAgent() {
		return userAgent;
	}


	public static void setUserAgent(String userAgent) {
		SessionUtil.userAgent = userAgent;
	}


	/**
	  * 获取登录用户IP信息的值
	  * @param request
	  * @param key
	  * @param value
	  */
	public static String getIp() {
		return ip;
	}


	public static void setIp(String ip) {
		SessionUtil.ip = ip;
	}


	/**
	  * 获取处理日志信息信息的值
	  * @param request
	  * @param key
	  * @param value
	  */
	public static String getMsg() {
		return msg;
	}


	public static void setMsg(String msg) {
		SessionUtil.msg = msg;
	}

	/**
	  * 获取登录状态的值
	  * @param request
	  * @param key
	  * @param value
	  */

	public static Integer getStatus() {
		return status;
	}


	public static void setStatus(Integer status) {
		SessionUtil.status = status;
	}

}
