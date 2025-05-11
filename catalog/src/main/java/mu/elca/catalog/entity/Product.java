package mu.elca.catalog.entity;

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
@Table(name = "product")
public class Product {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    private String imageUrl;

    private String description;

    @NotNull
    private Double price;
}
