

package org.coiol.platform.service;

import java.util.List;

import org.coiol.platform.core.model.BaseUserRole;
import org.coiol.platform.core.model.Criteria;

public abstract interface BaseUserRoleService
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract BaseUserRole selectByPrimaryKey(String paramString);

  public abstract List<BaseUserRole> selectByExample(Criteria paramCriteria);
}
