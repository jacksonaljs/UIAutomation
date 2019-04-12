package com.anarock.uiautomation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.Until;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    private final UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    @Before
    public void setup() throws UiObjectNotFoundException {

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName(Utils.PACKAGE_NAME,
                "com.anarock.brokernetworkapp.ui.SplashActivity"));
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(Utils.PACKAGE_NAME).depth(0)),
                2000);
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
