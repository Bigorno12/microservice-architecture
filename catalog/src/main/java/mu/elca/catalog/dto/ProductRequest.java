package mu.elca.catalog.dto;

public record ProductRequest(String code, String name, String imageUrl, String description, Double price) {
    public ProductRequest {
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
