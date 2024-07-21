
## AWS CLI
- [Installation Steps](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)

## AWS SDK Reference
- [AWS SDK](https://github.com/aws/aws-sdk-java-v2/#using-the-sdk)

## Maven Dependencies

```
<properties>
    <slf.nop.version>2.0.13</slf.nop.version>
    <aws.sdk.version>2.26.16</aws.sdk.version>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>

<dependencies>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-nop</artifactId>
        <version>${slf.nop.version}</version>
    </dependency>
    <dependency>
        <groupId>software.amazon.awssdk</groupId>
        <artifactId>s3</artifactId>
        <version>${aws.sdk.version}</version>
    </dependency>
</dependencies>
```

## AWS Access Key / Credentials File Location
- MAC/Linux
    - `~/.aws/credentials`
- Windows
    - `C:\Users\USERNAME \. aws\credentials`
- File Format
```
[default]
aws_access_key_id = ABCDEFGHIJ
aws_secret_access_key = abcdefghijklmnop0123456789
```    