package org.coiol.platform.web.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.springmvc.DateConvertEditor;
import org.coiol.platform.common.utils.MethodUtil;
import org.coiol.platform.common.utils.SessionUtil;
import org.coiol.platform.core.constant.ResultCode;
import org.coiol.platform.core.jackjson.JackJson;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseLoginLog;
import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.core.model.ExceptionReturn;
import org.coiol.platform.core.model.ExtReturn;
import org.coiol.platform.core.model.Tree;
import org.coiol.platform.service.BaseModulesService;
import org.coiol.platform.service.BaseUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class LoginController
{

	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(LoginController.class);
	public static MethodUtil util = new MethodUtil();
	@Autowired
	private BaseUsersService baseUsersService;
	@Autowired
	private BaseModulesService baseModulesService;
	@Value("${limit.millis:3600000}")
	private Long millis;
	public LoginController()
	{
	}
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	@RequestMapping({"/logout"})
	public String logout(HttpSession session, Locale locale)
	{
		try
	    {
	      session.removeAttribute("CURRENT_USER");
	      session.invalidate();
	      return "login";
	    }
	    catch (Exception e)
	    {
	      logger.error("Exception: ", e);
	      String er = JackJson.fromObjectToJson(e.getMessage());
		  return er;
	    }
	}
	@RequestMapping(value = { "/treeMenu" })
	@ResponseBody
	public Object treeMenu(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			PrintWriter writer = response.getWriter();
			response.reset();
			BaseUsers user = (BaseUsers) SessionUtil.getAttr(request, "CURRENT_USER");;

			Tree tree = this.baseModulesService.selectModulesByUser(user);
			String json = JackJson.fromObjectToJson(tree.getChildren());
			writer.write(json);
			writer.flush();
			writer.close();
			return "01";
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}
	
	@RequestMapping(value = { "/login" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public Object login(@RequestParam String username, @RequestParam String password,BaseLoginLog loginLog, HttpServletRequest request)
	{
		try {
			String msg;
			// 帐号不能为空！
			if (StringUtils.isBlank(username)) {
				return new ExtReturn(false, ResultCode.USERNAME_IS_NULL);
			}
			// 密码不能为空
			if (StringUtils.isBlank(password)) {
				return new ExtReturn(false, ResultCode.USER_PASSWORD_IS_NULL);
			}
			// 获取递交的验证码
			String verifyCode = request.getParameter("verifyCode");
			
			// 验证码不能为空
			if (StringUtils.isBlank(verifyCode)) {
				return new ExtReturn(false, ResultCode.VERIFY_CODE_IS_NULL);
			}
			SessionUtil.set_user(username);
			SessionUtil.setUserAgent(request.getParameter("userAgent"));
			SessionUtil.setIp(MethodUtil.getIpAddr(request));
			String sessionCaptcha = (String) SessionUtil.getAttr(request, "KAPTCHA_SESSION_KEY");// session验证码
			
			// 验证码已经失效!请重新输入新的验证码
			if (null == sessionCaptcha) {
				SessionUtil.setStatus(1);
				return new ExtReturn(false, ResultCode.VERIFY_CODE_IS_INVAL);
			}
			// 验证码输入不正确,请重新输入
			if (!verifyCode.equalsIgnoreCase((String) sessionCaptcha)) {
				SessionUtil.setStatus(1);
				return new ExtReturn(false, ResultCode.VERIFY_CODE_IS_FAILD);
			}
			SessionUtil.removeAttr(request, "KAPTCHA_SESSION_KEY");
			Criteria criteria = new Criteria();
			criteria.put("account", username);
			criteria.put("passwordIn", password);
			criteria.put("loginip", MethodUtil.getIpAddr(request));
			String result = this.baseUsersService.selectByBaseUser(criteria);
			if ("01".equals(result)) {
				BaseUsers baseUser = (BaseUsers) criteria.get("baseUser");
				Integer isAdmin = baseUser.getIsAdmin() == null ? 1 : baseUser.getIsAdmin();
				SessionUtil.setAttr(request, "isAdmin", "" + isAdmin);
				SessionUtil.setAttr(request, "CURRENT_USER",baseUser);
				SessionUtil.setStatus(0);
				List<String> authUrls = new ArrayList<String>();
				authUrls.add("/main");
				SessionUtil.setAttr(request, "userName",baseUser.getRealName());
				SessionUtil.setAttr(request, "authUrls", authUrls);
				logger.info("{}登陆成功", baseUser.getRealName());
				return new ExtReturn(true, ResultCode.SUCCESS);
			}
			// 用户名或者密码错误
			if ("00".equals(result)) {
				SessionUtil.setStatus(1);
				return new ExtReturn(false, ResultCode.USER_OR_PASSWORD_IS_FAILD);
			}
			return new ExtReturn(false, ResultCode.OTHER_SERVER_ERROR);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	
	@RequestMapping(value={"/findpwd"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String findpwd()
	{
		return "user/findpwd";
	}
	
	
	@RequestMapping(value={"/findPassword"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public Object saveFindpwd(BaseUsers user, HttpServletRequest request) {
		try {
			String msg;
			String verifyCode = request.getParameter("verifyCode"); // 获取递交的验证码
			if (null != user) {
				if (StringUtils.isBlank(user.getAccount())) {
					return new ExtReturn(false, ResultCode.USERNAME_IS_NULL);
				}
			}else{
				return new ExtReturn(false, ResultCode.USER_IS_NULL);
			}
			if (StringUtils.isBlank(user.getEmail())) {
				return new ExtReturn(false, ResultCode.REGISTER_USER_EMAIL_IS_NULL);
			}
			if (StringUtils.isBlank(verifyCode)) {
				return new ExtReturn(false, ResultCode.VERIFY_CODE_IS_NULL);
			}
			String sessionCaptcha = (String) SessionUtil.getAttr(request, "KAPTCHA_SESSION_KEY");// session验证码
			if (null == sessionCaptcha) {
				return new ExtReturn(false, ResultCode.VERIFY_CODE_IS_INVAL);
			}
			if (!verifyCode.equalsIgnoreCase((String) sessionCaptcha)) {
				return new ExtReturn(false, ResultCode.VERIFY_CODE_IS_FAILD);
			}
			SessionUtil.removeAttr(request, "KAPTCHA_SESSION_KEY");
			Criteria criteria = new Criteria();
			criteria.put("user", user);
			String result = this.baseUsersService.findPassword(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, ResultCode.SEND_EMAIL_SUCCESS);
			if ("00".equals(result)) {
				return new ExtReturn(false, ResultCode.SEND_EMAIL_FAILED);
			}
			return new ExtReturn(false, ResultCode.OTHER_SERVER_ERROR);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}
	@RequestMapping(value={"/resetpwd/{token}/{userId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String resetpwd(@PathVariable String token, @PathVariable String userId, Model model)
	{
		BaseUsers user = baseUsersService.selectByPrimaryKey(userId);
		if (user == null || !user.getPassword().equals(token.toLowerCase()) || compareTo(user.getLastLoginTime()))
		{
			model.addAttribute("error", "链接已经失效！");
			return "user/resetpwd";
		} else
		{
			model.addAttribute("t", token);
			model.addAttribute("u", userId);
			return "user/resetpwd";
		}
	}
	@RequestMapping(value={"/resetpwd"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	public String resetpwdSave(@RequestParam String u, @RequestParam String t, @RequestParam String newpwd, @RequestParam String renewpwd, Model model)
	{
		try
	    {
	      model.addAttribute("t", t);
	      model.addAttribute("u", u);
	      if (StringUtils.isBlank(u)) {
	        model.addAttribute("msg", "密码修改失败！");
	        return "user/resetpwd";
	      }
	      if (StringUtils.isBlank(t)) {
	        model.addAttribute("msg", "密码修改失败！");
	        return "user/resetpwd";
	      }
	      if (StringUtils.isBlank(newpwd)) {
	        model.addAttribute("msg", "新密码不能为空！");
	        return "user/resetpwd";
	      }
	      if (StringUtils.isBlank(renewpwd)) {
	        model.addAttribute("msg", "确认密码不能为空！");
	        return "user/resetpwd";
	      }
	      if (!renewpwd.equals(newpwd)) {
	        model.addAttribute("msg", "新密码和确认密码输入不一致！");
	        return "user/resetpwd";
	      }
	      Criteria criteria = new Criteria();
	      criteria.put("token", t);
	      criteria.put("userId", u);
	      criteria.put("password", newpwd);
	      String result = this.baseUsersService.updatePassword(criteria);
	      if ("01".equals(result))
	        model.addAttribute("msg", "密码修改成功！请重新登录");
	      else if ("00".equals(result))
	        model.addAttribute("msg", "密码修改失败！");
	      else
	        model.addAttribute("msg", result);
	    }
	    catch (Exception e) {
	      logger.error("Exception: ", e);
	      model.addAttribute("msg", e.getMessage());
	    }
	    return "user/resetpwd";
	}

	private boolean compareTo(Date date)
	{
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		c.setTime(date);
		long lastly = c.getTimeInMillis();
		return now - lastly >= millis.longValue();
	}
	//找回密码页面
	@RequestMapping(value={"/forgotPassword"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String forgotPassword()
	{
			return "login/forgotPassword";
	}

}
