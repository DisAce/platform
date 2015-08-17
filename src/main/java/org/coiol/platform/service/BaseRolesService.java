
package org.coiol.platform.service;

import java.util.List;

import org.coiol.platform.core.model.BaseRoles;
import org.coiol.platform.core.model.Criteria;

public abstract interface BaseRolesService
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract BaseRoles selectByPrimaryKey(String paramString);

  public abstract List<BaseRoles> selectByExample(Criteria paramCriteria);

  public abstract String saveRole(BaseRoles paramBaseRoles,String moduleIds);

  public abstract String deleteByPrimaryKey(Criteria paramCriteria);
}
