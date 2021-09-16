package com.sean.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.amazon.awscdk.core.IConstruct;
import software.amazon.awscdk.core.Tags;

@Getter
@RequiredArgsConstructor
public class ApplicationEnvironment {

    private final String applicationName;
    private final String environmentName;

    /**
     * Strips non-alphanumeric characters from a String since some AWS resources don't cope with
     * them when using them in resource names.
     */
    private String sanitize(String environmentName) {
        return environmentName.replaceAll("[^a-zA-Z0-9-]", "");
    }

    @Override
    public String toString() {
        return sanitize(environmentName + "-" + applicationName);
    }

    /**
     * Prefixes a string with the application name and environment name.
     */
    public String prefix(String string) {
        return this.toString() + "-" + string;
    }

    /**
     * Prefixes a string with the application name and environment name. Returns only the last <code>characterLimit</code>
     * characters from the name.
     */
    public String prefix(String string, int characterLimit) {
        String name = this.toString() + "-" + string;
        if (name.length() <= characterLimit) {
            return name;
        }
        return name.substring(name.length() - 21);
    }

    public void tag(IConstruct construct) {
        Tags.of(construct).add("environment", environmentName);
        Tags.of(construct).add("application", applicationName);
    }
}
