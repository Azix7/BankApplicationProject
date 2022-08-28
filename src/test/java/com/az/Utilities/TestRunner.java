package com.az.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;

public class TestRunner {

	public YamlReader yaml=new YamlReader();
	static Process process = null;
	XmlParser xmlParser = new XmlParser();
	XmlParserForReport xmlParserForReport = new XmlParserForReport();
	CreateReportHtml createReportHtml=new CreateReportHtml();

	public void initiateTest(String suite_name) throws IOException, InterruptedException{
		
		HashMap<String, ArrayList<String>> testResultMap=new HashMap<String, ArrayList<String>>();
		ArrayList<String> executedTestList=new ArrayList<String>();
		double totalTimeCount=0;
		long hours=0;
		long minutes=0;
		long seconds=0;
		HashMap<String, String> listSuite = xmlParser.getSuite(yaml.getYamlValue("TEST_NG_CONFIG_FILE"));
		HashMap<String, ArrayList<String>> listTest = xmlParser.getTestCases();
		System.out.println(listSuite);
		System.out.println(listTest);
		if(suite_name!=null){

			ArrayList<String> tests = listTest.get(suite_name);
			System.out.println(tests);
			System.out.println(listTest);
			for (String test : tests) {
				executedTestList.add(test.split("\\.")[test.split("\\.").length-1]);
				/*String[] command = {"cmd.exe", "/c", "mvn -Dtest="+test+" test"};
				ProcessBuilder probuilder = new ProcessBuilder( command );*/
				String line="cmd.exe /c mvn -Dtest="+test+" test";
				DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

			    CommandLine cmdLine = CommandLine.parse(line);
			    Executor executor = new DefaultExecutor();
			    executor.execute(cmdLine,resultHandler);
			    resultHandler.waitFor();
			    int exitValue=resultHandler.getExitValue();
			    if(executor.isFailure(exitValue)){
			     executor.setExitValue(1);
			    }

				//Wait to get exit value
				try {
//					exitValue = process.waitFor();
					moveReportFile("tests", test);
					System.out.println("\n\nExit Value is " + exitValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Thread.sleep(5000);
				ArrayList<String> testResultDetails=new ArrayList<String>();
				testResultDetails.add(Double.toString(xmlParserForReport.getTotalTime()));
				testResultDetails.add(xmlParserForReport.getTotalNumberOfPassedTests());
				testResultDetails.add(xmlParserForReport.getTotalNumberOfTestsFailed());
				testResultDetails.add(xmlParserForReport.getTotalNumberOfTestsSkipped());
				testResultMap.put(test.split("\\.")[test.split("\\.").length-1], testResultDetails);
				totalTimeCount=totalTimeCount+(xmlParserForReport.getTotalTime()*1000);
			}
			hours=TimeUnit.MILLISECONDS.toHours((long)totalTimeCount);
			minutes=TimeUnit.MILLISECONDS.toMinutes((long)totalTimeCount)-TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours((long)totalTimeCount));
			seconds=TimeUnit.MILLISECONDS.toSeconds((long)totalTimeCount)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)totalTimeCount));
			createReportHtml.createReportSummary(suite_name, hours+"hour"+minutes+"minute"+seconds+"second", executedTestList, testResultMap);
		}
	}

	public void moveReportFile(String targetFolder,String testName){
		InputStream inStream = null;
		OutputStream outStream = null;

		try{
			File afile =new File("target\\surefire-reports\\Surefire suite\\Surefire test.html");
			File bfile =new File("report\\"+targetFolder+"\\"+testName.split("\\.")[testName.split("\\.").length-1]+".html");

			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024];

			int length;
			//copy the file content in bytes 
			while ((length = inStream.read(buffer)) > 0){
				outStream.write(buffer, 0, length);
			}

			inStream.close();
			outStream.close();

			System.out.println("File is copied successful!");

		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void MapTestResult(){}
}
