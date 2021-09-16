package com.sean.models.service;

import java.util.Objects;

public class DockerImageSource {
    private final String dockerRepositoryName;
    private final String dockerImageTag;
    private final String dockerImageUrl;

    /**
     * Loads a Docker image from the given URL.
     */
    public DockerImageSource(String dockerImageUrl) {
        Objects.requireNonNull(dockerImageUrl);
        this.dockerImageUrl = dockerImageUrl;
        this.dockerImageTag = null;
        this.dockerRepositoryName = null;
    }

    /**
     * Loads a Docker image from the given ECR repository and image tag.
     */
    public DockerImageSource(String dockerRepositoryName, String dockerImageTag) {
        Objects.requireNonNull(dockerRepositoryName);
        Objects.requireNonNull(dockerImageTag);
        this.dockerRepositoryName = dockerRepositoryName;
        this.dockerImageTag = dockerImageTag;
        this.dockerImageUrl = null;
    }

    public boolean isEcrSource() {
        return this.dockerRepositoryName != null;
    }

    public String getDockerRepositoryName() {
        return dockerRepositoryName;
    }

    public String getDockerImageTag() {
        return dockerImageTag;
    }

    public String getDockerImageUrl() {
        return dockerImageUrl;
    }
}
