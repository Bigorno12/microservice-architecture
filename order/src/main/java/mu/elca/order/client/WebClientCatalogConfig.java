package mu.elca.order.client;

import mu.elca.order.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientCatalogConfig {

    @Bean("webClientCatalog")
    public WebClient webClientCatalogConfig(ApplicationProperties properties) {
        return WebClient.builder()
                .baseUrl(properties.catalogServiceUrl())
                .build();
    }
}
