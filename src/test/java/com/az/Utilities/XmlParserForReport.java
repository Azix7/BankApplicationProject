package com.az.Utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XmlParserForReport {

	@SuppressWarnings({ })
	public double getTotalTime(){

		SAXBuilder builder = new SAXBuilder();
		double time = 0;
		File xmlFile = new File("target\\surefire-reports\\Surefire suite\\Surefire test.xml");

		try {
			Document document = (Document) builder.build(xmlFile);

			//Get root node from XML Document
			Element rootNode = document.getRootElement();

			time=Double.parseDouble(rootNode.getAttributeValue("time"));

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
		return time;
	}

	@SuppressWarnings({ })
	public String getTotalNumberOfPassedTests(){

		SAXBuilder builder = new SAXBuilder();
		int numberOfTests = 0;
		String numberOfPassedTests=null;
		File xmlFile = new File("target\\surefire-reports\\Surefire suite\\Surefire test.xml");

		try {
			Document document = (Document) builder.build(xmlFile);

			//Get root node from XML Document
			Element rootNode = document.getRootElement();

			numberOfTests=Integer.parseInt(rootNode.getAttributeValue("tests"));
			numberOfPassedTests=Integer.toString(numberOfTests-(Integer.parseInt(getTotalNumberOfTestsFailed())+Integer.parseInt(getTotalNumberOfTestsSkipped())));

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
		return numberOfPassedTests;
	}

	@SuppressWarnings({ })
	public String getTotalNumberOfTestsFailed(){

		SAXBuilder builder = new SAXBuilder();
		String numberOfFailedTests = null;
		File xmlFile = new File("target\\surefire-reports\\Surefire suite\\Surefire test.xml");

		try {
			Document document = (Document) builder.build(xmlFile);

			//Get root node from XML Document
			Element rootNode = document.getRootElement();

			numberOfFailedTests=rootNode.getAttributeValue("failures");

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
		return numberOfFailedTests;
	}

	@SuppressWarnings({ "unchecked" })
	public String getTotalNumberOfTestsSkipped(){

		SAXBuilder builder = new SAXBuilder();
		int numberOfSkippedTests = 0;
		File xmlFile = new File("target\\surefire-reports\\Surefire suite\\Surefire test.xml");

		try {
			Document document = (Document) builder.build(xmlFile);

			//Get root node from XML Document
			Element rootNode = document.getRootElement();

			List<Element> childTestCaseNode=rootNode.getChildren("testcase");

			for(Element child:childTestCaseNode){
				if(child.getChild("skipped")!=null){
					numberOfSkippedTests++;
				}
			}

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
		return Integer.toString(numberOfSkippedTests);
	}
	
	public static void main(String []a){
		XmlParserForReport x=new XmlParserForReport();
		System.out.println(x.getTotalTime());
		System.out.println(x.getTotalNumberOfPassedTests());
		System.out.println(x.getTotalNumberOfTestsFailed());
		System.out.println(x.getTotalNumberOfTestsSkipped());
	}
}