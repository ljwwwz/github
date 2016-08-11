package com.coolshow.jeesite.modules.cmag.ds.dsync;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolshow.dsyn.common.utils.JsonMapper;
import com.coolshow.dsyn.dc.format.CRMCmObjectDcFromat;
import com.coolshow.dsyn.dc.model.CRMCmObjectDc;
import com.coolshow.esb.exception.ESBReceiveException;
import com.coolshow.esb.web.transport.entity.resp.client.asynpush.ResSubscribeRecMsg;
import com.coolshow.frm.jeesite.esb.handler.service.IESBReceiveBusiness;
import com.coolshow.jeesite.modules.cmag.ds.dsync.service.DistributorExtendService;
import com.coolshow.jeesite.modules.cmag.ds.dsync.service.SupplierExtendService;

/**
 * <p>
 * <b>Title:&nbsp</b>
 * </p>
 * <p>
 * <b>Description:&nbsp</b>客商基础档案接收业务处理<br>
 * <p>
 * <b>Copyright(c)&nbsp</b> by 2016
 * </p>
 * 
 * @author ay_lin
 * @date 2016-6-7
 * @version 1.0
 */
@Service(value = "receiveBasicCmagArchives")
public class ReceiveBasicCmagArchives implements IESBReceiveBusiness {

	@Autowired
	DistributorExtendService dservice;

	@Autowired
	SupplierExtendService sservice;

	private static Logger logger = Logger.getLogger(ReceiveBasicCmagArchives.class);

	List<CRMCmObjectDc> distributorInsertList = new ArrayList<CRMCmObjectDc>();
	List<CRMCmObjectDc> distributorUpdateList = new ArrayList<CRMCmObjectDc>();
	List<CRMCmObjectDc> distributorDeleteList = new ArrayList<CRMCmObjectDc>();
	List<CRMCmObjectDc> distributorErrList = new ArrayList<CRMCmObjectDc>();

	List<CRMCmObjectDc> supplierInsertList = new ArrayList<CRMCmObjectDc>();
	List<CRMCmObjectDc> supplierUpdateList = new ArrayList<CRMCmObjectDc>();
	List<CRMCmObjectDc> supplierDeleteList = new ArrayList<CRMCmObjectDc>();
	List<CRMCmObjectDc> supplierErrList = new ArrayList<CRMCmObjectDc>();

	public void setDservice(DistributorExtendService uservice) {
		this.dservice = uservice;
	}

	public void setSservice(SupplierExtendService sservice) {
		this.sservice = sservice;
	}

	@Override
	public void subscribeRecMsgDeal(ResSubscribeRecMsg resSubscribeRecMsg) throws ESBReceiveException {

		JSONObject resultObj = new JSONObject();
		String jsonPushMsg = resSubscribeRecMsg.getJsonPushMsg();

		List<CRMCmObjectDc> distributorList = CRMCmObjectDcFromat.formatList(jsonPushMsg);

		for (CRMCmObjectDc cmagDc : distributorList) {
			int ksType = cmagDc.getKsType();
			if (ksType == 2) { // 分销商
				parseDistributorJson(cmagDc, resultObj);
			} else if (ksType == 1) { // 供应商
				parseSupplierJson(cmagDc, resultObj);
			} else if (ksType == 3) { // ksType = 3 既是分销商也是供应商
				parseDistributorJson(cmagDc, resultObj);
				parseSupplierJson(cmagDc, resultObj);
			}
		}

		resultObj.put("distributorInsertSize", distributorInsertList.size());
		resultObj.put("distributorUpdateSize", distributorUpdateList.size());
		resultObj.put("distributorDeleteSize", distributorDeleteList.size());
		resultObj.put("distributorErrSize", distributorErrList.size());
		if (distributorInsertList.size() > 0) {
			resultObj.put("distributorInsertList", new JsonMapper().toJson(distributorInsertList));
		}
		if (distributorUpdateList.size() > 0) {
			resultObj.put("distributorUpdateList", new JsonMapper().toJson(distributorUpdateList));
		}
		if (distributorDeleteList.size() > 0) {
			resultObj.put("distributorDeleteList", new JsonMapper().toJson(distributorDeleteList));
		}
		if (distributorErrList.size() > 0) {
			resultObj.put("distributorErrList", new JsonMapper().toJson(distributorErrList));
		}
		logger.info("[lightESB] Distributor syn result is " + resultObj.toString());

		resultObj.put("supplierInsertSize", supplierInsertList.size());
		resultObj.put("supplierUpdateSize", supplierUpdateList.size());
		resultObj.put("supplierDeleteSize", supplierDeleteList.size());
		resultObj.put("supplierErrSize", supplierErrList.size());
		if (supplierInsertList.size() > 0) {
			resultObj.put("orgInsertList", new JsonMapper().toJson(supplierInsertList));
		}
		if (supplierUpdateList.size() > 0) {
			resultObj.put("cmagUpdateList", new JsonMapper().toJson(supplierUpdateList));
		}
		if (supplierDeleteList.size() > 0) {
			resultObj.put("cmagDeleteList", new JsonMapper().toJson(supplierDeleteList));
		}
		if (supplierErrList.size() > 0) {
			resultObj.put("cmagErrList", new JsonMapper().toJson(supplierErrList));
		}
		logger.info("[lightESB] Supplier syn result is " + resultObj.toString());

	}

	/**
	 * 分销商数据同步
	 * 
	 * @param jsonPushMsg
	 * @param resultObj
	 * @throws ESBReceiveException
	 */
	public void parseDistributorJson(CRMCmObjectDc cmagDc, JSONObject resultObj) throws ESBReceiveException {

		try {
			boolean flag = dservice.exitCmagDc(cmagDc.getKsCode());
			if (flag) {
				if (cmagDc.getIsEnable() == 0) {
					// 删除
					dservice.deleteCmagDc(cmagDc);
					distributorDeleteList.add(cmagDc);
				} else if (cmagDc.getUpdateTime() != null) {
					// 更新
					dservice.updateCmagDc(cmagDc);
					distributorUpdateList.add(cmagDc);
				}
			} else {// 新增
				if (cmagDc.getIsEnable() != 0) {
					dservice.insertCmagDc(cmagDc);
					distributorInsertList.add(cmagDc);
				}
			}
		} catch (Exception e) {
			distributorErrList.add(cmagDc);
			logger.error(e);
		}

	}

	/**
	 * 供应商数据同步
	 * 
	 * @param jsonPushMsg
	 * @param resultObj
	 * @throws ESBReceiveException
	 */
	public void parseSupplierJson(CRMCmObjectDc cmagDc, JSONObject resultObj) throws ESBReceiveException {

		try {
			boolean flag = sservice.exitCmagDc(cmagDc.getKsCode());
			if (flag) {
				if (cmagDc.getIsEnable() == 0) {
					// 删除
					sservice.deleteCmagDc(cmagDc);
					supplierDeleteList.add(cmagDc);
				} else if (cmagDc.getUpdateTime() != null) {
					// 更新
					sservice.updateCmagDc(cmagDc);
					supplierUpdateList.add(cmagDc);
				}
			} else {// 新增
				if (cmagDc.getIsEnable() != 0) {
					sservice.insertCmagDc(cmagDc);
					supplierInsertList.add(cmagDc);
				}
			}

		} catch (Exception e) {
			supplierErrList.add(cmagDc);
			logger.error(e);
		}

	}

}
