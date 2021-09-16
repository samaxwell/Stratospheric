package com.sean.models.service;

import lombok.Getter;
import software.amazon.awscdk.services.iam.PolicyStatement;
import software.amazon.awscdk.services.logs.RetentionDays;

import java.util.*;

@Getter
public class ServiceInputParameters {
    private final DockerImageSource dockerImageSource;
    private final Map<String, String> environmentVariables;
    private final List<String> securityGroupIdsToGrantIngressFromEcs;
    private List<PolicyStatement> taskRolePolicyStatements = new ArrayList<>();
    private int healthCheckIntervalSeconds = 15;
    private String healthCheckPath = "/";
    private int containerPort = 8080;
    private String containerProtocol = "HTTP";
    private int healthCheckTimeoutSeconds = 5;
    private int healthyThresholdCount = 2;
    private int unhealthyThresholdCount = 8;
    private RetentionDays logRetention = RetentionDays.ONE_WEEK;
    private int cpu = 256;
    private int memory = 512;
    private int desiredInstancesCount = 2;
    private int maximumInstancesPercent = 200;
    private int minimumHealthyInstancesPercent = 50;
    private boolean stickySessionsEnabled = false;
    private String awslogsDateTimeFormat = "%Y-%m-%dT%H:%M:%S.%f%z";

    /**
     * Knobs and dials you can configure to run a Docker image in an ECS service. The default values are set in a way
     * to work out of the box with a Spring Boot application.
     *
     * @param dockerImageSource                     the source from where to load the Docker image that we want to deploy.
     * @param securityGroupIdsToGrantIngressFromEcs Ids of the security groups that the ECS containers should be granted access to.
     * @param environmentVariables                  the environment variables provided to the Java runtime within the Docker containers.
     */
    public ServiceInputParameters(
            DockerImageSource dockerImageSource,
            List<String> securityGroupIdsToGrantIngressFromEcs,
            Map<String, String> environmentVariables) {
        this.dockerImageSource = dockerImageSource;
        this.environmentVariables = environmentVariables;
        this.securityGroupIdsToGrantIngressFromEcs = securityGroupIdsToGrantIngressFromEcs;
    }

    /**
     * Knobs and dials you can configure to run a Docker image in an ECS service. The default values are set in a way
     * to work out of the box with a Spring Boot application.
     *
     * @param dockerImageSource    the source from where to load the Docker image that we want to deploy.
     * @param environmentVariables the environment variables provided to the Java runtime within the Docker containers.
     */
    public ServiceInputParameters(
            DockerImageSource dockerImageSource,
            Map<String, String> environmentVariables) {
        this.dockerImageSource = dockerImageSource;
        this.environmentVariables = environmentVariables;
        this.securityGroupIdsToGrantIngressFromEcs = Collections.emptyList();
    }

    /**
     * The interval to wait between two health checks.
     * <p>
     * Default: 15.
     */
    public ServiceInputParameters withHealthCheckIntervalSeconds(int healthCheckIntervalSeconds) {
        this.healthCheckIntervalSeconds = healthCheckIntervalSeconds;
        return this;
    }

    /**
     * The path of the health check URL.
     * <p>
     * Default: "/actuator/health".
     */
    public ServiceInputParameters withHealthCheckPath(String healthCheckPath) {
        Objects.requireNonNull(healthCheckPath);
        this.healthCheckPath = healthCheckPath;
        return this;
    }

    /**
     * The port the application listens on within the container.
     * <p>
     * Default: 8080.
     */
    public ServiceInputParameters withContainerPort(int containerPort) {
        Objects.requireNonNull(containerPort);
        this.containerPort = containerPort;
        return this;
    }

    /**
     * The protocol to access the application within the container. Default: "HTTP".
     */
    public ServiceInputParameters withContainerProtocol(String containerProtocol) {
        Objects.requireNonNull(containerProtocol);
        this.containerProtocol = containerProtocol;
        return this;
    }

    /**
     * The number of seconds to wait for a response until a health check is deemed unsuccessful.
     * <p>
     * Default: 5.
     */
    public ServiceInputParameters withHealthCheckTimeoutSeconds(int healthCheckTimeoutSeconds) {
        this.healthCheckTimeoutSeconds = healthCheckTimeoutSeconds;
        return this;
    }

