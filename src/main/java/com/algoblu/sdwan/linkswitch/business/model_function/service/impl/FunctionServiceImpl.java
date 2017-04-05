package  com.algoblu.sdwan.linkswitch.business.model_function.service.impl;

import com.algoblu.sdwan.linkswitch.basic.po.Function;
import com.algoblu.sdwan.linkswitch.basic.service.BasicService;
import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_function.service.FunctionService;
import com.algoblu.sdwan.linkswitch.business.model_function.vo.FunctionVo;
import com.algoblu.sdwan.linkswitch.common.dao.FunctionDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 
 * @author  wang yang
 */
@Service
public class FunctionServiceImpl  extends BasicService implements FunctionService{
	
	protected Logger logger = Logger.getLogger(this.getClass());
	//~ fields =======================================================
	@Autowired
	private FunctionDao functionDao;
	
	//~ Method =======================================================
	@Override
	public List<FunctionVo> searchList(FunctionVo vo, boolean isPage) {
		// TODO Auto-generated method stub
		List<Function> mList = null;

		if (isPage) {
			int findEntityListSize = functionDao.findCount(vo);
			vo.setTotalResult(findEntityListSize);
			vo.setPageDisable(false);

			mList = functionDao.find(vo);
		} else {
			vo.setPageDisable(true);
			mList = functionDao.find(vo);
		}

		List<FunctionVo> resultVoList = new ArrayList<FunctionVo>();
		for (Function function : mList) {
			FunctionVo one = new FunctionVo();
			this.copyProperties(one, function);
			resultVoList.add(one);
		}

		return resultVoList;
	}
	
	@Override
	public FunctionVo searchById(Long id) {
		// TODO Auto-generated method stub
		FunctionVo returnVo = new FunctionVo();
		Function po = functionDao.findUniqueBySingleParams("id", String.valueOf(id));
		this.copyProperties(returnVo, po);
		return returnVo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ErrorVo add(FunctionVo vo) {
		// TODO Auto-generated method stub
		ErrorVo error =new ErrorVo();
		try {
			int n = functionDao.save(vo);
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
	public ErrorVo update(FunctionVo vo) {
		// TODO Auto-generated method stub
		ErrorVo er = new ErrorVo();
		Function po = new Function();
		this.copyProperties(po, vo);

		// find update
		Function bossUser = functionDao.findUniqueBySingleParams("id", po.getId().toString());
		if (bossUser == null) {
			er.setSuccess(false);
			er.setErrorCode("the record is not exist! update fail!");
			er.setErrorMessage("该记录不存在,更新失败");
			return er;
		}

		// sql operate
		try {
			int n = functionDao.update(po);
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
	public List<Function> searchFunctionList(FunctionVo vo) {
		List<Function> functionList = null;
		vo.setPageDisable(true);

		functionList = functionDao.selectFunction(vo);

		return functionList;
	}

	@Override
	public long findMaxId(FunctionVo vo) {
		// TODO Auto-generated method stub
		long returnValue = 0;
		List<Function> functionList = null;
		vo.setPageDisable(true);
		functionList = functionDao.findMaxId(vo);
		System.out.println(functionList.get(0));
		if (functionList.get(0) != null) {
			returnValue = functionList.get(0).getId();
		}else {
			returnValue = vo.getParentFunctionId()*10;
		}
		return returnValue;
	}
}