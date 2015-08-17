/**   
* @Project: Platform 
* @Title: BaseMaterialMapper.java 
* @Package com.coiol.platform.dao 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2014年11月26日 上午10:38:33 
* @Copyright: 2014  
* @version V1.0 
 */
package org.coiol.platform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.coiol.platform.core.model.BaseMaterial;
import org.coiol.platform.core.model.Criteria;

/** 
 * @ClassName BaseMaterialMapper  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2014年11月26日 
 * 
 */
public abstract interface BaseMaterialMapper {
	
	  public abstract int countByExample(Criteria paramCriteria);

	  public abstract int deleteByExample(Criteria paramCriteria);

	  public abstract int deleteByPrimaryKey(String paramString);

	  public abstract int insert(BaseMaterial paramMaterial);

	  public abstract int insertSelective(BaseMaterial paramMaterial);

	  public abstract List<BaseMaterial> selectByExample(Criteria paramCriteria);

	  public abstract BaseMaterial selectByPrimaryKey(String paramString);
	  
	  public abstract int updateByExampleSelective(@Param("record") BaseMaterial paramMaterial, @Param("condition") Map<String, Object> paramMap);

	  public abstract int updateByExample(@Param("record") BaseMaterial paramMaterial, @Param("condition") Map<String, Object> paramMap);

	  public abstract int updateByPrimaryKeySelective(BaseMaterial paramMaterial);
	  
	  public abstract int deleteByPrimaryKeySelective(BaseMaterial paramMaterial);

	  public abstract int updateByPrimaryKey(BaseMaterial paramMaterial);
	  
	  public abstract List<BaseMaterial> chartByExample(Criteria paramCriteria);
	  

}
