package com.ttnhat.shop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
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
    @Column(name = "num_ordered")
    private Integer numOrdered;
    @Column(name = "num_click")
    private Integer numClick;
    @Column(name = "url_image_main")
    private String imageMain;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImageDetailProduct> imageDetailProduct;

    @Column(name = "date_create")
    private Date dateCreate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderedProduct> orderedProductList;

    public Product(String name, String price, String description, Date lastUpdate,
                   Integer numOrdered, Integer numClick, String hot, Category category,
                   List<OrderedProduct> orderedProductList, String imageMain, List<ImageDetailProduct> imageDetailProduct,
                   Date dateCreate) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.numOrdered = numOrdered;
        this.numClick = numClick;
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

    public Integer getNumOrdered() {
        return numOrdered;
    }

    public Integer getNumClick() {
        return numClick;
    }

    public String getImageMain() {
        return imageMain;
    }

    public List<ImageDetailProduct> getImageDetailProduct() {
        return imageDetailProduct;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public Category getCategory() {
        return category;
    }

    public List<OrderedProduct> getOrderedProductList() {
        return orderedProductList;
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

    public void setNumOrdered(Integer numOrdered) {
        this.numOrdered = numOrdered;
    }

    public void setNumClick(Integer numClick) {
        this.numClick = numClick;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setOrderedProductList(List<OrderedProduct> orderedProductList) {
        this.orderedProductList = orderedProductList;
    }

    public void setImageDetailProduct(List<ImageDetailProduct> imageDetailProduct) {
        this.imageDetailProduct = imageDetailProduct;
    }

    public void setImageMain(String imageMain) {
        this.imageMain = imageMain;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", numOrdered=" + numOrdered +
                ", numClick=" + numClick +
                ", imageMain='" + imageMain + '\'' +
                ", category=" + category +
                ", orderedProductList=" + orderedProductList +
                '}';
    }
}
