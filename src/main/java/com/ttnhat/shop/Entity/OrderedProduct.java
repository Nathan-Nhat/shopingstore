package com.ttnhat.shop.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ordered_product")
public class OrderedProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn(name = "customer_order_id", referencedColumnName = "id")
    private CustomerOrder customerOrder;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedProduct that = (OrderedProduct) o;
        return Objects.equals(customerOrder, that.customerOrder) &&
                Objects.equals(product, that.product) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerOrder, product, quantity);
    }
}
