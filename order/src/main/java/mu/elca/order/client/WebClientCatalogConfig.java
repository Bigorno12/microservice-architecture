package mu.elca.order.client;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import mu.elca.order.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientCatalogConfig {

    @Bean("webClientCatalog")
    public WebClient webClientCatalogConfig(WebClient.Builder builder, ApplicationProperties properties) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .doOnConnected(conn -> conn.addHandlerFirst(new ReadTimeoutHandler(5, TimeUnit.SECONDS)));
        return builder.baseUrl(properties.catalogServiceUrl())
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
