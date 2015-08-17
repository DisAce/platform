/**   
* @Project: Platform 
* @Title: BaseSupplierPriceMapper.java 
* @Package com.coiol.platform.dao 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2014年11月25日 上午11:23:22 
* @Copyright: 2014  
* @version V1.0 
 */
package org.coiol.platform.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.coiol.platform.core.model.BaseSupplierPrice;
import org.coiol.platform.core.model.Criteria;

/** 
 * @ClassName BaseSupplierPriceMapper  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2014年11月25日 
 * 
 */
public interface BaseSupplierPriceMapper {

	 public abstract int countByExample(Criteria paramCriteria);

	  public abstract int deleteByExample(Criteria paramCriteria);

	  public abstract int deleteByPrimaryKey(String paramString);

	  public abstract int insert(BaseSupplierPrice paramSupplierPrice);

	  public abstract int insertSelective(BaseSupplierPrice paramSupplierPrice);

	  public abstract List<BaseSupplierPrice> selectByExample(Criteria paramCriteria);

	  public abstract BaseSupplierPrice selectByPrimaryKey(String paramString);
	  
	  public abstract int updateByExampleSelective(@Param("record") BaseSupplierPrice paramSupplierPrice, @Param("condition") Map<String, Object> paramMap);

	  public abstract int updateByExample(@Param("record") BaseSupplierPrice paramSupplierPrice, @Param("condition") Map<String, Object> paramMap);

	  public abstract int updateByPrimaryKeySelective(BaseSupplierPrice paramSupplierPrice);
	  
	  public abstract int deleteByPrimaryKeySelective(BaseSupplierPrice paramSupplierPrice);
	  
	  public abstract int updateByPrimaryKey(BaseSupplierPrice paramSupplierPrice);
}
