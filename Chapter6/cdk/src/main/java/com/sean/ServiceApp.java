package com.sean;

import com.sean.models.ApplicationEnvironment;
import com.sean.models.Service;
import com.sean.models.network.Network;
import com.sean.models.network.NetworkOutputParameters;
import com.sean.models.service.DockerImageSource;
import com.sean.models.service.ServiceInputParameters;
import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;

import java.util.HashMap;
import java.util.Map;

public class ServiceApp {

    public static void main(final String[] args) {

        App app = new App();

        String environmentName = StackUtils.getCommandLineArg(app, "environmentName", true);
        String applicationName = StackUtils.getCommandLineArg(app, "applicationName", true);
        String accountId = StackUtils.getCommandLineArg(app, "accountId", true);
        String springProfile = StackUtils.getCommandLineArg(app, "springProfile", true);
        String dockerImageUrl = StackUtils.getCommandLineArg(app, "dockerImageUrl", true);
        String region = StackUtils.getCommandLineArg(app, "region", true);

        System.out.printf("Found --> environmentName: %s, applicationName: %s, region: %s, accountId: %s, springProfile: %s, dockerImageUrl: %s",
                environmentName, applicationName, region, accountId, springProfile, dockerImageUrl);

        Environment awsEnvironment = StackUtils.makeEnv(accountId, region);

        ApplicationEnvironment applicationEnvironment = new ApplicationEnvironment(
                applicationName,
                environmentName
        );

        Stack serviceStack = new Stack(app, "ServiceStack", StackProps.builder()
                .stackName(applicationEnvironment.prefix("Service"))
                .env(awsEnvironment)
                .build());

        DockerImageSource dockerImageSource = new DockerImageSource(dockerImageUrl);
        NetworkOutputParameters networkOutputParameters = Network.getOutputParametersFromParameterStore(serviceStack, applicationEnvironment.getEnvironmentName());
        ServiceInputParameters serviceInputParameters = new ServiceInputParameters(dockerImageSource, environmentVariables(springProfile))
                .withHealthCheckIntervalSeconds(30);

        Service service = new Service(
                serviceStack,
                "Service",
                awsEnvironment,
                applicationEnvironment,
                serviceInputParameters,
                networkOutputParameters);

        app.synth();
    }

    static Map<String, String> environmentVariables(String springProfile) {
        Map<String, String> vars = new HashMap<>();
        vars.put("SPRING_PROFILES_ACTIVE", springProfile);
        return vars;
    }

}
