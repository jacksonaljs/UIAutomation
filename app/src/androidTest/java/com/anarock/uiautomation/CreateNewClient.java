package com.anarock.uiautomation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

@RunWith(AndroidJUnit4.class)

public class CreateNewClient {
    private  UiDevice device;

    @Before
    public void setup() {
        device = Utils.beforeClass();
    }

    @Test
    public void postClient() throws UiObjectNotFoundException {

        //Test to create rent client requirement post
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_create_post")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_rental_client")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_name")
                .childSelector(new UiSelector().className(Utils.EDIT_TEXT_CLASS))).setText("West Wind Park Rent");
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
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "input_search")).setText("West Wind");
        device.wait(Until.findObject(By.text("West Wind Park")), 10000);
        device.findObject(By.text("West Wind Park")).click();
        clickButton(device);
        UiScrollable postView = new UiScrollable(new UiSelector().scrollable(true));
        UiSelector postSelector = new UiSelector().text("POST");
        postView.scrollIntoView(postSelector);
        device.findObject(postSelector).click();
        device.wait(Until.findObject(By.text("POST").enabled(true)), 10000);
        device.findObject(By.text("POST")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_close")).click();

        Utils.markPostExpired(device);

        //Test to create resale client requirement post
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_create_post")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_resale_client")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "txt_name")
                .childSelector(new UiSelector().className(Utils.EDIT_TEXT_CLASS))).setText("West Wind Park Resale");
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
        postView.scrollIntoView(postSelector);
        device.findObject(By.text("ADD BUILDING")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "input_search")).setText("West Wind");
        device.wait(Until.findObject(By.text("West Wind Park")), 10000);
        device.findObject(By.text("West Wind Park")).click();
        clickButton(device);
        postView.scrollIntoView(postSelector);
        device.findObject(postSelector).click();
        device.wait(Until.findObject(By.text("POST").enabled(true)), 10000);
        device.findObject(By.text("POST")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_close")).click();

        Utils.markPostExpired(device);
    }

    public static void clickButton(UiDevice device) {
        device.findObject(By.text("Done")).click();
    }
}
