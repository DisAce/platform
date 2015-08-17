package org.coiol.platform.web.interseptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import org.coiol.platform.common.utils.SessionUtil;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;


public class LoginInterceptor implements HandlerInterceptor
{
	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(LoginInterceptor.class);
	
	private List<String> excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
	public LoginInterceptor()
	{
	}
	
	/**
	 * 在调用controller具体方法前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception
	{
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		logger.info(url);

			if (excludeUrls.contains(url)) {
				return true;
			} else {
				//SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
				String isAdmin=(String) SessionUtil.getAttr(request, "isAdmin");
				if (null!=isAdmin && isAdmin.equals("0")) {// 超管不需要验证权限
					return true;
				} else {
					@SuppressWarnings("unchecked")
					List<String> urls = (List<String>) SessionUtil.getAttr(request, "authUrls");
					if (null!=urls && urls.contains(url)) {
						return true;
					} else {
						System.out.println("[" + url + "]-无访问权限->请联系管理员或登录!");
						request.setAttribute("url", url);
						request.getRequestDispatcher("/intercept").forward(request, response);
						return false;
					}
				}
			}
	}


	/**
	 * 在调用controller具体方法后拦截
	 */
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 完成页面的render后调用
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
