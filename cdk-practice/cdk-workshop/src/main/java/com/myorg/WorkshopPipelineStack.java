package com.myorg;

import com.sun.tools.javac.util.List;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.pipelines.CdkPipeline;
import software.amazon.awscdk.pipelines.SimpleSynthAction;
import software.amazon.awscdk.services.codecommit.Repository;
import software.amazon.awscdk.services.codepipeline.Artifact;
import software.amazon.awscdk.services.codepipeline.actions.CodeCommitSourceAction;

public class WorkshopPipelineStack extends Stack {

    public WorkshopPipelineStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public WorkshopPipelineStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);
    }

    final Repository repo = Repository.Builder.create(this, "WorkshopRepo")
            .repositoryName("workshoprepo")
            .build();

    // artifact for sourcecode
    final Artifact sourceArtifact = new Artifact();

    // artifact for cloud assembly (cloudformation template + all other assets)
    final Artifact cloudAssemblyArtifact = new Artifact();

    // Basic pipeline declaration. This sets the initial structure
    final CdkPipeline pipeline = CdkPipeline.Builder.create(this, "Pipeline")
            .pipelineName("WorkshopPipeline")
            .cloudAssemblyArtifact(cloudAssemblyArtifact)

            .sourceAction(CodeCommitSourceAction.Builder.create()
                    .actionName("CodeCommit") // any Git-based source control
                    .output(sourceArtifact)
                    .repository(repo)
                    .build())

            .synthAction(SimpleSynthAction.Builder.create()
                    .installCommands(List.of("npm install -g aws-cdk"))
//                    .synthCommand("npx cdk synth")
                    .synthCommand("cdk synth")
                    .sourceArtifact(sourceArtifact)
                    .cloudAssemblyArtifact(cloudAssemblyArtifact)
                    .buildCommands(List.of(
                            "pwd",
                            "ls -l",
                            "cd cdk-practice/cdk-workshop && mvn package"))
                    .build())
            .build();

}
