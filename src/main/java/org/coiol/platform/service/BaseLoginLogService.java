package org.coiol.platform.service;

import java.util.List;

import org.coiol.platform.core.model.BaseLoginLog;
import org.coiol.platform.core.model.Criteria;

public abstract interface BaseLoginLogService {
	
	public abstract int countByExample(Criteria paramCriteria);

	public abstract BaseLoginLog selectByPrimaryKey(String paramString);

	public abstract List<BaseLoginLog> selectByExample(Criteria paramCriteria);
	
	public abstract String insert(BaseLoginLog paramCriteria);
}
