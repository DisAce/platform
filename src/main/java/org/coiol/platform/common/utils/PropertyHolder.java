/**   
* @Project: Platform 
* @Title: PropertyHolder.java 
* @Package com.coiol.platform.common.utils 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2015年7月9日 下午8:16:22 
* @Copyright: 2015  
* @version V1.0 
 */
package org.coiol.platform.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.springframework.core.io.ClassPathResource;

/** 
 * @ClassName PropertyHolder  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2015年7月9日 
 * 
 */
public class PropertyHolder
{

	 private static final PlatFormLogger LOG = PlatFormLoggerFactory.getPlatFormLogger(PropertyHolder.class);
	    private static final Map<String, String> PROPERTIES = new HashMap<String, String>();

	    static {
	        init();
	    }

	    public static Map<String, String> getProperties() {
	        return PROPERTIES;
	    }
	    private static void load(InputStream inputStream, Map<String, String> map){
	        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"))){
	            String line;
	            while((line = reader.readLine()) != null){
	                line = line.trim();
	                if("".equals(line) || line.startsWith("#")){
	                    continue;
	                }
	                int index = line.indexOf("=");
	                if(index==-1){
	                    LOG.error("错误的配置："+line);
	                    continue;
	                }
	                if(index>0 && line.length()>index+1) {
	                    String key = line.substring(0, index).trim();
	                    String value = line.substring(index + 1, line.length()).trim();
	                    map.put(key, value);
	                }else{
	                    LOG.error("错误的配置："+line);
	                }
	            }
	        } catch (IOException ex) {
	            LOG.error("配置文件加载失败:" + ex.getMessage());
	            throw new RuntimeException(ex);
	        }
	    }
	    /**
	     * 本方法中的日志只能输出中文，因为APDPlatLoggerImpl中默认指定输出中文
	     * 只有配置项加载完毕，调用了指定日志输出语言方法LOG.setLocale(getLogLanguage())
	     * 之后，配置的日志输出语言才会生效
	     */
	    private static void init() {
	            String systemConfig="/config/others/config.properties";
	            String localConfig="/config.local.properties";
	            String dbConfig="/org/apdplat/db.properties";
	            String localDBConfig="/db.local.properties";
	            ClassPathResource cr = null;
	            try{
	                cr = new ClassPathResource(systemConfig);
	                load(cr.getInputStream(), PROPERTIES);
	                LOG.info("装入主配置文件:"+systemConfig);
	            }catch(Exception e){
	                LOG.info("装入主配置文件"+systemConfig+"失败!", e);
	            }
	            try{
	                cr = new ClassPathResource(localConfig);
	                load(cr.getInputStream(), PROPERTIES);
	                LOG.info("装入自定义主配置文件："+localConfig);
	            }catch(Exception e){
	                LOG.info("装入自定义主配置文件"+localConfig+"失败！", e);
	            }            
	            try{
	                cr = new ClassPathResource(dbConfig);
	                load(cr.getInputStream(), PROPERTIES);
	                LOG.info("装入数据库配置文件："+dbConfig);
	                LOG.info("Database profile is loaded："+dbConfig);
	            }catch(Exception e){
	                LOG.info("装入数据库配置文件"+dbConfig+"失败！", e);
	            }      
	            try{  
	                cr = new ClassPathResource(localDBConfig);
	                load(cr.getInputStream(), PROPERTIES);
	                LOG.info("装入自定义数据库配置文件："+localDBConfig);
	            }catch(Exception e){
	                LOG.info("装入自定义数据库配置文件"+localDBConfig+"失败！",e);
	            }      
	            
	            String extendPropertyFiles = PROPERTIES.get("extend.property.files");
	            if(extendPropertyFiles!=null && !"".equals(extendPropertyFiles.trim())){
	                String[] files=extendPropertyFiles.trim().split(",");
	                for(String file : files){
	                    try{  
	                        cr = new ClassPathResource(file);
	                        load(cr.getInputStream(), PROPERTIES);
	                        LOG.info("装入扩展配置文件："+file);
	                    }catch(Exception e){
	                        LOG.info("装入扩展配置文件"+file+"失败！",e);
	                    }      
	                }
	            }    
	            LOG.info("系统配置属性装载完毕");
	            LOG.info("******************属性列表***************************");
	            for(Map.Entry<String, String> entry:PROPERTIES.entrySet()){   
	            	 LOG.info("  " + entry.getKey() + " = " + entry.getValue());
	            }  
	            LOG.info("***********************************************************");
	            //指定日志输出语言
	            LOG.setLocale(getLogLanguage());
	    }
	    /**
	     * 日志使用什么语言输出
	     * @return 
	     */
	    public static Locale getLogLanguage(){
	       String language = getProperty("log.locale.language");
	       return Locale.forLanguageTag(language);
	    }

	    public static boolean getBooleanProperty(String name) {
	        String value = PROPERTIES.get(name);

	        return "true".equals(value);
	    }

	    public static int getIntProperty(String name) {
	        String value = PROPERTIES.get(name);

	        return Integer.parseInt(value);
	    }

	    public static String getProperty(String name) {
	        String value = PROPERTIES.get(name);

	        return value;
	    }

	    public static void setProperty(String name, String value) {
	        PROPERTIES.put(name, value);
	    }
}
