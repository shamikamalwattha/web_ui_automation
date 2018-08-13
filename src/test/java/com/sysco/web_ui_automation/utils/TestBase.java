package com.sysco.web_ui_automation.utils;

import com.sysco.web_ui_automation.pages.HomePage;
import com.sysco.web_ui_automation.pages.PageBase;
import com.syscolab.qe.core.reporting.SyscoLabListener;
import com.syscolab.qe.core.reporting.SyscoLabQCenter;
import com.syscolab.qe.core.reporting.SyscoLabReporting;
import com.sysco.web_ui_automation.common.Constants;
import com.syscolab.qe.core.ui.SyscoLabCapabilityUtil;
import com.syscolab.qe.core.ui.SyscoLabUI;
import com.syscolab.qe.core.ui.web.SyscoLabWUI;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.sysco.web_ui_automation.common.Constants.APP_URL;

@Listeners(SyscoLabListener.class)
public class TestBase {
    private SyscoLabListener testListeners;
    private SyscoLabQCenter syscoLabQCenter;
    private SyscoLabWUI syscoLabWUI;

    @BeforeClass
    public void init() {
        testListeners = new SyscoLabListener();
        syscoLabQCenter = new SyscoLabQCenter();
        DriverSetUpUtil.setToRunLocally();
        /*ChromeOptions options = new ChromeOptions();
        System.setProperty("hub.url", APP_URL);
        DesiredCapabilities capabilities1 = SyscoLabCapabilityUtil.getPCCapabilities("", "");
        capabilities1.setBrowserName(BrowserType.CHROME);
        capabilities1.setCapability(ChromeOptions.CAPABILITY, options);*/
        syscoLabWUI = new SyscoLabWUI(null);
        syscoLabWUI.navigateTo(APP_URL);
        Dimension d = new Dimension(1366,768); //Resize the current window to the given dimension driver.manage().window().setSize(d);
        syscoLabWUI.driver.manage().window().setSize(d);
        PageBase.setSyscoLabUI(syscoLabWUI);

    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Test Running " + this.getClass().toString());
    }

    @AfterClass(alwaysRun = true)
    public void cleanUp(ITestContext iTestContext) {


        try {
            syscoLabQCenter.setProjectName(Constants.TEST_PROJECT);
            syscoLabQCenter.setEnvironment(Constants.TEST_ENV);

            syscoLabQCenter.setRelease(Constants.TEST_RELEASE);
            syscoLabQCenter.setModule(iTestContext.getAttribute("feature").toString());
            syscoLabQCenter.setFeature(iTestContext.getAttribute("feature").toString());
            syscoLabQCenter.setClassName(iTestContext.getClass().getName());

            if (Constants.UPDATE_DASHBOARD)
                SyscoLabReporting.generateJsonFile(SyscoLabListener.getResults(), syscoLabQCenter);
            syscoLabWUI.closeDriver();
            syscoLabWUI.quit();

        } catch (Exception e) {
            syscoLabWUI.closeDriver();
            syscoLabWUI.quit();
            e.printStackTrace();
        }
    }
}
