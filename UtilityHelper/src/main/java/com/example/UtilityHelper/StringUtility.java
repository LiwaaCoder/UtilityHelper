package com.example.UtilityHelper;

import java.util.Random;

public class StringUtility {

    // Check if a string is empty or null
    public static boolean isEmptyOrNull(String str) {
        return str == null || str.isEmpty();
    }

    // Trim leading and trailing whitespace from a string
    public static String trimWhitespace(String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }

    public static String capitalizeFirstLetter(String str) {
        if (isEmptyOrNull(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // Convert a string to title case (capitalize the first letter of each word)
    public static String toTitleCase(String str) {
        if (isEmptyOrNull(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        boolean capitalizeNext = true;
        for (char c : str.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                c = Character.toUpperCase(c);
                capitalizeNext = false;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String generateRandomString(int length) {
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
