package org.coiol.platform.common.jackjson.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.codec.digest.DigestUtils;
import org.coiol.platform.common.utils.MethodUtil;
import org.coiol.platform.common.utils.PropertiesHolder;
import org.coiol.platform.core.util.ClassLoaderUtil;
import org.coiol.platform.core.util.FileDigest;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;



public class MainTest
{

	public MainTest()
	{
	}

	public static void main(String args[])
		throws Exception
	{
		String str = "admin";
		MethodUtil md = new MethodUtil();
		System.out.println("================="+md.getDES("c5a704f01300a735",1));
		System.out.println(DigestUtils.md5Hex(str));
		 String password = DigestUtils.md5Hex("admin");
		 System.out.println(DigestUtils.md5Hex(password + "{admin}"));
		System.out.println(DigestUtils.sha256Hex(str));
		System.out.println(DigestUtils.sha384Hex(str));
		System.out.println(DigestUtils.sha512Hex(str));
		System.out.println(FileDigest.getFileMD5(new File("D:/apache/tomcat-6.0.35/logs/lottery2.0/all.2011-12-31.txt")));
		System.out.println(FileDigest.getFileMD5(new File("D:/apache/tomcat-6.0.35/logs/lottery2.0/all.2011-12-30.txt")));
		System.out.println(FileDigest.getFileMD5(new File("D:/apache/tomcat-6.0.35/logs/lottery2.0/all.2011-12-29.txt")));
		long start = System.currentTimeMillis();
		System.out.println(FileDigest.getFileMD5(new File("F:/movie/金陵十三钗DVD高清全集.rmvb")));
		System.out.println((new StringBuilder()).append("耗时(毫秒)：").append(System.currentTimeMillis() - start).toString());
//		Map<?, ?> maps = FileDigest.getDirMD5(new File("D:/apache/tomcat-6.0.35/logs"), true);
//		String key;
//		String md5;
//		for (Iterator i$ = maps.entrySet().iterator(); i$.hasNext(); System.out.println((new StringBuilder()).append(md5).append(" ").append(key).toString()))
//		{
//			java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
//			key = (String)entry.getKey();
//			md5 = (String)entry.getValue();
//		}
		
		test1();

	}

	private static void test1()
		throws JsonGenerationException, JsonMappingException, IOException
	{
		User user = new User();
		user.setAge(Integer.valueOf(23));
		user.setName("cx");
		user.setPassword("123456");
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = (new SimpleFilterProvider()).addFilter("myFilter", SimpleBeanPropertyFilter.serializeAllExcept(new String[] {
			"password"
		}));
		String json = mapper.writer(filters).writeValueAsString(user);
		System.out.println(json);
		InputStreamReader reader = null;
		Properties properties = new Properties();
		InputStream is = ClassLoaderUtil.getResourceAsStream("config/others/config.properties", MainTest.class);
		if (null != is)
		{
			reader = new InputStreamReader(is, "UTF-8");
			properties.load(reader);
		}
		PropertiesHolder p = new PropertiesHolder();
		p.setProperties(properties);
		PropertiesHolder.getDouble("1");
		System.out.println(PropertiesHolder.getDouble("78",5.6));
		
	}
}
