/**   
* @Project: Platform 
* @Title: ShipmentController.java 
* @Package com.coiol.platform.web.controller 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2015年1月11日 下午8:09:20 
* @Copyright: 2015  
* @version V1.0 
 */
package org.coiol.platform.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.springmvc.DateConvertEditor;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseModules;
import org.coiol.platform.core.model.BaseShipment;
import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.core.model.ExceptionReturn;
import org.coiol.platform.core.model.ExtGridReturn;
import org.coiol.platform.core.model.ExtReturn;
import org.coiol.platform.core.model.PageParams;
import org.coiol.platform.service.BaseModulesService;
import org.coiol.platform.service.BaseShipmentService;
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

/** 
 * @ClassName ShipmentController  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2015年1月11日 
 * 
 */
@Controller
@RequestMapping({"/admin/shipment/"})
public class ShipmentController {
	
	@Autowired 
	private BaseModulesService baseModulesService;
	
	@Autowired
	private BaseShipmentService baseShipmentService;
	
	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(ShipmentController.class);
	
	public ShipmentController(){
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	/**
	 * 
	* @Title: index  
	* @Description: TODO 
	* @author Yin MingXing master@coiol.com  
	* @date 2015年1月11日
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
		return "admin/TbsShipment/index";
	}
	
	/**
	 * 
	* @Title: all  
	* @Description: 查询出货商信息列表 
	* @author Yin MingXing master@coiol.com  
	* @date 2015年1月11日
	* @param @param pageParams
	* @param @param realName
	* @param @return      
	* @return Object     
	* @throws  
	 */
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
		List<BaseShipment> list = this.baseShipmentService.selectByExample(criteria);
		int total = this.baseShipmentService.countByExample(criteria);
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
	* @Description: 基本信息页面 
	* @author Yin MingXing master@coiol.com  
	* @date 2015年1月11日
	* @param @return      
	* @return String     
	* @throws  
	 */
	@RequestMapping(value = { "/baseDlg" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String baseDlg(){
		return "admin/TbsShipment/baseDlg";
	}
	
	/**
	 * 
	* @Title: save  
	* @Description: 保存出货商信息 
	* @author Yin MingXing master@coiol.com  
	* @date 2015年1月11日
	* @param @param supplier
	* @param @return      
	* @return Object     
	* @throws  
	 */
	@RequestMapping(value = { "/save" },method={org.springframework.web.bind.annotation.RequestMethod.POST})
	@ResponseBody
	public Object save(BaseShipment shipment){
		
		try {
			
			if (StringUtils.isBlank(shipment.getName())) {
				return new ExtReturn(false, "店面名称不能为空！");
			}
			
			if (StringUtils.isBlank(shipment.getAddress())) {
				return new ExtReturn(false, "店面地址不能为空！");
			}
			
			if (StringUtils.isBlank(shipment.getTelephone())) {
				return new ExtReturn(false, "店面联系方式不能为空！");
			}
			
			Criteria criteria = new Criteria();
			criteria.put("shipment", shipment);
			
			String result = this.baseShipmentService.saveShipment(criteria);
			
			if ("01".equals(result))
				return new ExtReturn(true, "出货商信息保存成功！");
			if ("00".equals(result)) {
				return new ExtReturn(false, "出货商信息保存失败！");
			}
			return new ExtReturn(false, result);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}
	
	/**
	 * 
	* @Title: delete  
	* @Description: 删除出货商数据信息 
	* @author Yin MingXing master@coiol.com  
	* @date 2015年1月11日
	* @param @param id
	* @param @return      
	* @return Object     
	* @throws  
	 */
	@RequestMapping(value = { "/del/{id}" },method={org.springframework.web.bind.annotation.RequestMethod.POST})
	@ResponseBody
	public Object delete(@PathVariable String id) {
		try {
			if (StringUtils.isBlank(id)) {
				return new ExtReturn(false, "供应商主键不能为空！");
			}
			Criteria criteria = new Criteria();
			criteria.put("id", id);
			String result = this.baseShipmentService.deleteByPrimaryKey(criteria);
			if ("02".equals(result))
				return new ExtReturn(false, "该供应商数据处于使用状态，不能删除！");
			if ("01".equals(result))
				return new ExtReturn(true, "供应商信息删除成功！");
			if ("00".equals(result)) {
				return new ExtReturn(false, "供应商信息删除失败！");
			}
			return new ExtReturn(false, result);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}
	
	/**
	 * 
	* @Title: comboxTree  
	* @Description: 获取出货商数据comboxTree 
	* @author Yin MingXing master@coiol.com  
	* @date 2015年1月11日
	* @param @return      
	* @return String     
	* @throws  
	 */
	@RequestMapping(value = { "/comboxList" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public String comboxTree(){
		try {
		Criteria criteria = new Criteria();
		criteria.put("isDelete", "0");
		criteria.setOrderByClause(" CREATETIME DESC ");
		List<BaseShipment> list = baseShipmentService.selectByExample(criteria);
		if (list != null && list.size() > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for (int i = 0; i < list.size(); i++) {
				sb.append("{");
				BaseShipment supplier = (BaseShipment) list.get(i);
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
