

package org.coiol.platform.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.springmvc.DateConvertEditor;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseModules;
import org.coiol.platform.core.model.BaseRoles;
import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.core.model.ExceptionReturn;
import org.coiol.platform.core.model.ExtGridReturn;
import org.coiol.platform.core.model.ExtReturn;
import org.coiol.platform.core.model.PageParams;
import org.coiol.platform.service.BaseModulesService;
import org.coiol.platform.service.BaseRolesService;
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
@RequestMapping({"/admin/role/"})
public class RoleController
{

	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(RoleController.class);
	@Autowired
	private BaseRolesService baseRolesService;
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private BaseModulesService baseModulesService;
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@RequestMapping(value = { "/index" },method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String role(String id,HttpSession session,  ModelMap modelMap)
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
					buttons.add(button);
				}
			}
		}
		
		} catch (Exception e) {
			logger.error("Exception: ", e);
		}
		modelMap.addAttribute("buttons", buttons);
		return "admin/TbsRole/index";
	}
	
	@RequestMapping(value = { "/data" },method={org.springframework.web.bind.annotation.RequestMethod.POST})
	@ResponseBody
	public Object all(PageParams pageParams,BaseRoles roles, @RequestParam(required=false) String roleName)
	{
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
				&& (StringUtils.isNotBlank(pageParams.getSord())))  // 字段
			criteria.setOrderByClause(new StringBuilder().append(
					pageParams.getSidx()).append(" ").append(pageParams.getSord())
					.toString());
		else {
			criteria.setOrderByClause(" CREATETIME DESC ");
		}
		
		if (StringUtils.isNotBlank(roleName))
			criteria.put("roleNameLike", roleName);
		List<BaseRoles> list = baseRolesService.selectByExample(criteria);
		int total = baseRolesService.countByExample(criteria);
		pageParams.setTotal(total);
		logger.debug("total:{}", Integer.valueOf(total));
		return new ExtGridReturn(pageParams.getRecords(total),pageParams.getPage(),total, list);
	}

	
	@RequestMapping("baseDlg")
	public String baseDlg(){
		return "admin/TbsRole/baseDlg";
	}
	
	@RequestMapping( { "/save" })
	@ResponseBody
	public Object save(BaseRoles role,@RequestParam String moduleIds) {
		try {
			if (role == null) {
				return new ExtReturn(false, "角色不能为空!");
			}
			if (StringUtils.isBlank(role.getRoleName())) {
				return new ExtReturn(false, "角色名称不能为空!");
			}
			
			if (StringUtils.isBlank(moduleIds)) {
				return new ExtReturn(false, "选择的资源不能为空！");
			}
			String[] modules = StringUtils.split(moduleIds, ",");
			if ((null == modules) || (modules.length == 0)) {
				return new ExtReturn(false, "选择的资源不能为空！");
			}
			String result = this.baseRolesService.saveRole(role, moduleIds);
			if ("01".equals(result)){
				
				return new ExtReturn(true, "保存成功！");
			}
			
			if ("00".equals(result)) {
				return new ExtReturn(false, "保存失败！");
			}
			return new ExtReturn(false, result);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping( value = { "/del" })
	@ResponseBody
	public Object delete( String ids) {
		try {
			if (StringUtils.isBlank(ids)) {
				return new ExtReturn(false, "角色主键不能为空！");
			}
			Criteria criteria = new Criteria();
			criteria.put("roleId", ids);
			String result = this.baseRolesService.deleteByPrimaryKey(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, "删除成功！");
			if ("00".equals(result)) {
				return new ExtReturn(false, "删除失败！");
			}
			return new ExtReturn(false, result);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}
	
	@RequestMapping(value = { "/comboxTree" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public String comboxTree() throws Exception {
		Criteria criteria = new Criteria();
		criteria.setOrderByClause(" CREATETIME DESC ");
		List<BaseRoles> list = baseRolesService.selectByExample(criteria);
		if (list != null && list.size() > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for (int i = 0; i < list.size(); i++) {
				sb.append("{");
				BaseRoles model = (BaseRoles) list.get(i);
				String menuId =  model.getRoleId();
				String name = model.getRoleName();
				sb.append("\"id\":" + "\"" + menuId + "\",");
				sb.append("\"name\":" + "\"" + name + "\"");
				sb.append("},");
				}
			sb = sb.delete(sb.length() - 1, sb.length());
			sb.append("]");
			return sb.toString();
		}
		return "[]";
	}
	}