package  com.algoblu.sdwan.linkswitch.business.model_port.action;

import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_port.service.PortService;
import com.algoblu.sdwan.linkswitch.business.model_port.vo.PortVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @description 
 * @author  wang yang
 */
 
@Controller("PortAction")
@Scope("prototype")
public class PortAction{

	//~ fields ===================================================================
	@Autowired
	private PortService portService;
	private List<PortVo> resultList;
	private PortVo vo;
	private PortVo avo;// ajax搜索条件
	private PortVo svo;// 搜索条件
	private ErrorVo error;
	
	//~ Getters && Setters =======================================================

	public PortService getPortService() {
		return portService;
	}

	public void setPortService(PortService portService) {
		this.portService = portService;
	}

	public List<PortVo> getResultList() {
		return resultList;
	}

	public void setResultList(List<PortVo> resultList) {
		this.resultList = resultList;
	}

	public PortVo getVo() {
		return vo;
	}

	public void setVo(PortVo vo) {
		this.vo = vo;
	}

	public PortVo getAvo() {
		return avo;
	}

	public void setAvo(PortVo avo) {
		this.avo = avo;
	}

	public PortVo getSvo() {
		return svo;
	}

	public void setSvo(PortVo svo) {
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
	 * [Port]搜索列表页面 进入
	 * 
	 * @return
	 */
	public String ajaxSearch() {
		if (null == avo) {
			avo = new PortVo();
		}
		// 查询
		resultList = portService.searchList(avo, false);

		return "success";
	}

	/**
	 * [Port]搜索列表页面 进入初始化
	 * 
	 * @return
	 */
	public String inSearch() {
		if (null == svo) {
			svo = new PortVo();
		}

		return "success";
	}

	/**
	 * [Port]搜索列表页面 搜索
	 * 
	 * @return
	 */
	public String search() {
		if (null == svo) {
			svo = new PortVo();
		}
		// 查询
		resultList = portService.searchList(svo, true);

		return "success";

	}

	/**
	 * [Port]新增列表页面 进入
	 * 
	 * @return
	 */
	public String inAdd() {
		if (null == vo) {
			vo = new PortVo();
		}

		return "success";
	}

	/**
	 * [Port]新增列表页面 增加
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
		error = portService.add(vo);

		return "success";
	}

	/**
	 * [Port]更新列表页面 进入
	 * 
	 * @return
	 */
	public String inUpdate() {
		if (null == vo) {
			vo = new PortVo();
		}
		vo = portService.searchById(vo.getId());

		return "success";
	}

	/**
	 * [Port]基本参数更新 修改
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
		error = portService.update(vo);

		return "success";
	}

	/**
	 * [Port]基本参数删除 修改
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
			error = portService.update(vo);
		}

		return "success";
	}
	
	//~ User-defined Function =======================================================
	
	/**
	 * [Port]基本参数更新 修改
	 * 
	 * @return
	 */
	public String updatePortInfo() {
		error = portService.updatePortInfo();

		return "success";
	}
	
}