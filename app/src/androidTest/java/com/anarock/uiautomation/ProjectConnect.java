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

public class ProjectConnect {
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
    public void projectConnect() throws UiObjectNotFoundException {

        //Test case to search for a project and click on call
        device.findObject(By.text("MORE")).click();
        device.wait(Until.findObject(By.text("PROJECT CONNECT")), 1000);
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_builder_connect")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "search_project")).click();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "input_search")).setText("Lodha Belmondo");
        device.wait(Until.findObject(By.text("Lodha Belmondo")), 10000);
        device.wait(Until.findObject(By.text("Gahunje, Pune")), 10000);
        device.findObject(By.text("Gahunje, Pune")).click();
        //code to disconnect call needs to be written
        device.wait(Until.findObject(By.text("ARPIT SANGHVI")), 10000);
        device.findObject(By.text("ARPIT SANGHVI")).click();

    }
}
