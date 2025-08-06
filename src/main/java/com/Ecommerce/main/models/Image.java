package com.Ecommerce.main.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Blob;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String filename;
    private String filetype;

    @Lob
    private Blob image;

    private String downloadurl;

    @ManyToOne
    @JoinColumn( name="product-id",referencedColumnName ="id",foreignKey = @ForeignKey(name = "product-id-key"))
    private Product product;
}
