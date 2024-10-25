package br.com.brendosp.authapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Value("${myProps.server-url:http://localhost:8081}")
    private String serverUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        var openAPIInfo = new Info();
        openAPIInfo.setTitle("Auth API");
        openAPIInfo.setVersion("1.0.0");
        openAPIInfo.setDescription("API for authentication");

        var server = new Server();
        server.url(serverUrl);
        server.description("Local server");

        return new OpenAPI().info(openAPIInfo).servers(List.of(server));
    }
}
