package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
@Data
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String description;
    private BigDecimal amount;
    @OneToOne(fetch = FetchType.EAGER)
    UnitOfMeasure uom;

    @ManyToOne
    Recipe recipe;

    public Ingredient() {

    }
    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }


}
