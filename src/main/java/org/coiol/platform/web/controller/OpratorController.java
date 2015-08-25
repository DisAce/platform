package org.coiol.platform.web.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.springmvc.DateConvertEditor;
import org.coiol.platform.core.constant.ResultCode;
import org.coiol.platform.core.jackjson.JackJson;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseModules;
import org.coiol.platform.core.model.BaseRoleModule;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.core.model.ExceptionReturn;
import org.coiol.platform.core.model.ExtGridReturn;
import org.coiol.platform.core.model.ExtPager;
import org.coiol.platform.core.model.ExtReturn;
import org.coiol.platform.core.model.Tree;
import org.coiol.platform.service.BaseModulesService;
import org.coiol.platform.service.BaseRoleModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/oprator")
public class OpratorController {
	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(OpratorController.class);
	@Autowired
	private BaseModulesService baseModulesService;
	@Autowired
	private BaseRoleModuleService baseRoleModuleService;

	public OpratorController()
	{
	}
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String module(Model model)
	{
		Criteria criteria = new Criteria();
		StringBuilder sb = new StringBuilder();
		sb.append("select a.module_id   as i, ").append("       a.module_name as n ").append("from   base_modules a ").append("where  a.leaf = 0 ").append("order by a.module_id asc ");
		criteria.put("dynamicSql", sb.toString());
		List<HashMap<String, Object>> list = baseModulesService.selectByDynamicSql(criteria);
		@SuppressWarnings("rawtypes")
		HashMap map = new HashMap();
		for (int i = 0; i < list.size(); i++)
		{
			HashMap<?, ?> temp = (HashMap<?, ?>)list.get(i);
			map.put(temp.get("I"), temp.get("N"));
		}

		map.put("0", "主菜单");
		model.addAttribute("moduleMap", JackJson.fromObjectToJson(map));
		return "user/module";
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
	@RequestMapping({"/all"})
	@ResponseBody
	public Object all(ExtPager pager, @RequestParam(required=false) String moduleName){
		try {
			Criteria criteria = new Criteria();

			if ((pager.getLimit() != null) && (pager.getStart() != null)) {
				criteria.setOracleEnd(Integer.valueOf(pager.getLimit()
						.intValue()
						+ pager.getStart().intValue()));
				criteria.setOracleStart(pager.getStart());
			}

			if ((StringUtils.isNotBlank(pager.getDir()))
					&& (StringUtils.isNotBlank(pager.getSort())))
				criteria.setOrderByClause(new StringBuilder().append(
						pager.getSort()).append(" ").append(pager.getDir())
						.toString());
			else {
				criteria.setOrderByClause(" PARENT asc,DISPLAY_INDEX asc ");
			}
			if (StringUtils.isNotBlank(moduleName)) {
				criteria.put("moduleNameLike", moduleName);
			}
			List<BaseModules> list = this.baseModulesService.selectByExample(criteria);
			int total = this.baseModulesService.countByExample(criteria);
			logger.debug("total:{}", Integer.valueOf(total));
			return new ExtGridReturn(total, list);
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

}
