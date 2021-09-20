package com.sean;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;

import java.util.Objects;

public class StackUtils {

    static String getCommandLineArg(App app, String arg, boolean required) {
        String value = (String) app
                .getNode()
                .tryGetContext(arg);
        if (required) {
            Objects.requireNonNull(value, arg + " must not be null");
        }

        return value;
    }

    static Environment makeEnv(String accountId, String region) {
        return Environment.builder()
                .account(accountId)
                .region(region)
                .build();
    }

    /* Characters only */
    public static String getStackName(String applicationName, String stackDescription) {
        return applicationName + stackDescription;
    }
}
