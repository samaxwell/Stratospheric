package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.codecommit.Repository;

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


}
