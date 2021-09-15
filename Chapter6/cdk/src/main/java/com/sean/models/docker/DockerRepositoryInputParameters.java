package com.sean.models.docker;

import java.util.Objects;

public class DockerRepositoryInputParameters {
    final String dockerRepositoryName;
    final String accountId;
    final int maxImageCount;
    final boolean retainRegistryOnDelete;

    /**
     * @param dockerRepositoryName the name of the docker repository to create.
     * @param accountId            ID of the AWS account which shall have permission to push and pull the Docker repository.
     */
    public DockerRepositoryInputParameters(String dockerRepositoryName, String accountId) {
        this.dockerRepositoryName = dockerRepositoryName;
        this.accountId = accountId;
        this.maxImageCount = 10;
        this.retainRegistryOnDelete = true;
    }

    /**
     * @param dockerRepositoryName the name of the docker repository to create.
     * @param accountId            ID of the AWS account which shall have permission to push and pull the Docker repository.
     * @param maxImageCount        the maximum number of images to be held in the repository before old images get deleted.
     */
    public DockerRepositoryInputParameters(String dockerRepositoryName, String accountId, int maxImageCount) {
        Objects.requireNonNull(accountId, "accountId must not be null");
        Objects.requireNonNull(dockerRepositoryName, "dockerRepositoryName must not be null");
        this.accountId = accountId;
        this.maxImageCount = maxImageCount;
        this.dockerRepositoryName = dockerRepositoryName;
        this.retainRegistryOnDelete = true;
    }

    /**
     * @param dockerRepositoryName   the name of the docker repository to create.
     * @param accountId              ID of the AWS account which shall have permission to push and pull the Docker repository.
     * @param maxImageCount          the maximum number of images to be held in the repository before old images get deleted.
     * @param retainRegistryOnDelete indicating whether or not the container registry should be destroyed or retained on deletion.
     */
    public DockerRepositoryInputParameters(String dockerRepositoryName, String accountId, int maxImageCount, boolean retainRegistryOnDelete) {
        Objects.requireNonNull(accountId, "accountId must not be null");
        Objects.requireNonNull(dockerRepositoryName, "dockerRepositoryName must not be null");
        System.out.printf("Creating Input --> dockerRepositoryName: %s, accountId: %s, maxImageCount: %s, retainRegistryOnDelete: %s",
                dockerRepositoryName, accountId, maxImageCount, retainRegistryOnDelete);
        this.accountId = accountId;
        this.maxImageCount = maxImageCount;
        this.dockerRepositoryName = dockerRepositoryName;
        this.retainRegistryOnDelete = retainRegistryOnDelete;
    }
}
