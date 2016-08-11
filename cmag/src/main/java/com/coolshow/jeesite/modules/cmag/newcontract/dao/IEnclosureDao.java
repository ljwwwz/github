package com.coolshow.jeesite.modules.cmag.newcontract.dao;

import java.util.List;

import com.coolshow.frm.jeesite.common.persistence.CrudDao;
import com.coolshow.frm.jeesite.common.persistence.annotation.MyBatisDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Enclosure;

/**
 * @Description 附件信息数据库操作类
 * ClassName: IEnclosureDao
 * @author LJW
 * @date 2016年5月10日
 */
@MyBatisDao
public interface IEnclosureDao extends CrudDao<Enclosure>{

	/**
	  * @Description: 批量插入
	  * @param  @param enclosures
	  * @param  @return 
	  * @return int  
	  * @throws
	  * @author LJW
	  * @date 2016年5月19日
	 */
	public void insertList(List<Enclosure> enclosures);
	
	/**
	  * @Description:  根据附件id和附件状态查询记录总数
	  * @param  @param enclosure
	  * @param  @return 
	  * @return Long  
	  * @throws
	  * @author LJW
	  * @date 2016年5月20日
	 */
	public Long findEnclosureCount(Enclosure enclosure);
}
