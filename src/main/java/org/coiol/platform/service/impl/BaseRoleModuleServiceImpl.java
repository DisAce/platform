

package org.coiol.platform.service.impl;

import java.util.List;

import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseRoleModule;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.dao.BaseRoleModuleMapper;
import org.coiol.platform.service.BaseRoleModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
@Component
public class BaseRoleModuleServiceImpl implements BaseRoleModuleService
{
	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(BaseRoleModuleServiceImpl.class);
	@Autowired
	private BaseRoleModuleMapper baseRoleModuleMapper;
	
	public BaseRoleModuleServiceImpl()
	{
	}

	public int countByExample(Criteria example)
	{
		int count = baseRoleModuleMapper.countByExample(example);
		logger.debug("count: {}", Integer.valueOf(count));
		return count;
	}

	public BaseRoleModule selectByPrimaryKey(String roleModuleId)
	{
		return baseRoleModuleMapper.selectByPrimaryKey(roleModuleId);
	}

	public List<BaseRoleModule> selectByExample(Criteria example)
	{
		return baseRoleModuleMapper.selectByExample(example);
	}

}
