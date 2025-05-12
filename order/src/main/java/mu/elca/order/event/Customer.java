package mu.elca.order.event;

import jakarta.validation.constraints.NotBlank;

public record Customer(
        @NotBlank(message = "Customer Name is required") String name,
        @NotBlank(message = "Customer email is required") String email,
        @NotBlank(message = "Customer Phone number is required") String phone) {}