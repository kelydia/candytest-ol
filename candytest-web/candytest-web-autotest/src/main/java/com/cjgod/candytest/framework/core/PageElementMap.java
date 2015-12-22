package com.cjgod.candytest.framework.core;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.openqa.selenium.By;

import com.cjgod.candytest.exception.BaseException;

public class PageElementMap {
	Properties properties;
	public PageElementMap(String propFile){
		properties = new Properties();
		try {
			FileInputStream in = new FileInputStream(propFile);
			properties.load(in);
			in.close();
		} catch (Exception e) {
			Log.error("读取页面元素仓库对象文件出错");
		}
	}
	
	public By getLocator(String elementNameInPropFile)throws BaseException{
		//根据变量elementNameInPropFile，从属性配置文件中读取对应的配置对象
		String locator = properties.getProperty(elementNameInPropFile);
		if(locator == null) throw new BaseException("100001","未从页面元素仓库文件中读取到对应的配置对象");
		//将配置对象中的定位类型存储到locatorType变量中，将定位表达式的值存储到locatorValue变量中
		String[] strs = locator.split(Constants.Locator_Split);
		String locatorType = strs[0];
		String locatorValue = strs[1];
		if(locatorType == null) throw new BaseException("100002","定位表达式的值为空");
		locatorType = locatorType.toLowerCase();
		try{
			if(locatorValue != null) 
				locatorValue = new String(locatorValue.getBytes("ISO-8859-1"),"UTF-8");
		}catch(UnsupportedEncodingException e){
			throw new BaseException("500","在进行操作值的夫妇编码转换时出现了异常："+e.getMessage());
		}
		Log.info("获取的定位类型： " + locatorType + "\t 获取的定位表达式" + locatorValue);
		
		//根据locatorType变量值得内容判断返回何种定位方式的By对象
		if(locatorType.equals("id")){
			return By.id(locatorValue);
		}else if(locatorType.equals("css")){
			return By.cssSelector(locatorValue);
		}else if(locatorType.equals("xpath")){
			return By.xpath(locatorValue);
		}else if(locatorType.equals("name")){
			return By.name(locatorValue);
		}else if(locatorType.equals("link")){
			return By.linkText(locatorValue);
		}else{
			throw new BaseException("100003","所输入的定位表达式locatorType未在程序中被定义 ： " + locatorType);
		}
		
		
	}
}
