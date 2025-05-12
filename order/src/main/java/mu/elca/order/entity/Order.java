package mu.elca.order.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    private Long id;

    @NotNull
    private String orderNumber;

    @NotNull
    private String username;

    @NotNull
    private String customerName;

    @NotNull
    private String customerEmail;

    @NotNull
    private String customerPhone;

    @NotNull
    private String deliveryAddressLine1;

    private String deliveryAddressLine2;

    @NotNull
    private String deliveryAddressCity;

    @NotNull
    private String deliveryAddressState;

    @NotNull
    private String deliveryAddressZipCode;

    @NotNull
    private String deliveryAddressCountry;

    @NotNull
    private String status;

    private String comments;

    @NotNull
    @CreatedDate
    private LocalDateTime createdDate;

    @NotNull
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

}
