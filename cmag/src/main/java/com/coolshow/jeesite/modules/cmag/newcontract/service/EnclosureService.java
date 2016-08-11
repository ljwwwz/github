package com.coolshow.jeesite.modules.cmag.newcontract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.frm.jeesite.common.utils.SpringContextHolder;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IEnclosureDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Enclosure;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Scanning;

/**
 * @Description 附件信息业务操作类
 * ClassName: EnclosureService.java
 * @author LJW
 * @date 下午8:20:56
 */
@Service
@Transactional(readOnly = true)
public class EnclosureService extends CrudService<IEnclosureDao, Enclosure>{

	@Autowired
	private IEnclosureDao iEnclosureDao;
	
	/**
	 * 保存
	 */
	@Transactional(readOnly = false)
	public void save(Enclosure enclosure){
		iEnclosureDao.insert(enclosure);
	}
	
	/**
	  * @Description: 查询单条附件信息
	  * @param  @param enclosure
	  * @param  @return 
	  * @return Enclosure  
	  * @throws
	  * @author LJW
	  * @date 2016年5月17日
	 */
	@Transactional(readOnly = true)
	public Enclosure getEnclosure(Enclosure enclosure){
		return iEnclosureDao.get(enclosure);
	}
	
	/**
	  * @Description: 批量插入附件信息
	  * @param  @param enclosures 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年5月19日   
	 */
	@Transactional(readOnly = false)
	public void saveScannings(List<Enclosure> enclosures){
		iEnclosureDao.insertList(enclosures);
	}
	
	/**
	  * @Description: 根据附件id和附件状态查询记录总数
	  * @param  @param enclosure
	  * @param  @return 
	  * @return Long  
	  * @throws
	  * @author LJW
	  * @date 2016年5月20日
	 */
	@Transactional(readOnly = true)
	public Long findEnclosureCount(Enclosure enclosure){
		return iEnclosureDao.findEnclosureCount(enclosure);
	}
	
	/**
	  * @Description: 根据附件id和flag标识 01：附件  02 ：扫描件 更新附件信息
	  * @param  @param enclosure 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年6月2日
	 */
	@Transactional(readOnly = false)
	public void updateEnclosure(Enclosure enclosure){
		enclosure.preUpdate();
		enclosure.setCreate_user(enclosure.getUpdateBy().getName());
		enclosure.setUpload_time(enclosure.getUpdateDate());
		iEnclosureDao.update(enclosure);
	}
}
