package com.algoblu.sdwan.linkswitch.filter;

import com.algoblu.sdwan.linkswitch.basic.Constants;
import com.algoblu.sdwan.linkswitch.basic.po.Admin;
import com.algoblu.sdwan.linkswitch.basic.po.Function;
import com.algoblu.sdwan.linkswitch.basic.utils.DataUtil;
import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_admin.service.AdminService;
import com.algoblu.sdwan.linkswitch.business.model_admin.vo.AdminVo;
import com.algoblu.sdwan.linkswitch.business.model_function.service.FunctionService;
import com.algoblu.sdwan.linkswitch.business.model_function.vo.FunctionVo;
import com.algoblu.sdwan.linkswitch.common.dao.SystemConfigDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 业务部分登陆拦截器
 * 
 */
public class Loginer {

	private static Logger logger = Logger.getLogger(Loginer.class);

	@Autowired
	private AdminService adminService;

	@Autowired
	private FunctionService functionService;

	@Autowired
	private SystemConfigDao systemConfigDao;

	private Map<String, Object> resultMsg;
	private AdminVo vo;

	private List<AdminVo> resultList;
	private List<MenuVo> menuList;
	private ErrorVo error;

	private String sessionKey;
	private String sessionValue;

	/**
	 * 系统登录入口
	 * 
	 * @return
	 */
	public String login() {
		if (null == vo) {
			vo = new AdminVo();
		}
		vo.setStatus(Constants.RESOURCE_STATUS_VALID);
		List<AdminVo> adminVoList;
		if (vo.getUsername().indexOf("@") != -1) {
			vo.setAdminEmail(vo.getUsername());
			vo.setUsername(null);
			adminVoList = adminService.searchList(vo, false);
		}else {
			logger.info(vo);
			adminVoList = adminService.searchList(vo, false);
		}		
		
		resultMsg = new HashMap<String, Object>();
		if (adminVoList.size() > 0) {
			HttpSession session = ServletActionContext.getRequest().getSession();

			// 获取用户信息
			for (Admin admin : adminVoList) {
				vo = adminService.searchById(admin.getId());
			}

			session.setAttribute(Constants.USER_INFO, adminVoList.get(0));
			resultMsg.put("success", true);// 设置登录成功标识
			logger.info("用户：[" + adminVoList.get(0).getRealName() + "]成功登录。");
			
			vo.setLastLoginTime(DataUtil.getCurrentTime());
			adminService.update(vo);
			
			// 获取用户菜单信息
			FunctionVo functionVo = new FunctionVo();
			functionVo.setFunctionLevel(0);

			// 去除权限编码前后空格
			String moderatorRole = adminVoList.get(0).getFunctionPrivilege();
			functionVo.setRole(moderatorRole);
			List<Function> functionList_L1 = functionService.searchFunctionList(functionVo);
			// 遍历根菜单
			menuList = new ArrayList<MenuVo>();
			for (Function function_L1 : functionList_L1) {
				functionVo = new FunctionVo();
				MenuVo am_L1 = new MenuVo();
				am_L1.setName(function_L1.getFunctionName());
				am_L1.setType(function_L1.getFunctionType());
				am_L1.setUrl(function_L1.getFunctionUrl());

				// 遍历二级子菜单
				List<MenuVo> nextLevelMenu = new ArrayList<MenuVo>();
				functionVo.setFunctionLevel(1);
				functionVo.setParentFunctionId(function_L1.getId());
				functionVo.setRole(moderatorRole);
				List<Function> functionList_L2 = functionService.searchFunctionList(functionVo);
				for (Function function_L2 : functionList_L2) {
					MenuVo am_L2 = new MenuVo();
					am_L2.setName(function_L2.getFunctionName());
					am_L2.setUrl(function_L2.getFunctionUrl());

					// 遍历三级子菜单
					List<MenuVo> nextLevel3Menu = new ArrayList<MenuVo>();
					functionVo.setFunctionLevel(2);
					functionVo.setParentFunctionId(function_L2.getId());
					functionVo.setRole(moderatorRole);
					List<Function> functionList_L3 = functionService.searchFunctionList(functionVo);
					for (Function function_L3 : functionList_L3) {
						MenuVo am_L3 = new MenuVo();
						am_L3.setName(function_L3.getFunctionName());
						am_L3.setUrl(function_L3.getFunctionUrl());
						nextLevel3Menu.add(am_L3);
					}
					am_L2.setNextLevelMenu(nextLevel3Menu);

					nextLevelMenu.add(am_L2);
				}
				am_L1.setNextLevelMenu(nextLevelMenu);

				menuList.add(am_L1);
			}
			session.setAttribute(Constants.MENU_LIST, menuList);
		} else {
			resultMsg.put("success", false);// 设置登录失败标识
			resultMsg.put("msg", "用户名或密码错误");// 设置登录失败标识
			logger.info("尝试登录失败：[" + vo.getUsername() + ":" + vo.getPassword() + "]");
		}
		return "success";
	}

	/**
	 * 系统修改 SESSION
	 * 
	 * @return
	 */
	public String setSession() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (StringUtils.isNotBlank(sessionKey) && StringUtils.isNotBlank(sessionValue)) {
			session.setAttribute(sessionKey, sessionValue);

			resultMsg = new HashMap<String, Object>();
			resultMsg.put("success", true);
			logger.info("SESSION 更新：[" + sessionKey + ":" + sessionValue + "]");
		}

		return "success";
	}
	
	/**
	 * 系统注销出口
	 * 
	 * @return
	 */
	public String logout() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute(Constants.USER_INFO);
		logger.info("用户：[" + admin.getRealName() + "]成功注销。");
		session.removeAttribute(Constants.USER_INFO);
		session.removeAttribute(Constants.MENU_LIST);
		resultMsg = new HashMap<String, Object>();
		resultMsg.put("success", true);
		return "success";
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	public SystemConfigDao getSystemConfigDao() {
		return systemConfigDao;
	}

	public void setSystemConfigDao(SystemConfigDao systemConfigDao) {
		this.systemConfigDao = systemConfigDao;
	}

	public Map<String, Object> getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(Map<String, Object> resultMsg) {
		this.resultMsg = resultMsg;
	}

	public AdminVo getVo() {
		return vo;
	}

	public void setVo(AdminVo vo) {
		this.vo = vo;
	}

	public List<AdminVo> getResultList() {
		return resultList;
	}

	public void setResultList(List<AdminVo> resultList) {
		this.resultList = resultList;
	}

	public List<MenuVo> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuVo> menuList) {
		this.menuList = menuList;
	}

	public ErrorVo getError() {
		return error;
	}

	public void setError(ErrorVo error) {
		this.error = error;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getSessionValue() {
		return sessionValue;
	}

	public void setSessionValue(String sessionValue) {
		this.sessionValue = sessionValue;
	}
}
