package com.coolshow.jeesite.modules.cmag.esb.client;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coolshow.fctl.client.api.ClientInvokeOpenAPI;
import com.coolshow.fctl.client.metadata.entity.ClientReqGenFlowParameter;
import com.coolshow.fctl.client.metadata.entity.PullFlowStatusReqClientParameter;
import com.coolshow.fctl.client.metadata.entity.TsfStatusClientParameter;
import com.coolshow.fctl.exception.BusinessException;
import com.coolshow.fctl.metadata.entity.GenFlowData;

/**
 * <p>
 * <b>Title:&nbsp</b>合同管理基于ESB向流程中控发起(合同签订)OA审批流程对接
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
public class ContractReqFFlowClient {
	/**
	 * 日志对象
	 */
	protected static Logger logger = LoggerFactory.getLogger(ContractReqFFlowClient.class);

	/**
	 * 发起(合同)OA审批流程对接
	 * 
	 * @param flowCode
	 *            流程编码code(指定合同类型在流程中控的编码)
	 * @param attachFiles
	 *            待上传的附件文件数组，包含附件文件的全路径名及扩展名<br>
	 * @param data
	 *            流程接入参数json对象（参照OA流程发布手册-接入参数，除附件id外的）
	 * @return TsfStatusClientParameter
	 * @throws BusinessException
	 *             当发起失败
	 */
	public static TsfStatusClientParameter genFormFlow(String flowCode, String[] attachFiles, JSONObject data) throws BusinessException {

		Long[] attachIds =new Long[]{74L,75L};// ClientInvokeOpenAPI.uploadFlowDataAttachs(attachFiles);
		ClientReqGenFlowParameter transParameter = new ClientReqGenFlowParameter();
		transParameter.setSystemCode("cmag");// 合同管理发起系统code
		transParameter.setFlowCode(flowCode);
		GenFlowData flowData = new GenFlowData(attachIds, data);
		transParameter.setFlowData(flowData);
		TsfStatusClientParameter respClient = ClientInvokeOpenAPI.executeGenFlow(transParameter);
		logger.info(respClient.toString());
		return respClient;

	}

	/**
	 * 发起(分销商合同签订)OA审批流程对接
	 * 
	 * @param attachFiles
	 *            待上传的附件文件数组，包含附件文件的全路径名及扩展名<br>
	 * @param data
	 *            流程接入参数json对象（参照OA流程发布手册-接入参数，除附件id外的）
	 * @throws BusinessException
	 *             当发起失败
	 */
	public static TsfStatusClientParameter genDistributorFormFlow(String[] attachFiles, JSONObject data) throws BusinessException {
		return genFormFlow("OA_FFLOW_T00001", attachFiles, data);

	}

	/**
	 * 发起(景区(供应商)合同签订)OA审批流程对接
	 * 
	 * @param attachFiles
	 *            待上传的附件文件数组，包含附件文件的全路径名及扩展名<br>
	 * @param data
	 *            流程接入参数json对象（参照OA流程发布手册-接入参数，除附件id外的）
	 * @throws BusinessException
	 *             当发起失败
	 */
	public static TsfStatusClientParameter genSupplierFormFlow(String[] attachFiles, JSONObject data) throws BusinessException {
		return genFormFlow("OA_FFLOW_T00002", attachFiles, data);
	}

	/**
	 * 流程状态查询
	 * 
	 * @param tsfSeq
	 *            流程流转序列ID（合同管理保存的流程中控返回的流程序列）
	 * @return TsfStatusClientParameter
	 * @throws BusinessException
	 *             流程状态查询失败
	 * 
	 */
	public static TsfStatusClientParameter pullFlowStatus(Long tsfSeq) throws BusinessException {
		PullFlowStatusReqClientParameter transParameter = new PullFlowStatusReqClientParameter();
		transParameter.setSystemCode("cmag");// 合同管理发起系统code
		transParameter.setTsfSeq(tsfSeq);
		TsfStatusClientParameter respClient = ClientInvokeOpenAPI.pullFlowStatus(transParameter);
		logger.info(respClient.toString());
		return respClient;

	}
}
