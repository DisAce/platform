

package org.coiol.platform.web.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.springmvc.DateConvertEditor;
import org.coiol.platform.core.constant.ResultCode;
import org.coiol.platform.core.jackjson.JackJson;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseModules;
import org.coiol.platform.core.model.BaseRoleModule;
import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.core.model.ExceptionReturn;
import org.coiol.platform.core.model.ExtGridReturn;
import org.coiol.platform.core.model.ExtReturn;
import org.coiol.platform.core.model.PageParams;
import org.coiol.platform.core.model.Tree;
import org.coiol.platform.service.BaseModulesService;
import org.coiol.platform.service.BaseRoleModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping({"/admin/module/"})
public class ModuleController
{

	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(ModuleController.class);
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private BaseModulesService baseModulesService;
	@Autowired
	private BaseRoleModuleService baseRoleModuleService;

	public ModuleController()
	{
	}
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
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
		return "admin/TbsMenu/index";
	}
	@RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST})
	public void all(PrintWriter writer)
		//throws IOException
	{
		try {
			Criteria criteria = new Criteria();
			Tree tree = baseModulesService.selectAllModules(criteria);
			String json = JackJson.fromObjectToJson(tree.getChildren());
			writer.write(json.replaceAll("\"leaf", "\"checked\":false,\"leaf"));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			logger.error("Exception: ", e);
		}	
	}
	@RequestMapping({"/data"})
	@ResponseBody
	public Object all(PageParams pageParams,HttpServletRequest request, @RequestParam(required=false) String moduleName,PrintWriter writer){
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
				criteria.setOrderByClause(" PARENT asc,DISPLAY_INDEX asc ");
			}
			
			
			if (StringUtils.isNotBlank(moduleName)) {  //查询关键字
				criteria.put("moduleNameLike", moduleName);
			}
			StringBuilder sbuilder = new StringBuilder(50);
			sbuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	        if(pageParams.getGridName() != null){
	        	
	        }
	        List<BaseModules> listBaseModules = null;
			int total = 0;
			if (pageParams.getGridName() == null) {  //dataGrid
				listBaseModules = this.baseModulesService.selectByExample(criteria);
				total = this.baseModulesService.countByExample(criteria);
				return new ExtGridReturn(pageParams.getRecords(total),pageParams.getPage(),total, listBaseModules);
			} else {//treeGrid
				criteria.put("parent", 0);
				total = this.baseModulesService.countByExample(criteria);
				 sbuilder.append("<rows>");
			     sbuilder.append("<page>" + pageParams.getPage() + "</page>");
			     sbuilder.append("<total>" + pageParams.getRecords(total) + "</total>");
			     sbuilder.append("<records>" + total + "</records>");
			     String nodeid = request.getParameter("nodeid");
				if(nodeid == null){  //首次打开
					listBaseModules = this.baseModulesService.selectByExample(criteria);
			        for (BaseModules bm : listBaseModules){
			        	 sbuilder.append("<row>");
			             sbuilder.append("<cell>" + bm.getId() + "</cell>");
			             sbuilder.append("<cell>" + bm.getName() + "</cell>");
			             sbuilder.append("<cell>" + bm.getParent() + "</cell>");
			             sbuilder.append("<cell>" + bm.getLeaf() + "</cell>");
			             sbuilder.append("<cell>" + bm.getType() + "</cell>");
			             sbuilder.append("<cell>" + bm.getDisplayIndex() + "</cell>");
			             sbuilder.append("<cell>" + bm.getModuleUrl() + "</cell>");
			             sbuilder.append("<cell>" + bm.getButton() + "</cell>");
			             sbuilder.append("<cell>" + bm.getIsDisplay() + "</cell>");
			             sbuilder.append("<cell>" + bm.getCreateTime() + "</cell>");
			             sbuilder.append("<cell>" + bm.getLevel() + "</cell>");
			             sbuilder.append("<cell>" + -1 + "</cell>");
			             if (1 == bm.getExpanded())
			             {
			               sbuilder.append("<cell>true</cell>");
			             }
			             else
			             {
			               sbuilder.append("<cell>false</cell>");
			             }
			             sbuilder.append("<cell>false</cell></row>");
			           }
			          
				}else{ // 子节点加载
					criteria.put("parent", nodeid);
					listBaseModules = this.baseModulesService.selectByExample(criteria);
					 for (BaseModules bm : listBaseModules){
			        	 sbuilder.append("<row>");
			             sbuilder.append("<cell>" + bm.getId() + "</cell>");
			             sbuilder.append("<cell>" + bm.getName() + "</cell>");
			             sbuilder.append("<cell>" + bm.getParent() + "</cell>");
			             sbuilder.append("<cell>" + bm.getLeaf() + "</cell>");
			             sbuilder.append("<cell>" + bm.getType() + "</cell>");
			             sbuilder.append("<cell>" + bm.getDisplayIndex() + "</cell>");
			             sbuilder.append("<cell>" + bm.getModuleUrl() + "</cell>");
			             sbuilder.append("<cell>" + bm.getButton() + "</cell>");
			             sbuilder.append("<cell>" + bm.getIsDisplay() + "</cell>");
			             sbuilder.append("<cell>" + bm.getCreateTime() + "</cell>");
			             sbuilder.append("<cell>" + bm.getLevel() + "</cell>");
			             sbuilder.append("<cell>" + bm.getParent() + "</cell>");
			             if (1 == bm.getExpanded())
			             {
			               sbuilder.append("<cell>true</cell>");
			             }
			             else
			             {
			               sbuilder.append("<cell>false</cell>");
			             }
			             sbuilder.append("<cell>false</cell></row>");
			             
					 }
				}
				
				 sbuilder.append("</rows>");
				
			}
			
			writer.write(sbuilder.toString());
			writer.flush();
			writer.close();
			logger.debug("total:{}", Integer.valueOf(total));
			return null;
			//return new ExtGridReturn(pageParams.getRecords(total),pageParams.getPage(),total, listBaseModules);
		} catch (Exception e) {
			
			logger.error("Exception: ", e);
			
			return new ExceptionReturn(e);
		}
	}
	
	

	@RequestMapping( { "/{roleId}" })
	@ResponseBody
	public Object selectModulesByRoleId(@PathVariable String roleId) {
		try {
			if (StringUtils.isBlank(roleId)) {
				return new ExtReturn(false, ResultCode.ROLE_ID_IS_NULL);
			}
			Criteria criteria = new Criteria();
			criteria.put("roleId", roleId);
			List<BaseRoleModule> list = this.baseRoleModuleService.selectByExample(criteria);
			return list;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping( { "/saverole" })
	@ResponseBody
	public Object save(@RequestParam String roleId, @RequestParam String moduleIds) {
		try {
			ArrayList<Integer> modulesIdList = new ArrayList<Integer>();
			if (StringUtils.isBlank(roleId)) {
				return new ExtReturn(false, ResultCode.ROLE_NAME_IS_NULL);
			}
			if (StringUtils.isBlank(moduleIds)) {
				return new ExtReturn(false, ResultCode.CHOOSE_RESOURCES_IS_NULL);
			}
			String[] modules = StringUtils.split(moduleIds, ",");
			if ((null == modules) || (modules.length == 0)) {
				return new ExtReturn(false, ResultCode.CHOOSE_RESOURCES_IS_NULL);
			}
			for (int i = 0; i < modules.length; i++) {
				modulesIdList.add(new Integer(modules[i]));
			}

			logger.debug("save() - String roleId={}", roleId);
			logger.debug("save() - String moduleIds={}", moduleIds);
			Criteria criteria = new Criteria();
			criteria.put("modulesIdList", modulesIdList);
			criteria.put("roleId", roleId);
			String result = this.baseModulesService.saveModule(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, ResultCode.SUCCESS);
			if ("00".equals(result)) {
				return new ExtReturn(false, ResultCode.FAILED);
			}
			return new ExtReturn(false, ResultCode.OTHER_SERVER_ERROR);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping( { "/save" })
	@ResponseBody
	public Object save(BaseModules modules) {
		try {
			if (modules == null) {
				return new ExtReturn(false, ResultCode.MODULE_NAME_IS_NULL);
			}
			if (StringUtils.isBlank(modules.getName())) {
				return new ExtReturn(false, ResultCode.MODULE_NAME_IS_NULL);
			}
			Criteria criteria = new Criteria();
			criteria.put("modules", modules);
			String result = this.baseModulesService.saveModules(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, ResultCode.SUCCESS);
			if ("00".equals(result)) {
				return new ExtReturn(false, ResultCode.FAILED);
			}
			return new ExtReturn(false, ResultCode.OTHER_SERVER_ERROR);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping( { "/del/{moduleId}" })
	@ResponseBody
	public Object delete(@PathVariable String moduleId) {
		try {
			if (StringUtils.isBlank(moduleId)) {
				return new ExtReturn(false, ResultCode.MODULE_KEY_IS_NULL);
			}
			Criteria criteria = new Criteria();
			criteria.put("moduleId", moduleId);
			String result = this.baseModulesService.delete(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, ResultCode.SUCCESS);
			if ("00".equals(result)) {
				return new ExtReturn(false, ResultCode.FAILED);
			}
			return new ExtReturn(false, ResultCode.OTHER_SERVER_ERROR);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}
	
	
	@RequestMapping("baseDlg")
	public String baseDlg(){
		return "admin/TbsMenu/baseDlg";
	}
	
	@RequestMapping(value = { "/comboxTree" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public String comboxTree(String id, String roleId, Integer type) throws Exception {
		
		Criteria criteria = new Criteria();
		List<BaseModules> listBaseModules = null;
		int total = 0;
		if (null == id) { 
			criteria.put("parentId", 0); //打开一级菜单
			listBaseModules = this.baseModulesService.selectByExample(criteria);
		} else {
			criteria.put("parentId", id);
			listBaseModules = this.baseModulesService.selectByExample(criteria);
		}
		criteria.clear();
		
		if (listBaseModules != null && listBaseModules.size() > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for (int i = 0; i < listBaseModules.size(); i++) {
				sb.append("{");
				BaseModules model = listBaseModules.get(i);
				String menuId =  model.getId();
				criteria.put("parentId", model.getId());  //判断此节点下面是否还有菜单
				total = this.baseModulesService.countByExample(criteria);
				if (total > 0) {
					sb.append("\"state\":\"closed\",");
					if (type != null && type != 0) { // 同步递归
						sb.append("\"children\":").append(this.comboxTree(menuId, roleId, type) + ",");
					}
				}
				sb.append("\"id\":" + "\"" + menuId + "\",");
				sb.append("\"text\":" + "\"" + model.getName() + "\"");
				sb.append("},");
				}
			sb = sb.delete(sb.length() - 1, sb.length());
			sb.append("]");
			System.out.println("json:" + sb.toString());
			return sb.toString();
			}
		return "[]";
	}

}
