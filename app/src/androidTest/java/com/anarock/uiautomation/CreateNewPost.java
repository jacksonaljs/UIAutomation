package com.anarock.uiautomation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateNewPost {
    private  UiDevice device;
    public int matchCount;

    @Before
    public void setup() {
        device = Utils.beforeClass();
    }

    @Test
    public void test1PostRentPropertyMatchCount() throws UiObjectNotFoundException {

        //Test to create Rental property
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_create_post")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_rental_prop")).click();
        device.findObject(By.text("Search building name")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "input_search")).setText("West Wind");
        device.wait(Until.findObject(By.text("West Wind Park")), 10000);
        device.findObject(By.text("West Wind Park")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_configuration")).click();
        device.findObject(By.text("1 BHK")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_pricing")).click();
        device.findObject(By.text("2")).click();
        device.findObject(By.text("5")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("Done")).click();
        device.wait(Until.findObject(By.text("No")), 2000);
        device.findObject(By.text("No")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_furnishing")).click();
        device.findObject(By.text("Fully-Furnished")).click();
        UiScrollable postView = new UiScrollable(new UiSelector().scrollable(true));
        UiSelector postSelector = new UiSelector().text("POST");
        postView.scrollIntoView(postSelector);
        device.findObject(postSelector).click();
        device.wait(Until.findObject(By.text("POST").enabled(true)), 10000);

        //Test to check match count
        String matchCountString = device.findObject(By.textContains("matches found")).getText().split(" ")[0];
        int matchCount = matchCountString.equalsIgnoreCase("no") ? 0 : Integer.parseInt(matchCountString);
        device.findObject(By.text("POST")).click();
        device.wait(Until.findObject(By.text("MATCHES")), 10000);
        if (matchCount==0)
        {
            UiObject2 noMatchFound = device.findObject(By.text("NO MATCHES FOUND"));
            Assert.assertNotNull(noMatchFound);
        }
        else {
            String matches = device.findObject(By.textContains("MATCHES")).getText().split(" ")[0];
            int finalMatches = matches.equalsIgnoreCase("no") ? 0 : Integer.valueOf(matches);
            Assert.assertEquals("Match Count", matchCount, finalMatches);
        }
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_close")).click();

        Utils.markPostExpired(device);
    }

    @Test
    public void test2PostRentBrokerCount() throws UiObjectNotFoundException {

        //Test to create Rental property
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_create_post")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_rental_prop")).click();
        device.findObject(By.text("Search building name")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "input_search")).setText("West Wind");
        device.wait(Until.findObject(By.text("West Wind Park")), 10000);
        device.findObject(By.text("West Wind Park")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_configuration")).click();
        device.findObject(By.text("1 BHK")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_pricing")).click();
        device.findObject(By.text("2")).click();
        device.findObject(By.text("5")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("Done")).click();
        device.wait(Until.findObject(By.text("No")), 2000);
        device.findObject(By.text("No")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_furnishing")).click();
        device.findObject(By.text("Fully-Furnished")).click();
        UiScrollable postView = new UiScrollable(new UiSelector().scrollable(true));
        UiSelector postSelector = new UiSelector().text("POST");
        postView.scrollIntoView(postSelector);
        device.findObject(postSelector).click();
        device.wait(Until.findObject(By.text("POST").enabled(true)), 10000);

        //Test to check broker count
        String matchCountString = device.findObject(By.textContains("matches found")).getText().split(" ")[0];
        int matchCount = matchCountString.equalsIgnoreCase("no") ? 0 : Integer.parseInt(matchCountString);
        UiObject2 subTitle = device.findObject(By.textContains("brokers will be immediately"));
        int brokerCount = subTitle == null ? 0 : Integer.parseInt(subTitle.getText().split(" ")[0]);

        device.findObject(By.text("POST")).click();
        device.wait(Until.findObject(By.text("MATCHES")), 30000);
        if (matchCount==0)
        {
            UiObject2 noMatchFound = device.findObject(By.text("NO MATCHES FOUND"));
            Assert.assertNotNull(noMatchFound);
        }
        else {
            device.wait(Until.findObject(By.text("MATCHES")), 30000);
            UiObject finalBrokerCount = device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "list_matches"));
            int childBrokerCount = finalBrokerCount.getChildCount()-1;
            Assert.assertEquals("Broker Count", brokerCount, childBrokerCount);
            device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_close")).click();
        }

        Utils.markPostExpired(device);
    }


        @Test
        public void test3PostResalePropertyMatchCount() throws UiObjectNotFoundException, InterruptedException {
            //Test to create Resale property
            device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_create_post")).click();
            device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_resale_prop")).click();
            device.findObject(By.text("Search building name")).click();
            device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "input_search")).setText("West Wind");
            device.wait(Until.findObject(By.text("West Wind Park")), 10000);
            device.findObject(By.text("West Wind Park")).click();
            device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_configuration")).click();
            device.findObject(By.text("3 BHK")).click();
            device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_pricing")).click();
            device.findObject(By.text("7")).click();
            device.findObject(By.text("5")).click();
            device.findObject(By.text("0")).click();
            device.findObject(By.text("0")).click();
            device.findObject(By.text("0")).click();
            device.findObject(By.text("0")).click();
            device.findObject(By.text("0")).click();
            device.findObject(By.text("Done")).click();
            device.wait(Until.findObject(By.text("sq ft")), 2000);
            device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_carpet_area")
                    .childSelector(new UiSelector().className(Utils.EDIT_TEXT_CLASS))).setText("1200");
            device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_floor")).click();
            device.findObject(By.text("Higher")).click();
            device.findObject(By.text("4")).click();
            UiScrollable postView = new UiScrollable(new UiSelector().scrollable(true));
            UiSelector postSelector = new UiSelector().text("POST");
            postView.scrollIntoView(postSelector);
            device.findObject(postSelector).click();
            device.wait(Until.findObject(By.text("POST").enabled(true)), 10000);

