package com.sean;

import com.sean.models.network.Network;
import com.sean.models.network.NetworkInputParameters;
import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;

public class NetworkApp {

    public static void main(final String[] args) {
        App app = new App();

        String environmentName = StackUtils.getCommandLineArg(app, "environmentName", true);
        String accountId = StackUtils.getCommandLineArg(app, "accountId", true);
        String region = StackUtils.getCommandLineArg(app, "region", true);
        String sslCertificateArn = StackUtils.getCommandLineArg(app, "region", false);

        System.out.printf("Found --> region: %s, accountId: %s, sslCertificateArn", region, accountId, sslCertificateArn);

        Environment awsEnvironment = StackUtils.makeEnv(accountId, region);

        Stack networkStack = new Stack(
                app,
                "NetworkStack",
                StackProps.builder()
                        .stackName(environmentName + "-Network")
                        .env(awsEnvironment)
                        .build());

        Network network = new Network(
                networkStack,
                "Network",
                awsEnvironment,
                environmentName,
                new NetworkInputParameters());
//                new NetworkInputParameters(sslCertificateArn));

        app.synth();
    }

}
