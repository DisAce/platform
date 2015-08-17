<%@ page language="java" import="java.util.*,java.util.HashMap" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xht1ml">
<head>
<%@ include file="path.jsp"%>
</head>
<body id="body" >
			<div title="欢迎页">
				<div style="padding-left: 10px;">
					<h2>简介</h2>
					<p><span style="padding-left: 30px;" />JeeTemp（J2ee  template）是由（<a onclick="javascript:window.open('http://www.yy606.com');" href="javascript:void(0)">咿呀网</a> 跟 <a onclick="javascript:window.open('http://www.mn606.com')" href="javascript:void(0)">美女图片</a>）开发的一款在线代码生成器。生成的代码可提供下载，可与本系统无缝集成，还提供了自定义采集，源码编辑等功能，为你节约时间
					   可帮助你解决所有的模板功能 一键生成源码JSP页面,无需你再为这里功能浪费时间，生成的代码规范，易修改，  简单方便，实用。
					   <br><span style="padding-left: 30px;"> </span>JeeTemp 宗旨：让复杂事情简单化;抽象的功能留给你自己，提供给你简洁易修改的源码，让开发变的简单，快速，易操作。
					</p>					 	
					<h3>其他</h3>
					<ul>
					    <li>Coiol 采用J2EE技术 SpringMVC+Mybatis+Jquery+Mysql+velocity 框架开发而成 </li>
					    <li>Coiol 1.0正式版本六大技术点: <b>1.在线代码生成器</b> <b>2.自定义采集</b> <b>3.数据管理(模板)</b> <b>4.在线源码修改</b></li>
					    <li>技术点一：在线代码生成器，是基于解析SQL生成， 暂时是对mysql做了解析集成 ,根据创建的SQL语句生成对应的 entity,service,dao,controller,jsp 等模板功能一键生成直接使用</li> 
					    <li>技术点二：模板库，可以指定模板生成</li> 
					    <li>技术点三：自定义模板采集爬虫，是一款自定义采集抓取程序 ，由 httpclient 与 jsoup 开发而成，可以任意抓取， 地图页 ,列表页，内容页，的网站  </li> 
					    <li>技术点四：源码编辑可编辑 web-apps下面的任何文件，对不可编辑的扩展名进行了过滤</li> 
						<li>支持多种浏览器: IE, 火狐, Google</li> 
						<li>支持 Mysql</li>
						<li>基础权限: 用户，角色，菜单权限，按钮权限，数据权限</li>
						<li>运行环境 JDK6 Tomcat6～7</li>
					</ul>
				</div>
				<div style="padding-left: 10px;">
					<h2>技术交流</h2>
					<ul>
						<li>作者：EingXin</li>
						<li>邮箱：master@coiol.com</li> 
					</ul>
				</div>
			</div>
</body>
</html>