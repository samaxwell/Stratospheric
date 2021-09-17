package com.myorg;

import software.amazon.awscdk.core.*;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.s3.Bucket;


public class HelloWorldStack extends Stack {
    public HelloWorldStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public HelloWorldStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        Bucket.Builder.create(this, "MyFirstBucket")
                .versioned(Boolean.TRUE)
                .removalPolicy(RemovalPolicy.DESTROY) // Can be automatically dleted when we delete the stack
                .autoDeleteObjects(Boolean.TRUE) // remove all contained objects on delete
                .build();
    }
}
