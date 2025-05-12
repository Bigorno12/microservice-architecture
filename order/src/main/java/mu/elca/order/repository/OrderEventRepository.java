package mu.elca.order.repository;

import mu.elca.order.entity.OrderEvent;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEventRepository extends R2dbcRepository<OrderEvent, Long> {
}
