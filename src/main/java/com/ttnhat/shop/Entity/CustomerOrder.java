package com.ttnhat.shop.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer_order")
public class CustomerOrder {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_create")
    private Date dateCreate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_username", referencedColumnName = "username")
    private UsersDetails usersDetails;

    @OneToMany(mappedBy = "customerOrder")
    private List<OrderedProduct> orderedProductList;

    public CustomerOrder() {
    }
    public CustomerOrder(Date dateCreate, UsersDetails usersDetails, List<OrderedProduct> orderedProductList) {
        this.usersDetails =  usersDetails;
        this.dateCreate = dateCreate;
        this.orderedProductList = orderedProductList;
    }

    public Integer getId() {
        return id;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public UsersDetails getUsersDetails() {
        return usersDetails;
    }

    public List<OrderedProduct> getOrderedProductList() {
        return orderedProductList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setUsersDetails(UsersDetails usersDetails) {
        this.usersDetails = usersDetails;
    }

    public void setOrderedProductList(List<OrderedProduct> orderedProductList) {
        this.orderedProductList = orderedProductList;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", dateCreate=" + dateCreate +
                ", usersDetails=" + usersDetails +
                '}';
    }
}
