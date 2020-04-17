package com.slack.api.automation.utilities;


import org.apache.commons.lang3.RandomStringUtils;

public class RandomGenerator {
    public static String generateRandomString(int size) {
        return RandomStringUtils.randomAlphabetic(size).toLowerCase();
    }
}
