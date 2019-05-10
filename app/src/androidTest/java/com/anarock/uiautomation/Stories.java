package com.anarock.uiautomation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.LinearLayout;

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

import static com.anarock.uiautomation.Utils.PACKAGE_NAME_PREFIX;

@RunWith(AndroidJUnit4.class)

public class Stories {
    private  UiDevice device;

    @Before
    public void setup() {
        device = Utils.beforeClass();
    }

    @Test
    public void storiesView() throws UiObjectNotFoundException {
        UiSelector firstStoryUiObject = new UiSelector().resourceId(PACKAGE_NAME_PREFIX + "list_matches")
                .childSelector(new UiSelector().index(0).className("android.support.v7.widget.RecyclerView"))
                .childSelector(new UiSelector().index(0).className("android.widget.LinearLayout"));

        String storyTitle = device.findObject(firstStoryUiObject.childSelector(new UiSelector().resourceId(PACKAGE_NAME_PREFIX + "caption"))).getText();

        device.findObject(firstStoryUiObject).click();
        device.wait(Until.findObject(By.text(storyTitle)), 2000);
        device.findObject(new UiSelector().resourceId(PACKAGE_NAME_PREFIX + "save_story")).click();
        device.pressBack();
        device.findObject(By.text("MORE")).click();
        device.findObject(new UiSelector().resourceId(PACKAGE_NAME_PREFIX + "lbl_projects_and_offers")).click();
        device.findObject(By.text("FAVORITES")).click();
        String favTitleMatch = device.findObject(new UiSelector().resourceId(PACKAGE_NAME_PREFIX + "recyclerView").clickable(true)
                .childSelector(new UiSelector().index(0).className("android.support.v7.widget.CardView"))
                .childSelector(new UiSelector().resourceId(PACKAGE_NAME_PREFIX + "lbl_title"))).getText();
        Assert.assertEquals("Story should have had been found with Title " +storyTitle, storyTitle, favTitleMatch);
        device.findObject(new UiSelector().resourceId(PACKAGE_NAME_PREFIX + "recyclerView").clickable(true)
                .childSelector(new UiSelector().index(0).className("android.support.v7.widget.CardView"))).click();




    }
}
