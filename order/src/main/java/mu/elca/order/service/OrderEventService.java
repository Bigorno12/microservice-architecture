package mu.elca.order.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import mu.elca.order.entity.OrderEvent;
import mu.elca.order.enumeration.EventType;
import mu.elca.order.event.OrderCreatedEvent;
import mu.elca.order.event.OrderEventPublisher;
import mu.elca.order.repository.OrderEventRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventService {

    private final OrderEventRepository orderEventRepository;
    private final OrderEventPublisher orderEventPublisher;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public Mono<Void> saveOrderEvent(OrderCreatedEvent orderEvent) {
        var orderEventEntity = new OrderEvent()
                .eventId(orderEvent.eventId())
                .eventType(EventType.ORDER_CREATED)
                .orderNumber(orderEvent.orderNumber())
                .payload(objectMapper.writeValueAsString(orderEvent));

        return orderEventRepository.save(orderEventEntity).then();
    }

    public Mono<Void> publishOrderEvent() {
        return orderEventRepository.count()
                .switchIfEmpty(Mono.empty())
                .flatMapMany(unused -> orderEventRepository.findAll(Sort.by("createdDate").ascending()))
                .delayUntil(this::publish)
                .flatMap(orderEventRepository::delete)
                .then();
    }

    @SneakyThrows
    private Mono<Void> publish(OrderEvent orderEvent) {
        return switch (orderEvent.eventType()) {
            case ORDER_CREATED ->
                    orderEventPublisher.publish(objectMapper.readValue(orderEvent.payload(), OrderCreatedEvent.class));
            default -> Mono.empty();
        };
    }


}
