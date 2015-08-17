
package org.coiol.platform.service;

import java.util.List;

import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.core.model.Criteria;

public abstract interface BaseUsersService
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract BaseUsers selectByPrimaryKey(String paramString);

  public abstract List<BaseUsers> selectByExample(Criteria paramCriteria);

  public abstract String updatePassword(Criteria paramCriteria);

  public abstract String findPassword(Criteria paramCriteria)
    throws Exception;

  public abstract String resetPwdByPrimaryKey(Criteria paramCriteria);

  public abstract String deleteByPrimaryKey(Criteria paramCriteria);

  public abstract String selectByBaseUser(Criteria paramCriteria);

  public abstract String validateAccount(Criteria paramCriteria);

  public abstract String updateUserPassword(Criteria paramCriteria);

  public abstract String saveUser(Criteria paramCriteria);

  public abstract String updateByPrimaryKeySelective(BaseUsers paramBaseUsers);
}
