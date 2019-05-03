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

@RunWith(AndroidJUnit4.class)

public class Matches {
    private  UiDevice device;

    @Before
    public void setup() throws UiObjectNotFoundException {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.dialectic.brokernetworkapp",
                "com.anarock.brokernetworkapp.ui.SplashActivity"));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        device.wait(Until.hasObject(By.pkg("com.dialectic.brokernetworks").depth(0)),
                2000);
    }

    @Test
    public void rentPropertyMatch() throws UiObjectNotFoundException {

        //Test to check match with rental client
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
        Assert.assertEquals("Test Case Failed...Match should have had been found with", "vishal karkera", postMatchName.toLowerCase());
    }
}
