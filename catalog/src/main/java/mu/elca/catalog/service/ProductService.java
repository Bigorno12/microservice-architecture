package mu.elca.catalog.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.elca.catalog.dto.ProductRequest;
import mu.elca.catalog.dto.ProductView;
import mu.elca.catalog.entity.Product;
import mu.elca.catalog.exception.ProductNotFound;
import mu.elca.catalog.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
        return productRepository.save(new Product()
                .code(product.code())
                .name(product.name())
                .imageUrl(product.imageUrl())
                .description(product.description())
                .price(product.price())
        ).then();
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
    public Mono<Page<ProductView>> findProductsByCodeIgnoreCase(Pageable pageable, String code) {
        return productRepository.findProductsByCodeIgnoreCase(code, pageable)
                .switchIfEmpty(Mono.error(new ProductNotFound("Products not Found")))
                .map(product -> ProductView.builder()
                        .id(product.id())
                        .code(product.code())
                        .name(product.name())
                        .imageUrl(product.imageUrl())
                        .description(product.description())
                        .price(product.price())
                        .build()
                )
                .collectList()
                .zipWith(productRepository.count())
                .map(product -> new PageImpl<>(product.getT1(), pageable, product.getT2()));

    }

    @Transactional
    public Mono<Void> updateProduct(ProductRequest productRequest, Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFound("Product with id: " + id + "not found")))
                .mapNotNull(product -> {
                    product.code(productRequest.code());
                    product.name(productRequest.name());
                    product.imageUrl(productRequest.imageUrl());
                    product.description(productRequest.description());
                    product.price(productRequest.price());
                    return product;
                })
                .flatMap(productRepository::save)
                .then();
    }

    @Transactional(readOnly = true)
    public Flux<ProductView> findAllProducts() {
        return productRepository.findAll()
                .mapNotNull(product -> ProductView.builder()
                        .id(product.id())
                        .code(product.code())
                        .name(product.name())
                        .imageUrl(product.imageUrl())
                        .description(product.description())
                        .price(product.price())
                        .build()
                )
                .onBackpressureBuffer(100);
    }

    @Transactional(readOnly = true)
    public Mono<ProductView> findProductByCode(String code) {
        return productRepository.findProductByCodeIgnoreCase(code)
                .switchIfEmpty(Mono.error(new ProductNotFound("Product Not found for Code: " + code)))
                .map(product -> ProductView.builder()
                        .id(product.id())
                        .code(product.code())
                        .name(product.name())
                        .imageUrl(product.imageUrl())
                        .description(product.description())
                        .price(product.price())
                        .build());
    }

}
