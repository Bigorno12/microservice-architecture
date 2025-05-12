package mu.elca.order.client.catalog;

import lombok.Builder;

@Builder
public record ProductView(String name, String imageUrl, String description, Double price) {
    public ProductView {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }

        if (price == null) {
            throw new IllegalArgumentException("price cannot be null");
        }
    }
}