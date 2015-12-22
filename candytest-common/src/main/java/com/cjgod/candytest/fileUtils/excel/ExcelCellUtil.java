package com.cjgod.candytest.fileUtils.excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelCellUtil {
	public static XSSFSheet excelWSheet;
	public static XSSFWorkbook excelWBook;
	public static XSSFCell cell;
	public static XSSFRow row;
	//设置要操作的Excel文件的路径
	public static void setExcelFile(String path){
		
	}
	//设置要操作的Excel文件的路径和Sheet名称
	public static void setExcelFile(String path,String sheetName){
		
	}
	//读取Excel文件指定单元格的函数
	public static String getCellData(String sheetName,int rowNum,int colNum){
		
		return "";
	}
	//获取Excel文件最后一行的行号
	public static int getLastRowNum(){
		return excelWSheet.getLastRowNum();
	}
	//获取Excel文件中数据总行数
	public static int getRowCount(String sheetName){
		excelWSheet = excelWBook.getSheet(sheetName);
		return excelWSheet.getLastRowNum();
	}
	//在Excel文件的执行单元格中写入数据
	public static void setCellData(String sheetName,int rowNum,int colNum,String value){
		
	}	
}
