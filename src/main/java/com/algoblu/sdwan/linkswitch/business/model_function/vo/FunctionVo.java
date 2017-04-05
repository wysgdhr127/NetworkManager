package  com.algoblu.sdwan.linkswitch.business.model_function.vo;

import com.algoblu.sdwan.linkswitch.basic.po.Function;

/**
 * @description 
 * @author  wang yang
 */
@SuppressWarnings("serial")
public class FunctionVo extends Function{

	//~ fields =======================================================
    //操作id
    private String ids;
    private String role;
    private String functionLevelStr;
    private String roleStr;
    private String parentStr;

    public String getIds() {
        return ids;
    }
    public void setIds(String ids) {
        this.ids = ids;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getFunctionLevelStr() {
        return functionLevelStr;
    }
    public void setFunctionLevelStr(String functionLevelStr) {
        this.functionLevelStr = functionLevelStr;
    }
    public String getRoleStr() {
        return roleStr;
    }
    public void setRoleStr(String roleStr) {
        this.roleStr = roleStr;
    }
    public String getParentStr() {
        return parentStr;
    }
    public void setParentStr(String parentStr) {
        this.parentStr = parentStr;
    }
}