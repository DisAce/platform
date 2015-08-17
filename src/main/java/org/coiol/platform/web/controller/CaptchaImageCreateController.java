

package org.coiol.platform.web.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Producer;
@Controller
@RequestMapping({"/Authentication/"})
public class CaptchaImageCreateController
{
	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(CaptchaImageCreateController.class);
	private Producer captchaProducer;
	public CaptchaImageCreateController()
	{
		captchaProducer = null;
	}

	@Autowired
	public void setCaptchaProducer(Producer captchaProducer)
	{
		this.captchaProducer = captchaProducer;
	}
	@RequestMapping({"/captchaImage.do"})
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		response.setDateHeader("Expires", 0);
	    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
	    response.setHeader("Pragma", "no-cache");
	    response.setContentType("image/jpeg");
	    
	    String capText = this.captchaProducer.createText();
	    request.getSession().setAttribute("KAPTCHA_SESSION_KEY", capText);
	    logger.info("KAPTCHA_SESSION_KEY:{}", capText);
	    BufferedImage bi = this.captchaProducer.createImage(capText);
	    ServletOutputStream out = response.getOutputStream();
	    ImageIO.write(bi, "jpg", out);
	    try {
	    	
	    	//刷新验证码
	      out.flush();
	      
	    } finally {
	    	
	      out.close();
	    }
	    return null;
	}
}
