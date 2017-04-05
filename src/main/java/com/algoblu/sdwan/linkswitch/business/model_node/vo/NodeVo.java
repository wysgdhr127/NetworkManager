package  com.algoblu.sdwan.linkswitch.business.model_node.vo;

import java.util.List;

import com.algoblu.sdwan.linkswitch.basic.po.Node;
import com.algoblu.sdwan.linkswitch.business.model_link.vo.LinkVo;

/**
 * @description 
 * @author  wang yang
 */
@SuppressWarnings("serial")
public class NodeVo extends Node {

	//~ fields =======================================================
	
	private List<LinkVo> links;

	public List<LinkVo> getLinks() {
		return links;
	}

	public void setLinks(List<LinkVo> links) {
		this.links = links;
	}
	
}