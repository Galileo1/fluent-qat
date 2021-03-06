package com.tpg.quality.web.sample.tests;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.testng.annotations.Test;

import com.tpg.quality.web.sample.pages.HomePage;
import com.tpg.quality.web.sample.pages.LandingPage;
import com.tpg.quality.web.sample.pages.RegisterationPage;
import com.tpg.quality.web.sample.pages.RegisteredPage;
import com.tpg.quality.web.utility.ExtentTestManager;

@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class LoginTest extends BaseTest {
	@Inject
	private LandingPage landingPageobj;
	@Inject
	private HomePage homePageobj;
	@Inject
	private RegisterationPage registerationPageobj;
	@Inject
	private RegisteredPage registeredPageobj;

	@Test(description = "Register the user ",priority=1)
	public void registerUser() {

		ExtentTestManager.getTest().setDescription("Testing the registeration workflow");
		landingPageobj.openLandingPage("http://localhost:8080/welcome");
		landingPageobj.clickRegiserLink();
		registerationPageobj.enterFirstname("qates");
		registerationPageobj.enterLastname("qatest");
		registerationPageobj.enterEmail("qatest@test.com");
		registerationPageobj.enterUsername("qauser1");
		registerationPageobj.enterPassword("qauser1");
		registerationPageobj.clickSubmit();
		registeredPageobj.verifyRegisteration();
	}


	@Test(description = "Register user with default values and login with same user ", priority=2)
	public void registerAndLoginUser() {
		ExtentTestManager.getTest().setDescription("Register a user and login with same user");
		landingPageobj.openLandingPage("http://localhost:8080/welcome");
		landingPageobj.clickRegiserLink();
		registerationPageobj.enterFirstname("qatest");
		registerationPageobj.enterLastname("qatest");
		registerationPageobj.enterEmail("qatest@test.com");
		registerationPageobj.enterUsername("qanewuser1");
		registerationPageobj.enterPassword("qanewuser1");
		registerationPageobj.clickSubmit();
		registeredPageobj.clickLoginLink();
		landingPageobj.enterUsername("qanewuser1");
		landingPageobj.enterPassword("qanewuser1");
		landingPageobj.clickLogIn();
		homePageobj.verifyHomePage();
		homePageobj.logOut();
	}



}
