/**   
* @Project: Platform 
* @Title: SupplierPriceController.java 
* @Package com.coiol.platform.web.controller 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2014年11月25日 上午11:29:19 
* @Copyright: 2014  
* @version V1.0 
 */
package org.coiol.platform.web.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.springmvc.DateConvertEditor;
import org.coiol.platform.core.constant.ResultCode;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseModules;
import org.coiol.platform.core.model.BaseSupplier;
import org.coiol.platform.core.model.BaseSupplierPrice;
import org.coiol.platform.core.model.BaseSupplierUnion;
import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.core.model.ExceptionReturn;
import org.coiol.platform.core.model.ExtGridReturn;
import org.coiol.platform.core.model.ExtReturn;
import org.coiol.platform.core.model.PageParams;
import org.coiol.platform.service.BaseModulesService;
import org.coiol.platform.service.BaseSupplierPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
 * @ClassName SupplierPriceController  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2014年11月25日 
 * 
 */
@Controller
@RequestMapping({"/admin/supplierPrice/"})
public class SupplierPriceController {
	
	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(SupplierPriceController.class);
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private BaseModulesService baseModulesService;
	@Autowired
	private BaseSupplierPriceService supplierPriceService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@RequestMapping(value = { "/index" },method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String index(String id,HttpSession session,  ModelMap modelMap)
	{
		List<String> buttons = new java.util.ArrayList<String>();
		try {
		BaseUsers user = (BaseUsers) session.getAttribute("CURRENT_USER");
		Criteria criteria = new Criteria();
		criteria.put("parent", id);
		criteria.put("userId", user.getUserId());
		criteria.put("module_id_fun", "!= null"); //按钮
		
		List<BaseModules> list = this.baseModulesService.selectByButtons(criteria);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				BaseModules bm = (BaseModules) list.get(i);
				String button = bm.getButton();
				if (null != button && "null" != button) {
					buttons.add(button);
				}
			}
		}
		
		} catch (Exception e) {
			logger.error("Exception: ", e);
		}
		modelMap.addAttribute("buttons", buttons);
		return "admin/TbsSupplierPrice/index";
	}
	
	
	@RequestMapping(value = { "/baseDlg" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String baseDlg(){
		return "admin/TbsSupplierPrice/baseDlg";
	}
	
	@RequestMapping(value = { "/data" },method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public Object all(PageParams pageParams) {
		List<BaseSupplierPrice> baseSupplierPrice=null;
		Criteria criteria = new Criteria();
		if (pageParams.getPage() != null) {
			try {
				pageParams.setPage(pageParams.getPage()); // 当前页
			} catch (Exception e) {
				logger.error("Exception: ", e);
			}
		}
		
		if (pageParams.getRows() != null) {
			try {
				pageParams.setPageSize(pageParams.getRows());// 显示X条
				criteria.setOracleEnd(pageParams.getRows());
				criteria.setOracleStart(pageParams.getPageOffset());
			} catch (Exception e) {
				logger.error("Exception: ", e);
			}
		}
		
		try {
			
		if ((StringUtils.isNotBlank(pageParams.getSord()))  //  排序方式
				&& (StringUtils.isNotBlank(pageParams.getSidx())))  // 字段
			criteria.setOrderByClause(new StringBuilder().append(
					pageParams.getSidx()).append(" ").append(pageParams.getSord())
					.toString());
		else {
			criteria.setOrderByClause(" p.CREATETIME desc");
		}
		
			criteria.put("isDelete", "0");
			
		baseSupplierPrice = this.supplierPriceService.selectByExample(criteria);
		int total = this.supplierPriceService.countByExample(criteria);
		List<BaseSupplierUnion> baseSupplierUnion = new LinkedList<BaseSupplierUnion>();
		 for (BaseSupplierPrice sp : baseSupplierPrice){
			 BaseSupplierUnion su = new BaseSupplierUnion();
			 su.setId(sp.getId());
			 List<BaseSupplier> list = sp.getBaseSupplier();
				for(Iterator<BaseSupplier> it  = list.iterator();it.hasNext();){
					BaseSupplier bs = it.next();
					su.setName(bs.getName()); //获取公司名称
				}
			 su.setSupplierId(sp.getSupplierId());
			 su.setPrice(sp.getPrice());
			 su.setPrices(sp.getPrice());
			 su.setRemarks(sp.getRemarks());
			 su.setIsDelete(sp.getIsDelete());
			 su.setCreateTime(sp.getCreateTime());
			 baseSupplierUnion.add(su);
		 }
		logger.debug("total:{}", Integer.valueOf(total));
		return new ExtGridReturn(pageParams.getRecords(total),pageParams.getPage(),total, baseSupplierUnion);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}
	
	@RequestMapping(value = { "/save" },method={org.springframework.web.bind.annotation.RequestMethod.POST})
	@ResponseBody
	public Object save(BaseSupplierPrice supplierPrice){
		
		try {
			
			if (StringUtils.isBlank(supplierPrice.getSupplierId())) {
				return new ExtReturn(false, ResultCode.COMPANT_NAME_IS_NULL);
			}
			String _price = supplierPrice.getPrice()+"";
			if (StringUtils.isBlank(_price)) {
				return new ExtReturn(false, ResultCode.PRICE_IS_NULL);
			}
			
			Criteria criteria = new Criteria();
			criteria.put("supplierPrice", supplierPrice);
			
			String result = this.supplierPriceService.saveSupplierPrice(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, ResultCode.SUCCESS);
			if ("00".equals(result)) {
				return new ExtReturn(false, ResultCode.FAILED);
			}
			return new ExtReturn(false, ResultCode.OTHER_SERVER_ERROR);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}
	
	
	@RequestMapping(value = { "/del/{id}" },method={org.springframework.web.bind.annotation.RequestMethod.POST})
	@ResponseBody
	public Object delete(@PathVariable String id,String supplierId) {
		try {
			if (StringUtils.isBlank(id)) {
				return new ExtReturn(false, ResultCode.SUPPLIER_PRIMARY_KEY_IS_NULL);
			}
			if (StringUtils.isBlank(supplierId)) {
				return new ExtReturn(false, ResultCode.SUPPLIERS_PRICE_IS_NULL);
			}
			Criteria criteria = new Criteria();
			criteria.put("id", id);
			criteria.put("supplierId", supplierId);
			String result = this.supplierPriceService.deleteByPrimaryKey(criteria);
			if ("02".equals(result))
				return new ExtReturn(false, ResultCode.SUPPLIER_STATUS_USEING_NOT_DELETE);
			if ("01".equals(result))
				return new ExtReturn(true, ResultCode.SUCCESS);
			if ("00".equals(result)) {
				return new ExtReturn(false, ResultCode.FAILED);
			}
			return new ExtReturn(false, ResultCode.OTHER_SERVER_ERROR);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}
	
	@RequestMapping(value = { "/comboxList" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public String comboxTree(String supplierId){
		try {
		Criteria criteria = new Criteria();
		criteria.put("isDelete", "0");
		criteria.put("supplierId", supplierId);
		criteria.setOrderByClause(" CREATETIME DESC ");
		List<BaseSupplierPrice> list = supplierPriceService.selectByExample(criteria);
		if (list != null && list.size() > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for (int i = 0; i < list.size(); i++) {
				sb.append("{");
				BaseSupplierPrice supplier = (BaseSupplierPrice) list.get(i);
				String id =  supplier.getId();
				String name = supplier.getPrice().toString();
				sb.append("\"id\":" + "\"" + id + "\",");
				sb.append("\"name\":" + "\"" + name +" / 斤"+ "\"");
				sb.append("},");
				}
			sb = sb.delete(sb.length() - 1, sb.length());
			sb.append("]");
			return sb.toString();
		}
		return "[]";
		} catch (Exception e) {
			return e.getMessage();
		}
	}


}
