

package org.coiol.platform.service.impl;

import java.util.List;

import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseUserRole;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.dao.BaseUserRoleMapper;
import org.coiol.platform.service.BaseUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
@Component
public class BaseUserRoleServiceImpl implements BaseUserRoleService
{
	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(BaseUserRoleServiceImpl.class);
	@Autowired
	private BaseUserRoleMapper baseUserRoleMapper;
	
	public BaseUserRoleServiceImpl()
	{
	}

	public int countByExample(Criteria example)
	{
		int count = baseUserRoleMapper.countByExample(example);
		logger.debug("count: {}", Integer.valueOf(count));
		return count;
	}

	public BaseUserRole selectByPrimaryKey(String userRoleId)
	{
		return baseUserRoleMapper.selectByPrimaryKey(userRoleId);
	}

	public List<BaseUserRole> selectByExample(Criteria example)
	{
		return baseUserRoleMapper.selectByExample(example);
	}

}
