package mu.elca.order.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.elca.order.service.OrderEventService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventPublisher {

    private final OrderEventService orderEventService;

    @Scheduled(cron = "*/5 * * * * *")
    public Mono<Void> publishOrderEvent() {
        log.info("Publishing order event");
        return orderEventService.publishOrderEvent();
    }
}
