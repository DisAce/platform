

package org.coiol.platform.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.coiol.platform.core.model.BaseUserRole;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.dao.BaseUserRoleMapper;
import org.coiol.platform.service.BaseUserRoleService;
@Service
@Component
public class BaseUserRoleServiceImpl implements BaseUserRoleService
{
	@Autowired
	private BaseUserRoleMapper baseUserRoleMapper;
	private static final Logger logger = LoggerFactory.getLogger(BaseUserRoleServiceImpl.class);

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
