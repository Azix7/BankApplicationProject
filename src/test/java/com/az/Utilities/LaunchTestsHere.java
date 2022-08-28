package com.az.Utilities;

import java.io.IOException;

import org.testng.annotations.Test;

import com.az.Utilities.TestRunner;

public class LaunchTestsHere extends TestRunner{

	@Test
	public void startTestHere() throws IOException, InterruptedException{
	initiateTest(yaml.getYamlValue("SUITE_NAME"));
	}
}
