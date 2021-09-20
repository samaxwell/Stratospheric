package com.sean;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.CfnOutput;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.codecommit.Repository;

public class CodeCommitApp {

    public static void main(String[] args) {
        App app = new App();

        String accountId = StackUtils.getCommandLineArg(app, "accountId", true);
        String region = StackUtils.getCommandLineArg(app, "region", true);
        String applicationName = StackUtils.getCommandLineArg(app, "applicationName", true);

        System.out.printf("Found --> applicationName: %s, region: %s, accountId: %s", applicationName, region, accountId);
        String stackName = StackUtils.getStackName(applicationName, "CodeCommit");

        Stack stack = new Stack(app, applicationName, StackProps.builder()
                .stackName(stackName)
                .env(StackUtils.makeEnv(accountId, region))
                .build() );

        final Repository repo = Repository.Builder.create(stack, applicationName+"-codecommit")
                .repositoryName(applicationName)
                .build();

        CfnOutput repoUrl = CfnOutput.Builder.create(stack, applicationName+"-codecommit-http-url")
                .value(repo.getRepositoryCloneUrlHttp())
                .build();

        app.synth();
    }
}
