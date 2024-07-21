
## Build Spec

- Create `buildspec.yml` in the project root directory with the following content.
```
version: 0.2

phases:
  install:
    runtime-versions:
      java: latest
  pre_build:
    commands:
      - ACCOUNT_NUMBER=$(echo $CODEBUILD_BUILD_ARN | cut -f5 -d ':')
      - COMMIT_HASH=$(echo $CODEBUILD_RESOLVED_SOURCE_VERSION | cut -c 1-7)
      - ECR_REPO=$ACCOUNT_NUMBER.dkr.ecr.$AWS_REGION.amazonaws.com
      - IMAGE_URI=$ECR_REPO/$DOCKER_IMAGE:$COMMIT_HASH
      - echo 'docker login'
      - aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $ECR_REPO
  build:
    commands:
      - echo 'build jar'
      - mvn clean package
      - echo 'build docker image'
      - docker build --platform=linux/amd64 -t=$IMAGE_URI .
  post_build:
    commands:
      - echo 'docker push'
      - docker push $IMAGE_URI
      - echo 'create image definitions'
      - printf '[{"name":"%s","imageUri":"%s"}]' $CONTAINER_NAME $IMAGE_URI > imagedefinitions.json
artifacts:
  files:
    - imagedefinitions.json
```    