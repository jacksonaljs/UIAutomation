package com.anarock.uiautomation;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
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

public class LoginTest {
    private  UiDevice device;

    @Before
    public void setup() {
        device = Utils.beforeClass();
    }

    @Test
    public void typeInMobileNumber() throws UiObjectNotFoundException {
        device.wait(Until.findObject(By.clazz(Utils.EDIT_TEXT_CLASS)), 10000);
        device.findObject(By.clazz(Utils.EDIT_TEXT_CLASS)).setText("9318303560");
        device.findObject(By.text("CONTINUE")).click();
        device.wait(Until.findObject(By.text("HOME")), 20000);
        Assert.assertNotNull(device.findObject(By.text("HOME")));
    }

    @After
    public void tearDown() {
        device.pressBack();
    }
}
