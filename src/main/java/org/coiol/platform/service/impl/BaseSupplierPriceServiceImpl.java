/**   
* @Project: Platform 
* @Title: BaseSupplierPriceServiceImpl.java 
* @Package com.coiol.platform.service.impl 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2014年11月25日 上午11:28:33 
* @Copyright: 2014  
* @version V1.0 
 */
package org.coiol.platform.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.utils.MethodUtil;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseSupplierPrice;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.dao.BaseMaterialMapper;
import org.coiol.platform.dao.BaseSupplierPriceMapper;
import org.coiol.platform.service.BaseSupplierPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/** 
 * @ClassName BaseSupplierPriceServiceImpl  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2014年11月25日 
 * 
 */
@Service
@Component
public class BaseSupplierPriceServiceImpl implements BaseSupplierPriceService {

	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(BaseSupplierPriceServiceImpl.class);
	@Autowired
	private BaseSupplierPriceMapper supplierMapperPrice;
	@Autowired
	private BaseMaterialMapper materialMapper;
	
	public static MethodUtil util = new MethodUtil();
	/* (non-Javadoc)
	 * <p>Title: countByExample</p> 
	 * <p>Description: </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseSupplierPriceService#countByExample(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	public int countByExample(Criteria paramCriteria) {
		int count = this.supplierMapperPrice.countByExample(paramCriteria);
	    logger.debug("count: {}", Integer.valueOf(count));
	    return count;
	}

	/* (non-Javadoc)
	 * <p>Title: selectByPrimaryKey</p> 
	 * <p>Description: </p> 
	 * @param paramString
	 * @return  
	 * @see com.coiol.platform.service.BaseSupplierPriceService#selectByPrimaryKey(java.lang.String) 
	 */
	@Override
	public BaseSupplierPrice selectByPrimaryKey(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * <p>Title: selectByExample</p> 
	 * <p>Description: </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseSupplierPriceService#selectByExample(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	public List<BaseSupplierPrice> selectByExample(Criteria paramCriteria) {
		return this.supplierMapperPrice.selectByExample(paramCriteria);
	}

	/* (non-Javadoc)
	 * <p>Title: saveSupplierPrice</p> 
	 * <p>Description: </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseSupplierPriceService#saveSupplierPrice(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String saveSupplierPrice(Criteria paramCriteria) {
		BaseSupplierPrice supplierPrice = (BaseSupplierPrice) paramCriteria.get("supplierPrice");
		Criteria criteria = new Criteria();
			
		 if(StringUtils.isNotBlank(supplierPrice.getId()))
		 	{
			   criteria.put("id", supplierPrice.getId());
			}
		  if (StringUtils.isNotBlank(supplierPrice.getSupplierId()))
		    {
		      criteria.put("supplierId", supplierPrice.getSupplierId());
		      criteria.put("isDelete", "0");
		    }
		  criteria.put("price", supplierPrice.getPrice());
		 
		   boolean exit = this.supplierMapperPrice.countByExample(criteria) > 0;
		  
		    if (exit) {
		      return "该公司对应价格已经存在！请重新填写!";
		    }
		    int result = 0;
		    
		    if (StringUtils.isBlank(supplierPrice.getId()))
		    {
		    	supplierPrice.setId(util.getUid());
		    	supplierPrice.setIsDelete(0);
		    	supplierPrice.setCreateTime(util.getDate(0,null));
		    	result = this.supplierMapperPrice.insertSelective(supplierPrice);
		    }else{
		    	result = this.supplierMapperPrice.updateByPrimaryKeySelective(supplierPrice);
		    }
		    return result <= 0 ? "00" : "01";
	}

	/* (non-Javadoc)
	 * <p>Title: updateByPrimaryKeySelective</p> 
	 * <p>Description: </p> 
	 * @param paramSupplierPrice
	 * @return  
	 * @see com.coiol.platform.service.BaseSupplierPriceService#updateByPrimaryKeySelective(com.coiol.platform.pojo.BaseSupplierPrice) 
	 */
	@Override
	public String updateByPrimaryKeySelective(
			BaseSupplierPrice paramSupplierPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * <p>Title: deleteByPrimaryKey</p> 
	 * <p>Description: </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseSupplierPriceService#deleteByPrimaryKey(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	public String deleteByPrimaryKey(Criteria paramCriteria) {
		int result = 0;
	    String id = paramCriteria.getAsString("id");
	    String supplierId = paramCriteria.getAsString("supplierId");
	    Criteria criteria = new Criteria();
	    criteria.put("priceId", id);
	    criteria.put("supplierId", supplierId);
	    criteria.put("isDelete", "0");
	    int count = this.materialMapper.countByExample(criteria);
	    if(count > 0){
	    	return "02";
	    }
	    BaseSupplierPrice supplierPrice = new BaseSupplierPrice();
	    supplierPrice.setId(id);
	    supplierPrice.setIsDelete(1);
	    result = this.supplierMapperPrice.deleteByPrimaryKeySelective(supplierPrice);
	    return result > 0 ? "01" : "00";
	}

}
