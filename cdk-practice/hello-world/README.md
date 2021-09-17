###Description
This app just deploys a stack with an S3 bucket. On stack deletion, 
the S3 bucket is emptied and destroyed as well.


#### Build/Deploy without Metadata
cdk --no-version-reporting synth
cdk --no-version-reporting deploy
