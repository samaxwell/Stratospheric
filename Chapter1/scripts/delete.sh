# Turning off the AWS pager so that the CLI doesn't open an editor for each command result
export AWS_PAGER=""

aws cloudformation delete-stack \
  --stack-name todo-basic-service
echo "Deleting service"

aws cloudformation wait stack-delete-complete \
  --stack-name todo-basic-service
echo "Service deleted"

aws cloudformation delete-stack \
  --stack-name todo-basic-network
echo "Deleting network"

aws cloudformation wait stack-delete-complete \
  --stack-name todo-basic-network
echo "Network deleted"
