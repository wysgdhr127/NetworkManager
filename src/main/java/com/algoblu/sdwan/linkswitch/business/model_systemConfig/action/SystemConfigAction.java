package  com.algoblu.sdwan.linkswitch.business.model_systemConfig.action;

import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_systemConfig.service.SystemConfigService;
import com.algoblu.sdwan.linkswitch.business.model_systemConfig.vo.SystemConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @description 
 * @author  wang yang
 */
 
@Controller("SystemConfigAction")
@Scope("prototype")
public class SystemConfigAction{

	//~ fields ===================================================================
	@Autowired
	private SystemConfigService systemConfigService;
	private List<SystemConfigVo> resultList;
	private SystemConfigVo vo;
	private SystemConfigVo avo;// ajax搜索条件
	private SystemConfigVo svo;// 搜索条件
	private ErrorVo error;
	
	//~ Getters && Setters =======================================================
	
	//~ Function =================================================================
	/**
	 * [SystemConfig]搜索列表页面 进入
	 * 
	 * @return
	 */
	public String ajaxSearch() {
		if (null == avo) {
			avo = new SystemConfigVo();
		}
		// 查询
		resultList = systemConfigService.searchList(avo, false);

		return "success";
	}

	/**
	 * [SystemConfig]搜索列表页面 进入初始化
	 * 
	 * @return
	 */
	public String inSearch() {
		if (null == svo) {
			svo = new SystemConfigVo();
		}

		return "success";
	}

	/**
	 * [SystemConfig]搜索列表页面 搜索
	 * 
	 * @return
	 */
	public String search() {
		if (null == svo) {
			svo = new SystemConfigVo();
		}
		// 查询
		resultList = systemConfigService.searchList(svo, true);

		return "success";

	}

	/**
	 * [SystemConfig]新增列表页面 进入
	 * 
	 * @return
	 */
	public String inAdd() {
		if (null == vo) {
			vo = new SystemConfigVo();
		}

		return "success";
	}

	/**
	 * [SystemConfig]新增列表页面 增加
	 * 
	 * @return
	 */
	public String add() {
		if (vo == null) {
			error = new ErrorVo();
			error.setSuccess(false);
			error.setErrorCode("object is empty!,save fail!");
			error.setErrorMessage("参数为空,保存失败!");
			return "success";
		}
		error = systemConfigService.add(vo);

		return "success";
	}

	/**
	 * [SystemConfig]更新列表页面 进入
	 * 
	 * @return
	 */
	public String inUpdate() {
		if (null == vo) {
			vo = new SystemConfigVo();
		}
		vo = systemConfigService.searchById(vo.getId());

		return "success";
	}

	/**
	 * [SystemConfig]基本参数更新 修改
	 * 
	 * @return
	 */
	public String update() {
		if (vo == null) {
			error = new ErrorVo();
			error.setSuccess(false);
			error.setErrorCode("object is empty!,update fail!");
			error.setErrorMessage("参数为空,更新失败!");
			return "success";
		}
		error = systemConfigService.update(vo);

		return "success";
	}

	/**
	 * [SystemConfig]基本参数删除 修改
	 * 
	 * @return
	 */
	public String delete() {

		if (vo == null) {
			error = new ErrorVo();
			error.setSuccess(false);
			error.setErrorCode("object is empty!,update fail!");
			error.setErrorMessage("参数为空,更新失败!");
			return "success";
		}

		if (null != vo.getId()) {
			error = systemConfigService.update(vo);
		}

		return "success";
	}
	
	//~ User-defined Function =======================================================
	
}