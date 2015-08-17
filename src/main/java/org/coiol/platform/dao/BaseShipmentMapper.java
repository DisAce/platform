/**   
* @Project: Platform 
* @Title: BaseShipmentMapper.java 
* @Package com.coiol.platform.dao 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2015年1月11日 下午7:08:14 
* @Copyright: 2015  
* @version V1.0 
 */
package org.coiol.platform.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.coiol.platform.core.model.BaseShipment;
import org.coiol.platform.core.model.Criteria;

/** 
 * @ClassName BaseShipmentMapper  
 * @Description 出货商信息管理DAO 
 * @author Yin MingXing master@coiol.com 
 * @date 2015年1月11日 
 * 
 */
public abstract interface BaseShipmentMapper
{
	
	/**
	 * 
	* @Title: countByExample  
	* @Description: 获取出货商信息记录总行数 
	* @author Yin MingXing master@coiol.com  
	* @date 2015年1月11日
	* @param @param paramCriteria
	* @param @return      
	* @return int     
	* @throws  
	 */
	  public abstract int countByExample(Criteria paramCriteria);
	  
	  /**
	   * 
	  * @Title: deleteByExample  
	  * @Description: 删除出货商信息记录 
	  * @author Yin MingXing master@coiol.com  
	  * @date 2015年1月11日
	  * @param @param paramCriteria
	  * @param @return      
	  * @return int     
	  * @throws  
	   */
	  public abstract int deleteByExample(Criteria paramCriteria);
	  
	  /**
	   * 
	  * @Title: deleteByPrimaryKey  
	  * @Description: 通过ID删除出货商信息数据 
	  * @author Yin MingXing master@coiol.com  
	  * @date 2015年1月11日
	  * @param @param paramString
	  * @param @return      
	  * @return int     
	  * @throws  
	   */
	  public abstract int deleteByPrimaryKey(String paramString);

	  public abstract int insert(BaseShipment paramShipment);

	  public abstract int insertSelective(BaseShipment paramShipment);

	  public abstract List<BaseShipment> selectByExample(Criteria paramCriteria);

	  public abstract BaseShipment selectByPrimaryKey(String paramString);
	  
	  public abstract int updateByExampleSelective(@Param("record") BaseShipment paramShipment, @Param("condition") Map<String, Object> paramMap);

	  public abstract int updateByExample(@Param("record") BaseShipment paramShipment, @Param("condition") Map<String, Object> paramMap);

	  public abstract int updateByPrimaryKeySelective(BaseShipment paramShipment);

	  public abstract int updateByPrimaryKey(BaseShipment paramShipment);

}