    /**
     * The number of consecutive successful health checks after which an instance is declared healthy.
     * <p>
     * Default: 2.
     */
    public ServiceInputParameters withHealthyThresholdCount(int healthyThresholdCount) {
        this.healthyThresholdCount = healthyThresholdCount;
        return this;
    }

    /**
     * The number of consecutive unsuccessful health checks after which an instance is declared unhealthy.
     * <p>
     * Default: 8.
     */
    public ServiceInputParameters withUnhealthyThresholdCount(int unhealthyThresholdCount) {
        this.unhealthyThresholdCount = unhealthyThresholdCount;
        return this;
    }

    /**
     * The number of CPU units allocated to each instance of the application. See
     * <a ahref="https://docs.aws.amazon.com/AmazonECS/latest/developerguide/task-cpu-memory-error.html"?>the docs</a>
     * for a table of valid values.
     * <p>
     * Default: 256 (0.25 CPUs).
     */
    public ServiceInputParameters withCpu(int cpu) {
        this.cpu = cpu;
        return this;
    }

    /**
     * The memory allocated to each instance of the application in megabytes. See
     * <a ahref="https://docs.aws.amazon.com/AmazonECS/latest/developerguide/task-cpu-memory-error.html"?>the docs</a>
     * for a table of valid values.
     * <p>
     * Default: 512.
     */
    public ServiceInputParameters withMemory(int memory) {
        this.memory = memory;
        return this;
    }

    /**
     * The duration the logs of the application should be retained.
     * <p>
     * Default: 1 week.
     */
    public ServiceInputParameters withLogRetention(RetentionDays logRetention) {
        Objects.requireNonNull(logRetention);
        this.logRetention = logRetention;
        return this;
    }

    /**
     * The number of instances that should run in parallel behind the load balancer.
     * <p>
     * Default: 2.
     */
    public ServiceInputParameters withDesiredInstances(int desiredInstances) {
        this.desiredInstancesCount = desiredInstances;
        return this;
    }

    /**
     * The maximum percentage in relation to the desired instances that may be running at the same time
     * (for example during deployments).
     * <p>
     * Default: 200.
     */
    public ServiceInputParameters withMaximumInstancesPercent(int maximumInstancesPercent) {
        this.maximumInstancesPercent = maximumInstancesPercent;
        return this;
    }

    /**
     * The minimum percentage in relation to the desired instances that must be running at the same time
     * (for example during deployments).
     * <p>
     * Default: 50.
     */
    public ServiceInputParameters withMinimumHealthyInstancesPercent(int minimumHealthyInstancesPercent) {
        this.minimumHealthyInstancesPercent = minimumHealthyInstancesPercent;
        return this;
    }

    /**
     * The list of PolicyStatement objects that define which operations this service can perform on other
     * AWS resources (for example ALLOW sqs:GetQueueUrl for all SQS queues).
     * <p>
     * Default: none (empty list).
     */
    public ServiceInputParameters withTaskRolePolicyStatements(List<PolicyStatement> taskRolePolicyStatements) {
        this.taskRolePolicyStatements = taskRolePolicyStatements;
        return this;
    }

    /**
     * Disable or enable sticky sessions for the the load balancer.
     * <p>
     * Default: false.
     */
    public ServiceInputParameters withStickySessionsEnabled(boolean stickySessionsEnabled) {
        this.stickySessionsEnabled = stickySessionsEnabled;
        return this;
    }

    /**
     * The format of the date time used in log entries. The awslogs driver will use this pattern to extract
     * the timestamp from a log event and also to distinguish between multiple multi-line log events.
     * <p>
     * Default: %Y-%m-%dT%H:%M:%S.%f%z (to work with JSON formatted logs created with <a href="https://github.com/osiegmar/logback-awslogs-json-encoder">awslogs JSON Encoder</a>).
     * <p>
     * See also: <a href="https://docs.docker.com/config/containers/logging/awslogs/#awslogs-datetime-format">awslogs driver</a>
     */
    public ServiceInputParameters withAwsLogsDateTimeFormat(String awsLogsDateTimeFormat) {
        this.awslogsDateTimeFormat = awsLogsDateTimeFormat;
        return this;
    }

}