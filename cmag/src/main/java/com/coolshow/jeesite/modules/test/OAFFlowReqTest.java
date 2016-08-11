package com.coolshow.jeesite.modules.test;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coolshow.fctl.client.metadata.entity.TsfStatusClientParameter;
import com.coolshow.fctl.exception.BusinessException;
import com.coolshow.jeesite.modules.cmag.esb.client.ContractReqFFlowClient;

/**
 * <p>
 * <b>Title:&nbsp</b>OA流程对接测试
 * </p>
 * <p>
 * <b>Description:&nbsp</b>
 * <p>
 * <b>Copyright(c)&nbsp</b> by 2016
 * </p>
 * 
 * @author wsy
 * @date 2016-07-19
 * @time am 10:07:09
 * @version 1.0
 */
public class OAFFlowReqTest {
	/**
	 * 日志对象
	 */
	protected static Logger logger = LoggerFactory.getLogger(OAFFlowReqTest.class);

	/**
	 * 分销商合同签订对接OA测试
	 */
	public static void genDistributorFormFlowTest() {
		try {
			String[] attachFiles = new String[] { "F:\\coolshow-incenter\\酷秀JAVA编码规范.doc", "F:\\coolshow-incenter\\酷秀IT系统功能与开发能力汇总清单.xlsx" };

			JSONObject data = new JSONObject();
			data.put("staffCode", "1000000254");
			data.put("startDate", "2016-07-12");
			data.put("distributorName", "三亚之星旅行社有限公司");
			data.put("protocolType", -3411280978416088296L);// -3411280978416088296
			data.put("effectStartDate", "2016-06-26");
			data.put("effectEndDate", "2017-06-25");
			data.put("settlement", "按单结算");
			data.put("yearSaleAmount", "300000");
			data.put("deposit", "20000（续签，押金已缴纳）");
			data.put("advances", new Double(0d));
			data.put("contractSubject", "酷秀电子商务有限公司");
			TsfStatusClientParameter respClient = ContractReqFFlowClient.genDistributorFormFlow(attachFiles, data);
			logger.info(respClient.toString());
		} catch (BusinessException e) {
			logger.error("genDistributorFormFlow eror:" + e);
		}

	}

	/**
	 * 景区(供应商)合同签订对接OA测试
	 */
	public static void genSupplierFormFlowTest() {
		try {
			String[] attachFiles = new String[] { "F:\\coolshow-incenter\\酷秀JAVA编码规范.doc", "F:\\coolshow-incenter\\酷秀IT系统功能与开发能力汇总清单.xlsx" };
			JSONObject data = new JSONObject();
			data.put("staffCode", "1000000254");
			data.put("startDate", "2016-07-12");
			data.put("supplierName", "城头山国家考古遗址");
			data.put("effectStartDate", "2016-07-30");
			data.put("effectEndDate", "2017-07-30");
			data.put("cooperation", "景区直签");
			data.put("settlement", "月结");
			data.put("payment", "银行转账");
			
			data.put("advances", new Double(0d));
			data.put("deposit", new Double(0d));
			data.put("contractSubject", "酷秀电子商务有限公司");
			data.put("remark", "合同无改动，我们先盖章门市价75 网售价65 含税结算价56.65 景区直签  使用对方系统月结");
			TsfStatusClientParameter respClient = ContractReqFFlowClient.genSupplierFormFlow(attachFiles, data);
			logger.info(respClient.toString());
		} catch (BusinessException e) {
			logger.error("genDistributorFormFlow eror:" + e);
		}
	}

	/**
	 * 流程状态查询-测试
	 */
	public static void pullFlowStatusTest() {
		try {
			Long tsfSeq = 88L;
			TsfStatusClientParameter respClient = ContractReqFFlowClient.pullFlowStatus(tsfSeq);
			logger.info(respClient.toString());
		} catch (BusinessException e) {
			logger.error("pullFlowStatus eror:" + e);
		}
	}

	public static void main(String[] args) throws Exception {
		//genDistributorFormFlowTest();
		genSupplierFormFlowTest();
		//pullFlowStatusTest();
	}
}
