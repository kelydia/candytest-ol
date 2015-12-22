package com.cjgod.candytest.framework.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.cjgod.candytest.exception.BaseException;
import com.cjgod.candytest.framework.util.KeyBoardUtil;

public class KeyWordsAction {
	//声明静态WebDriver对象，用于在此类中相关Driver的操作
	public static WebDriver driver;
	//声明静态ObjectMap（页面元素仓库）对象
	private static PageElementMap elementMap = new PageElementMap(Constants.Path_ConfigurationFile);
	/**此方法的名称对应Excel文件“关键字”列中的open_browser关键字，
	* Excel文件中“操作值”列中的内容用于指定测试用例用何种浏览器运行测试用例。
	* ie表示启动IE浏览器运行测试用例，firefox表示启动火狐浏览器，chrome表示
	* 启动Chrome浏览器进行测试。参数placeholder为无实际值传入的参数，仅为了通过
	* 反射机制统一地使用两个函数参数来调用此函数
	**/
	public static void open_browser(String placeholder,String browserName){
		if(browserName.equals("ie")){
			System.setProperty("webdriver.ie.driver","");
			driver = new InternetExplorerDriver();
			Log.info("IE浏览器实例已经声明");
		}else if(browserName.equals("firefox")){
			driver = new FirefoxDriver();
			Log.info("火狐浏览器实例已经声明");
		}else{
			System.setProperty("webdriver.chrome.driver","");
			driver = new InternetExplorerDriver();
			Log.info("Chrome浏览器实例已经声明");
		}
	}
	/**此方法的名称对应Excel文件“关键字”列中的navigate关键字，
	* Excel文件中“操作值”列中的内容用于指定浏览器访问的URL地址。
	* 通过参数url来传入浏览器访问的网址。参数placeholder为无实际值传入的参数，
	* 仅为了通过反射机制统一地使用两个函数参数来调用此函数
	**/
	public static void navigate(String placeholder,String url){
		driver.get(url);
		Log.info("浏览器访问网址" + url);
	}
	/**此方法的名称对应Excel文件“关键字”列中的input关键字，
	* Excel文件中“操作值”列中的内容用于指定输入框的输入内容。
	* 参数locatorExpression表示输入框的定位表达式。
	* 参数inputStr表示输入框中所要输入的内容
	**/
	public static void input(String locatorExpression,String inputStr){
			WebElement element;
			try {
				element = driver.findElement(elementMap.getLocator(locatorExpression));
				element.clear();
				Log.info("清除"+locatorExpression+"输入框中的所有内容");
				element.sendKeys(inputStr);
				Log.info("在"+locatorExpression+"输入框中输入："+inputStr);
			} catch (BaseException e) {
				TestSuiteByExcel.testResult = false;
				Log.error("异常代码：" + e.getRetCd() + "异常详情：" + e.getMsgDes());
			}
			
	}
	
	public static void click(String locatorExpression,String placeholder) throws BaseException{
		driver.findElement(elementMap.getLocator(locatorExpression)).click();
		Log.info("在"+locatorExpression+"按钮上面进行单击操作成功");
	}
	
	public static void press_Tab(String placeholder1,String placeholder2){
		KeyBoardUtil.pressTabKey();
		Log.info("按Tab键操作成功");
	}
	
	public static void press_Enter(String placeholder1,String placeholder2){
		KeyBoardUtil.pressEnterKey();
		Log.info("按Enter键操作成功");
	}
	
	public static void pasteString(String placeholder,String pasteContent){
		KeyBoardUtil.setAndctrlVClipboardData(pasteContent);
		Log.info("在当前输入框粘贴内容操作成功");
	}
	
	public static void sleep(String placeholder,String sleepTime){
		
	}
	
}
