

package org.coiol.platform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.utils.MethodUtil;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseModules;
import org.coiol.platform.core.model.BaseRoleModule;
import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.core.model.Tree;
import org.coiol.platform.core.model.TreeMenu;
import org.coiol.platform.dao.BaseModulesMapper;
import org.coiol.platform.dao.BaseRoleModuleMapper;
import org.coiol.platform.service.BaseModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
public class BaseModulesServiceImpl implements BaseModulesService
{
	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(BaseModulesServiceImpl.class);
	@Autowired
	private BaseModulesMapper baseModulesMapper;
	@Autowired
	private BaseRoleModuleMapper baseRoleModuleMapper;
	@Value("${resoved:false}")
	private boolean resoved;
	@Value("${isDisplay:false}")
	private boolean isDisplay;
	
	public static MethodUtil util = new MethodUtil();

	public BaseModulesServiceImpl()
	{
	}

	public Tree selectAllModules(Criteria example)
	{
		example.setDistinct(true);
		example.setOrderByClause(" DISPLAY_INDEX ASC");
		if (!isDisplay)
			example.put("isDisplay", Integer.valueOf(1));
		List<BaseModules> list = baseModulesMapper.selectByExample(example);
		TreeMenu menu = new TreeMenu(list);
		return menu.getTreeJson();
	}

	public Tree selectModulesByUser(BaseUsers baseUser)
	{
		Criteria example = new Criteria();
		example.setDistinct(true);
		example.put("b.module_id_fun", "");
		if (!isDisplay)
			example.put("isDisplay", Integer.valueOf(1));
		List<BaseModules> list = null;
		if (!resoved)
		{
			example.setOrderByClause(" DISPLAY_INDEX ASC");
			list = baseModulesMapper.selectByExample(example);
		} else
		{
			example.setOrderByClause(" a.display_index ASC");
			example.put("userId", baseUser.getUserId());
			System.out.println(baseUser.getUserId());
			list = baseModulesMapper.selectMyModules(example);
		}
		TreeMenu menu = new TreeMenu(list);
		return menu.getTreeJson();
	}
	 @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String saveModule(Criteria criteria)
	{
		String roleId = criteria.getAsString("roleId");
		ArrayList<?> modulesIds = (ArrayList<?>)criteria.get("modulesIdList");
		baseRoleModuleMapper.deleteByExample(criteria);
		Iterator<?> i$ = modulesIds.iterator();
		do
		{
			if (!i$.hasNext())
				break;
			String moduleId = i$.next().toString();
			if (moduleId != null)
			{
				BaseRoleModule roleModule = new BaseRoleModule();
				roleModule.setModuleId(moduleId);
				roleModule.setRoleId(roleId);
				baseRoleModuleMapper.insert(roleModule);
			}
		} while (true);
		return "01";
	}
	 @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String saveModules(Criteria example)
	{
		BaseModules modules = (BaseModules)example.get("modules");
		int result = 0;
		if (modules.getId() == null){
			if(StringUtils.isBlank(modules.getButton())){
				modules.setId(util.getUid());
			}else{
				modules.setId("f"+util.getUid());
			}
		if(modules.getIsDisplay() == null){
			modules.setIsDisplay(Short.valueOf((short) 1));
		}
		
		if(modules.getExpanded() == null){
			modules.setExpanded(1);
		}
			
			modules.setCreateTime(util.getDate(0,null));
			result = baseModulesMapper.insertSelective(modules);
		}
		else{
			result = baseModulesMapper.updateByPrimaryKeySelective(modules);
		}
			
		return result <= 0 ? "00" : "01";
	}
	 @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String delete(Criteria example)
	{
		Integer moduleId = example.getAsInteger("moduleId");
		int result = 0;
		example.clear();
		example.put("parentId", moduleId);
		baseModulesMapper.deleteByExample(example);
		result = baseModulesMapper.deleteByPrimaryKey(moduleId);
		return result <= 0 ? "00" : "01";
	}

	public int countByExample(Criteria example)
	{
		int count = baseModulesMapper.countByExample(example);
		logger.debug("count: {}", Integer.valueOf(count));
		return count;
	}

	public BaseModules selectByPrimaryKey(Integer moduleId)
	{
		return baseModulesMapper.selectByPrimaryKey(moduleId);
	}

	public List<BaseModules> selectByExample(Criteria example)
	{
		return baseModulesMapper.selectByExample(example);
	}

	public List<HashMap<String, Object>> selectByDynamicSql(Criteria example)
	{
		return baseModulesMapper.selectByDynamicSql(example);
	}

	public List<BaseModules> selectByButtons(Criteria example) 
	{
		example.setDistinct(true);
		if (!isDisplay)
			example.put("isDisplay", Integer.valueOf(1));
		List<BaseModules> list = null;
		if (!resoved)
		{
			example.setOrderByClause(" DISPLAY_INDEX ASC");
			list = baseModulesMapper.selectByExample(example);
		} else
		{
			example.setOrderByClause(" a.display_index ASC");
			
			list = baseModulesMapper.selectMyModules(example);
		}
		return list;
	}

}
