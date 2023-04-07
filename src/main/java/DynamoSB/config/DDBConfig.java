package DynamoSB.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DDBConfig {

    @Bean
    public DynamoDBMapper dynamoDBMapper(){
        return new DynamoDBMapper(buildAWSDDBMapper());
        //will use this bean to inject in repository
    }

    private AmazonDynamoDB buildAWSDDBMapper() {
        return AmazonDynamoDBClientBuilder.standard().
                withEndpointConfiguration(
                        new AwsClientBuilder.
                                EndpointConfiguration(
                                        "dynamodb.us-east-1.amazonaws.com",
                                "us-east-1")).
                withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        "your-access-key",
                                        "your-secret"
                                )
                        )
                )
                .build();
    }
}
