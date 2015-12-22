package com.cjgod.candytest.framework.core;
/**
 * candytest framework 常量
 * @author Administrator
 *
 */
public class Constants {
	/*测试数据项常量设定*/
	
	public static final String Path_ExcelFile = "";
	public static final String Path_ConfigurationFile="";
	
	/*测试数据Sheet中的列号常量设定*/
	
	//第一列用0表示，测试用例序号列
	public static final int Col_TestCaseID = 0;
	//第四列用3表示，关键字列
	public static final int Col_KeyWordAction = 3;
	//第五列用4表示，操作元素的定位表达式列
	public static final int Col_LocatorExpression = 4;
	//第六列用5表示，
	public static final int Col_ActionValue = 5;
	//第七列用6表示，
	public static final int Col_TestStepTestResult = 6;
	
	/*测试集合Sheet中的列好常量设定*/
	
	//第三列用2表示，是否执行列
	public static final int Col_RunFlag = 2;
	//第四列用3表示，测试结果列
	public static final int Col_TestSuiteTestResult = 3;
	
	/*测试用例Sheet名称的常量设定*/
	
	public static final String Sheet_TestSteps = "";
	public static final String Sheet_TestSuite ="测试用例集合";
	
	public static final String Locator_Split = ">";
	
}
