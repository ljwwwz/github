package com.coolshow.jeesite.common.tool;

import java.util.List;
import java.util.UUID;

import com.coolshow.frm.jeesite.modules.sys.entity.Menu;

/**
 * 字符串操作工具类
 */
public final class StringTool {

    /**
     *  根据 splitFlag 分割标识，对 str 进行分割
     * @param str
     * @param splitFlag
     * @return
     */
    public static String[] getSplitStr(String str,String splitFlag){
        String[] strs = str.split("\\"+splitFlag);
        return  strs;
    }
    
    public static String getUuidRemoveSeparator(){
    	return  UUID.randomUUID().toString().replace("-", "");
    }
    
    public static boolean isEmpty(String ...strings){
    	for(String s : strings){
    		if(s==null || "".equals(s)){
    			return false;
    		}
    	}
    	
    	return Boolean.TRUE;
    }
    
  /* public static void main(String[] args){
	   System.out.println(getUuidRemoveSeparator().length());
   }*/
}
