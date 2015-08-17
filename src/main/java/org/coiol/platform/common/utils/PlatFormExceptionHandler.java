/**   
* @Project: Platform 
* @Title: PlatFormExceptionHandler.java 
* @Package com.coiol.platform.common.utils 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2015年7月9日 下午12:46:49 
* @Copyright: 2015  
* @version V1.0 
 */
package org.coiol.platform.common.utils;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.coiol.platform.common.exception.BusinessException;
import org.coiol.platform.common.exception.ParameterException;

/** 
 * @ClassName PlatFormExceptionHandler  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2015年7月9日 
 * 
 */
public class PlatFormExceptionHandler implements HandlerExceptionResolver
{

	/* (non-Javadoc)
	 * <p>Title: resolveException</p> 
	 * <p>Description: </p> 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return  
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception) 
	 */
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
	{
		 Map<String, Object> model = new HashMap<String, Object>();  
	        model.put("ex", ex);  
	        // 根据不同错误转向不同页面  
	        if(ex instanceof BusinessException) {  
	            return new ModelAndView("business_error", model);  
	        }else if(ex instanceof ParameterException) {  
	            return new ModelAndView("parameter_error", model);  
	        } else {  
	            return new ModelAndView("error", model);  
	        }  
	}

}
