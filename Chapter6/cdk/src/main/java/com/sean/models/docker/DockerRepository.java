package com.sean.models.docker;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.RemovalPolicy;
import software.amazon.awscdk.services.ecr.IRepository;
import software.amazon.awscdk.services.ecr.LifecycleRule;
import software.amazon.awscdk.services.ecr.Repository;
import software.amazon.awscdk.services.iam.AccountPrincipal;

import java.util.Collections;

public class DockerRepository extends Construct {

    private final IRepository ecrRepository;

    public DockerRepository(
            final Construct scope,
            final String id,
            final Environment environment,
            final DockerRepositoryInputParameters dockerRepositoryInputParameters) {
        super(scope, id);

        this.ecrRepository = Repository.Builder.create(this, "ecrRepository")
                .repositoryName(dockerRepositoryInputParameters.dockerRepositoryName)
                .removalPolicy(dockerRepositoryInputParameters.retainRegistryOnDelete ? RemovalPolicy.RETAIN : RemovalPolicy.DESTROY)
                .lifecycleRules(Collections.singletonList(LifecycleRule.builder()
                        .rulePriority(1)
                        .description("limit to " + dockerRepositoryInputParameters.maxImageCount + " images")
                        .maxImageCount(dockerRepositoryInputParameters.maxImageCount)
                        .build()))
                .build();

        ecrRepository.grantPullPush(new AccountPrincipal(dockerRepositoryInputParameters.accountId));
    }
    public IRepository getEcrRepository() {
        return ecrRepository;
    }


}
