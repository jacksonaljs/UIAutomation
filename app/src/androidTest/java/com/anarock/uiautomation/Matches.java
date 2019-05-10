package com.anarock.uiautomation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import static com.anarock.uiautomation.CreateNewClient.clickButton;

@RunWith(AndroidJUnit4.class)

public class Matches {
    private  UiDevice device;

    @Before
    public void setup() {
        device = Utils.beforeClass();
    }

    @Test
    public void rentPropertyMatch() throws UiObjectNotFoundException {

        //Test to check match with rental client
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
        UiScrollable postView = new UiScrollable(new UiSelector().scrollable(true));
        UiSelector postSelector = new UiSelector().text("POST");
        postView.scrollIntoView(postSelector);
        device.findObject(postSelector).click();
        device.wait(Until.findObject(By.text("POST").enabled(true)), 10000);
        String matchNameString = device.findObject(By.textContains("matches")).getText().split(" ")[0];
        device.findObject(By.text("POST")).click();
        device.wait(Until.findObject(By.text("RENTAL PROPERTY")), 9000);
        Assert.assertNotEquals("Test Case Failed...No Match found", "no", matchNameString.toLowerCase());
        String postMatchName = device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_name")).getText();
        Assert.assertEquals("Test Case Failed...Match should have had been found with", "dialectic test", postMatchName.toLowerCase());

        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_close")).click();
        Utils.markPostExpired(device);
    }

    @Test
    public void resalePropertyMatch() throws UiObjectNotFoundException {

        //Test to check match with resale client
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_create_post")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_resale_prop")).click();
        device.findObject(By.text("Search building name")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "input_search")).setText("Icon");
        device.wait(Until.findObject(By.text("Icon Linera")), 10000);
        device.findObject(By.text("Icon Linera")).click();
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
        String matchNameString = device.findObject(By.textContains("matches")).getText().split(" ")[0];
        device.findObject(By.text("POST")).click();
        device.wait(Until.findObject(By.text("RESALE PROPERTY")), 9000);
        Assert.assertNotEquals("Test Case Failed...No Match found", "no", matchNameString.toLowerCase());
        String postMatchName = device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_name")).getText();
        Assert.assertEquals("Test Case Failed...Match should have had been found with", "dialectic test", postMatchName.toLowerCase());

        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_close")).click();
        Utils.markPostExpired(device);
    }

    @Test
    public void rentClientMatch() throws UiObjectNotFoundException {

        //Test to check match with rent property
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_create_post")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_rental_client")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_name")
                .childSelector(new UiSelector().className(Utils.EDIT_TEXT_CLASS))).setText("Icon Windsor Rent");
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_configuration")).click();
        device.findObject(By.text("3 BHK")).click();
        clickButton(device);
        device.wait(Until.findObject(By.text("No")), 2000);
        device.findObject(By.text("No")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_furnishing")).click();
        device.findObject(By.text("Fully-Furnished")).click();
        clickButton(device);
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_pricing")
                .childSelector(new UiSelector().index(1))).click();
        device.findObject(By.text("2")).click();
        device.findObject(By.text("5")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        clickButton(device);
        device.wait(Until.findObject(By.text("Building Names")), 2000);
        device.findObject(By.text("ADD BUILDING")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "input_search")).setText("Icon");
        device.wait(Until.findObject(By.text("Icon Windsor Park")), 10000);
        device.findObject(By.text("Icon Windsor Park")).click();
        clickButton(device);
        UiScrollable postView = new UiScrollable(new UiSelector().scrollable(true));
        UiSelector postSelector = new UiSelector().text("POST");
        postView.scrollIntoView(postSelector);
        device.findObject(postSelector).click();
        device.wait(Until.findObject(By.text("POST").enabled(true)), 10000);
        String matchNameString = device.findObject(By.textContains("matches")).getText().split(" ")[0];
        device.findObject(By.text("POST")).click();
        device.wait(Until.findObject(By.text("RENTAL CLIENT")), 9000);
        Assert.assertNotEquals("Test Case Failed...No Match found", "no", matchNameString.toLowerCase());
        String postMatchName = device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_name")).getText();
        Assert.assertEquals("Test Case Failed...Match should have had been found with", "dialectic test", postMatchName.toLowerCase());

        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_close")).click();
        Utils.markPostExpired(device);
    }

    @Test
    public void resaleClientMatch() throws UiObjectNotFoundException {

        //Test to check match with resale property
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_create_post")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_resale_client")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_name")
                .childSelector(new UiSelector().className(Utils.EDIT_TEXT_CLASS))).setText("Icon Windsor Resale");
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_configuration")).click();
        device.findObject(By.text("2 BHK")).click();
        clickButton(device);
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_pricing")
                .childSelector(new UiSelector().index(1))).click();
        device.findObject(By.text("7")).click();
        device.findObject(By.text("5")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        device.findObject(By.text("0")).click();
        clickButton(device);
        device.wait(Until.findObject(By.text("Min Carpet Area")), 2000);
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_carpet_area")
                .childSelector(new UiSelector().className(Utils.EDIT_TEXT_CLASS))).setText("880");
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "dd_floor")).click();
        device.findObject(By.text("Mid")).click();
        clickButton(device);
        device.wait(Until.findObject(By.text("Min Parking")), 2000);
        device.findObject(By.text("4")).click();
        UiScrollable postView = new UiScrollable(new UiSelector().scrollable(true));
        UiSelector postSelector = new UiSelector().text("POST");
        postView.scrollIntoView(postSelector);
        device.findObject(By.text("ADD BUILDING")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "input_search")).setText("Icon");
        device.wait(Until.findObject(By.text("Icon Windsor Apartments")), 10000);
        device.findObject(By.text("Icon Windsor Apartments")).click();
        clickButton(device);
        postView.scrollIntoView(postSelector);
        device.findObject(postSelector).click();
        device.wait(Until.findObject(By.text("POST").enabled(true)), 10000);
        String matchNameString = device.findObject(By.textContains("matches")).getText().split(" ")[0];
        device.findObject(By.text("POST")).click();
        device.wait(Until.findObject(By.text("RESALE CLIENT")), 9000);
        Assert.assertNotEquals("Test Case Failed...No Match found", "no", matchNameString.toLowerCase());
        String postMatchName = device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_name")).getText();
        Assert.assertEquals("Test Case Failed...Match should have had been found with", "dialectic test", postMatchName.toLowerCase());

        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_close")).click();
        Utils.markPostExpired(device);
    }
}
