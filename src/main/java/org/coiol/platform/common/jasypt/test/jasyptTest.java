package org.coiol.platform.common.jasypt.test;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

/**
 * 配置文件解密测试
 * @author ACTION
 *
 */
public class jasyptTest {

    private static final StandardPBEStringEncryptor ENCRYPTOR = new StandardPBEStringEncryptor();
    static{
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        //自己在用的时候更改此密码
        config.setPassword("admin");        
        
        ENCRYPTOR.setConfig(config);
    }
    public static void main(String[] args){
        String plaintext="admin";
        String ciphertext=ENCRYPTOR.encrypt(plaintext);
        System.out.println(plaintext+" : "+ciphertext);
        System.out.println(ciphertext+" : "+ENCRYPTOR.decrypt(ciphertext));
        
      
    }
}
