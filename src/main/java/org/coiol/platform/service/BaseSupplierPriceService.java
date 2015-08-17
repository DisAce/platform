/**   
* @Project: Platform 
* @Title: BaseSupplierPriceService.java 
* @Package com.coiol.platform.service 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2014年11月25日 上午11:25:44 
* @Copyright: 2014  
* @version V1.0 
 */
package org.coiol.platform.service;

import java.util.List;

import org.coiol.platform.core.model.BaseSupplierPrice;
import org.coiol.platform.core.model.Criteria;

/** 
 * @ClassName BaseSupplierPriceService  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2014年11月25日 
 * 
 */
public abstract interface BaseSupplierPriceService {

	
	  public abstract int countByExample(Criteria paramCriteria);
	 
	  public abstract BaseSupplierPrice selectByPrimaryKey(String paramString);

	  public abstract List<BaseSupplierPrice> selectByExample(Criteria paramCriteria);
	  
	  public abstract String saveSupplierPrice(Criteria paramCriteria);
	  
	  public abstract String updateByPrimaryKeySelective(BaseSupplierPrice paramSupplierPrice);
	  
	  public abstract String deleteByPrimaryKey(Criteria paramCriteria);
}
