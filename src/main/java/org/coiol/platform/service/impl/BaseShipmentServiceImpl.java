/**   
* @Project: Platform 
* @Title: BaseShipmentServiceImpl.java 
* @Package com.coiol.platform.service.impl 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2015年1月11日 下午8:01:19 
* @Copyright: 2015  
* @version V1.0 
 */
package org.coiol.platform.service.impl;

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
import org.coiol.platform.core.model.BaseShipment;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.dao.BaseShipmentMapper;
import org.coiol.platform.service.BaseShipmentService;

@Service
@Component
public class BaseShipmentServiceImpl implements BaseShipmentService
{
	
	@Autowired
	private BaseShipmentMapper shipmentMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(BaseShipmentServiceImpl.class);
	
	public static MethodUtil util = new MethodUtil();
	
	/* (non-Javadoc)
	 * <p>Title: countByExample</p> 
	 * <p>Description:获取出货商信息数据总条数 </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseShipmentService#countByExample(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	public int countByExample(Criteria paramCriteria)
	{
		int count = this.shipmentMapper.countByExample(paramCriteria);
	    logger.debug("count: {}", Integer.valueOf(count));
	    return count;
	}

	/* (non-Javadoc)
	 * <p>Title: selectByPrimaryKey</p> 
	 * <p>Description:通过主键ID查询出货商对应的数据信息 </p> 
	 * @param paramString
	 * @return  
	 * @see com.coiol.platform.service.BaseShipmentService#selectByPrimaryKey(java.lang.String) 
	 */
	@Override
	public BaseShipment selectByPrimaryKey(String paramString)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * <p>Title: selectByExample</p> 
	 * <p>Description:查询所有的出货商信息数据 </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseShipmentService#selectByExample(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	public List<BaseShipment> selectByExample(Criteria paramCriteria)
	{
		 return this.shipmentMapper.selectByExample(paramCriteria);
	}

	/* (non-Javadoc)
	 * <p>Title: saveShipment</p> 
	 * <p>Description:保存新增的出货商信息数据 </p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseShipmentService#saveShipment(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String saveShipment(Criteria paramCriteria)
	{
		BaseShipment shipment = (BaseShipment) paramCriteria.get("shipment");
		Criteria criteria = new Criteria();
			
		  if (StringUtils.isNotBlank(shipment.getId()))
		    {
		      criteria.put("id", shipment.getId());
		      criteria.put("isDelete", "0");
		    }
		  criteria.put("name", shipment.getName());
		  
		  boolean exit = this.shipmentMapper.countByExample(criteria) > 0;
		    if (exit) {
		      return "店面名称已经被注册！请重新填写!";
		    }
		    int result = 0;
		    
		    if (StringUtils.isBlank(shipment.getId()))
		    {
		    	shipment.setId(util.getUid());
		    	shipment.setIsDelete(0);
		    	shipment.setCreateTime(util.getDate(0,null));
		    	result = this.shipmentMapper.insertSelective(shipment);
		    }else{
		    	result = this.shipmentMapper.updateByPrimaryKeySelective(shipment);
		    }
		    return result <= 0 ? "00" : "01";
	}

	/* (non-Javadoc)
	 * <p>Title: updateByPrimaryKeySelective</p> 
	 * <p>Description:更新出货商数据信息 </p> 
	 * @param paramSupplier
	 * @return  
	 * @see com.coiol.platform.service.BaseShipmentService#updateByPrimaryKeySelective(com.coiol.platform.pojo.BaseShipment) 
	 */
	@Override
	public String updateByPrimaryKeySelective(BaseShipment paramShipment)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * <p>Title: deleteByPrimaryKey</p> 
	 * <p>Description: 删除出货商信息数据</p> 
	 * @param paramCriteria
	 * @return  
	 * @see com.coiol.platform.service.BaseShipmentService#deleteByPrimaryKey(com.coiol.platform.pojo.Criteria) 
	 */
	@Override
	public String deleteByPrimaryKey(Criteria paramCriteria)
	{
		int result = 0;
	    String id = paramCriteria.getAsString("id");
	    Criteria criteria = new Criteria();
	    criteria.put("supplierId", id);
	    criteria.put("isDelete", "0");
//	    int count = this.supplierMapperPrice.countByExample(criteria);
//	    if(count > 0){
//	    	return "02";
//	    }
	    BaseShipment shipment = new BaseShipment();
	    shipment.setId(id);
	    shipment.setIsDelete(1);
	    result = this.shipmentMapper.updateByPrimaryKeySelective(shipment);
	    return result > 0 ? "01" : "00";
	}

}
