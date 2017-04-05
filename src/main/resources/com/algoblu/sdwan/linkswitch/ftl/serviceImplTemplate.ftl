package  ${defaultTemplate.packagePath};

import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ${defaultTemplate.modelPath};
import ${defaultTemplate.daoPath};
import ${defaultTemplate.voPath};
import ${defaultTemplate.servicePath};
import ${defaultTemplate.baseServiceImplPath};

import com.algoblu.template.basic.vo.ErrorVo;

/**
 * @description 
 * @author  wang yang
 */
@Service
public class ${defaultTemplate.serviceImplName}  extends ${defaultTemplate.baseServiceImplName} implements ${defaultTemplate.serviceName}{
	
	protected Logger logger = Logger.getLogger(this.getClass());
	//~ fields =======================================================
	@Autowired
	private ${defaultTemplate.daoName} ${defaultTemplate.daoEntity};
	
	//~ Method =======================================================
	@Override
	public List<${defaultTemplate.voName}> searchList(${defaultTemplate.voName} vo, boolean isPage) {
		// TODO Auto-generated method stub
		List<${defaultTemplate.entityName}> mList = null;

		if (isPage) {
			int findEntityListSize = ${defaultTemplate.daoEntity}.findCount(vo);
			vo.setTotalResult(findEntityListSize);
			vo.setPageDisable(false);

			mList = ${defaultTemplate.daoEntity}.find(vo);
		} else {
			vo.setPageDisable(true);
			mList = ${defaultTemplate.daoEntity}.find(vo);
		}

		List<${defaultTemplate.voName}> resultVoList = new ArrayList<${defaultTemplate.voName}>();
		for (${defaultTemplate.entityName} ${defaultTemplate.entityViable} : mList) {
			${defaultTemplate.voName} one = new ${defaultTemplate.voName}();
			this.copyProperties(one, ${defaultTemplate.entityViable});
			resultVoList.add(one);
		}

		return resultVoList;
	}
	
	@Override
	public ${defaultTemplate.voName} searchById(Long id) {
		// TODO Auto-generated method stub
		${defaultTemplate.voName} returnVo = new ${defaultTemplate.voName}();
		${defaultTemplate.entityName} po = ${defaultTemplate.daoEntity}.findUniqueBySingleParams("id", String.valueOf(id));
		this.copyProperties(returnVo, po);
		return returnVo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ErrorVo add(${defaultTemplate.voName} vo) {
		// TODO Auto-generated method stub
		ErrorVo error =new ErrorVo();
		try {
			int n = ${defaultTemplate.daoEntity}.save(vo);
			if (n>0) {
				error.setSuccess(true);
				error.setErrorCode("save success!");
				error.setErrorMessage("保存成功,条数:" + n);
				error.setErrorMessage("保存成功");
			}else {
				error.setSuccess(false);
				error.setErrorCode("save fail!");
				error.setErrorMessage("保存出错");
			}
		} catch (Exception e) {
			// TODO: handle exception
			error.setSuccess(false);
			error.setErrorCode("save fail!");
			error.setErrorMessage("保存出错");
			e.printStackTrace();
		}
		
		return error;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ErrorVo update(${defaultTemplate.voName} vo) {
		// TODO Auto-generated method stub
		ErrorVo er = new ErrorVo();
		${defaultTemplate.entityName} po = new ${defaultTemplate.entityName}();
		this.copyProperties(po, vo);

		// find update
		${defaultTemplate.entityName} bossUser = ${defaultTemplate.daoEntity}.findUniqueBySingleParams("id", po.getId().toString());
		if (bossUser == null) {
			er.setSuccess(false);
			er.setErrorCode("the record is not exist! update fail!");
			er.setErrorMessage("该记录不存在,更新失败");
			return er;
		}

		// sql operate
		try {
			int n = ${defaultTemplate.daoEntity}.update(po);
			if (n > 0) {
				this.copyProperties(vo, po);
				er.setSuccess(true);
				er.setErrorCode("save success!");
				er.setErrorMessage("更新成功,条数:" + n);
				er.setErrorMessage("更新成功");
			} else {
				er.setSuccess(false);
				er.setErrorCode("save fail![" + n + "]");
				er.setErrorMessage("更新失败");
			}
		} catch (Exception e) {
			er.setSuccess(false);
			er.setErrorCode("update fail!");
			er.setErrorMessage("更新出错");
			e.printStackTrace();
		}

		return er;
	}

	@Override
	public ErrorVo delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	//~ User-defined Method =======================================================
}