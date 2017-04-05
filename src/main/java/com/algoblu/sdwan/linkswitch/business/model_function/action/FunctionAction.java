package com.algoblu.sdwan.linkswitch.business.model_function.action;

import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_function.service.FunctionService;
import com.algoblu.sdwan.linkswitch.business.model_function.vo.FunctionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @description 
 * @author  wang yang
 */
 
@Controller("FunctionAction")
@Scope("prototype")
public class FunctionAction{

	//~ fields ===================================================================
	@Autowired
	private FunctionService functionService;
	private List<FunctionVo> resultList;
	private FunctionVo vo;
	private FunctionVo avo;// ajax搜索条件
	private FunctionVo svo;// 搜索条件
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
			avo = new FunctionVo();
		}
		// 查询
		resultList = functionService.searchList(avo, false);

		return "success";
	}

	/**
	 * [SystemConfig]搜索列表页面 进入初始化
	 * 
	 * @return
	 */
	public String inSearch() {
		if (null == svo) {
			svo = new FunctionVo();
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
			svo = new FunctionVo();
		}
		// 查询
		resultList = functionService.searchList(svo, true);

		return "success";

	}

	/**
	 * [SystemConfig]新增列表页面 进入
	 * 
	 * @return
	 */
	public String inAdd() {
		if (null == vo) {
			vo = new FunctionVo();
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
		error = functionService.add(vo);

		return "success";
	}

	/**
	 * [SystemConfig]更新列表页面 进入
	 * 
	 * @return
	 */
	public String inUpdate() {
		if (null == vo) {
			vo = new FunctionVo();
		}
		vo = functionService.searchById(vo.getId());

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
		error = functionService.update(vo);

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
			error = functionService.update(vo);
		}

		return "success";
	}
	
	//~ User-defined Function =======================================================
	
}