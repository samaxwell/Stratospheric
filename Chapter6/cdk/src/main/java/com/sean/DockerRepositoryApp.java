package com.sean;

import com.sean.models.docker.DockerRepository;
import com.sean.models.docker.DockerRepositoryInputParameters;
import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;

public class DockerRepositoryApp {

    public static void main(final String[] args) {
        App app = new App();

        String accountId = StackUtils.getCommandLineArg(app, "accountId", true);
        String region = StackUtils.getCommandLineArg(app, "region", true);
        String applicationName = StackUtils.getCommandLineArg(app, "applicationName", true);

        System.out.printf("Found --> applicationName: %s, region: %s, accountId: %s", applicationName, region, accountId);

        Environment awsEnvironment = StackUtils.makeEnv(accountId, region);

        Stack dockerRepositoryStack = new Stack( app, "DockerRepository", StackProps.builder()
                        .stackName(applicationName + "-DockerRepository")
                        .env(awsEnvironment)
                        .build() );

        DockerRepository dockerRepository = new DockerRepository(
                dockerRepositoryStack,
                "DockerRepository",
                awsEnvironment,
                new DockerRepositoryInputParameters(applicationName, accountId, 10, false));

        app.synth();
    }

}
