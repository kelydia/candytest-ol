package com.cjgod.candytest.framework.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class KeyBoardUtil {
	//按Tab键的封装方法
	public static void pressTabKey(){
		pressKey(KeyEvent.VK_TAB);
	}
	
	//按ENTER键的封装方法
	public static void pressEnterKey(){
		pressKey(KeyEvent.VK_ENTER);
	}
	
	/*将制定字符串设为剪切板的内容，然后执行粘贴操作
	将页面焦点切换到输入框后，调用此函数可以将制定字符串粘贴到输入框中*/
	public static void setAndctrlVClipboardData(String text){
		StringSelection stringSelection = new StringSelection(text);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		Robot robot = getRobot();
		//按下和释放Ctrl+V组合键
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	private static void pressKey(int keyEvent){
		Robot robot = getRobot();
		//调用keyPress方法来实现按下键的操作
		robot.keyPress(keyEvent);
		//调用keyRelease方法来实现释放按下键的操作
		robot.keyRelease(keyEvent);
	}
	
	private static Robot getRobot(){
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return robot;
	}
}
