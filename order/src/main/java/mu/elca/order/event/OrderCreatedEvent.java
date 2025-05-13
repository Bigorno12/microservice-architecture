package mu.elca.order.event;

import lombok.Builder;

@Builder
public record OrderCreatedEvent(
        String eventId,
        String orderNumber,
        Customer customer,
        Address deliveryAddress) {
}
