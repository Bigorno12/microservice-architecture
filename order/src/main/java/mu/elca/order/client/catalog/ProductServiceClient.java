package mu.elca.order.client.catalog;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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

    @CircuitBreaker(name = "catalog-service")
    @Retry(name = "catalog-service", fallbackMethod = "getProductFallBack")
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

    Mono<ProductView> getProductFallBack(String productCode, Throwable throwable) {
        log.error("Product Code: {}", productCode, throwable);
        return Mono.empty();
    }
}
