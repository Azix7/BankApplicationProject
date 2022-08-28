package com.az.Test;

import static com.az.Utilities.YamlReader.getYamlValue;

import com.az.Utilities.PropFileHandler;

public class BaseTest {
	
	//get base url from TestData.yaml file
	protected String baseUrl = getYamlValue("url");
	
	//get user name of all users from filename.properties file and TestData.Yaml file.
	protected String User = getYamlValue("loginInfo.username");
	
	//get database name in login page
	protected String societe = getYamlValue("loginInfo.societe");
	
	//get password of all users from TestData.Yaml file.
	protected String password = getYamlValue("loginInfo.password");
	

		
}
