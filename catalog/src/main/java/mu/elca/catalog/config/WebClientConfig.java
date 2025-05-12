package mu.elca.catalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private static final String URL = "";

    @Bean("orderWebClient")
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(URL)
                .build();
    }
}
