package com.coolshow.jeesite.modules.cmag.newcontract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolshow.frm.jeesite.common.persistence.Page;
import com.coolshow.frm.jeesite.common.service.CrudService;
import com.coolshow.jeesite.modules.cmag.newcontract.dao.IOtherDao;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.DistributorBase;
import com.coolshow.jeesite.modules.cmag.newcontract.entity.Other;

/**
 * @Description 其他合同业务操作类
 * ClassName: OtherService.java
 * @author WQH
 * @date 2016年6月3日
 */
@Service
@Transactional(readOnly= true)
public class OtherService extends CrudService<IOtherDao, Other> {

		@Autowired
		private IOtherDao iOtherDao;
		
		/**
		 * @Description 保存其他合同的基本信息
		 * @author WQH
		 * @date 2016年6月3日
		 */
		@Transactional(readOnly= false)
		public void saveOtherInfo(Other other) {
			other.setIsNewRecord(true);
			other.preInsert();
			iOtherDao.insert(other);
		}
		
		/**
		 * @Description 更新其他合同的基本信息
		 * @author WQH
		 * @date 2016年6月3日
		 */
		@Transactional(readOnly= false)
		public void updateOther(Other other) {
			other.preUpdate();
			iOtherDao.update(other);
		}
		
		/**
		  * @Description: 查询其他信息列表
		  * @param  @return 
		  * @return List<Other>  
		  * @throws
		  * @author LJW
		  * @date 2016年6月6日
		 */
		@Transactional(readOnly = true)
		public List<Other> findList(){
			 return iOtherDao.findList();
		}
		
		/**
		  * @Description: 分页查询其他信息
		  * @param  @param page
		  * @param  @param other
		  * @param  @return 
		  * @return Page<Other>  
		  * @throws
		  * @author LJW
		  * @date 2016年6月14日
		 */
		@Transactional(readOnly = true)
		public Page<Other> findPage(Page<Other> page, Other other) {
			other.setPage(page);
			page.setList(iOtherDao.findList(other));
			return page;
		}
		
		/**
		  * @Description: 查询单条记录
		  * @param  @param other
		  * @param  @return 
		  * @return Other  
		  * @throws
		  * @author LJW
		  * @date 2016年6月16日
		 */
		public Other finOther(Other other){
			return iOtherDao.get(other);
		}
}
