# Welcome to your CDK Java project!

This is a blank project for Java development with CDK.

The `cdk.json` file tells the CDK Toolkit how to execute your app.

It is a [Maven](https://maven.apache.org/) based project, so you can open this project with any Maven compatible Java IDE to build and run tests.

## Useful commands

 * `mvn package`     compile and run tests
 * `cdk ls`          list all stacks in the app
 * `cdk synth`       emits the synthesized CloudFormation template
 * `cdk deploy`      deploy this stack to your default AWS account/region
 * `cdk diff`        compare deployed stack with current state
 * `cdk docs`        open CDK documentation


###Deploy Docker Repository Stack
```bash
cdk deploy \
  -- app "./mvnw -e -q compile exec:java \
    -Dexec.mainClass=com.sean.DockerRepositoryApp" \
  -c accountId=... \
  -c region=... \
  -c applicationName=...
```