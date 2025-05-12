package mu.elca.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.elca.order.dto.OrderRequest;
import mu.elca.order.entity.Order;
import mu.elca.order.repository.OrderEventRepository;
import mu.elca.order.repository.OrderItemRepository;
import mu.elca.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderEventRepository orderEventRepository;

    public Mono<Void> createOrder(OrderRequest orderRequest) {
        Order username = new Order()
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
        return orderRepository.save(username).then();
    }
}
