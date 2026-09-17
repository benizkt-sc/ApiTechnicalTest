package mx.com.nath.apitechnicaltest;

import mx.com.nath.apitechnicaltest.client.TvMazeClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.ImportHttpServices;

@ImportHttpServices(TvMazeClient.class)
@SpringBootApplication
public class ApiTechnicalTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiTechnicalTestApplication.class, args);
    }

}
