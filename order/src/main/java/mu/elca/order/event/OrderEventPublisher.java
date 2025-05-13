package mu.elca.order.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.elca.order.ApplicationProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties properties;

    public Mono<Void> publish(OrderCreatedEvent event) {
        return this.send(properties.newOrdersQueue(), event);
    }

    private Mono<Void> send(String routingKey, Object payload) {
        return Mono.create(voidMonoSink -> rabbitTemplate.convertAndSend(routingKey, payload)).then();
    }
}
