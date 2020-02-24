package com.ttnhat.shop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "image_details")
public class ImageDetailProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "image_detail")
    private String imageDetails;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public ImageDetailProduct() {};
    public ImageDetailProduct(String imageDetails) {
        this.imageDetails = imageDetails;
    }

    public Integer getId() {
        return id;
    }

    public String getImageDetails() {
        return imageDetails;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setImageDetails(String imageDetails) {
        this.imageDetails = imageDetails;
    }
}
