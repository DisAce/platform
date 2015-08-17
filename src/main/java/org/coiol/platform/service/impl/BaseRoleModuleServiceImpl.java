

package org.coiol.platform.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.coiol.platform.core.model.BaseRoleModule;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.dao.BaseRoleModuleMapper;
import org.coiol.platform.service.BaseRoleModuleService;
@Service
@Component
public class BaseRoleModuleServiceImpl implements BaseRoleModuleService
{
	@Autowired
	private BaseRoleModuleMapper baseRoleModuleMapper;
	private static final Logger logger = LoggerFactory.getLogger(BaseRoleModuleServiceImpl.class);

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
