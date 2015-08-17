
/**
 * 
 * APDPlat - Application Product Development Platform
 * Copyright (c) 2015, master@coiol.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.coiol.platform.security.service.sequence;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;


/**
 *此工具负责根据用户的机器码来生成注册码
 * @author master@coiol.com
 */
public class AuthorizationUtils {
    private static final int SPLITLENGTH=4;
    public static void main(String args[]) throws Exception {
        String code="B6F2-8C46-3D2F-7137-22D1-3995-307C-9D07";
        String authCode=auth(code);
        System.out.println("机器码："+code);
        System.out.println("注册码："+authCode);
    }
    public static String auth(String machineCode){
        String newCode="(eingxin@163.com)["+machineCode.toUpperCase()+"](PlatForm master for master@coiol.com)";
        String code = new Md5PasswordEncoder().encodePassword(newCode,"master@coiol.com").toUpperCase()+machineCode.length();
        return getSplitString(code);
    }
    private static String getSplitString(String str){ 
        return getSplitString(str, "-", SPLITLENGTH);
    }
    private static String getSplitString(String str, String split, int length){        
        int len=str.length();
        StringBuilder temp=new StringBuilder();
        for(int i=0;i<len;i++){
            if(i%length==0 && i>0){
                temp.append(split);
            }
            temp.append(str.charAt(i));
        }
        String[] attrs=temp.toString().split(split);
        StringBuilder finalMachineCode=new StringBuilder();
        for(String attr : attrs){
            if(attr.length()==length){
                finalMachineCode.append(attr).append(split);
            }
        }
        String result=finalMachineCode.toString().substring(0, finalMachineCode.toString().length()-1);
        return result;
    }
}