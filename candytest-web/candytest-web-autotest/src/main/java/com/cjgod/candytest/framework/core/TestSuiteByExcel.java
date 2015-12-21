package com.cjgod.candytest.framework.core;

import java.lang.reflect.Method;
/**
 * page 361
 * @author Administrator
 *
 */
public class TestSuiteByExcel {
	
	public static Method method[];
	public static String keyword;
	public static String value;
	public static KeyWordsAction keyWordsAction;
	public static int testStep;
	public static int testLastStep;
	public static String testCaseID;
	public static String testCaseRunFlag;
	
	public void testTestSuite(){
		//声明一个关键动作类的实例
		keyWordsAction = new KeyWordsAction();
		//使用java的反射机制获取KeyWordsAction类的所有方法对象
		method = keyWordsAction.getClass().getMethods();
		//定义Excel关键文件的路径
		String excelFilePath= Constants.Path_ExcelFile;
		//设定读取Excel文件中的“发送邮件”sheet为操作目标
		ExcelUtil.setExcelFile(excelFilePath);
		//读取“测试用例集合”Sheet中的测试用例总数
		int testCaseCount = ExcelUtil.getRowCount(Constants.Sheet_TestSuite);
		//使用for循环，执行所有标记为“y”的测试用例
		for(int testCaseNo = 1;testCaseNo <= testCaseCount;testCaseNo++){
			//读取“测试用例集合”Sheet中每行的测试用例序号
			testCaseID = ExcelUtil.getCellData(Constants.Sheet_TestSuite,
					testCaseNo,Constants.Col_TestCaseID);
			//读取“测试用例集合”Sheet中每行的是否运行列中的值
			testCaseRunFlag = ExcelUtil.getCellData(Constants.Sheet_TestSuite,
					testCaseNo,Constants.Col_RunFlag);
			//如果是否运行列中的值为“y”，则执行测试用例中的所有步骤
		}
	}
	
}
