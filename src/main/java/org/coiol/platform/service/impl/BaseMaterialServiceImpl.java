/**   
* @Project: Platform 
* @Title: BaseMaterialServiceImpl.java 
* @Package com.coiol.platform.service.impl 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2014年11月26日 上午10:42:43 
* @Copyright: 2014  
* @version V1.0 
 */
package org.coiol.platform.service.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.coiol.platform.common.utils.MethodUtil;
import org.coiol.platform.core.model.BaseMaterial;
import org.coiol.platform.core.model.BaseSupplierPrice;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.dao.BaseMaterialMapper;
import org.coiol.platform.service.BaseMaterialService;
import org.coiol.platform.service.BaseSupplierPriceService;

/** 
 * @ClassName BaseMaterialServiceImpl  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2014年11月26日 
 * 
 */
@Service
@Component
public class BaseMaterialServiceImpl implements BaseMaterialService {
	
	@Autowired
	private BaseMaterialMapper materialMapper;
	@Autowired
	private BaseSupplierPriceService supplierPriceService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(BaseMaterialServiceImpl.class);
	public static MethodUtil util = new MethodUtil();

	/* (non-Javadoc)
	 * <p>Title: countByExample</p> 
	 * <p>Description: </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseMaterialService#countByExample(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	public int countByExample(Criteria paramCriteria) {
		int count = this.materialMapper.countByExample(paramCriteria);
	    logger.debug("count: {}", Integer.valueOf(count));
	    return count;
	}

	/* (non-Javadoc)
	 * <p>Title: selectByPrimaryKey</p> 
	 * <p>Description: </p> 
	 * @param paramString
	 * @return  
	 * @see com.coiol.platform.service.BaseMaterialService#selectByPrimaryKey(java.lang.String) 
	 */
	@Override
	public BaseMaterial selectByPrimaryKey(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * <p>Title: selectByExample</p> 
	 * <p>Description: </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseMaterialService#selectByExample(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	public List<BaseMaterial> selectByExample(Criteria paramCriteria) {
		return this.materialMapper.selectByExample(paramCriteria);
	}

	/* (non-Javadoc)
	 * <p>Title: saveSupplier</p> 
	 * <p>Description: </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseMaterialService#saveSupplier(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String saveMaterial(Criteria paramCriteria) {
		BaseMaterial material = (BaseMaterial) paramCriteria.get("material");
		Criteria criteria = new Criteria();
		BigDecimal price = null;
			if (StringUtils.isNotBlank(material.getPriceId()))
		    {
		      criteria.put("id", material.getPriceId());
		      criteria.put("isDelete", "0");
		      List<BaseSupplierPrice> sp = this.supplierPriceService.selectByExample(criteria);
		      for(Iterator<BaseSupplierPrice> it  = sp.iterator();it.hasNext();){
		    	  	BaseSupplierPrice bs = it.next();
		    	  	price = bs.getPrice();
				}
		      //计算平均价格
		      BigDecimal bd = new BigDecimal(util.div(material.getTotal().doubleValue(), material.getNum(), 3)); 
		      //计算平均重量
		      BigDecimal _total = new BigDecimal(util.div(material.getTotal().doubleValue(), price.doubleValue(), 3));
		      
		      BigDecimal sing = new BigDecimal(util.div(_total.doubleValue(), material.getNum(), 3)); 
		      material.setWeight(_total);
		      material.setSinglePrice(bd);
		      material.setSingleTotal(sing);
		      criteria.clear();
		    }else{
		    	  return "该进货商单价信息不存在！请确认后重新填写！";	
		    }
		  if (StringUtils.isNotBlank(material.getId()))
		    {
		      criteria.put("id", material.getId());
		      criteria.put("isDelete", "0");
		    }
		  criteria.put("num", material.getNum());
		  criteria.put("total", material.getTotal());
		  boolean exit = this.materialMapper.countByExample(criteria) > 0;
		    if (exit) {
		      return "该进货单信息系统已存在！请重新填写！";
		    }
		    int result = 0;
		    if (StringUtils.isBlank(material.getId()))
		    {
		    	material.setId(util.getUid());
		    	material.setIsDelete(0);
		    	material.setCreateTime(util.getDate(0,null));
		    	result = this.materialMapper.insertSelective(material);
		    }else{
		    	result = this.materialMapper.updateByPrimaryKeySelective(material);
		    }
		    return result <= 0 ? "00" : "01";
	}

	/* (non-Javadoc)
	 * <p>Title: updateByPrimaryKeySelective</p> 
	 * <p>Description: </p> 
	 * @param paramMaterial
	 * @return  
	 * @see com.coiol.platform.service.BaseMaterialService#updateByPrimaryKeySelective(com.coiol.platform.pojo.BaseMaterial) 
	 */
	@Override
	public String updateByPrimaryKeySelective(BaseMaterial paramMaterial) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * <p>Title: deleteByPrimaryKey</p> 
	 * <p>Description: </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseMaterialService#deleteByPrimaryKey(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	public String deleteByPrimaryKey(Criteria paramCriteria) {
		int result = 0;
	    String id = paramCriteria.getAsString("id");
	    BaseMaterial material = new BaseMaterial();
	    material.setId(id);
	    material.setIsDelete(1);
	    result = this.materialMapper.deleteByPrimaryKeySelective(material);
	    return result > 0 ? "01" : "00";
	}

	/* (non-Javadoc)
	 * <p>Title: chart</p> 
	 * <p>Description: </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseMaterialService#chart(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	public List<BaseMaterial> chart(Criteria paramCriteria) {
		return this.materialMapper.chartByExample(paramCriteria);
	}

}
