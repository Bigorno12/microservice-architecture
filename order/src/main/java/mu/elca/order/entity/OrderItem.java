package mu.elca.order.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
public class OrderItem {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantity;

    private Long orderId;
}
