package org.coiol.platform.dao;

import java.util.List;

import org.coiol.platform.core.model.BaseLoginLog;
import org.coiol.platform.core.model.Criteria;

public abstract interface BaseLoginLogMapper {

	/**
	 * 获取日志记录总条数
	 * @param paramCriteria
	 * @return
	 */
	  public abstract int countByExample(Criteria paramCriteria);

	  /**
	   * 删除日志
	   * @param paramCriteria
	   * @return
	   */
	  public abstract int deleteByExample(Criteria paramCriteria);
	  
	  /**
	   * 查询日志记录信息
	   * @param paramCriteria
	   * @return
	   */
	  public abstract List<BaseLoginLog> selectByExample(Criteria paramCriteria);

	  /**
	   * 通过主键删除日志信息
	   * @param paramString
	   * @return
	   */
	  public abstract int deleteByPrimaryKey(String paramString);

	  /**
	   * 新增日子记录信息
	   * @param loginLog
	   * @return
	   */
	  public abstract int insert(BaseLoginLog loginLog);

	  public abstract int insertSelective(BaseLoginLog loginLog);
}
