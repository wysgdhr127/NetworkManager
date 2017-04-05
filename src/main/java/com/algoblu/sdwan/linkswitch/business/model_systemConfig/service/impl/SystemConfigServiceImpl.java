package  com.algoblu.sdwan.linkswitch.business.model_systemConfig.service.impl;

import com.algoblu.sdwan.linkswitch.basic.po.SystemConfig;
import com.algoblu.sdwan.linkswitch.basic.service.BasicService;
import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;
import com.algoblu.sdwan.linkswitch.business.model_systemConfig.service.SystemConfigService;
import com.algoblu.sdwan.linkswitch.business.model_systemConfig.vo.SystemConfigVo;
import com.algoblu.sdwan.linkswitch.common.dao.SystemConfigDao;
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
public class SystemConfigServiceImpl  extends BasicService implements SystemConfigService{
	
	protected Logger logger = Logger.getLogger(this.getClass());
	//~ fields =======================================================
	@Autowired
	private SystemConfigDao systemConfigDao;
	
	//~ Method =======================================================
	@Override
	public List<SystemConfigVo> searchList(SystemConfigVo vo, boolean isPage) {
		// TODO Auto-generated method stub
		List<SystemConfig> mList = null;

		if (isPage) {
			int findEntityListSize = systemConfigDao.findCount(vo);
			vo.setTotalResult(findEntityListSize);
			vo.setPageDisable(false);

			mList = systemConfigDao.find(vo);
		} else {
			vo.setPageDisable(true);
			mList = systemConfigDao.find(vo);
		}

		List<SystemConfigVo> resultVoList = new ArrayList<SystemConfigVo>();
		for (SystemConfig systemConfig : mList) {
			SystemConfigVo one = new SystemConfigVo();
			this.copyProperties(one, systemConfig);
			resultVoList.add(one);
		}

		return resultVoList;
	}
	
	@Override
	public SystemConfigVo searchById(Long id) {
		// TODO Auto-generated method stub
		SystemConfigVo returnVo = new SystemConfigVo();
		SystemConfig po = systemConfigDao.findUniqueBySingleParams("id", String.valueOf(id));
		this.copyProperties(returnVo, po);
		return returnVo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ErrorVo add(SystemConfigVo vo) {
		// TODO Auto-generated method stub
		ErrorVo error =new ErrorVo();
		try {
			int n = systemConfigDao.save(vo);
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
	public ErrorVo update(SystemConfigVo vo) {
		// TODO Auto-generated method stub
		ErrorVo er = new ErrorVo();
		SystemConfig po = new SystemConfig();
		this.copyProperties(po, vo);

		// find update
		SystemConfig bossUser = systemConfigDao.findUniqueBySingleParams("id", po.getId().toString());
		if (bossUser == null) {
			er.setSuccess(false);
			er.setErrorCode("the record is not exist! update fail!");
			er.setErrorMessage("该记录不存在,更新失败");
			return er;
		}

		// sql operate
		try {
			int n = systemConfigDao.update(po);
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