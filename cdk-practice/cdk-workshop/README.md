# CDK Workshop Code
This repo follows [this useful workshop](https://cdkworkshop.com/50-java.html) on working with the AWS CDK for Java projects.

The above tutorial combines several things:
1. Building an application 
2. Building the application infrastructure via CDK
3. Building a CI/CD pipeline for the application

All these components are in the same project, which makes it kind of confusing.

To get up and running:
- First create the CodeCommit repo (comment out everything else in 
`WorkshopPipelineStack.java`) and from the cli run `cdk deploy`. This will create the code repository.
Commit ony this project (not the whole parent, otherwise execution directories become a problem)
to the repository. You will need to set the git remote to the HTTPS url of the new repo and use
HTTPS tokens from IAM to authenticate. 


- Uncomment the rest of the code and run `cdk deploy` again. This will create the pipeline and
link it to the CodeCommit repository, create a code-commit hook as well. It will also trigger a 
build which "should" pass.

From this point on, deploys happen through code commits only, and you should not have to manually
run `cdk deploy` anymore. 

###Improvements
- The CodeCommit repository should be its own stack
- The CodePipeline should be its own stack
- The application should be (and in this case, is) its own stack

This means there would be a minimum of 3 stacks per app. This may impact the 'self-mutation'
of the pipelines (the trade-off being a slightly decreased deploy time).