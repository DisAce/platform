

package org.coiol.platform.service;

import java.util.HashMap;
import java.util.List;

import org.coiol.platform.core.model.BaseFields;
import org.coiol.platform.core.model.Criteria;

public abstract interface BaseFieldsService
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract BaseFields selectByPrimaryKey(String paramString);

  public abstract List<BaseFields> selectByExample(Criteria paramCriteria);

  public abstract HashMap<String, String> selectAllByExample(Criteria paramCriteria);

  public abstract String saveFields(Criteria paramCriteria);

  public abstract String delete(Criteria paramCriteria);
}
