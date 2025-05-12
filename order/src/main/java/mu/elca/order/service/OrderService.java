package mu.elca.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.elca.order.client.catalog.ProductServiceClient;
import mu.elca.order.dto.OrderRequest;
import mu.elca.order.entity.Order;
import mu.elca.order.exception.ProductNotFoundException;
import mu.elca.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;

    @Transactional
    public Mono<Void> createOrder(OrderRequest orderRequest) {
        Order order = new Order()
                .orderNumber(orderRequest.orderNumber())
                .username("username")
                .customerName(orderRequest.customerName())
                .customerEmail(orderRequest.customerEmail())
                .customerPhone(orderRequest.customerPhone())
                .deliveryAddressLine1(orderRequest.deliveryAddressLine1())
                .deliveryAddressLine2(orderRequest.deliveryAddressLine2())
                .deliveryAddressCity(orderRequest.deliveryAddressCity())
                .deliveryAddressState(orderRequest.deliveryAddressState())
                .deliveryAddressZipCode(orderRequest.deliveryAddressZipCode())
                .deliveryAddressCountry(orderRequest.deliveryAddressCountry())
                .status(orderRequest.status())
                .comments(orderRequest.comments());
        return validateProduct(orderRequest.orderNumber())
                .then(Mono.defer(() -> orderRepository.save(order)))
                .then();
    }

    @Transactional(readOnly = true)
    public Mono<Void> validateProduct(String code) {
        return productServiceClient.getProductCode(code)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found with code " + code)))
                .then();
    }
}
