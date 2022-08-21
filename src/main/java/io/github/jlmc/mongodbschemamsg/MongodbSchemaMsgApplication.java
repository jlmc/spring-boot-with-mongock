package io.github.jlmc.mongodbschemamsg;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongock
@EnableMongoAuditing

@SpringBootApplication
public class MongodbSchemaMsgApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbSchemaMsgApplication.class, args);
    }

}
