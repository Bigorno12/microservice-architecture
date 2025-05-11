package mu.elca.catalog.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.elca.catalog.dto.ProductRequest;
import mu.elca.catalog.dto.ProductView;
import mu.elca.catalog.entity.Product;
import mu.elca.catalog.exception.ProductAlreadyExistException;
import mu.elca.catalog.exception.ProductNotFound;
import mu.elca.catalog.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Mono<Void> save(ProductRequest product) {
        return productRepository.findById(product.id())
                .flatMap(existing -> Mono.error(new ProductAlreadyExistException("Product with id " + product.id() + " already exists")))
                .switchIfEmpty(productRepository.save(
                        new Product()
                                .id(product.id())
                                .code(product.code())
                                .name(product.name())
                                .imageUrl(product.imageUrl())
                                .description(product.description())
                                .price(product.price())
                ))
                .then();
    }

    @Transactional(readOnly = true)
    public Mono<ProductView> findProductById(Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFound("Product Not Found")))
                .mapNotNull(product -> ProductView.builder()
                        .id(product.id())
                        .code(product.code())
                        .name(product.name())
                        .imageUrl(product.imageUrl())
                        .description(product.description())
                        .price(product.price())
                        .build()
                );
    }

    @Transactional(readOnly = true)
    public Flux<Page<ProductView>> findProductsByCodeIgnoreCase(Pageable pageable, String code) {
        return productRepository.findProductsByCodeIgnoreCaseContaining(code, pageable)
                .switchIfEmpty(Mono.error(new ProductNotFound("Products not Found")))
                .mapNotNull(products -> products.map(product -> ProductView.builder()
                        .id(product.id())
                        .code(product.code())
                        .name(product.name())
                        .imageUrl(product.imageUrl())
                        .description(product.description())
                        .price(product.price())
                        .build())
                );
    }

}
