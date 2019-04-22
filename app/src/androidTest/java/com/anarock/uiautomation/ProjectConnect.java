package com.anarock.uiautomation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.LinearLayout;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
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
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "input_search")).setText("Lodha");
        device.wait(Until.findObject(By.text("Lodha Belmondo")), 10000);
        device.wait(Until.findObject(By.text("Gahunje, Pune")), 10000);
        device.findObject(By.text("Gahunje, Pune")).click();
        device.wait(Until.findObject(By.text("Gahunje, Pune")), 10000);
        String matchContactName = device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "salesNumberRecyclerView")
                .childSelector(new UiSelector().index(0).className(LinearLayout.class)).childSelector(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "lbl_name"))).getText();
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "salesNumberRecyclerView")
                .childSelector(new UiSelector().index(0).className(LinearLayout.class))).click();
        device.wait(Until.findObject(By.text("Gahunje, Pune")), 30000);
        device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "img_back")).click();
//        String matchRecentlyContactedName = device.findObject(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "list_recently_contcted")
//                .childSelector(new UiSelector().index(0).className(LinearLayout.class))
//                .childSelector(new UiSelector().index(1).className(LinearLayout.class))
//                .childSelector(new UiSelector().index(0).className(TextView.class))).getText();

        UiObject matchRecentlyContactedName = device.findObject(new UiSelector().textMatches(Utils.getRegexForName(matchContactName)));
        Assert.assertNotNull(matchRecentlyContactedName);
    }

}
