package com.az.Utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H2;
import com.hp.gagawa.java.elements.Head;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Tbody;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Thead;
import com.hp.gagawa.java.elements.Title;
import com.hp.gagawa.java.elements.Tr;

public class CreateReportHtml {

	public void createReportSummary(String suite, String totalTime,ArrayList<String> testCaseList,HashMap<String, ArrayList<String>> testcaseDetails){
		
		System.out.println("report file creation started");
		
		File file=new File("report\\"+suite+".html");
		Html html=new Html();

		try {
			// if file doesn't exists, then create it
			if (file.exists()) {
				file.delete();					
			}
			else{			
				file.createNewFile();			
			}			

			Head head=new Head();

			Title title=new Title();
			title.appendText(suite+"-Report");

			head.appendChild(title);
			html.appendChild(head);

			Body body=new Body();

			H2 heading=new H2();
			heading.setAlign("center");
			heading.appendText(suite+" (Total time : "+totalTime+" )");

			Table table=new Table();
			table.setBorder("2");
			table.setWidth("100%");
			Thead tableHead=new Thead();
			tableHead.setStyle("font-weight:bold; background-color: #306EFF; color: #FEFCFF");
			tableHead.setAlign("center");
			Td testNameHeader=new Td();
			Td passedTestCountHeader=new Td();
			Td failedTestCountHeader=new Td();
			Td skipTestCountHeader=new Td();
			Td timeTakenHeader=new Td();
			testNameHeader.appendText("Test Name");
			passedTestCountHeader.appendText("Tests passed");
			failedTestCountHeader.appendText("Tests failed");
			skipTestCountHeader.appendText("Tests skipped");
			timeTakenHeader.appendText("Time taken");
			tableHead.appendChild(testNameHeader);
			tableHead.appendChild(passedTestCountHeader);
			tableHead.appendChild(failedTestCountHeader);
			tableHead.appendChild(skipTestCountHeader);
			tableHead.appendChild(timeTakenHeader);
			table.appendChild(tableHead);

			Tbody tableBody=new Tbody();
			tableBody.setStyle("background-color: #79BAEC");
			tableBody.setAlign("center");
			for(String test:testCaseList){
				Tr tableBodyRow=new Tr();
				Td testNameCol=new Td();
				Td passedTestCountCol=new Td();
				Td failedTestCountCol=new Td();
				Td skipTestCountCol=new Td();
				Td timeTakenCol=new Td();
				A aLink=new A();
				
				aLink.setHref("tests\\"+test+".html");
				aLink.appendText(test);
				testNameCol.appendChild(aLink);
				passedTestCountCol.setStyle("background-color: #4CC417");
				failedTestCountCol.setStyle("background-color: #E41B17");
				skipTestCountCol.setStyle("background-color: #FFD801");
				passedTestCountCol.appendText(testcaseDetails.get(test).get(1));
				failedTestCountCol.appendText(testcaseDetails.get(test).get(2));
				skipTestCountCol.appendText(testcaseDetails.get(test).get(3));
				timeTakenCol.appendText(testcaseDetails.get(test).get(0));
				tableBodyRow.appendChild(testNameCol);
				tableBodyRow.appendChild(passedTestCountCol);
				tableBodyRow.appendChild(failedTestCountCol);
				tableBodyRow.appendChild(skipTestCountCol);
				tableBodyRow.appendChild(timeTakenCol);
				tableBody.appendChild(tableBodyRow);
			}
			table.appendChild(tableBody);

			body.appendChild(heading);
			body.appendChild(table);

			html.appendChild(body);			

			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(html.write());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
