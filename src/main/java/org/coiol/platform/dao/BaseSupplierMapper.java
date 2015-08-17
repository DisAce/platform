/**   
* @Project: Platform 
* @Title: BaseSupplierMapper.java 
* @Package com.coiol.platform.dao 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2014年11月24日 下午5:20:03 
* @Copyright: 2014  
* @version V1.0 
 */
package org.coiol.platform.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.coiol.platform.core.model.BaseSupplier;
import org.coiol.platform.core.model.Criteria;

/** 
 * @ClassName BaseSupplierMapper  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2014年11月24日 
 * 
 */
public abstract interface BaseSupplierMapper {

	
	/**
	 * 
	* @Title: countByExample  
	* @Description: 查询供应商条数 
	* @author Yin MingXing master@coiol.com  
	* @date 2014年11月24日
	* @param @param paramCriteria
	* @param @return      
	* @return int     
	* @throws  
	 */
	  public abstract int countByExample(Criteria paramCriteria);

	  public abstract int deleteByExample(Criteria paramCriteria);

	  public abstract int deleteByPrimaryKey(String paramString);

	  public abstract int insert(BaseSupplier paramSupplier);

	  public abstract int insertSelective(BaseSupplier paramSupplier);

	  public abstract List<BaseSupplier> selectByExample(Criteria paramCriteria);

	  public abstract BaseSupplier selectByPrimaryKey(String paramString);
	  
	  public abstract int updateByExampleSelective(@Param("record") BaseSupplier paramSupplier, @Param("condition") Map<String, Object> paramMap);

	  public abstract int updateByExample(@Param("record") BaseSupplier paramSupplier, @Param("condition") Map<String, Object> paramMap);

	  public abstract int updateByPrimaryKeySelective(BaseSupplier paramSupplier);

	  public abstract int updateByPrimaryKey(BaseSupplier paramSupplier);
}
