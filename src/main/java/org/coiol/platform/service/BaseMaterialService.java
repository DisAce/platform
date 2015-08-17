/**   
* @Project: Platform 
* @Title: BaseMaterialService.java 
* @Package com.coiol.platform.service 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2014年11月26日 上午10:40:48 
* @Copyright: 2014  
* @version V1.0 
 */
package org.coiol.platform.service;

import java.util.List;

import org.coiol.platform.core.model.BaseMaterial;
import org.coiol.platform.core.model.Criteria;

/** 
 * @ClassName BaseMaterialService  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2014年11月26日 
 * 
 */
public abstract interface BaseMaterialService {

	  public abstract int countByExample(Criteria paramCriteria);
	 
	  public abstract BaseMaterial selectByPrimaryKey(String paramString);

	  public abstract List<BaseMaterial> selectByExample(Criteria paramCriteria);
	  
	  public abstract String saveMaterial(Criteria paramCriteria);
	  
	  public abstract String updateByPrimaryKeySelective(BaseMaterial paramMaterial);
	  
	  public abstract String deleteByPrimaryKey(Criteria paramCriteria);
	  
	  public abstract List<BaseMaterial> chart(Criteria paramCriteria);
}
