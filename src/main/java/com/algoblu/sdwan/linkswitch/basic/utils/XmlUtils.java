package com.algoblu.sdwan.linkswitch.basic.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

public class XmlUtils {

	// 按照RootKey查询
	public static String getXMLElementByRootKey(String XMLstr, String Rootkey) {
		String returnValue = null;
		try {
			Document document = DocumentHelper.parseText(XMLstr);
			System.out.println(document.asXML());
			Node node = document.selectSingleNode(Rootkey);
			if (node != null) {
				returnValue = node.getStringValue();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return returnValue;
	}

	public static void main(String[] args) {
		String XMLstr = "<output xmlns=\"url:opendaylight:topologyModel:algoblu-topology\"><outNodes><algobluNodeIp>192.168.2.178</algobluNodeIp><algobluNodeRef xmlns:a=\"urn:opendaylight:inventory\">/a:nodes/a:node[a:id='openflow:69133981858']</algobluNodeRef><algobluNodeId>43a048fb-c933-4653-a89b-24fc8411e5f5</algobluNodeId><algobluNodeName>openflow:69133981858</algobluNodeName><portList><physicalAddress>00:10:18:b4:d8:a2</physicalAddress><algobluPortRef xmlns:a=\"urn:opendaylight:inventory\">/a:nodes/a:node[a:id='openflow:69133981858']/a:node-connector[a:id='openflow:69133981858:LOCAL']</algobluPortRef><algobluPortName>brGZ</algobluPortName><algobluPortNum>LOCAL</algobluPortNum></portList><portList><physicalAddress>20:47:47:81:e9:5c</physicalAddress><algobluPortRef xmlns:a=\"urn:opendaylight:inventory\">/a:nodes/a:node[a:id='openflow:69133981858']/a:node-connector[a:id='openflow:69133981858:4']</algobluPortRef><algobluPortName>eth3</algobluPortName><algobluPortNum>4</algobluPortNum></portList><portList><physicalAddress>20:47:47:81:e9:5a</physicalAddress><algobluPortRef xmlns:a=\"urn:opendaylight:inventory\">/a:nodes/a:node[a:id='openflow:69133981858']/a:node-connector[a:id='openflow:69133981858:3']</algobluPortRef><algobluPortName>eth2</algobluPortName><algobluPortNum>3</algobluPortNum></portList><portList><physicalAddress>00:10:18:b4:d8:a2</physicalAddress><algobluPortRef xmlns:a=\"urn:opendaylight:inventory\">/a:nodes/a:node[a:id='openflow:69133981858']/a:node-connector[a:id='openflow:69133981858:2']</algobluPortRef><algobluPortName>eth1</algobluPortName><algobluPortNum>2</algobluPortNum></portList></outNodes></output>";
		System.out.println(getXMLElementByRootKey(XMLstr, "/output/outNodes"));
	}

}
