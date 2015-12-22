使用autoIt转换为EXE格式的可执行文件

使用java的runTime类调用

Runtime.getRuntime().exec("E:\\test\\download.exe");



如果autoIt需要参数

String tag = "\"";
//String cmd = " \" " + executeFile + " \" " + " " + " \" " + saveFileName + " \" " ;
String cmd = tag + executeFile + tag + " " + tag + browser + tag + " " + tag + filepath + tag;

	Process p = Runtime.getRuntime().exec(cmd);