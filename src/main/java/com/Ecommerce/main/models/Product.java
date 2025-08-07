package com.Ecommerce.main.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String brand;
    private String name;
    private String description;
    private BigDecimal price;
    private int inventory;

    public Product(String brand, String name, String description, BigDecimal price, int inventory, Category category) {
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.category = category;
    }

    @ManyToOne

    @JoinColumn(name = "category-id",referencedColumnName = "id",foreignKey = @ForeignKey(name="category-id-key"))
    private Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Image> images;

    @OneToMany(mappedBy = "prodcut")
    private List<CartItem> cartItems;

    }


