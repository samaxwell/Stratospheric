package com.sean;

import com.sean.models.docker.DockerRepository;
import com.sean.models.docker.DockerRepositoryInputParameters;
import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;

import java.util.Objects;

public class DockerRepositoryApp {

    public static void main(final String[] args) {
        App app = new App();

        String accountId = (String) app
                .getNode()
                .tryGetContext("accountId");
        Objects.requireNonNull(accountId, "docker - accountId must not be null");

        String region = (String) app
                .getNode()
                .tryGetContext("region");
        Objects.requireNonNull(region, "region must not be null");

        String applicationName = (String) app
                .getNode()
                .tryGetContext("applicationName");
        Objects.requireNonNull(applicationName, "applicationName must not be null");

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
