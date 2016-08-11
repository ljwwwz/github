package com.coolshow.jeesite.modules.cmag.esb.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.coolshow.esb.exception.ESBReceiveException;
import com.coolshow.esb.web.transport.entity.resp.client.asynpush.ResSubscribeRecMsg;
import com.coolshow.fctl.client.api.ClientInvokeOpenAPI;
import com.coolshow.fctl.client.metadata.entity.TsfStatusClientParameter;
import com.coolshow.fctl.exception.BusinessException;
import com.coolshow.frm.jeesite.esb.handler.service.IESBReceiveBusiness;

/**
 * <p>
 * <b>Title:&nbsp</b>
 * </p>
 * <p>
 * <b>Description:&nbsp</b>流程中控-流程状态-接收业务处理<br>
 * <p>
 * <b>Copyright(c)&nbsp</b> by 2016
 * </p>
 * 
 * @author ay_lin
 * @date 2016-7-19
 * @version 1.0
 */
@Service(value = "receiveFFlowStatus")
public class ReceiveFFlowStatus implements IESBReceiveBusiness {
	protected static Logger logger = LoggerFactory.getLogger(ReceiveFFlowStatus.class);

	@Override
	public void subscribeRecMsgDeal(ResSubscribeRecMsg resSubscribeRecMsg) throws ESBReceiveException {

		String jsonPushMsg = resSubscribeRecMsg.getJsonPushMsg();
		try {
			TsfStatusClientParameter tsfStatusClientParameter = ClientInvokeOpenAPI.decodePublishTsfStatusParameter(jsonPushMsg);
			// 进行后续业务处理
		} catch (BusinessException e) {
			logger.error("subscribeRecMsgDeal error:" + e.toString());
		}

	}

}
