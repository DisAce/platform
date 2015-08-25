package org.coiol.platform.aop.aspect;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.coiol.platform.common.utils.MethodUtil;
import org.coiol.platform.common.utils.SessionUtil;
import org.coiol.platform.core.model.BaseLoginLog;
import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.service.BaseLoginLogService;

/**
 * 消息发送拦截器，拦截各版块中需要发消息的方法
 * @author 
 * 
 */
@Aspect
@Component
public class LogAspect {
	
	public static MethodUtil util = new MethodUtil();
	BaseUsers user = new BaseUsers();
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Autowired
	private BaseLoginLogService logService;
	
	
	/**
	 * 登录业务逻辑方法切入点
	 */
    @Pointcut("execution(* org.coiol.platform.web.controller.LoginController.login(..))")
    public void loginServiceCall() { }
	
	/**
	 * 查询业务逻辑方法切入点
	 
    @Pointcut("execution(* com.coiol.platform.service.*.select*(..))")
    public void selectServiceCall() { }
	*/
	/**
	 * 添加业务逻辑方法切入点
	 
    @Pointcut("execution(* com.coiol.platform.service.*.insertBy(..))")
    public void insertServiceCall() { }
    */
    /**
	 * 修改业务逻辑方法切入点
	 
    @Pointcut("execution(* com.coiol.platform.service.*.update*(..))")
    public void updateServiceCall() { }
    */
    /**
	 * 删除影片业务逻辑方法切入点
	 
    @Pointcut("execution(* com.coiol.platform.service.*.delete*(..))")
    public void deleteFilmCall() { }
    */
    
    /**
     * 1 前置通知
     * @param joinPoint
     */
    @Before("loginServiceCall()")
    public void beforeMethod(JoinPoint joinPoint) {
    	logger.info("系统记录日志开始！");
    }
    
    /**
     * 2 后置通知
     
    @After("insertServiceCall()") // 在方法执行之后执行的代码. 无论该方法是否出现异常
    public void afterMethod(JoinPoint joinPoint) {
    	logger.info("系统记录日志结束！");
    }
    */
   
    
    /**
     * 管理员添加操作日志(后置通知)
     * @param joinPoint
     * @param rtv
     * @throws Throwable
     
	@AfterReturning(value="insertServiceCall()", argNames="rtv", returning="rtv")
    public void insertServiceCallCalls(JoinPoint joinPoint, Object rtv) throws Throwable{
		
		//获取登录管理员id
		
		String adminUserId = SessionUtil.get_user();
		
		if(adminUserId == null){//没有管理员登录
			return;
		}
		
		//判断参数
		if(joinPoint.getArgs() == null){//没有参数
			return;
		}
		
		//获取方法名
		String methodName = joinPoint.getSignature().getName();
		
		//获取操作内容
		String opContent = adminOptionContent(joinPoint.getArgs(), methodName);
		
		//创建日志对象
		BaseLoginLog log = new BaseLoginLog();
		log.setId(util.getUid());
		log.setUsername(adminUserId);//设置管理员id
		log.setStatus(SessionUtil.getStatus());
		log.setCreateTime(util.getDate(0,null));//操作时间
		log.setMsg(opContent);//操作内容
		//log.setPageUtil("添加");//操作
		
		logService.insert(log);//添加日志
	}
	*/
	 /**
     * 管理员修改操作日志(后置通知)
     * @param joinPoint
     * @param rtv
     * @throws Throwable
     
	@AfterReturning(value="updateServiceCall()", argNames="rtv", returning="rtv")
    public void updateServiceCallCalls(JoinPoint joinPoint, Object rtv) throws Throwable{
		
		//获取登录管理员id
		String adminUserId = user.getAccount();
		
		if(adminUserId == null){//没有管理员登录
			return;
		}
		
		//判断参数
		if(joinPoint.getArgs() == null){//没有参数
			return;
		}
		
		//获取方法名
		String methodName = joinPoint.getSignature().getName();
		
		//获取操作内容
		String opContent = adminOptionContent(joinPoint.getArgs(), methodName);
		
		//创建日志对象
		BaseLoginLog log = new BaseLoginLog();
		log.setUsername(user.getAccount());//设置管理员id
		log.setCreateTime(util.getDate(0,null));//操作时间
		log.setMsg(opContent);//操作内容
		
		logService.insert(log);//添加日志
	}*/
	
