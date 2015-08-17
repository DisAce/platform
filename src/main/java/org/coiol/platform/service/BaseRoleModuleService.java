

package org.coiol.platform.service;

import java.util.List;

import org.coiol.platform.core.model.BaseRoleModule;
import org.coiol.platform.core.model.Criteria;

public abstract interface BaseRoleModuleService
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract BaseRoleModule selectByPrimaryKey(String paramString);

  public abstract List<BaseRoleModule> selectByExample(Criteria paramCriteria);
}
