

package org.coiol.platform.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.springmvc.DateConvertEditor;
import org.coiol.platform.core.constant.ResultCode;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseFields;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.core.model.ExceptionReturn;
import org.coiol.platform.core.model.ExtGridReturn;
import org.coiol.platform.core.model.ExtPager;
import org.coiol.platform.core.model.ExtReturn;
import org.coiol.platform.service.BaseFieldsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( { "/field" })
public class FieldController {

	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(FieldController.class);
	@Autowired
	private BaseFieldsService baseFieldsService;

	public FieldController() {
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder
				.registerCustomEditor(String.class, new StringTrimmerEditor(
						true));
	}

	@RequestMapping(method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String index() {
		return "user/field";
	}

	@RequestMapping( { "/all" })
	@ResponseBody
	public Object all(ExtPager pager, @RequestParam(required = false)
	String fieldName) {
		try {
			Criteria criteria = new Criteria();

			if ((pager.getLimit() != null) && (pager.getStart() != null)) {
				criteria.setOracleEnd(Integer.valueOf(pager.getLimit()
						.intValue()
						+ pager.getStart().intValue()));
				criteria.setOracleStart(pager.getStart());
			}

			if ((StringUtils.isNotBlank(pager.getDir()))
					&& (StringUtils.isNotBlank(pager.getSort())))
				criteria.setOrderByClause(pager.getSort() + " "
						+ pager.getDir());
			else {
				criteria.setOrderByClause(" field desc ,sort asc ");
			}
			if (StringUtils.isNotBlank(fieldName)) {
				criteria.put("fieldNameLike", fieldName);
			}
			List<BaseFields> list = this.baseFieldsService.selectByExample(criteria);
			int total = this.baseFieldsService.countByExample(criteria);
			logger.debug("total:{}", Integer.valueOf(total));
			return new ExtGridReturn(total, list);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping( { "/save" })
	@ResponseBody
	public Object save(BaseFields fields) {
		try {
			if (fields == null) {
				return new ExtReturn(false, ResultCode.SYSTEM_FIELD_IS_NULL);
			}
			if (StringUtils.isBlank(fields.getField())) {
				return new ExtReturn(false, ResultCode.SYSTEM_FIELD_IS_NULL);
			}
			if (StringUtils.isBlank(fields.getFieldName())) {
				return new ExtReturn(false, ResultCode.SYSTEM_FIELD_NAME_IS_NULL);
			}
			if (StringUtils.isBlank(fields.getValueField())) {
				return new ExtReturn(false, ResultCode.SYSTEM_FIELD_VALUE_IS_NULL);
			}
			if (StringUtils.isBlank(fields.getDisplayField())) {
				return new ExtReturn(false, ResultCode.SYSTEM_FIELD_DISPLAY_IS_NULL);
			}
			Criteria criteria = new Criteria();
			criteria.put("fields", fields);
			String result = this.baseFieldsService.saveFields(criteria);
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

	@RequestMapping( { "/del/{fieldId}" })
	@ResponseBody
	public Object delete(@PathVariable
	String fieldId) {
		try {
			if (StringUtils.isBlank(fieldId)) {
				return new ExtReturn(false, ResultCode.SYSTEM_PRIMARY_KEY_IS_NULL);
			}
			Criteria criteria = new Criteria();
			criteria.put("fieldId", fieldId);
			String result = this.baseFieldsService.delete(criteria);
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

	@RequestMapping( { "/synchro" })
	@ResponseBody
	public Object synchro(HttpSession session) {
		try {
			Criteria criteria = new Criteria();
			criteria.setOrderByClause(" field desc ,sort asc ");
			criteria.put("enabled", "1");
			session.getServletContext().removeAttribute("fields");
			session.getServletContext().setAttribute("fields",
					this.baseFieldsService.selectAllByExample(criteria));
			return new ExtReturn(true, ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}
}
