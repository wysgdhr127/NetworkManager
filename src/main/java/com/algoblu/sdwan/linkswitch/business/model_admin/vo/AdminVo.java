package  com.algoblu.sdwan.linkswitch.business.model_admin.vo;

import com.algoblu.sdwan.linkswitch.basic.po.Admin;

/**
 * @description 
 * @author  wang yang
 */
@SuppressWarnings("serial")
public class AdminVo extends Admin{

	//~ fields =======================================================
	
	private String createTimeStr;

	private String deleteTimeStr;

	private String lastLoginTimeStr;

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getDeleteTimeStr() {
		return deleteTimeStr;
	}

	public void setDeleteTimeStr(String deleteTimeStr) {
		this.deleteTimeStr = deleteTimeStr;
	}

	public String getLastLoginTimeStr() {
		return lastLoginTimeStr;
	}

	public void setLastLoginTimeStr(String lastLoginTimeStr) {
		this.lastLoginTimeStr = lastLoginTimeStr;
	}
	
	
}