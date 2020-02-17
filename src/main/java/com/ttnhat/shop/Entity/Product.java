package com.ttnhat.shop.Entity;

import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
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
    @Column(name = "hot")
    private String hot;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderedProduct> orderedProductList;

    public Product(String name, String price, String description, Date lastUpdate,
                   Integer numOrdered, Integer numClick, String hot, Category category,
                   List<OrderedProduct> orderedProductList) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.numOrdered = numOrdered;
        this.numClick = numClick;
        this.hot = hot;
        this.category = category;
        this.orderedProductList = orderedProductList;
    }
    public Product(){};

    public Integer getId() {
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

    public String getHot() {
        return hot;
    }

    public Category getCategory() {
        return category;
    }

    public List<OrderedProduct> getOrderedProductList() {
        return orderedProductList;
    }

    public void setId(Integer id) {
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

    public void setHot(String hot) {
        this.hot = hot;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setOrderedProductList(List<OrderedProduct> orderedProductList) {
        this.orderedProductList = orderedProductList;
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
                ", hot='" + hot + '\'' +
                '}';
    }
}
