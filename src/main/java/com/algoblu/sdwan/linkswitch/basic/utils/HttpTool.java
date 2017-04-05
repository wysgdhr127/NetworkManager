package com.algoblu.sdwan.linkswitch.basic.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class HttpTool {
	
	private static Logger logger = Logger.getLogger(HttpTool.class);

	/**
	 * 
	 * @param url
	 * @param sendMsg
	 * @param timeout
	 * @return
	 */
	public static String callHttpServerByXml(String url,String sendMsg, int timeout) {
		StringBuilder stringBuilder = new StringBuilder("\n");
		Exception exception = null;
		try {
			DataInputStream input = null;
			ByteArrayOutputStream output = null;
			
			stringBuilder.append("before call: "+ url+"\n");
			
			if(StringUtils.isEmpty(url)){
				stringBuilder.append("post url is blank");
				logger.info(stringBuilder.toString());
				return "-1";
			}
			
			// 判断url是否以http开头
			Pattern p = Pattern.compile("^http://.+");
			Matcher matcher = p.matcher(url);
			if (!matcher.matches())
				url= "http://" + url;
			
		/*	if(StringUtils.isEmpty(sendMsg)){
				stringBuilder.append("send message is blank");
				logger.info(stringBuilder.toString());
				return "-1";
			}*/
			
			stringBuilder.append("send xml："+sendMsg+"\n");
			
			// 获得到位置服务的链接
			URL request_url = null;
			HttpURLConnection httpUrlConnection = null;
			try {
				request_url = new URL(url);
				URLConnection urlCon = request_url.openConnection();
				httpUrlConnection = (HttpURLConnection) urlCon;
				
				httpUrlConnection.setConnectTimeout(timeout);
				httpUrlConnection.setReadTimeout(timeout);
				httpUrlConnection.setDoOutput(true);
				httpUrlConnection.setDoInput(true);
				httpUrlConnection.setUseCaches(false);
				httpUrlConnection.setRequestMethod("POST");
				
				byte[] bytes = sendMsg.getBytes("UTF-8");
				
				// 将xml数据发送到位置服务
				httpUrlConnection.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
				httpUrlConnection.setRequestProperty("Content-length",String.valueOf(bytes.length));
				httpUrlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
				httpUrlConnection.connect();   
				
				DataOutputStream printout = new DataOutputStream(httpUrlConnection.getOutputStream());
				printout.write(bytes);
				printout.flush();
				printout.close();
				
				stringBuilder.append("[Real time Sent:]" + url);
				
				/*
				 * 获取响应码
				 */
				int code = httpUrlConnection.getResponseCode();   
				if(code == 200){
					stringBuilder.append("ResponseCode "+code+" Successful call "+url+"\n");
				}else{
					stringBuilder.append("ResponseCode "+code+" fail call "+url+"\n");
					return "-1";
				}
			} catch (Exception e) {
				stringBuilder.append("error: "+e.toString()+"\n");
				exception = e;
				return "-1";
			}

			try {
				input = new DataInputStream(httpUrlConnection.getInputStream());
			} catch (IOException e) {
				stringBuilder.append("error: "+e.toString()+"\n");
				exception = e;
				return "-1";
			}		

			byte[] rResult = null;
			output = new ByteArrayOutputStream();
			
			byte[] bufferByte = new byte[256];
			int l = -1;

			try {
				while ((l = input.read(bufferByte)) > -1) {
					output.write(bufferByte, 0, l);
					output.flush();
				}
				stringBuilder.append("Success to get a response from "+url+"\n");
			} catch (IOException e) {
				stringBuilder.append("error: "+e.toString()+"\n");
				exception = e;
				return "-1";
			}
			
			rResult = output.toByteArray();
			String resultStr = new String(rResult);
			stringBuilder.append("receive xml:"+resultStr+"\n");
			return resultStr;
		} catch (RuntimeException e) {
			exception = e;
			return "-1";
		}finally{
			logger.info(stringBuilder.toString());
			if (exception != null) {
				logger.error(exception.getMessage(), exception);
			}
		}
	}
	
	/**
	 * post json
	 * 
	 * @param url
	 * @param sendMsg
	 * @param timeout
	 * @return
	 */
	public static String callHttpServerByJson(String url, String sendMsg, int timeout) {
		StringBuilder stringBuilder = new StringBuilder("\n");
		System.out.println("123");
		Exception exception = null;
		try {
			DataInputStream input = null;
			ByteArrayOutputStream output = null;
			
			stringBuilder.append("before call: "+ url+"\n");
			//LogUtil.info(logger, "before call: "+ url);
			
			if(StringUtils.isEmpty(url)){
				stringBuilder.append("post url is blank");
				logger.info(stringBuilder.toString());
				return "-1";
			}
			
			// 判断url是否以http开头
			Pattern p = Pattern.compile("^http://.+");
			Matcher matcher = p.matcher(url);
			if (!matcher.matches())
				url= "http://" + url;
			
			if(StringUtils.isEmpty(sendMsg)){
				stringBuilder.append("the sendMsg is blank");
				logger.info(stringBuilder.toString());
				return "-1";
			}
			
			stringBuilder.append("send xml："+sendMsg+"\n");
			
			// 获得到位置服务的链接
			URL request_url = null;
			HttpURLConnection httpUrlConnection = null;
			try {
				request_url = new URL(url);
				URLConnection urlCon = request_url.openConnection();
				httpUrlConnection = (HttpURLConnection) urlCon;
				
				httpUrlConnection.setConnectTimeout(timeout);
				httpUrlConnection.setReadTimeout(timeout);
				httpUrlConnection.setDoOutput(true);
				httpUrlConnection.setDoInput(true);
				httpUrlConnection.setUseCaches(false);
				httpUrlConnection.setRequestMethod("POST");
				
				byte[] bytes = sendMsg.getBytes("UTF-8");
				
				// 将xml数据发送到位置服务
				/*httpUrlConnection.setInstanceFollowRedirects(true);
				httpUrlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
				httpUrlConnection.connect();   */
				
				httpUrlConnection.setRequestProperty("Charset", "UTF-8");
				httpUrlConnection.setRequestProperty("Content-Type", "text/xml");
				httpUrlConnection.setRequestProperty("Content-length",String.valueOf(sendMsg.getBytes().length));
				httpUrlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
				
				DataOutputStream printout = new DataOutputStream(httpUrlConnection.getOutputStream());
				printout.write(bytes);
				printout.flush();
				printout.close();
				
				stringBuilder.append("[Real time Sent:]" + url+"\n");
				
				/*
				 * 获取响应码
				 */
				int code = httpUrlConnection.getResponseCode();   
				if(code == 200){
					stringBuilder.append("ResponseCode "+code+" Successful call "+url+"\n");
				}else{
					stringBuilder.append("ResponseCode "+code+" fail call "+url+"\n");
					return "-1";
				}
			} catch (Exception e) {
				stringBuilder.append("error: "+e.toString()+"\n");
				exception = e;
				return "-1";
			}

			try {
				input = new DataInputStream(httpUrlConnection.getInputStream());
			} catch (IOException e) {
				stringBuilder.append("error: "+e.toString()+"\n");
				exception = e;
				return "-1";
			}		

			byte[] rResult = null;
			output = new ByteArrayOutputStream();
			
			byte[] bufferByte = new byte[256];
			int l = -1;

			try {
				while ((l = input.read(bufferByte)) > -1) {
					output.write(bufferByte, 0, l);
					output.flush();
				}
				stringBuilder.append("Success to get a response from "+url+"\n");
			} catch (IOException e) {
				stringBuilder.append("error: "+e.toString()+"\n");
				exception = e;
				return "-1";
			}
			
			rResult = output.toByteArray();
			String resultStr = new String(rResult);
			stringBuilder.append("receive xml:"+resultStr+"\n");
			return resultStr;
		} catch (RuntimeException e) {
			exception = e;
			return "-1";
		}finally{
			logger.info(stringBuilder.toString());
			if (exception != null) {
				logger.error(exception.getMessage(), exception);
			}
		}
	}
	
	public String callZabbix(String url,String sendMsg, int timeout) {
		
		DataInputStream input = null;
		ByteArrayOutputStream output = null;
		try {
			
			if(url==null||url.length()<=0){
				 logger.error("url is null,validate url please");
				 return "-1";
			}
			// 判断url是否以http开头
			Pattern p = Pattern.compile("^http://.+");
			Matcher matcher = p.matcher(url);
			if (!matcher.matches())
				url= "http://" + url;
			if(url==null||url.length()<=0){
				 logger.error("url is null,validate url please");
				 return "-1";
			 }
			
			String xmlString = sendMsg;
			logger.info("send xml："+xmlString);
			byte[] xmlData = xmlString.getBytes();
			

			// 获得到位置服务的链接
			URL request_url = new URL(url);

			URLConnection urlCon = request_url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) urlCon;

			httpUrlConnection.setConnectTimeout(timeout);
			httpUrlConnection.setReadTimeout(timeout);
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestMethod("POST");

			// 将xml数据发送到位置服务
			httpUrlConnection.setRequestProperty("Charset", "UTF-8");
			httpUrlConnection.setRequestProperty("Content-Type", "application/json");
			httpUrlConnection.setRequestProperty("Content-length",
					String.valueOf(xmlData.length));
			httpUrlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			

			DataOutputStream printout = new DataOutputStream(
					httpUrlConnection.getOutputStream());

			printout.write(xmlData);

			printout.flush();

			printout.close();
			
			logger.info("sent:::::"+sendMsg);
			/*
			 * 获取响应码
			 */
			int code = httpUrlConnection.getResponseCode();   
			if(code == 200){
				logger.info("[Real time Receive:] ResponseCode "+code+" Successful call "+url);
			}else{
				logger.info("[Real time Receive:] ResponseCode "+code+" fail call "+url);
				return "-1";
			}

			try {
				input = new DataInputStream(httpUrlConnection.getInputStream());
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				logger.info("there is not process for openstack");
				return "-1";
			}		

			byte[] rResult;
			
			output = new ByteArrayOutputStream();
			
			byte[] bufferByte = new byte[256];

			int l = -1;

			while ((l = input.read(bufferByte)) > -1) {
				output.write(bufferByte, 0, l);
				output.flush();
			}
			
			rResult = output.toByteArray();
			String resultStr = new String(rResult);
			logger.info("rResult:"+resultStr);
			
			return resultStr;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "-1";
		} finally {
			try {

				if(input!=null)
					input.close();
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}

		}
	}
	
	
	public static void main(String[] args){
		System.out.println("111");
		String url = "http://192.168.2.178:8181/restconf/operations/algoblu-topology:getAlgobluNode";
		String sendMsg ="{input:{}}";
		int timeout = 1000;

		callHttpServerByJson(url,sendMsg,timeout);
	}

}
