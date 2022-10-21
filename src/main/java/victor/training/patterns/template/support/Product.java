package victor.training.patterns.template.support;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Product {
    private Long id;
    private String name;
    private Double price;
    private String imageUrl;
}
