package com.ttnhat.shop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "image_details")
public class ImageDetailProduct {
    @Id
    private String id;

    @Column(name = "image_detail_1")
    private String imageDetails1;

    @Column(name = "image_detail_2")
    private String imageDetails2;

    @Column(name = "image_detail_3")
    private String imageDetails3;

    @Column(name = "image_detail_4")
    private String imageDetails4;

    @Column(name = "image_detail_5")
    private String imageDetails5;

    @Column(name = "image_detail_6")
    private String imageDetails6;
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId
    @JoinColumn(name = "id")
    private Product product;

    public ImageDetailProduct() {};

    public ImageDetailProduct(String id, String imageDetails1, String imageDetails2, String imageDetails3, String imageDetails4, String imageDetails5, String imageDetails6, Product product) {
        this.id = id;
        this.imageDetails1 = imageDetails1;
        this.imageDetails2 = imageDetails2;
        this.imageDetails3 = imageDetails3;
        this.imageDetails4 = imageDetails4;
        this.imageDetails5 = imageDetails5;
        this.imageDetails6 = imageDetails6;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public String getImageDetails1() {
        return imageDetails1;
    }

    public String getImageDetails2() {
        return imageDetails2;
    }

    public String getImageDetails3() {
        return imageDetails3;
    }

    public String getImageDetails4() {
        return imageDetails4;
    }

    public String getImageDetails5() {
        return imageDetails5;
    }

    public String getImageDetails6() {
        return imageDetails6;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageDetails1(String imageDetails1) {
        this.imageDetails1 = imageDetails1;
    }

    public void setImageDetails2(String imageDetails2) {
        this.imageDetails2 = imageDetails2;
    }

    public void setImageDetails3(String imageDetails3) {
        this.imageDetails3 = imageDetails3;
    }

    public void setImageDetails4(String imageDetails4) {
        this.imageDetails4 = imageDetails4;
    }

    public void setImageDetails5(String imageDetails5) {
        this.imageDetails5 = imageDetails5;
    }

    public void setImageDetails6(String imageDetails6) {
        this.imageDetails6 = imageDetails6;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ImageDetailProduct{" +
                "id='" + id + '\'' +
                ", imageDetails1='" + imageDetails1 + '\'' +
                ", imageDetails2='" + imageDetails2 + '\'' +
                ", imageDetails3='" + imageDetails3 + '\'' +
                ", imageDetails4='" + imageDetails4 + '\'' +
                ", imageDetails5='" + imageDetails5 + '\'' +
                ", imageDetails6='" + imageDetails6 + '\'' +
                '}';
    }
}