//Test to check match count
            String matchCountString = device.findObject(By.textContains("matches found")).getText().split(" ")[0];
            int matchCount = matchCountString.equalsIgnoreCase("no") ? 0 : Integer.parseInt(matchCountString);
            device.findObject(By.text("POST")).click();
            device.wait(Until.findObject(By.text("MATCHES")), 10000);
            if (matchCount==0)
            {
                UiObject2 noMatchFound = device.findObject(By.text("NO MATCHES FOUND"));
                Assert.assertNotNull(noMatchFound);
            }
            else {
                String matches = device.findObject(By.textContains("MATCHES")).getText().split(" ")[0];
                int finalMatches = matches.equalsIgnoreCase("no") ? 0 : Integer.valueOf(matches);
                Assert.assertEquals("Match Count", matchCount, finalMatches);
            }
            device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_close")).click();

            Utils.markPostExpired(device);
    }

    @Test
    public void test4PostResaleBrokerCount() throws UiObjectNotFoundException, InterruptedException {
        //Test to create Resale property
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_create_post")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_resale_prop")).click();
        device.findObject(By.text("Search building name")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "input_search")).setText("West Wind");
        device.wait(Until.findObject(By.text("West Wind Park")), 10000);
        device.findObject(By.text("West Wind Park")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_configuration")).click();
        device.findObject(By.text("3 BHK")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_pricing")).click();
        device.findObject(By.text("7")).click();
        device.findObject(By.text("5")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("Done")).click();
        device.wait(Until.findObject(By.text("sq ft")), 2000);
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_carpet_area")
                .childSelector(new UiSelector().className(Utils.EDIT_TEXT_CLASS))).setText("1200");
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_floor")).click();
        device.findObject(By.text("Higher")).click();
        device.wait(Until.findObject(By.text("4")), 2000);
        device.findObject(By.text("4")).click();
        UiScrollable postView = new UiScrollable(new UiSelector().scrollable(true));
        UiSelector postSelector = new UiSelector().text("POST");
        postView.scrollIntoView(postSelector);
        device.findObject(postSelector).click();
        device.wait(Until.findObject(By.text("POST").enabled(true)), 10000);

//Test to check broker count
        String matchCountString = device.findObject(By.textContains("matches found")).getText().split(" ")[0];
        int matchCount = matchCountString.equalsIgnoreCase("no") ? 0 : Integer.parseInt(matchCountString);
        UiObject2 subTitle = device.findObject(By.textContains("brokers will be immediately"));
        int brokerCount = subTitle == null ? 0 : Integer.parseInt(subTitle.getText().split(" ")[0]);

        device.findObject(By.text("POST")).click();
        device.wait(Until.findObject(By.text("MATCHES")), 30000);
        if (matchCount==0)
        {
            UiObject2 noMatchFound = device.findObject(By.text("NO MATCHES FOUND"));
            Assert.assertNotNull(noMatchFound);
        }
        else {
            device.wait(Until.findObject(By.textContains("MATCHES")), 30000);
            UiObject finalBrokerCount = device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "list_matches"));
            Assert.assertTrue("Broker count found was " + brokerCount + " And final broker count found is 0", finalBrokerCount.exists());
            int childBrokerCount = finalBrokerCount.getChildCount()-1;
            Assert.assertEquals("Broker Count", brokerCount, childBrokerCount);
            device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_close")).click();
        }

        Utils.markPostExpired(device);
    }
}
