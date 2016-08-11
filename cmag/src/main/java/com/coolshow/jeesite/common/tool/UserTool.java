package com.coolshow.jeesite.common.tool;

import java.util.ArrayList;
import java.util.List;

import com.coolshow.frm.jeesite.modules.sys.entity.Menu;
import com.coolshow.frm.jeesite.modules.sys.utils.UserUtils;

/**
 * @Description 用户操作工具类
 * ClassName: UserTool.java
 * @author LJW
 * @date 下午1:23:20
 */
public final class UserTool {

	/**
	  * @Description: 获取该菜单下面系统开放的按钮操作
	  * @param  @param menuName  菜单名称
	  * @param  @return 
	  * @return List<Menu>  
	  * @throws
	  * @author LJW
	  * @date 2016年7月8日
	 */
	public static List<Menu> getUserOperBtn(String menuName){
		List<Menu> menus = UserUtils.getMenuList();
		List<Menu> results = new ArrayList<Menu>();
		String resultID = "";
		for (Menu menu:menus) {
			if (menuName.equals(menu.getName().trim())) {
				resultID = menu.getId();
				break;
			}
		}
		for (Menu menu:menus) {
			if (resultID.equals(menu.getParentId().trim()) && !"查看".equals(menu.getName().trim())) {
				results.add(menu);
			}
		}
		return results;
	}
	
	
	public static List<String> getUserOperBtnStr(String menuName){
		List<Menu> menus = UserUtils.getMenuList();
		List<String> results = new ArrayList<String>();
		String resultID = "";
		for (Menu menu:menus) {
			if (menuName.equals(menu.getName().trim())) {
				resultID = menu.getId();
				break;
			}
		}
		for (Menu menu:menus) {
			if (resultID.equals(menu.getParentId().trim()) && !"查看".equals(menu.getName().trim())) {
				results.add(menu.getName().trim());
			}
		}
		return results;
	}
}
