package com.ttnhat.shop.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
public class Product{
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private String price;
    @Column(name = "description")
    private String description;
    @Column(name = "last_update")
    private Date lastUpdate;
    @Column(name = "url_image_main")
    private String imageMain;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private ImageDetailProduct imageDetailProduct;

    @Column(name = "date_create")
    private Date dateCreate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderedProduct> orderedProductList;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductDate> productDates = new ArrayList<>();

    public Product(String name, String price, String description, Date lastUpdate,
                   Category category,
                   List<OrderedProduct> orderedProductList, String imageMain, ImageDetailProduct imageDetailProduct,
                   Date dateCreate) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.imageMain = imageMain;
        this.category = category;
        this.orderedProductList = orderedProductList;
        this.imageDetailProduct = imageDetailProduct;
        this.dateCreate = dateCreate;
    }
    public Product(){};

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String getImageMain() {
        return imageMain;
    }

    public ImageDetailProduct getImageDetailProduct() {
        return imageDetailProduct;
    }


    public Category getCategory() {
        return category;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    public void setCategory(Category category) {
        this.category = category;
    }

    public void setOrderedProductList(List<OrderedProduct> orderedProductList) {
        this.orderedProductList = orderedProductList;
    }

    public void setImageDetailProduct(ImageDetailProduct imageDetailProduct) {
        this.imageDetailProduct = imageDetailProduct;
    }

    public void setImageMain(String imageMain) {
        this.imageMain = imageMain;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public List<ProductDate> getProductDates() {
        return productDates;
    }

    public void setProductDates(List<ProductDate> productDates) {
        this.productDates = productDates;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", imageMain='" + imageMain + '\'' +
                ", imageDetailProduct=" + imageDetailProduct +
                ", dateCreate=" + dateCreate +
                ", category=" + category +
                ", orderedProductList=" + orderedProductList +
                '}';
    }

}
