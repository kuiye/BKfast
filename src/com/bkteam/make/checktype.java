package com.bkteam.make;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class checktype{
	/** 
     * 手机号验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }  
    /** 
     * 邮箱验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isEmail(String email){  
        if(email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")){  
            return true;  
        }else{  
            return false;  
        }  
    }  
    
  
    public static void main(String[] args) {
    	
    	String s="938424168qq1.com";
    	boolean b=isEmail(s);
    	if(!b){
    		System.out.println("N");
    	}else{
    		System.out.println("Y");
    	}
    	
 	 }

}