	/**
     * 管理员删除影片操作(环绕通知)，使用环绕通知的目的是
     * 在影片被删除前可以先查询出影片信息用于日志记录
     * @param joinPoint
     * @param rtv
     * @throws Throwable
    
	@Around(value="selectServiceCall()")
	public void selectServiceCallCalls(JoinPoint joinPoint) throws Throwable {
		
	     //环绕通知处理方法
	     try {
	    	
	    	//获取登录管理员id
	 		
	 		String adminUserId = SessionUtil.get_user();
	 		
	 		if(adminUserId == null){//没有管理员登录
	 			return;
	 		}
	 		
	 		//判断参数
	 		if(joinPoint.getArgs() == null){//没有参数
	 			return;
	 		}
	 		
	 		//获取方法名
	 		String methodName = joinPoint.getSignature().getName();
	 		
	 		//获取操作内容
	 		String opContent = adminOptionContent(joinPoint.getArgs(), methodName);
	 		
	 		//创建日志对象
	 		BaseLoginLog log = new BaseLoginLog();
	 		log.setId(util.getUid());
	 		log.setUsername(adminUserId);//设置管理员id
	 		log.setCreateTime(util.getDate(0,null));//操作时间
	 		log.setStatus(SessionUtil.getStatus());
	 		log.setUserAgent(SessionUtil.getUserAgent());
	 		log.setIp(SessionUtil.getIp());
	 		log.setMsg(opContent);//操作内容
	 		
	 		logService.insert(log);//添加日志
	    	
	     }
	     catch(Exception ex) {
	        ex.printStackTrace();
	     }
	     
	}
	 */
	
	/**
	 * 使用Java反射来获取被拦截方法(insert、update)的参数值，
	 * 将参数值拼接为操作内容
	 */
	public String adminOptionContent(Object[] args, String mName) throws Exception{

		if (args == null) {
			return null;
		}
		
		StringBuffer rs = new StringBuffer();
		rs.append(mName);
		String className = null;
		int index = 1;
		// 遍历参数对象
		for (Object info : args) {
			
			//获取对象类型
			className = info.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			rs.append("[参数" + index + "，类型：" + className + "，值：");
			
			// 获取对象的所有方法
			Method[] methods = info.getClass().getDeclaredMethods();
			
			// 遍历方法，判断get方法
			for (Method method : methods) {
				
				String methodName = method.getName();
				// 判断是不是get方法
				if (methodName.indexOf("get") == -1) {// 不是get方法
					continue;// 不处理
				}
				
				Object rsValue = null;
				try {
					
					// 调用get方法，获取返回值
					rsValue = method.invoke(info);
					
					if (rsValue == null) {//没有返回值
						continue;
					}
					
				} catch (Exception e) {
					continue;
				}
				
				//将值加入内容中
				rs.append("(" + methodName + " : " + rsValue + ")");
			}
			
			rs.append("]");
			
			index++;
		}
		
		return rs.toString();
	}
	
	
	/**
     * 记录系统用户的登录日志，使用环绕通知的目的是
     * 在调用登录方法之后执行、记录日志
     * @param joinPoint
     * @throws Throwable
     */
	@After(value="loginServiceCall()")
	public void loginServiceCallCalls(JoinPoint joinPoint) throws Throwable {
		
	     //环绕通知处理方法
	     try {
	    	
	    	//获取登录管理员id
	 		
	 		String adminUserId = SessionUtil.get_user();
	 		
	 		if(adminUserId == null){//没有管理员登录
	 			return;
	 		}
	 		
	 		//创建日志对象
	 		BaseLoginLog log = new BaseLoginLog();
	 		log.setId(util.getUid());
	 		log.setUsername(adminUserId);//设置管理员id
	 		log.setCreateTime(util.getDate(0,null));//操作时间
	 		log.setStatus(SessionUtil.getStatus());
	 		log.setUserAgent(SessionUtil.getUserAgent());
	 		log.setIp(SessionUtil.getIp());
	 		log.setMsg(SessionUtil.getMsg());//操作内容
	 		logService.insert(log);//添加日志
	     }
	     catch(Exception ex) {
	        ex.printStackTrace();
	     }
	     
	}
	
}





