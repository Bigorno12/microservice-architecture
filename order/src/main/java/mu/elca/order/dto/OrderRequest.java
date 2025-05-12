package mu.elca.order.dto;

import lombok.Builder;

@Builder
public record OrderRequest(String orderNumber,
                           String customerName,
                           String customerEmail,
                           String customerPhone,
                           String deliveryAddressLine1,
                           String deliveryAddressLine2,
                           String deliveryAddressCity,
                           String deliveryAddressState,
                           String deliveryAddressZipCode,
                           String deliveryAddressCountry,
                           String status,
                           String comments) {
}
