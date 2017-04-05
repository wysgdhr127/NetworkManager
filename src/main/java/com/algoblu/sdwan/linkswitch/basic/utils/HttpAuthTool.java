package com.algoblu.sdwan.linkswitch.basic.utils;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class HttpAuthTool {

	public static String postMethod(String URL, String jsonobj) throws RuntimeException {
		String output = null;
		try {
			Client client = Client.create();
			client.addFilter(new HTTPBasicAuthFilter("admin", "admin"));

			ClientResponse response = client.resource(URL).accept(MediaType.APPLICATION_JSON)
					.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, jsonobj);
			if (response.getStatus() != 200) {
				throw new RuntimeException(
						"Error while invoking " + URL + " with error code : " + response.getStatus());
			}
			output = response.getEntity(String.class);

		} catch (Exception e) {
			throw new RuntimeException("Error while invoking " + URL);
		}
		return output;
	}

	public static String putMethod(String URL, String jsonobj) throws RuntimeException {
		String output = null;
		try {
			Client client = Client.create();
			client.addFilter(new HTTPBasicAuthFilter("admin", "admin"));

			ClientResponse response = client.resource(URL).accept(MediaType.APPLICATION_XML)
					.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, jsonobj);
			System.out.println(response.getStatus());
			if (response.getStatus() != 200) {
				throw new RuntimeException(
						"Error while invoking " + URL + " with error code : " + response.getStatus());
			}
			output = response.getEntity(String.class);
		} catch (Exception e) {
			throw new RuntimeException("Error while invoking " + URL);
		}
		return output;
	}

	public static void main(String[] args) {
		String URL = "http://192.168.2.178:8181/restconf/operations/algoblu-topology:getAlgobluNode";
		String xmlstr = postMethod(URL, "{input:{}}");
		Document document;
		try {
			document = DocumentHelper.parseText(xmlstr);
			Element root = document.getRootElement();
			
			List<Element> childElements = root.elements();
			for (Element child : childElements) {
				System.out.println(child.asXML());
				// 未知属性名情况下
				List<Attribute> attributeList = child.attributes();
				for (Attribute attr : attributeList) {
					System.out.println(attr.getName() + ": " + attr.getValue());
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
