package com.anarock.uiautomation;

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
}
