package mu.elca.catalog.controller;

import lombok.RequiredArgsConstructor;
import mu.elca.catalog.dto.ProductRequest;
import mu.elca.catalog.dto.ProductView;
import mu.elca.catalog.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Mono<Void>> addProduct(@RequestBody ProductRequest product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<ProductView>> retrieveProduct(@PathVariable long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }
}
