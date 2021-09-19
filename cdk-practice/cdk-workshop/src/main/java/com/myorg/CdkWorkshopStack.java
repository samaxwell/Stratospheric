package com.myorg;

import software.amazon.awscdk.core.*;
import software.amazon.awscdk.services.apigateway.LambdaRestApi;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.sns.Topic;
import software.amazon.awscdk.services.sns.subscriptions.SqsSubscription;
import software.amazon.awscdk.services.sqs.Queue;

public class CdkWorkshopStack extends Stack {

    public final CfnOutput hcEndpoint;

    public CdkWorkshopStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public CdkWorkshopStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);

        // define a new lambda resource
        final Function hello = Function.Builder.create(this, "HelloHandler")
                .runtime(Runtime.NODEJS_14_X)
                .code(Code.fromAsset("lambda"))
                .handler("hello.handler")
                .build();

        final HitCounter helloWithCounter = new HitCounter(this, "HelloHitCounter", HitCounterProps.builder()
                .downstream(hello)
                .build());

        final LambdaRestApi gateway = LambdaRestApi.Builder.create(this, "Endpoint")
                .handler(helloWithCounter.getHandler())
                .build();

        hcEndpoint = CfnOutput.Builder.create(this, "GatewayUrl")
                .value(gateway.getUrl())
                .build();
    }
}
