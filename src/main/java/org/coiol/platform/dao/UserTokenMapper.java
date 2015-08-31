

package org.coiol.platform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.coiol.platform.core.model.UserToken;
import org.coiol.platform.core.model.Criteria;

public abstract interface UserTokenMapper
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract int deleteByExample(Criteria paramCriteria);

  public abstract int deleteByPrimaryKey(String paramString);

  public abstract int insert(UserToken paramUserToken);

  public abstract int insertSelective(UserToken paramUserToken);

  public abstract List<UserToken> selectByExample(Criteria paramCriteria);

  public abstract UserToken selectByPrimaryKey(String paramString);

  public abstract int updateByExampleSelective(@Param("record") UserToken paramUserToken, @Param("condition") Map<String, Object> paramMap);

  public abstract int updateByExample(@Param("record") UserToken paramUserToken, @Param("condition") Map<String, Object> paramMap);

  public abstract int updateByPrimaryKeySelective(UserToken paramUserToken);

  public abstract int updateByPrimaryKey(UserToken paramUserToken);
}
