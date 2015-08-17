/**   
* @Project: Platform 
* @Title: MaterialController.java 
* @Package com.coiol.platform.web.controller 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2014年11月26日 上午10:45:08 
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
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseMaterial;
import org.coiol.platform.core.model.BaseMaterialUnion;
import org.coiol.platform.core.model.BaseModules;
import org.coiol.platform.core.model.BaseSupplier;
import org.coiol.platform.core.model.BaseSupplierPrice;
import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.core.model.ExceptionReturn;
import org.coiol.platform.core.model.ExtGridReturn;
import org.coiol.platform.core.model.ExtReturn;
import org.coiol.platform.core.model.PageParams;
import org.coiol.platform.service.BaseMaterialService;
import org.coiol.platform.service.BaseModulesService;
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
 * @ClassName MaterialController  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2014年11月26日 
 * 
 */
@Controller
@RequestMapping({"/admin/material/"})
public class MaterialController {
	
	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(MaterialController.class);
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private BaseModulesService baseModulesService;
	@Autowired
	private BaseMaterialService materialService;
	
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
		return "admin/TbsMaterial/index";
	}
	
	@RequestMapping(value = { "/baseDlg" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String baseDlg(){
		
		return "admin/TbsMaterial/baseDlg";
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
			criteria.setOrderByClause(" DATE desc");
		}
		
		if (StringUtils.isNotBlank(realName)) {
			criteria.put("realNameLike", realName);
		}else{
			criteria.put("isDelete", "0");
		}
		
		List<BaseMaterial> baseMaterial = this.materialService.selectByExample(criteria);
		int total = this.materialService.countByExample(criteria);
		
		List<BaseMaterialUnion> baseMaterialUnion = new LinkedList<BaseMaterialUnion>();
		 for (BaseMaterial ms : baseMaterial){
			 BaseMaterialUnion mu = new BaseMaterialUnion();
			 mu.setId(ms.getId());
			 List<BaseSupplier> list = ms.getBaseSupplier();
			 for(Iterator<BaseSupplier> it  = list.iterator();it.hasNext();){
					BaseSupplier bs = it.next();
					mu.setName(bs.getName()); //获取公司名称
				}
			 List<BaseSupplierPrice> listPrice = ms.getBaseSupplierPrice();
			 for(Iterator<BaseSupplierPrice> it  = listPrice.iterator();it.hasNext();){
					BaseSupplierPrice sp = it.next();
					mu.setPrice(sp.getPrice()); //获取单价
				}
			 mu.setTotal(ms.getTotal());
			 mu.setWeight(ms.getWeight());
			 mu.setSupplierId(ms.getSupplierId());
			 mu.setNum(ms.getNum());
			 mu.setPriceId(ms.getPriceId());
			 mu.setSingleTotal(ms.getSingleTotal());
			 mu.setSinglePrice(ms.getSinglePrice());
			 mu.setRemarks(ms.getRemarks());
			 mu.setIsDelete(ms.getIsDelete());
			 mu.setDate(ms.getDate());
			 mu.setCreateTime(ms.getCreateTime());
			 baseMaterialUnion.add(mu);
		 }
		logger.debug("total:{}", Integer.valueOf(total));
		return new ExtGridReturn(pageParams.getRecords(total),pageParams.getPage(),total, baseMaterialUnion);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}
	
	
	@RequestMapping(value = { "/save" },method={org.springframework.web.bind.annotation.RequestMethod.POST})
	@ResponseBody
	public Object save(BaseMaterial material){
		
		try {
			String total = material.getTotal()+"";
			if (StringUtils.isBlank(total)) {
				return new ExtReturn(false, "订单总金额不能为空！");
			}
			
			if (StringUtils.isBlank(material.getPriceId())) {
				return new ExtReturn(false, "供应商单价不能为空！");
			}
			String num = material.getNum()+"";
			if (StringUtils.isBlank(num)) {
				return new ExtReturn(false, "订单总只数不能为空！");
			}
			
			Criteria criteria = new Criteria();
			criteria.put("material", material);
			
			String result = this.materialService.saveMaterial(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, "进货单信息保存成功！");
			if ("00".equals(result)) {
				return new ExtReturn(false, "进货单信息保存失败！");
			}
			return new ExtReturn(false, result);
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
				return new ExtReturn(false, "请选择要删除的记录！");
			}
			Criteria criteria = new Criteria();
			criteria.put("id", id);
			String result = this.materialService.deleteByPrimaryKey(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, "进货单信息删除成功！");
			if ("00".equals(result)) {
				return new ExtReturn(false, "进货单信息删除失败！");
			}
			return new ExtReturn(false, result);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

}
