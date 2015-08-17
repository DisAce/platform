/**   
* @Project: Platform 
* @Title: Price.java 
* @Package com.coiol.platform.common.jackjson.test 
* @Description: TODO 
* @author Yin MingXing master@coiol.com 
* @date 2014年11月26日 下午3:26:58 
* @Copyright: 2014  
* @version V1.0 
 */
package org.coiol.platform.common.jackjson.test;

import java.math.BigDecimal;

/** 
 * @ClassName Price  
 * @Description TODO 
 * @author Yin MingXing master@coiol.com 
 * @date 2014年11月26日 
 * 
 */
public class Price {
	 public static double add(double d1, double d2)
	 {        // 进行加法运算
	          BigDecimal b1 = new BigDecimal(d1);
	          BigDecimal b2 = new BigDecimal(d2);
	         return b1.add(b2).doubleValue();
	      }
	     public static double sub(double d1, double d2)
	 {        // 进行减法运算
	          BigDecimal b1 = new BigDecimal(d1);
	          BigDecimal b2 = new BigDecimal(d2);
	         return b1.subtract(b2).doubleValue();
	      }
	     public static double mul(double d1, double d2)
	 {        // 进行乘法运算
	          BigDecimal b1 = new BigDecimal(d1);
	          BigDecimal b2 = new BigDecimal(d2);
	         return b1.multiply(b2).doubleValue();
	      }
	     public static double div(double d1,
	 double d2,int len) {// 进行除法运算
	          BigDecimal b1 = new BigDecimal(d1);
	          BigDecimal b2 = new BigDecimal(d2);
	         return b1.divide(b2,len,BigDecimal.
	 ROUND_HALF_UP).doubleValue();
	      }
	     public static double round(double d,
	 int len) {     // 进行四舍五入
	 //操作
	          BigDecimal b1 = new BigDecimal(d);
	          BigDecimal b2 = new BigDecimal(1);
	         // 任何一个数字除以1都是原数字
	         // ROUND_HALF_UP是BigDecimal的一个常量，
	 //表示进行四舍五入的操作
	         return b1.divide(b2, len,BigDecimal.
	 ROUND_HALF_UP).doubleValue();
	      }
	
	public static void main(String args[]) throws Exception
		{
		System.out.println("除法运算：" + Price.div(4564.54, 435, 3));
		BigDecimal b = new BigDecimal("4564.54");
		System.out.println(b);
		}
}
