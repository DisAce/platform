
package org.coiol.platform.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.coiol.platform.core.model.BaseRoleModule;
import org.coiol.platform.core.model.Criteria;

public abstract interface BaseRoleModuleMapper
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract int deleteByExample(Criteria paramCriteria);

  public abstract int deleteByPrimaryKey(String paramString);

  public abstract int insert(BaseRoleModule paramBaseRoleModule);

  public abstract int insertSelective(BaseRoleModule paramBaseRoleModule);

  public abstract List<BaseRoleModule> selectByExample(Criteria paramCriteria);

  public abstract BaseRoleModule selectByPrimaryKey(String paramString);

  public abstract int updateByExampleSelective(@Param("record") BaseRoleModule paramBaseRoleModule, @Param("condition") Map<String, Object> paramMap);

  public abstract int updateByExample(@Param("record") BaseRoleModule paramBaseRoleModule, @Param("condition") Map<String, Object> paramMap);

  public abstract int updateByPrimaryKeySelective(BaseRoleModule paramBaseRoleModule);

  public abstract int updateByPrimaryKey(BaseRoleModule paramBaseRoleModule);
}
