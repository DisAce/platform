package org.coiol.platform.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.springmvc.DateConvertEditor;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseLoginLog;
import org.coiol.platform.core.model.BaseModules;
import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.core.model.ExceptionReturn;
import org.coiol.platform.core.model.ExtGridReturn;
import org.coiol.platform.core.model.PageParams;
import org.coiol.platform.service.BaseLoginLogService;
import org.coiol.platform.service.BaseModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/admin/syslog/"})
public class SysLogController {
	
	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(SysLogController.class);
	
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private BaseLoginLogService baseLogService;
	
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private BaseModulesService baseModulesService;
	
	public SysLogController(){
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	/**
	 * 获取用户操作权限
	 * @param id
	 * @param session
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = { "/index" },method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String module(String id,HttpSession session,  ModelMap modelMap)
	{
		List<String> buttons = new java.util.ArrayList<String>();
		try {
		BaseUsers user = (BaseUsers) session.getAttribute("CURRENT_USER");
		Criteria criteria = new Criteria();
		criteria.put("parent", id);
		criteria.put("userId", user.getUserId());
		criteria.put("module_id_fun", "!= null"); //按钮
		
		List<BaseModules> list = this.baseModulesService.selectByButtons(criteria);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				BaseModules bm = (BaseModules) list.get(i);
				String button = bm.getButton();
				if (null != button && "null" != button) {
					buttons.add(button);//.replaceAll("&apos;", "'").replaceAll("&quot;", "\"")
				}
			}
		}
		
		} catch (Exception e) {
			logger.error("Exception: ", e);
		}
		modelMap.addAttribute("buttons", buttons);
		return "admin/TbsSysLog/index";
	}
	
	
	@RequestMapping({"/data"})
	@ResponseBody
	public Object all(PageParams pageParams, BaseModules modules, @RequestParam(required=false) String Name){
		try {
			Criteria criteria = new Criteria();
			if (pageParams.getPage() != null) {
				try {
					pageParams.setPage(pageParams.getPage()); // 当前页
				} catch (Exception e) {
					logger.error("Exception: ", e);
				}
			}
			
			if (pageParams.getRows() != null) {
				try {
					pageParams.setPageSize(pageParams.getRows());// 显示X条
					criteria.setOracleEnd(pageParams.getRows());
					criteria.setOracleStart(pageParams.getPageOffset());
				} catch (Exception e) {
					logger.error("Exception: ", e);
				}
			}
			
			if ((StringUtils.isNotBlank(pageParams.getSord()))  //  排序方式
					&& (StringUtils.isNotBlank(pageParams.getSidx())))  // 字段
				criteria.setOrderByClause(new StringBuilder().append(
						pageParams.getSidx()).append(" ").append(pageParams.getSord())
						.toString());
			else {
				criteria.setOrderByClause(" CREATETIME desc ");
			}
			
			
			if (StringUtils.isNotBlank(Name)) {  //查询关键字
				criteria.put("nameLike", Name);
			}
			List<BaseLoginLog> list = this.baseLogService.selectByExample(criteria);
			int total = this.baseLogService.countByExample(criteria);
			return new ExtGridReturn(pageParams.getRecords(total),pageParams.getPage(),total, list);
		} catch (Exception e) {
			
			logger.error("Exception: ", e);
			
			return new ExceptionReturn(e);
		}
	}


}
