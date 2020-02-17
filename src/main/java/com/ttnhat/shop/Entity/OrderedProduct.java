package com.ttnhat.shop.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ordered_product")
public class OrderedProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn(name = "customer_order_id", referencedColumnName = "id")
    private CustomerOrder customerOrder;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    public OrderedProduct() {
    }

    public OrderedProduct(CustomerOrder customerOrder, Product product, Integer quantity) {
        this.customerOrder = customerOrder;
        this.product = product;
        this.quantity = quantity;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
