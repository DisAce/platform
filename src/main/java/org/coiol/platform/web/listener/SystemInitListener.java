package org.coiol.platform.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.coiol.platform.common.springmvc.SpringContextHolder;
import org.coiol.platform.common.utils.PropertyHolder;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.service.BaseFieldsService;
import org.coiol.platform.web.controller.LoginController;

/**
 * 初始化系统配置
 * @author eingxin
 *
 */
public class SystemInitListener implements ServletContextListener
{
	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(SystemInitListener.class);
	
	public SystemInitListener()
	{
	}

	public void contextInitialized(ServletContextEvent sce)
	{
		String URL= PropertyHolder.getProperty("system.url");
		if(logger.isDebugEnabled()){
			logger.debug("项目路径为========================================="+URL);
		}
		
		ServletContext servletContext = sce.getServletContext();
		BaseFieldsService baseFieldsService = (BaseFieldsService)SpringContextHolder.getBean("baseFieldsServiceImpl");
		Criteria criteria = new Criteria();
		criteria.setOrderByClause(" field desc ,sort asc ");
		criteria.put("enabled", "1");
		servletContext.setAttribute("fields", baseFieldsService.selectAllByExample(criteria));
	}

	public void contextDestroyed(ServletContextEvent servletcontextevent)
	{
	}
}
