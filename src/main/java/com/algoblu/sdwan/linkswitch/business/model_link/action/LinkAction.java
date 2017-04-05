package  com.algoblu.sdwan.linkswitch.business.model_link.action;

import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_link.service.LinkService;
import com.algoblu.sdwan.linkswitch.business.model_link.vo.LinkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @description 
 * @author  wang yang
 */
 
@Controller("LinkAction")
@Scope("prototype")
public class LinkAction{

	//~ fields ===================================================================
	@Autowired
	private LinkService linkService;
	private List<LinkVo> resultList;
	private LinkVo vo;
	private LinkVo avo;// ajax搜索条件
	private LinkVo svo;// 搜索条件
	private ErrorVo error;
	
	//~ Getters && Setters =======================================================

	public LinkService getLinkService() {
		return linkService;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

	public List<LinkVo> getResultList() {
		return resultList;
	}

	public void setResultList(List<LinkVo> resultList) {
		this.resultList = resultList;
	}

	public LinkVo getVo() {
		return vo;
	}

	public void setVo(LinkVo vo) {
		this.vo = vo;
	}

	public LinkVo getAvo() {
		return avo;
	}

	public void setAvo(LinkVo avo) {
		this.avo = avo;
	}

	public LinkVo getSvo() {
		return svo;
	}

	public void setSvo(LinkVo svo) {
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
			avo = new LinkVo();
		}
		// 查询
		resultList = linkService.searchList(avo, false);

		return "success";
	}

	/**
	 * [SystemConfig]搜索列表页面 进入初始化
	 * 
	 * @return
	 */
	public String inSearch() {
		if (null == svo) {
			svo = new LinkVo();
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
			svo = new LinkVo();
		}
		// 查询
		resultList = linkService.searchList(svo, true);

		return "success";

	}

	/**
	 * [SystemConfig]新增列表页面 进入
	 * 
	 * @return
	 */
	public String inAdd() {
		if (null == vo) {
			vo = new LinkVo();
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
		error = linkService.add(vo);

		return "success";
	}

	/**
	 * [SystemConfig]更新列表页面 进入
	 * 
	 * @return
	 */
	public String inUpdate() {
		if (null == vo) {
			vo = new LinkVo();
		}
		vo = linkService.searchById(vo.getId());

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
		error = linkService.update(vo);

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
			error = linkService.update(vo);
		}

		return "success";
	}
	
	//~ User-defined Function =======================================================
	
	public String updateLinkInfo() {
		error = linkService.updateLinkInfo();

		return "success";
	}
	
}