package mu.elca.order.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mu.elca.order.enumeration.EventType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_events")
public class OrderEvent {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private String orderNumber;

    @NotNull
    private String eventId;

    @NotNull
    private EventType eventType;

    @NotNull
    private String payload;

    @NotNull
    @CreatedDate
    private LocalDateTime createdDate;

    @NotNull
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
