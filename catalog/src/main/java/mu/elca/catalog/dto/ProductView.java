package mu.elca.catalog.dto;

import lombok.Builder;

@Builder
public record ProductView(Long id, String code, String name, String imageUrl, String description, Double price) {
    public ProductView {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        if (code == null) {
            throw new IllegalArgumentException("code cannot be null");
        }

        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }

        if (price == null) {
            throw new IllegalArgumentException("price cannot be null");
        }
    }
}
