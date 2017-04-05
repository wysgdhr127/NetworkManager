package  com.algoblu.sdwan.linkswitch.business.model_bridge.service.impl;

import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.algoblu.sdwan.linkswitch.basic.po.Bridge;
import com.algoblu.sdwan.linkswitch.common.dao.BridgeDao;
import com.algoblu.sdwan.linkswitch.business.model_bridge.vo.BridgeVo;
import com.algoblu.sdwan.linkswitch.business.model_bridge.service.BridgeService;
import com.algoblu.sdwan.linkswitch.basic.service.BasicService;

import com.algoblu.sdwan.linkswitch.basic.vo.ErrorVo;

/**
 * @description 
 * @author  wang yang
 */
@Service
public class BridgeServiceImpl  extends BasicService implements BridgeService{
	
	protected Logger logger = Logger.getLogger(this.getClass());
	//~ fields =======================================================
	@Autowired
	private BridgeDao bridgeDao;
	
	//~ Method =======================================================
	@Override
	public List<BridgeVo> searchList(BridgeVo vo, boolean isPage) {
		// TODO Auto-generated method stub
		List<Bridge> mList = null;

		if (isPage) {
			int findEntityListSize = bridgeDao.findCount(vo);
			vo.setTotalResult(findEntityListSize);
			vo.setPageDisable(false);

			mList = bridgeDao.find(vo);
		} else {
			vo.setPageDisable(true);
			mList = bridgeDao.find(vo);
		}

		List<BridgeVo> resultVoList = new ArrayList<BridgeVo>();
		for (Bridge bridge : mList) {
			BridgeVo one = new BridgeVo();
			this.copyProperties(one, bridge);
			resultVoList.add(one);
		}

		return resultVoList;
	}
	
	@Override
	public BridgeVo searchById(Long id) {
		// TODO Auto-generated method stub
		BridgeVo returnVo = new BridgeVo();
		Bridge po = bridgeDao.findUniqueBySingleParams("id", String.valueOf(id));
		this.copyProperties(returnVo, po);
		return returnVo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ErrorVo add(BridgeVo vo) {
		// TODO Auto-generated method stub
		ErrorVo error =new ErrorVo();
		try {
			int n = bridgeDao.save(vo);
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
	public ErrorVo update(BridgeVo vo) {
		// TODO Auto-generated method stub
		ErrorVo er = new ErrorVo();
		Bridge po = new Bridge();
		this.copyProperties(po, vo);

		// find update
		Bridge bossUser = bridgeDao.findUniqueBySingleParams("id", po.getId().toString());
		if (bossUser == null) {
			er.setSuccess(false);
			er.setErrorCode("the record is not exist! update fail!");
			er.setErrorMessage("该记录不存在,更新失败");
			return er;
		}

		// sql operate
		try {
			int n = bridgeDao.update(po);
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