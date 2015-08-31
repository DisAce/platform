
package org.coiol.platform.service;

import java.util.List;

import org.coiol.platform.core.model.UserToken;
import org.coiol.platform.core.model.Criteria;

public abstract interface UserTokenService
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract UserToken selectByPrimaryKey(String paramString);

  public abstract List<UserToken> selectByExample(Criteria paramCriteria);

  public abstract String updatePassword(Criteria paramCriteria);

  public abstract String findPassword(Criteria paramCriteria)
    throws Exception;

  public abstract String resetPwdByPrimaryKey(Criteria paramCriteria);

  public abstract String deleteByPrimaryKey(Criteria paramCriteria);

  public abstract String selectByBaseUser(Criteria paramCriteria);

  public abstract String validateAccount(Criteria paramCriteria);

  public abstract String updateUserPassword(Criteria paramCriteria);

  public abstract String saveUserToken(Criteria paramCriteria);

  public abstract String updateByPrimaryKeySelective(UserToken paramUserToken);
}
