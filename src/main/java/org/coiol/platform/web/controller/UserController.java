

package org.coiol.platform.web.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.springmvc.DateConvertEditor;
import org.coiol.platform.core.constant.ResultCode;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseModules;
import org.coiol.platform.core.model.BaseUserRole;
import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.core.model.ExceptionReturn;
import org.coiol.platform.core.model.ExtGridReturn;
import org.coiol.platform.core.model.ExtReturn;
import org.coiol.platform.core.model.PageParams;
import org.coiol.platform.service.BaseModulesService;
import org.coiol.platform.service.BaseUserRoleService;
import org.coiol.platform.service.BaseUsersService;
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
@RequestMapping( { "/admin/user" })
public class UserController {

	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(UserController.class);
	@Autowired
	private BaseUsersService baseUsersService;
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private BaseModulesService baseModulesService;
	@Autowired
	private BaseUserRoleService baseUserRoleService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping(value = { "/index" },method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String user(String id,HttpSession session,  ModelMap modelMap) {
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
					buttons.add(button);//.replaceAll("&apos;", "'").replaceAll("&quot;", "\"")
				}
			}
		}
		
		} catch (Exception e) {
			logger.error("Exception: ", e);
		}
		modelMap.addAttribute("buttons", buttons);
		return "admin/TbsUser/index";
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
		}
		List<BaseUsers> list = this.baseUsersService.selectByExample(criteria);
		int total = this.baseUsersService.countByExample(criteria);
		logger.debug("total:{}", Integer.valueOf(total));
		return new ExtGridReturn(pageParams.getRecords(total),pageParams.getPage(),total, list);
	}
	
	@RequestMapping(value = { "/baseDlg" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String baseDlg(){
		return "admin/TbsUser/baseDlg";
	}

	@RequestMapping(value = { "/changepwd" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String changePwd() {
		return "user/changepwd";
	}

	@RequestMapping(value = { "/changepwd" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public Object changePassword(@RequestParam
	String oldPassword, @RequestParam
	String newPassword, @RequestParam
	String comparePassword, @RequestParam
	String userId, HttpSession session) {
		try {
			if (StringUtils.isBlank(userId)) {
				return new ExtReturn(false, ResultCode.USER_ID_IS_NULL);
			}
			if (StringUtils.isBlank(oldPassword)) {
				return new ExtReturn(false, ResultCode.USER_PASSWORD_IS_NULL);
			}
			if (StringUtils.isBlank(newPassword)) {
				return new ExtReturn(false, ResultCode.NEW_PASSWORD_IS_NULL);
			}
			if (StringUtils.isBlank(comparePassword)) {
				return new ExtReturn(false, ResultCode.CONFIRM_PASSWORD_IS_NULL);
			}
			if (!comparePassword.equals(newPassword)) {
				return new ExtReturn(false, ResultCode.TWO_PASSWORD_IS_NULL);
			}
			BaseUsers user = (BaseUsers) session.getAttribute("CURRENT_USER");
			Criteria criteria = new Criteria();
			criteria.put("user", user);
			criteria.put("userId", userId);

			criteria.put("oldPassword", oldPassword);

			criteria.put("newPassword", newPassword);
			String result = this.baseUsersService.updateUserPassword(criteria);
			if ("01".equals(result)) {
				session.removeAttribute("CURRENT_USER");
				session.invalidate();
				return new ExtReturn(true, ResultCode.MODIFY_PASSWORD_SUCCESS);
			}
			if ("00".equals(result)) {
				return new ExtReturn(false, ResultCode.MODIFY_PASSWORD_FAILED);
			}
			return new ExtReturn(false, ResultCode.OTHER_SERVER_ERROR);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping(value = { "/{userId}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Object myRole(@PathVariable
	String userId) {
		try {
			Criteria criteria = new Criteria();
			criteria.put("userId", userId);
			logger.debug("{}", userId);
			List<BaseUserRole> list = this.baseUserRoleService.selectByExample(criteria);
			return list;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping( { "/reset/{userId}" })
	@ResponseBody
	public Object resetPassword(@PathVariable
	String userId) {
		try {
			if (StringUtils.isBlank(userId)) {
				return new ExtReturn(false, ResultCode.USER_ID_IS_NULL);
			}
			Criteria criteria = new Criteria();
			criteria.put("userId", userId);
			String result = this.baseUsersService
					.resetPwdByPrimaryKey(criteria);
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

	@RequestMapping( { "/del/{userId}" })
	@ResponseBody
	public Object delete(@PathVariable
	String userId, HttpSession session) {
		try {
			if (StringUtils.isBlank(userId)) {
				return new ExtReturn(false, ResultCode.USER_ID_IS_NULL);
			}

			BaseUsers user = (BaseUsers) session.getAttribute("CURRENT_USER");
			if (userId.equals(user.getUserId())) {
				return new ExtReturn(false, ResultCode.NOT_DELETE_CURRENT_COUNT);
			}
			Criteria criteria = new Criteria();
			criteria.put("userId", userId);
			String result = this.baseUsersService.deleteByPrimaryKey(criteria);
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

	@RequestMapping( { "/validate" })
	@ResponseBody
	public Object validateAccount(
			@RequestParam(value = "account", required = false, defaultValue = "")
			String account, @RequestParam
			String userId) {
		try {
			Criteria criteria = new Criteria();
			if (StringUtils.isNotBlank(account)) {
				criteria.put("account", account);
			}
			if (StringUtils.isNotBlank(userId)) {
				criteria.put("userId", userId);
			}
			String result = this.baseUsersService.validateAccount(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, ResultCode.ACCOUNT_IS_NOT_REGISTER);
			if ("00".equals(result)) {
				return new ExtReturn(false, ResultCode.ACCOUNT_IS_REDIST_REGISTER);
			}
			return new ExtReturn(false, ResultCode.OTHER_SERVER_ERROR);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping( { "/save" })
	@ResponseBody
	public Object save(BaseUsers user, @RequestParam
	Collection<String> roleIds) {
		try {
			if ((roleIds == null) || (roleIds.size() == 0)) {
				return new ExtReturn(false, ResultCode.ROLE_ID_IS_NULL);
			}
			if (StringUtils.isBlank(user.getAccount())) {
				return new ExtReturn(false, ResultCode.USERNAME_IS_NULL);
			}
			Criteria criteria = new Criteria();
			criteria.put("roleIds", roleIds);
			criteria.put("user", user);
			String result = this.baseUsersService.saveUser(criteria);
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

	@RequestMapping(value = { "/myinfo" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String myinfo() {
		return "user/myinfo";
	}

	@RequestMapping(value = { "/myinfo" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public Object saveMyinfo(BaseUsers user) {
		try {
			if (user == null) {
				return new ExtReturn(false, ResultCode.USER_IS_NULL);
			}
			if (StringUtils.isBlank(user.getUserId())) {
				return new ExtReturn(false, ResultCode.USER_ID_IS_NULL);
			}
			String result = this.baseUsersService
					.updateByPrimaryKeySelective(user);
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
}