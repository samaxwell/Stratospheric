package com.sean;

import software.amazon.awscdk.core.Environment;

public class StackUtils {

    static Environment makeEnv(String accountId, String region) {
        return Environment.builder()
                .account(accountId)
                .region(region)
                .build();
    }
}
