package com.cjgod.candytest.fileUtils.excel;

import java.io.IOException;

import org.testng.annotations.Test;

public class ExcelUtilTest {
	@Test(enabled=true)
	public void testRead(){
	    try {  
	          
	        ExcelUtil eu = new ExcelUtil();  
	        eu.setExcelPath("D:\\mars-files\\2.xls");  
	          
	        System.out.println("=======测试Excel 默认 读取========");  
	        eu.readExcel();  
	          
	        System.out.println("\n=======测试Excel 从第四行读取，倒数第二行结束========");  
	        eu = eu.RestoreSettings();//还原设定  
	        eu.setStartReadPos(3);  
	        eu.setEndReadPos(-1);  
	        eu.readExcel();  
	          
	        System.out.println("\n=======测试Excel 读取第二个sheet========");  
	        eu = eu.RestoreSettings();//还原设定  
	        eu.setSelectedSheetIdx(1);  
	        eu.readExcel();  
	          
	        System.out.println("\n=======测试Excel 读取所有的sheet========");  
	        eu = eu.RestoreSettings();//还原设定  
	        eu.setOnlyReadOneSheet(false);  
	        eu.readExcel();  
	          
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }
		
	}
	
	@Test(enabled=false)
	public void testMerge(){
		try {
			ExcelUtil eu1 = new ExcelUtil();//用来读取源xls
			ExcelUtil eu2 = new ExcelUtil();//用来读取目标xls，用于演示合并结果
			eu1.setExcelPath("d:\\2.xls");
			eu2.setExcelPath("d:\\1.xls");
			
			System.out.println("\n=======修改前，1.xls中的内容========");
			eu2.readExcel();
			
			System.out.println("\n=======读取源文件2.xls中的内容========");
			eu1.setStartReadPos(3);
			//eu1.setOverWrite(false);//是否覆写目标文件（默认覆写）
			//eu1.setComparePos(1);//设定比较哪一列内容（默认为0，比较第一列内容）
			//eu1.setNeedCompare(false);//设定是否比较（默认值是true）。只有当不覆盖目标文件时，设置检查重复才有效。
			
			eu1.writeExcel(eu1.readExcel(), "d:\\1.xls");//将读取到的2.xls中的数据合并到1.xls中
			System.out.println("\n=======修改后，1.xls中的内容========");
			eu2.readExcel();//读取合并后的1.xls的数据
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
