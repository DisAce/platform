

package org.coiol.platform.service;

import java.util.HashMap;
import java.util.List;

import org.coiol.platform.core.model.BaseModules;
import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.core.model.Tree;

public abstract interface BaseModulesService
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract BaseModules selectByPrimaryKey(Integer paramInteger);

  public abstract List<BaseModules> selectByExample(Criteria paramCriteria);
  
  public abstract List<BaseModules> selectByButtons(Criteria paramCriteria);

  public abstract Tree selectModulesByUser(BaseUsers paramBaseUsers);

  public abstract Tree selectAllModules(Criteria paramCriteria);

  public abstract String saveModule(Criteria paramCriteria);

  public abstract String saveModules(Criteria paramCriteria);

  public abstract String delete(Criteria paramCriteria);

  public abstract List<HashMap<String, Object>> selectByDynamicSql(Criteria paramCriteria);
}
