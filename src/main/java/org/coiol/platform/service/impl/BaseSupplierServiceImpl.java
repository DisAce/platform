/**   
* @Project: Platform 
* @Title: BaseSupplierServiceImpl.java 
* @Package com.coiol.platform.service.impl 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2014年11月24日 下午5:27:10 
* @Copyright: 2014  
* @version V1.0 
 */
package org.coiol.platform.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.utils.MethodUtil;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseSupplier;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.dao.BaseSupplierMapper;
import org.coiol.platform.dao.BaseSupplierPriceMapper;
import org.coiol.platform.service.BaseSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/** 
 * @ClassName BaseSupplierServiceImpl  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2014年11月24日 
 * 
 */
@Service
@Component
public class BaseSupplierServiceImpl implements BaseSupplierService {
	
	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(BaseSupplierServiceImpl.class);  
	@Autowired
	private BaseSupplierMapper supplierMapper;
	@Autowired
	private BaseSupplierPriceMapper supplierMapperPrice;
	
	public static MethodUtil util = new MethodUtil();
	/* (non-Javadoc)
	 * <p>Title: countByExample</p> 
	 * <p>Description: </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseSupplierService#countByExample(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	public int countByExample(Criteria paramCriteria) {
		int count = this.supplierMapper.countByExample(paramCriteria);
	    logger.debug("count: {}", Integer.valueOf(count));
	    return count;
	}

	/* (non-Javadoc)
	 * <p>Title: selectByPrimaryKey</p> 
	 * <p>Description: </p> 
	 * @param paramString
	 * @return  
	 * @see com.coiol.platform.service.BaseSupplierService#selectByPrimaryKey(java.lang.String) 
	 */
	@Override
	public BaseSupplier selectByPrimaryKey(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * <p>Title: selectByExample</p> 
	 * <p>Description: </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseSupplierService#selectByExample(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	public List<BaseSupplier> selectByExample(Criteria paramCriteria) {
		 return this.supplierMapper.selectByExample(paramCriteria);
	}

	/* (non-Javadoc)
	 * <p>Title: saveSupplier</p> 
	 * <p>Description: </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseSupplierService#saveSupplier(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String saveSupplier(Criteria paramCriteria) {
		BaseSupplier supplier = (BaseSupplier) paramCriteria.get("supplier");
		Criteria criteria = new Criteria();
			
		  if (StringUtils.isNotBlank(supplier.getId()))
		    {
		      criteria.put("id", supplier.getId());
		      criteria.put("isDelete", "0");
		    }
		  criteria.put("name", supplier.getName());
		  
		  boolean exit = this.supplierMapper.countByExample(criteria) > 0;
		    if (exit) {
		      return "公司名称已经被注册！请重新填写!";
		    }
		    int result = 0;
		    
		    if (StringUtils.isBlank(supplier.getId()))
		    {
		    	supplier.setId(util.getUid());
		    	supplier.setIsDelete(0);
		    	supplier.setCreateTime(util.getDate(0,null));
		    	result = this.supplierMapper.insertSelective(supplier);
		    }else{
		    	result = this.supplierMapper.updateByPrimaryKeySelective(supplier);
		    }
		    return result <= 0 ? "00" : "01";
	}

	/* (non-Javadoc)
	 * <p>Title: updateByPrimaryKeySelective</p> 
	 * <p>Description: </p> 
	 * @param paramBaseUsers
	 * @return  
	 * @see com.coiol.platform.service.BaseSupplierService#updateByPrimaryKeySelective(com.coiol.platform.pojo.BaseSupplier) 
	 */
	@Override
	public String updateByPrimaryKeySelective(BaseSupplier paramBaseUsers) {
		return null;
	}

	/* (non-Javadoc)
	 * <p>Title: deleteByPrimaryKey</p> 
	 * <p>Description: </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseSupplierService#deleteByPrimaryKey(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	public String deleteByPrimaryKey(Criteria paramCriteria) {
			int result = 0;
		    String id = paramCriteria.getAsString("id");
		    Criteria criteria = new Criteria();
		    criteria.put("supplierId", id);
		    criteria.put("isDelete", "0");
		    int count = this.supplierMapperPrice.countByExample(criteria);
		    if(count > 0){
		    	return "02";
		    }
		    BaseSupplier supplier = new BaseSupplier();
		    supplier.setId(id);
		    supplier.setIsDelete(1);
		    result = this.supplierMapper.updateByPrimaryKeySelective(supplier);
		    return result > 0 ? "01" : "00";
	}

}
