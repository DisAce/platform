
package org.coiol.platform.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.coiol.platform.core.model.BaseUserRole;
import org.coiol.platform.core.model.Criteria;

public abstract interface BaseUserRoleMapper
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract int deleteByExample(Criteria paramCriteria);

  public abstract int deleteByPrimaryKey(String paramString);

  public abstract int insert(BaseUserRole paramBaseUserRole);

  public abstract int insertSelective(BaseUserRole paramBaseUserRole);

  public abstract List<BaseUserRole> selectByExample(Criteria paramCriteria);

  public abstract BaseUserRole selectByPrimaryKey(String paramString);

  public abstract int updateByExampleSelective(@Param("record") BaseUserRole paramBaseUserRole, @Param("condition") Map<String, Object> paramMap);

  public abstract int updateByExample(@Param("record") BaseUserRole paramBaseUserRole, @Param("condition") Map<String, Object> paramMap);

  public abstract int updateByPrimaryKeySelective(BaseUserRole paramBaseUserRole);

  public abstract int updateByPrimaryKey(BaseUserRole paramBaseUserRole);
}
