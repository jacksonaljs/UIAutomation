package com.anarock.uiautomation;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

@RunWith(AndroidJUnit4.class)

public class Dashboard {
    private  UiDevice device;

    @Before
    public void setup() {
        device = Utils.beforeClass();
    }

    @Test
    public void rentPropertyDashboardVisibility() throws UiObjectNotFoundException {

        //Test to check rent property is visible on top of list after property is created
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_create_post")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_rental_prop")).click();
        device.findObject(By.text("Search building name")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "input_search")).setText("Icon");
        device.wait(Until.findObject(By.text("Icon Viva")), 10000);
        device.findObject(By.text("Icon Viva")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_configuration")).click();
        device.findObject(By.text("1 BHK")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_pricing")).click();
        device.findObject(By.text("2")).click();
        device.findObject(By.text("5")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("Done")).click();
        device.wait(Until.findObject(By.text("Yes")), 2000);
        device.findObject(By.text("Yes")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_furnishing")).click();
        device.findObject(By.text("Fully-Furnished")).click();
        dashboardRefresh();
        device.wait(Until.findObject(By.text("POST").enabled(true)), 10000);
        String matchNameString = device.findObject(By.textContains("matches")).getText().split(" ")[0];
        device.findObject(By.text("POST")).click();
        device.wait(Until.findObject(By.text("RENTAL PROPERTY")), 9000);
        Assert.assertNotEquals("Test Case Failed...No Match found", "no", matchNameString.toLowerCase());
        String postMatchName = device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_name")).getText();
        Assert.assertEquals("Test Case Failed...Match should have had been found with", "dialectic test", postMatchName.toLowerCase());
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_close")).click();
        device.findObject(By.text("HOME")).click();


        Utils.getFirstPostName(device).getText();
        Assert.assertEquals("Test Case Failed...Match should have had been found with", "dialectic test", postMatchName.toLowerCase());

        Utils.markPostExpired(device);
    }

    @Test
    public void rentPropertyNotRelevantMatch() throws UiObjectNotFoundException {

        //Test to check rent property is visible on top of list after property is created
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_create_post")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_rental_prop")).click();
        device.findObject(By.text("Search building name")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "input_search")).setText("Icon");
        device.wait(Until.findObject(By.text("Icon Viva")), 10000);
        device.findObject(By.text("Icon Viva")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_configuration")).click();
        device.findObject(By.text("1 BHK")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_pricing")).click();
        device.findObject(By.text("2")).click();
        device.findObject(By.text("5")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("Done")).click();
        device.wait(Until.findObject(By.text("Yes")), 2000);
        device.findObject(By.text("Yes")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_furnishing")).click();
        device.findObject(By.text("Fully-Furnished")).click();
        dashboardRefresh();
        device.wait(Until.findObject(By.text("POST").enabled(true)), 10000);
        String matchNameString = device.findObject(By.textContains("matches")).getText().split(" ")[0];
        device.findObject(By.text("POST")).click();
        device.wait(Until.findObject(By.text("RENTAL PROPERTY")), 9000);
        Assert.assertNotEquals("Test Case Failed...No Match found", "no", matchNameString.toLowerCase());
        String postMatchName = device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_name")).getText();
        Assert.assertEquals("Test Case Failed...Match should have had been found with", "dialectic test", postMatchName.toLowerCase());
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_close")).click();
        device.findObject(By.text("HOME")).click();
        Utils.getFirstPostName(device).getText();
        Assert.assertEquals("Test Case Failed...Match should have had been found with", "dialectic test", postMatchName.toLowerCase());

        //Test to mark first post as not a relevant match
        Utils.getFirstPostMenuButton(device).click();
        device.findObject(By.text("Not a relevant match")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "primary_button")).click();


        //Test to check if post is removed
        UiObject matchPostNotRelevantName = Utils.getFirstPostName(device);
        Assert.assertFalse("Test Case Failed...After marking post as Not A Relevant Match, post should not have had been found with Dialectic Test", matchPostNotRelevantName.exists());

        //Test to mark post as expired
        device.wait(Until.findObject(By.text("MY POSTS")), 9000);




        //CODE NOT WORKING



        Utils.markPostExpired(device);
    }

    @Test @Ignore
    public void rentPropertyAlreadyContacted() throws UiObjectNotFoundException {

        //Test to check rent property is visible on top of list after property is created
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_create_post")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_rental_prop")).click();
        device.findObject(By.text("Search building name")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "input_search")).setText("Icon");
        device.wait(Until.findObject(By.text("Icon Viva")), 10000);
        device.findObject(By.text("Icon Viva")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_configuration")).click();
        device.findObject(By.text("1 BHK")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_pricing")).click();
        device.findObject(By.text("2")).click();
        device.findObject(By.text("5")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("Done")).click();
        device.wait(Until.findObject(By.text("Yes")), 2000);
        device.findObject(By.text("Yes")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_furnishing")).click();
        device.findObject(By.text("Fully-Furnished")).click();
        dashboardRefresh();
        device.wait(Until.findObject(By.text("POST").enabled(true)), 10000);
        String matchNameString = device.findObject(By.textContains("matches")).getText().split(" ")[0];
        device.findObject(By.text("POST")).click();
        device.wait(Until.findObject(By.text("RENTAL PROPERTY")), 9000);
        Assert.assertNotEquals("Test Case Failed...No Match found", "no", matchNameString.toLowerCase());
        String postMatchName = device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_name")).getText();
        Assert.assertEquals("Test Case Failed...Match should have had been found with", "dialectic test", postMatchName.toLowerCase());
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_close")).click();
        device.findObject(By.text("HOME")).click();

        String matchPostContactName = Utils.getFirstPostName(device).getText();
        Assert.assertEquals("Test Case Failed...Match should have had been found with", "dialectic test", postMatchName.toLowerCase());

        //Test to mark first post as already contacted and check
        Utils.getFirstPostMenuButton(device).click();
        device.findObject(By.text("Already contacted")).click();
        Assert.assertEquals("Test Case Failed...Name for how was your call should have had been found with", "dialectic test", postMatchName.toLowerCase());
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "good_feedback")).click();


        //Scroll to older posts
//        BySelector olderPostsHeaderSelector = By.textStartsWith("OLDER");
//        UiObject2 olderPostsHeader;
//        while ((olderPostsHeader = device.findObject(olderPostsHeaderSelector)) == null) {
//            device.swipe(device.getDisplayWidth() / 2, device.getDisplayHeight() * 3 / 4,
//                    device.getDisplayWidth() / 2, 0, 100);
//        }
//
//        UiObject2 toolbar = device.findObject(By.res(Utils.PACKAGE_NAME_PREFIX + "toolbar"));
//        while (olderPostsHeader.getVisibleBounds().top > toolbar.getVisibleBounds().bottom) {
//            device.swipe(device.getDisplayWidth() / 2, device.getDisplayHeight() * 3 / 4,
//                    device.getDisplayWidth() / 2, (device.getDisplayHeight() * 3 / 4) - 100, 100);
//        }

        //Test to mark post as expired
        Utils.markPostExpired(device);
    }

    @Test
    public void rentPropertyMarkAsExpired() throws UiObjectNotFoundException {

        //Test to check rent property is visible on top of list after property is created
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_create_post")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_rental_prop")).click();
        device.findObject(By.text("Search building name")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "input_search")).setText("Icon");
        device.wait(Until.findObject(By.text("Icon Viva")), 10000);
        device.findObject(By.text("Icon Viva")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_configuration")).click();
        device.findObject(By.text("1 BHK")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_pricing")).click();
        device.findObject(By.text("2")).click();
        device.findObject(By.text("5")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("Done")).click();
        device.wait(Until.findObject(By.text("Yes")), 2000);
        device.findObject(By.text("Yes")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_furnishing")).click();
        device.findObject(By.text("Fully-Furnished")).click();
        dashboardRefresh();
        device.wait(Until.findObject(By.text("POST").enabled(true)), 10000);
        String matchNameString = device.findObject(By.textContains("matches")).getText().split(" ")[0];
        device.findObject(By.text("POST")).click();
        device.wait(Until.findObject(By.text("RENTAL PROPERTY")), 9000);
        Assert.assertNotEquals("Test Case Failed...No Match found", "no", matchNameString.toLowerCase());
        String postMatchName = device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_name")).getText();
        Assert.assertEquals("Test Case Failed...Match should have had been found with", "dialectic test", postMatchName.toLowerCase());
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_close")).click();
        device.findObject(By.text("HOME")).click();

        UiObject firstPostMatchUserNameObject = Utils.getFirstPostName(device);
        String matchPostContactName = firstPostMatchUserNameObject.getText();
        Assert.assertEquals("Test Case Failed...Match should have had been found with", "dialectic test", postMatchName.toLowerCase());

        //Test to mark post as expired
        Utils.markPostExpired(device);

    }

    @Test
    public void bakchodiTest() throws UiObjectNotFoundException {

        //Test to check rent property is visible on top of list after property is created
        device.findObject(By.text("HOME")).click();
        int refreshDashboard = 1;
        while (refreshDashboard < 10) {
            dashboardRefresh();
            refreshDashboard++;
        }
        
    }

    public void dashboardRefresh() throws UiObjectNotFoundException {
        UiScrollable dashboard = new UiScrollable(new UiSelector().scrollable(true));
        UiSelector dashboardScroll = new UiSelector().text("POST");
        dashboard.scrollIntoView(dashboardScroll);
    }


}
