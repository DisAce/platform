

package org.coiol.platform.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.core.model.Criteria;

public abstract interface BaseUsersMapper
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract int deleteByExample(Criteria paramCriteria);

  public abstract int deleteByPrimaryKey(String paramString);

  public abstract int insert(BaseUsers paramBaseUsers);

  public abstract int insertSelective(BaseUsers paramBaseUsers);

  public abstract List<BaseUsers> selectByExample(Criteria paramCriteria);

  public abstract BaseUsers selectByPrimaryKey(String paramString);

  public abstract int updateByExampleSelective(@Param("record") BaseUsers paramBaseUsers, @Param("condition") Map<String, Object> paramMap);

  public abstract int updateByExample(@Param("record") BaseUsers paramBaseUsers, @Param("condition") Map<String, Object> paramMap);

  public abstract int updateByPrimaryKeySelective(BaseUsers paramBaseUsers);

  public abstract int updateByPrimaryKey(BaseUsers paramBaseUsers);
}
