
package org.coiol.platform.web.controller;

import java.util.Date;
import java.util.Locale;

import org.coiol.platform.common.springmvc.DateConvertEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class JSLoaderController
{

	public JSLoaderController()
	{
	}
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	@RequestMapping(value={"/yepnope"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String yepnope(Locale locale, Model model)
	{
		return "user/resetpwd";
	}
	@RequestMapping(value={"/lab"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String lab(Locale locale, Model model)
	{
		return "loader/lab";
	}
	@RequestMapping(value={"/headers"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String header(Locale locale, Model model)
	{
		return "loader/header";
	}
	@RequestMapping(value={"/loginlab"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String loginlab()
	{
		return "loader/login.LAB";
	}
	@RequestMapping(value={"/loginyepnope"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String loginyepnope()
	{
		return "loader/login.yepnope";
	}
}
