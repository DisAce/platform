

package org.coiol.platform.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{

	public HomeController()
	{
	}
	@RequestMapping({"/"})
	public String home()
	{
		return "login";
	}
	
	@RequestMapping({"/index"})
	public String index()
	{
		return "index";
	}
	
	@RequestMapping({"/main"})
	public String main()
	{
		return "main";
	}
	
	@RequestMapping({"/intercept"})
	public String intercept()
	{
		return "intercept";
	}
	

	@RequestMapping({"/header"})
	public String header()
	{
		return "header";
	}
	
	@RequestMapping({"/welcome"})
	public String welcome()
	{
		return "welcome";
	}
}
