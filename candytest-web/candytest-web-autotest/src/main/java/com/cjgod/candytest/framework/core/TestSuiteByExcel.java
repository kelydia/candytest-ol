package com.cjgod.candytest.framework.core;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.internal.TestResult;
/**
 * page 361
 * @author Administrator
 *
 */
public class TestSuiteByExcel {
	
	public static Method method[];
	public static String keyword;
	public static String locatorExpression;
	public static String value;
	public static KeyWordsAction keyWordsAction;
	public static int testStep;
	public static int testLastStep;
	public static String testCaseID;
	public static String testCaseRunFlag;
	public static boolean testResult;
	
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
			if(testCaseRunFlag = ExcelUtil.getCellData(Constants.Sheet_TestSuite,
					testCaseNo,Constants.Col_RunFlag) ){
				//在日志中打印测试用例开始执行
				Log.startTestCase(testCaseID);
				//设定测试用例的当前结果为true，即表明测试执行成功
				testResult = true;
				//在指定的sheet中，获取当前要执行测试用例的第一个步骤所在行的行号
				testStep = ExcelUtil.getFirstRowContainsTestCaseID(
						Constants.Sheet_TestSteps,testCaseID,Constants.Col_TestCaseID);
				//在指定的sheet中，获取当前要执行测试用例的最后一个步骤所在行的行号
				testLastStep = ExcelUtil.getTestCaseLastStepRow(
						Constants.Sheet_TestSteps,testCaseID,testStep);
				//遍历测试用例中的所有测试步骤
				for(;testStep < testLastStep;testStep++){
					//读取测试用例步骤中的关键字和操作值，并调用execute_Actions方法执行
					keyword = ExcelUtil.getCellData(Constants.Sheet_TestSteps,
							testStep,Constants.Col_KeyWordAction);
					//在日志中打印关键字信息
					Log.info("从Excel文件中读取到的关键字是： "+keyword);
					locatorExpression = ExcelUtil.getCellData(Constants.Sheet_TestSteps,
							testStep,Constants.Col_LocatorExpression);
					//在日志中打印操作元素表达式信息
					Log.info("从Excel文件中读取到的操作元素表达式是： "+locatorExpression);
					value = ExcelUtil.getCellData(Constants.Sheet_TestSteps,
							testStep,Constants.Col_ActionValue);
					//在日志中打印操作值信息
					Log.info("从Excel文件中读取到的操作值是： "+value);
					//执行用例
					execute_Actions();
					//用例执行失败的处理
					if(testResult == false){
						//如果测试用例的任何一个测试步骤执行失败，则测试用例集合Sheet中的当前执行测试用例的执行结果设定为“测试执行失败”
						ExcelUtil.setCellData("测试用例集合",testCaseNo,
								Constants.Col_TestSuiteTestResult,"测试执行失败");
						/*当前测试用例出现执行失败的步骤，则整个测试用例设置为失败状态，
						break语句跳出当前的for循环，继续执行测试集合中的下一个测试用例*/
						break;
					}
					if(testResult == true){
						//如果测试用例的所有步骤执行成功，则将在测试用例集合Sheet中的当前执行测试用例的执行结果设定为“测试执行成功”
						ExcelUtil.setCellData("测试用例集合",testCaseNo,
								Constants.Col_TestSuiteTestResult,"测试执行成功");
					}
				}
				
				
			}
			
			
		}//循环结束
		//在日志中打印测试用例执行完毕
		Log.endTestCase(testCaseID);
	}
	
	private static void execute_Actions(){
		try{
			for(int i=0;i<method.length;i++){
				//使用反射的方式，找到关键字对应的测试方法，并使用操作值value作为测试方法的函数值进行调用
				if(method[i].getName().equals(keyword)){
					method[i].invoke(keyWordsAction,locatorExpression,value);
					if(testResult == true){
						ExcelUtil.setCellData(Constants.Sheet_TestSteps,
								testStep,Constants.Col_TestStepTestResult,"测试步骤执行成功");
						break;
					}else{
						ExcelUtil.setCellData(Constants.Sheet_TestSteps,
								testStep,Constants.Col_TestStepTestResult,"测试步骤执行失败");
						//测试步骤执行失败，则直接关闭浏览器，不再执行后续的测试步骤
						KeyWordsAction.close_browser("","");
						break;
					}
				}
			}
			
		}catch(Exception e){
			Assert.fail("执行出现异常，测试用例执行失败！");
		}
	}
	
}
