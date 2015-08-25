

package org.coiol.platform.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.utils.MethodUtil;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseRoleModule;
import org.coiol.platform.core.model.BaseRoles;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.dao.BaseRoleModuleMapper;
import org.coiol.platform.dao.BaseRolesMapper;
import org.coiol.platform.dao.BaseUserRoleMapper;
import org.coiol.platform.service.BaseRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
@Component
public class BaseRolesServiceImpl implements BaseRolesService
{
	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(BaseRolesServiceImpl.class);
	@Autowired
	private BaseRolesMapper baseRolesMapper;
	@Autowired
	private BaseUserRoleMapper baseUserRoleMapper;
	@Autowired
	private BaseRoleModuleMapper baseRoleModuleMapper;
	
	public static MethodUtil util = new MethodUtil();

	public BaseRolesServiceImpl()
	{
	}

	public int countByExample(Criteria example)
	{
		int count = baseRolesMapper.countByExample(example);
		logger.debug("count: {}", Integer.valueOf(count));
		return count;
	}

	public BaseRoles selectByPrimaryKey(String roleId)
	{
		return baseRolesMapper.selectByPrimaryKey(roleId);
	}

	public List<BaseRoles> selectByExample(Criteria example)
	{
		return baseRolesMapper.selectByExample(example);
	}
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String saveRole(BaseRoles role, String moduleIds)
	{
		ArrayList<String> modulesIdList = new ArrayList<String>();
		int result = 0;
		if (StringUtils.isBlank(role.getRoleId())){
			role.setRoleId(util.getUid());
			role.setCreateTime(util.getDate(0,null));
			result = baseRolesMapper.insertSelective(role);
			
		}else{
			result = baseRolesMapper.updateByPrimaryKeySelective(role);
		}
		
		if(result>=0){
			String[] modules = StringUtils.split(moduleIds, ",");
			for (int i = 0; i < modules.length; i++) {
				modulesIdList.add(modules[i]);
			}
			logger.debug("save() - String roleId={}", role.getRoleId());
			logger.debug("save() - String moduleIds={}", moduleIds);
			Criteria criteria = new Criteria();
			criteria.put("modulesIdList", modulesIdList);
			criteria.put("roleId", role.getRoleId());
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
					roleModule.setRoleModuleId(util.getUid());
					roleModule.setModuleId(moduleId);
					if(moduleId.subSequence(0, 1).equals("f")){  //判断是否为按钮事件
						roleModule.setModuleIdFun(moduleId);
					}
					roleModule.setRoleId(roleId);
					roleModule.setCreateTime(util.getDate(0,null));
					baseRoleModuleMapper.insert(roleModule);
				}
			} while (true);
			return "01";
		}else{
			return "00";
		}
	
	}
	 @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String deleteByPrimaryKey(Criteria criteria)
	{
		String roleId = criteria.getAsString("roleId");
		int result = 0;
		int count = baseUserRoleMapper.countByExample(criteria);
		if (count > 0)
		{
			return "其他用户拥有该角色，还不能删除";
		} else
		{
			baseRoleModuleMapper.deleteByExample(criteria);
			result = baseRolesMapper.deleteByPrimaryKey(roleId);
			return result <= 0 ? "00" : "01";
		}
	}

}
