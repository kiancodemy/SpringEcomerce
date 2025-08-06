package com.Ecommerce.main.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter

@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "idiot it is null")
    private String name;

    public Category(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category",cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Product> products;

}
