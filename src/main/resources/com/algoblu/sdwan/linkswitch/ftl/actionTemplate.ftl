package  ${defaultTemplate.packagePath};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ${defaultTemplate.modelPath};
import ${defaultTemplate.servicePath};
import ${defaultTemplate.voPath};

import com.algoblu.template.basic.vo.ErrorVo;

/**
 * @description 
 * @author  wang yang
 */
 
@Controller("${defaultTemplate.actionName}")
@Scope("prototype")
public class ${defaultTemplate.actionName}{

	//~ fields ===================================================================
	@Autowired
	private ${defaultTemplate.serviceName} ${defaultTemplate.serviceEntity};
	private List<${defaultTemplate.voName}> resultList;
	private ${defaultTemplate.voName} vo;
	private ${defaultTemplate.voName} avo;// ajax搜索条件
	private ${defaultTemplate.voName} svo;// 搜索条件
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
			avo = new ${defaultTemplate.voName}();
		}
		// 查询
		resultList = ${defaultTemplate.serviceEntity}.searchList(avo, false);

		return "success";
	}

	/**
	 * [SystemConfig]搜索列表页面 进入初始化
	 * 
	 * @return
	 */
	public String inSearch() {
		if (null == svo) {
			svo = new ${defaultTemplate.voName}();
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
			svo = new ${defaultTemplate.voName}();
		}
		// 查询
		resultList = ${defaultTemplate.serviceEntity}.searchList(svo, true);

		return "success";

	}

	/**
	 * [SystemConfig]新增列表页面 进入
	 * 
	 * @return
	 */
	public String inAdd() {
		if (null == vo) {
			vo = new ${defaultTemplate.voName}();
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
		error = ${defaultTemplate.serviceEntity}.add(vo);

		return "success";
	}

	/**
	 * [SystemConfig]更新列表页面 进入
	 * 
	 * @return
	 */
	public String inUpdate() {
		if (null == vo) {
			vo = new ${defaultTemplate.voName}();
		}
		vo = ${defaultTemplate.serviceEntity}.searchById(vo.getId());

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
		error = ${defaultTemplate.serviceEntity}.update(vo);

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
			error = ${defaultTemplate.serviceEntity}.update(vo);
		}

		return "success";
	}
	
	//~ User-defined Function =======================================================
	
}