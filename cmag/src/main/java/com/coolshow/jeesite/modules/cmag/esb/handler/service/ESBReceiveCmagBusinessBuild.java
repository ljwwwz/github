package com.coolshow.jeesite.modules.cmag.esb.handler.service;

import org.apache.commons.lang.StringUtils;

import com.coolshow.frm.jeesite.common.SpringApplicationContext;
import com.coolshow.frm.jeesite.esb.handler.service.ESBReceiveBusinessBuild;
import com.coolshow.frm.jeesite.esb.handler.service.IESBReceiveBusiness;

/**
 * <p>
 * <b>Title:&nbsp</b>
 * </p>
 * <p>
 * <b>Description:&nbsp</b>合同管理-接收ESB推送消消息业务处理接口构建-扩展类<br>
 * <p>
 * <b>Copyright(c)&nbsp</b> by 2016
 * </p>
 * 
 * @author wsy
 * @date 2016-2-18
 * @time am 10:07:09
 * @version 1.0
 */
public class ESBReceiveCmagBusinessBuild extends ESBReceiveBusinessBuild {

	@Override
	public IESBReceiveBusiness getInstance(String resourceCode) {
		IESBReceiveBusiness ins = super.getInstance(resourceCode);
		if (ins == null) {
			if (StringUtils.equals(resourceCode, "dataSyn-psh-004")) {
				return ins = SpringApplicationContext.getBean("receiveBasicCmagArchives");
			} else if (StringUtils.equals(resourceCode, "fctl-psh-001")) {
				return ins = SpringApplicationContext.getBean("receiveFFlowStatus");
			}
		}
		return ins;

	}
}
