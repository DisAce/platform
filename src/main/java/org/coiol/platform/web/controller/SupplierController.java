
package org.coiol.platform.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.springmvc.DateConvertEditor;
import org.coiol.platform.core.constant.ResultCode;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseModules;
import org.coiol.platform.core.model.BaseSupplier;
import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.core.model.ExceptionReturn;
import org.coiol.platform.core.model.ExtGridReturn;
import org.coiol.platform.core.model.ExtReturn;
import org.coiol.platform.core.model.PageParams;
import org.coiol.platform.service.BaseModulesService;
import org.coiol.platform.service.BaseSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping({"/admin/supplier/"})
public class SupplierController {

	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(SupplierController.class);
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private BaseModulesService baseModulesService;
	@Autowired
	private BaseSupplierService supplierService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	
	/**
	 * 
	* @Title: INDEX  
	* @Description: 获取请求 
	* @author Yin MingXing master@coiol.com  
	* @date 2014年11月24日
	* @param @param id
	* @param @param session
	* @param @param modelMap
	* @param @return      
	* @return String     
	* @throws  
	*/
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
		return "admin/TbsSupplier/index";
	}
	
	
	@RequestMapping(value = { "/data" },method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public Object all(PageParams pageParams, @RequestParam(required = false, defaultValue = "") String realName) {
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
			criteria.setOrderByClause(" CREATETIME desc");
		}
		
		if (StringUtils.isNotBlank(realName)) {
			criteria.put("realNameLike", realName);
		}else{
			criteria.put("isDelete", "0");
		}
		List<BaseSupplier> list = this.supplierService.selectByExample(criteria);
		int total = this.supplierService.countByExample(criteria);
		logger.debug("total:{}", Integer.valueOf(total));
		return new ExtGridReturn(pageParams.getRecords(total),pageParams.getPage(),total, list);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}
	
	
	/**
	 * 
	* @Title: baseDlg  
	* @Description: 输入弹出框 
	* @author Yin MingXing master@coiol.com  
	* @date 2014年11月25日
	* @param @return      
	* @return String     
	* @throws  
	 */
	@RequestMapping(value = { "/baseDlg" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String baseDlg(){
		return "admin/TbsSupplier/baseDlg";
	}
	
	@RequestMapping(value = { "/save" },method={org.springframework.web.bind.annotation.RequestMethod.POST})
	@ResponseBody
	public Object save(BaseSupplier supplier){
		
		try {
			
			if (StringUtils.isBlank(supplier.getName())) {
				return new ExtReturn(false, ResultCode.COMPANT_NAME_IS_NULL);
			}
			
			if (StringUtils.isBlank(supplier.getAddress())) {
				return new ExtReturn(false, ResultCode.COMPANT_ADDRESS_IS_NULL);
			}
			
			if (StringUtils.isBlank(supplier.getTelephone())) {
				return new ExtReturn(false, ResultCode.COMPANT_PHONE_IS_NULL);
			}
			
			Criteria criteria = new Criteria();
			criteria.put("supplier", supplier);
			
			String result = this.supplierService.saveSupplier(criteria);
			
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
	public Object delete(@PathVariable String id) {
		try {
			if (StringUtils.isBlank(id)) {
				return new ExtReturn(false, ResultCode.SUPPLIER_PRIMARY_KEY_IS_NULL);
			}
			Criteria criteria = new Criteria();
			criteria.put("id", id);
			String result = this.supplierService.deleteByPrimaryKey(criteria);
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
	public String comboxTree(){
		try {
		Criteria criteria = new Criteria();
		criteria.put("isDelete", "0");
		criteria.setOrderByClause(" CREATETIME DESC ");
		List<BaseSupplier> list = supplierService.selectByExample(criteria);
		if (list != null && list.size() > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for (int i = 0; i < list.size(); i++) {
				sb.append("{");
				BaseSupplier supplier = (BaseSupplier) list.get(i);
				String id =  supplier.getId();
				String name = supplier.getName();
				sb.append("\"id\":" + "\"" + id + "\",");
				sb.append("\"name\":" + "\"" + name + "\"");
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



