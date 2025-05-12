package mu.elca.order.client.catalog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ProductServiceClient {

    private final WebClient webClient;

    public ProductServiceClient(@Qualifier("webClientCatalog") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ProductView> getProductCode(String code) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/product/find-product")
                        .queryParam("code", code)
                        .build()
                )
                .retrieve()
                .bodyToMono(ProductView.class);
    }
}
