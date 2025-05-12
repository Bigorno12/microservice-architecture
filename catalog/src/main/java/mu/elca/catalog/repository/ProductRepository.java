package mu.elca.catalog.repository;

import mu.elca.catalog.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

    Flux<Product> findProductsByCodeIgnoreCase(String code, Pageable pageable);

    Mono<Product> findProductByCodeIgnoreCase(String code);
}
