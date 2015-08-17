/**   
* @Project: Platform 
* @Title: BaseShipmentService.java 
* @Package com.coiol.platform.service 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2015年1月11日 下午7:58:52 
* @Copyright: 2015  
* @version V1.0 
 */
package org.coiol.platform.service;

import java.util.List;

import org.coiol.platform.core.model.BaseShipment;
import org.coiol.platform.core.model.Criteria;

/** 
 * @ClassName BaseShipmentService  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2015年1月11日 
 * 
 */
public abstract interface BaseShipmentService
{
	
	  public abstract int countByExample(Criteria paramCriteria);
	  
	  public abstract BaseShipment selectByPrimaryKey(String paramString);

	  public abstract List<BaseShipment> selectByExample(Criteria paramCriteria);
	  
	  public abstract String saveShipment(Criteria paramCriteria);
	  
	  public abstract String updateByPrimaryKeySelective(BaseShipment paramShipment);
	  
	  public abstract String deleteByPrimaryKey(Criteria paramCriteria);

}
