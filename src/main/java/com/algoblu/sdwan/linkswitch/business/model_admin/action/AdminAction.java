package  com.algoblu.sdwan.linkswitch.business.model_admin.action;

import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_admin.service.AdminService;
import com.algoblu.sdwan.linkswitch.business.model_admin.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @description 
 * @author  wang yang
 */
 
@Controller("AdminAction")
@Scope("prototype")
public class AdminAction{

	//~ fields ===================================================================
	@Autowired
	private AdminService adminService;
	private List<AdminVo> resultList;
	private AdminVo vo;
	private AdminVo avo;// ajax搜索条件
	private AdminVo svo;// 搜索条件
	private ErrorVo error;
	
	//~ Getters && Setters =======================================================
	
	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public List<AdminVo> getResultList() {
		return resultList;
	}

	public void setResultList(List<AdminVo> resultList) {
		this.resultList = resultList;
	}

	public AdminVo getVo() {
		return vo;
	}

	public void setVo(AdminVo vo) {
		this.vo = vo;
	}

	public AdminVo getAvo() {
		return avo;
	}

	public void setAvo(AdminVo avo) {
		this.avo = avo;
	}

	public AdminVo getSvo() {
		return svo;
	}

	public void setSvo(AdminVo svo) {
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
			avo = new AdminVo();
		}
		// 查询
		resultList = adminService.searchList(avo, false);

		return "success";
	}

	
	/**
	 * [SystemConfig]搜索列表页面 进入初始化
	 * 
	 * @return
	 */
	public String inSearch() {
		if (null == svo) {
			svo = new AdminVo();
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
			svo = new AdminVo();
		}
		// 查询
		resultList = adminService.searchList(svo, true);

		return "success";

	}

	/**
	 * [SystemConfig]新增列表页面 进入
	 * 
	 * @return
	 */
	public String inAdd() {
		if (null == vo) {
			vo = new AdminVo();
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
		error = adminService.add(vo);

		return "success";
	}

	/**
	 * [SystemConfig]更新列表页面 进入
	 * 
	 * @return
	 */
	public String inUpdate() {
		if (null == vo) {
			vo = new AdminVo();
		}
		vo = adminService.searchById(vo.getId());

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
		error = adminService.update(vo);

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
			error = adminService.update(vo);
		}

		return "success";
	}
	
	//~ User-defined Function =======================================================
	
}