package com.anarock.uiautomation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.widget.LinearLayout;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Assert;

public class Utils {
    public static final String PACKAGE_NAME = "com.dialectic.brokernetworkapp";
    public static final String PACKAGE_NAME_PREFIX = PACKAGE_NAME + ":id/";
    public static final String EDIT_TEXT_CLASS = "android.widget.EditText";

    public static String getRegexForName(String string) {
        String regex = "";

        String[] characters = string.trim().split("");
        for (String character : characters) {
            if (!character.isEmpty()) {
                regex += "[" + character.toUpperCase() + character.toLowerCase() + "]";
            }
        }

        return regex + ".*";
    }

    public static UiObject getFirstPostName(UiDevice device) throws UiObjectNotFoundException {
        return getFirstPostCard(device)
                .getChild(new UiSelector().resourceId(PACKAGE_NAME_PREFIX + "user_container")
                        .childSelector(new UiSelector().resourceId(PACKAGE_NAME_PREFIX + "lbl_name")));
    }

    public static UiObject getFirstPostMenuButton(UiDevice device) throws UiObjectNotFoundException {
        return getFirstPostCard(device)
                .getChild((new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "list_posts"))
                        .childSelector(new UiSelector().className(LinearLayout.class))
                        .childSelector(new UiSelector().index(0))
                        .childSelector(new UiSelector().resourceId(Utils.PACKAGE_NAME_PREFIX + "btn_menu")));
    }

    public static UiObject getFirstPostCard(UiDevice device) throws UiObjectNotFoundException {
        int indexOfFirstPost = 1;

        UiObject uiObject;
        while (!(uiObject = device.findObject(new UiSelector().resourceId(PACKAGE_NAME_PREFIX + "list_matches")
                .childSelector(new UiSelector().index(indexOfFirstPost).className(LinearLayout.class))))
                .getChild(new UiSelector().resourceId(PACKAGE_NAME_PREFIX + "user_container")
                        .childSelector(new UiSelector().resourceId(PACKAGE_NAME_PREFIX + "lbl_name"))).exists() && indexOfFirstPost < 10) {
            indexOfFirstPost++;
        }
        return uiObject;
    }

    public static void markPostExpired(UiDevice device) throws UiObjectNotFoundException {
        device.wait(Until.findObject(By.text("MY POSTS").clickable(true)),5000);
        device.findObject(By.text("MY POSTS")).click();
        device.wait(Until.findObject(By.res(PACKAGE_NAME_PREFIX + "btn_archived")), 2000);
        device.findObject(new UiSelector().resourceId(PACKAGE_NAME_PREFIX + "list_posts")
                .childSelector(new UiSelector().index(0).className(LinearLayout.class))
                .childSelector(new UiSelector().resourceId(PACKAGE_NAME_PREFIX + "post_holder"))
                .childSelector(new UiSelector().index(1).className(LinearLayout.class))).click();
        device.findObject(new UiSelector().resourceId(PACKAGE_NAME_PREFIX + "btn_menu")).click();
        device.findObject(By.text("Delete")).click();
        device.wait(Until.findObject(By.text("Create new post")), 2000);
        UiObject2 noPosts = device.findObject(By.text("Create new post"));
        Assert.assertNotNull("No post should have had been found",noPosts);
    }

    public static UiDevice beforeClass() {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.dialectic.brokernetworkapp",
                "com.anarock.brokernetworkapp.ui.SplashActivity"));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        device.wait(Until.hasObject(By.pkg("com.dialectic.brokernetworks").depth(0)),
                2000);
        return device;
    }
}
