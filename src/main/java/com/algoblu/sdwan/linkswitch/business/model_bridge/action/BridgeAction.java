package  com.algoblu.sdwan.linkswitch.business.model_bridge.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_bridge.service.BridgeService;
import com.algoblu.sdwan.linkswitch.business.model_bridge.vo.BridgeVo;

/**
 * @description 
 * @author  wang yang
 */
 
@Controller("BridgeAction")
@Scope("prototype")
public class BridgeAction{

	//~ fields ===================================================================
	@Autowired
	private BridgeService bridgeService;
	private List<BridgeVo> resultList;
	private BridgeVo vo;
	private BridgeVo avo;// ajax搜索条件
	private BridgeVo svo;// 搜索条件
	private ErrorVo error;
	
	//~ Getters && Setters =======================================================
	public BridgeService getBridgeService() {
		return bridgeService;
	}

	public void setBridgeService(BridgeService bridgeService) {
		this.bridgeService = bridgeService;
	}

	public List<BridgeVo> getResultList() {
		return resultList;
	}

	public void setResultList(List<BridgeVo> resultList) {
		this.resultList = resultList;
	}

	public BridgeVo getVo() {
		return vo;
	}

	public void setVo(BridgeVo vo) {
		this.vo = vo;
	}

	public BridgeVo getAvo() {
		return avo;
	}

	public void setAvo(BridgeVo avo) {
		this.avo = avo;
	}

	public BridgeVo getSvo() {
		return svo;
	}

	public void setSvo(BridgeVo svo) {
		this.svo = svo;
	}

	public ErrorVo getError() {
		return error;
	}

	public void setError(ErrorVo error) {
		this.error = error;
	}
	//~ Function =================================================================
	/**
	 * [SystemConfig]搜索列表页面 进入
	 * 
	 * @return
	 */
	public String ajaxSearch() {
		if (null == avo) {
			avo = new BridgeVo();
		}
		// 查询
		resultList = bridgeService.searchList(avo, false);

		return "success";
	}
	
	/**
	 * [SystemConfig]搜索列表页面 进入初始化
	 * 
	 * @return
	 */
	public String inSearch() {
		if (null == svo) {
			svo = new BridgeVo();
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
			svo = new BridgeVo();
		}
		// 查询
		resultList = bridgeService.searchList(svo, true);

		return "success";

	}

	/**
	 * [SystemConfig]新增列表页面 进入
	 * 
	 * @return
	 */
	public String inAdd() {
		if (null == vo) {
			vo = new BridgeVo();
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
		error = bridgeService.add(vo);

		return "success";
	}

	/**
	 * [SystemConfig]更新列表页面 进入
	 * 
	 * @return
	 */
	public String inUpdate() {
		if (null == vo) {
			vo = new BridgeVo();
		}
		vo = bridgeService.searchById(vo.getId());

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
		error = bridgeService.update(vo);

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
			error = bridgeService.update(vo);
		}

		return "success";
	}
	
	//~ User-defined Function =======================================================
	
}