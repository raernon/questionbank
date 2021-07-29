package hu.ujratervezes.questionbank;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@OpenAPIDefinition(
        info = @Info(
                title = "Questionbank API",
                version = "1.0.0",
                description = "This application gives you test questions and checks its correctness."
        ),
        servers = {
                @Server(
                        description = "Live server",
                        url = "https://webdevquiz.mysqlhost.ml:8090"
                ),
                @Server(
                        description = "Local development server",
                        url = "http://localhost:8090"
                )
        }
)
@SpringBootApplication
public class QuestionBankApp {
    public static void main(String[] args) {
        SpringApplication.run(QuestionBankApp.class, args);
    }

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    Random getRandom() {
        return new Random();
    }
}
