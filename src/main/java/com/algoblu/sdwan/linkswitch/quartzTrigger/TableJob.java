package com.algoblu.sdwan.linkswitch.quartzTrigger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.algoblu.sdwan.linkswitch.basic.Constants;
import com.algoblu.sdwan.linkswitch.basic.po.Link;
import com.algoblu.sdwan.linkswitch.basic.po.Policy;
import com.algoblu.sdwan.linkswitch.basic.springcontext.SpringContextHolder;
import com.algoblu.sdwan.linkswitch.basic.utils.HttpAuthTool;
import com.algoblu.sdwan.linkswitch.basic.utils.XmlUtils;
import com.algoblu.sdwan.linkswitch.business.model_policy.vo.PolicyVo;
import com.algoblu.sdwan.linkswitch.common.dao.LinkDao;
import com.algoblu.sdwan.linkswitch.common.dao.PolicyDao;

/**
 * @author 作者：王阳 E-mail:282404989@qq.com
 * @version 创建时间：5 Jan 2017 15:24:27 类说明
 */
@Service
public class TableJob {

	private static Logger logger = Logger.getLogger(TableJob.class);
	
	@Resource
	private PolicyDao policyDao;
	@Resource
	private LinkDao linkDao;

	public void execute() {
		logger.info("********************定时执行***********************");
		updateCurrentPolicy();
	}

	public void updateCurrentPolicy() {
		Map<String, String> systemConfigMap = SpringContextHolder.getBean("systemConfigMap");
		Map<String, String> urlContentMap = null;
		List<Policy> policies = null;

		policies = policyDao.findByIdName("findPolicy", null);
		for (Policy policy : policies) {
			PolicyVo policyVo = new PolicyVo();
			urlContentMap = new HashMap<String, String>();
			copyProperties(policyVo, policy);
			urlContentMap.put(Constants.APPLY_POLICY_NODEIP, policyVo.getNodeIp());
			String URL = contentTemplate(urlContentMap, systemConfigMap.get(Constants.GET_ALGOBLU_POLICY_URL_TEMPLATE));
			String jsonContent = "{input:{\"brName\":\"" + policyVo.getBridgeCode() + "\"}}";
			String xmlstr = HttpAuthTool.postMethod(URL, jsonContent);
			if (XmlUtils.getXMLElementByRootKey(xmlstr, "output") == "failed") {
				logger.info("输出错误！");
			}else{
				Link link = new Link();
				link.setId(policyVo.getLinkId());
				if (XmlUtils.getXMLElementByRootKey(xmlstr, "output").equals(policyVo.getCurrentPort())) {
					link.setIsActive(true);
				}else {
					link.setIsActive(false);
				}				
				linkDao.update(link);
			}
		}

	}

	public String contentTemplate(Map<String, String> parametersMap, String strContent) {
		for (Map.Entry<String, String> entry : parametersMap.entrySet()) {
			if (strContent.indexOf(entry.getKey()) > 0) {
				String value = entry.getValue();
				if ("".equals(value) || null == value) {
					value = " ";
				}
				strContent = strContent.replace("^&" + entry.getKey() + "^&", value);
			}
		}
		return strContent;
	}

	/**
	 * 拷贝
	 * 
	 * @param dest
	 * @param orig
	 */
	public void copyProperties(Object dest, Object orig) {
		if (null == orig) {
			return;
		}
		try {
			PropertyUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
