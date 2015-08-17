/**   
* @Project: Platform 
* @Title: BaseSupplierService.java 
* @Package com.coiol.platform.service 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2014年11月24日 下午5:24:30 
* @Copyright: 2014  
* @version V1.0 
 */
package org.coiol.platform.service;

import java.util.List;

import org.coiol.platform.core.model.BaseSupplier;
import org.coiol.platform.core.model.Criteria;

/** 
 * @ClassName BaseSupplierService  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2014年11月24日 
 * 
 */
public abstract interface BaseSupplierService {
	
	  public abstract int countByExample(Criteria paramCriteria);
 
	  public abstract BaseSupplier selectByPrimaryKey(String paramString);

	  public abstract List<BaseSupplier> selectByExample(Criteria paramCriteria);
	  
	  public abstract String saveSupplier(Criteria paramCriteria);
	  
	  public abstract String updateByPrimaryKeySelective(BaseSupplier paramSupplier);
	  
	  public abstract String deleteByPrimaryKey(Criteria paramCriteria);

}
