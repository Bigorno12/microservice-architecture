package mu.elca.catalog.repository;

import mu.elca.catalog.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

    Flux<Page<Product>> findProductsByCodeIgnoreCaseContaining(String code, Pageable pageable);
}
