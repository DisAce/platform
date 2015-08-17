

package org.coiol.platform.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.coiol.platform.core.model.BaseModules;
import org.coiol.platform.core.model.Criteria;

public abstract interface BaseModulesMapper {

		/**
		 * 构建动态查询条件
		 * @param paramCriteria
		 * @return
		 */
	  public abstract List<HashMap<String, Object>> selectByDynamicSql(Criteria paramCriteria);
	  
	  /**
	   * 查询模块总调试
	   * @param paramCriteria
	   * @return
	   */

	  public abstract int countByExample(Criteria paramCriteria);

	  /**
	   * 根据条件删除模块信息
	   * @param paramCriteria
	   * @return
	   */
	  public abstract int deleteByExample(Criteria paramCriteria);

	  /**
	   * 通过主键删除模块信息
	   * @param paramInteger
	   * @return
	   */
	  public abstract int deleteByPrimaryKey(Integer paramInteger);

	  /**
	   * 新增模块信息
	   * @param paramBaseModules
	   * @return
	   */
	  public abstract int insert(BaseModules paramBaseModules);

	  public abstract int insertSelective(BaseModules paramBaseModules);

	  public abstract List<BaseModules> selectByExample(Criteria paramCriteria);

	  public abstract List<BaseModules> selectMyModules(Criteria paramCriteria);
	  
	  public abstract BaseModules selectByPrimaryKey(Integer paramInteger);

	  public abstract int updateByExampleSelective(@Param("record") BaseModules paramBaseModules, @Param("condition") Map<String, Object> paramMap);

	  /**
	   * 更新模块信息
	   * @param paramBaseModules
	   * @param paramMap
	   * @return
	   */
	  public abstract int updateByExample(@Param("record") BaseModules paramBaseModules, @Param("condition") Map<String, Object> paramMap);

	  public abstract int updateByPrimaryKeySelective(BaseModules paramBaseModules);

	  public abstract int updateByPrimaryKey(BaseModules paramBaseModules);
}
