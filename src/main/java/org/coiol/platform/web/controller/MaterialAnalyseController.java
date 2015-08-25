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

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.springmvc.DateConvertEditor;
import org.coiol.platform.core.constant.ResultCode;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseMaterial;
import org.coiol.platform.core.model.BaseModules;
import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.core.model.ExceptionReturn;
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
import org.springframework.web.bind.annotation.ResponseBody;

/** 
 * @ClassName MaterialController  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2014年11月26日 
 * 
 */
@Controller
@RequestMapping({"/admin/materialAnalyse/"})
public class MaterialAnalyseController {
	
	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(MaterialAnalyseController.class);
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
		return "admin/TbsMaterialAnalyse/index";
	}
	

	
	@RequestMapping(value = { "/data" },method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public void all(PageParams pageParams, PrintWriter writer) {
		String resultJson="[]";
		String total = null;
		String num = null;
		String singleTotal = null;
		String weight = null;
		String categories=null;
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
			criteria.setOrderByClause(" DATE asc");
		}
		
		criteria.put("dataLike", "7");
		criteria.put("isDelete", "0");
		List<BaseMaterial> list = this.materialService.chart(criteria);
		logger.debug("total:{}", Integer.valueOf(list.size()));
		if(null != list && list.size() > 0){
			total = "\"total\":[";
			num = "\"num\":[";
			weight = "\"weight\":[";
			singleTotal = "\"singleTotal\":[";
			categories = "\"categories\":[";
		 for (BaseMaterial ms : list){
			 String date = ms.getDate();
			 categories += "\""+date+"\",";
			 int _num = ms.getNum().intValue();
			 num += _num+",";
			 double _weight = ms.getWeight().doubleValue();
			 weight += _weight+",";
			 double _single = ms.getSingleTotal().doubleValue();
			 singleTotal += _single + ",";
			 double memory = ms.getTotal().doubleValue();
			 total += memory+",";
		 }
		 	total = total.substring(0,total.length()-1);
		 	total = total+"]";
		 	num = num.substring(0,num.length()-1);
		 	num = num+"]";
		 	weight = weight.substring(0,weight.length()-1);
		 	weight = weight+"]";
		 	singleTotal = singleTotal.substring(0,singleTotal.length()-1);
		 	singleTotal = singleTotal+"]";
			categories = categories.substring(0,categories.length()-1);
			categories = categories+"]";
			String success = "\"success\":true";
			resultJson="{"+success+","+total+","+num+","+weight+","+singleTotal+","+categories+"}";
			writer.write(resultJson);
			writer.flush();
			writer.close();
			}
		} catch (Exception e) {
			logger.error("Exception: ", e);
//			/return new ExceptionReturn(e);
		}
	}
	
	
	@RequestMapping(value = { "/save" },method={org.springframework.web.bind.annotation.RequestMethod.POST})
	@ResponseBody
	public Object save(BaseMaterial material){
		
		try {
			String total = material.getTotal()+"";
			if (StringUtils.isBlank(total)) {
				return new ExtReturn(false, ResultCode.ORDER_AMOUNT_IS_NULL);
			}
			
			if (StringUtils.isBlank(material.getPriceId())) {
				return new ExtReturn(false, ResultCode.SUPPLIERS_PRICE_IS_NULL);
			}
			String num = material.getNum()+"";
			if (StringUtils.isBlank(num)) {
				return new ExtReturn(false, ResultCode.ORDER_TOTAL_NUM_IS_NULL);
			}
			
			Criteria criteria = new Criteria();
			criteria.put("material", material);
			
			String result = this.materialService.saveMaterial(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, ResultCode.JHDS_INFO_SAVE_SUCCESS);
			if ("00".equals(result)) {
				return new ExtReturn(false, ResultCode.JHDS_INFO_SAVE_FAILED);
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
				return new ExtReturn(false, ResultCode.PLEASE_CHOOSE);
			}
			Criteria criteria = new Criteria();
			criteria.put("id", id);
			String result = this.materialService.deleteByPrimaryKey(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, ResultCode.JHDS_INFO_DELETE_SUCCESS);
			if ("00".equals(result)) {
				return new ExtReturn(false, ResultCode.JHDS_INFO_DELETE_FAILED);
			}
			return new ExtReturn(false, ResultCode.OTHER_SERVER_ERROR);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

}
