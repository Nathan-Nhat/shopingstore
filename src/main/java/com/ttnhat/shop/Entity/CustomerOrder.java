package com.ttnhat.shop.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer_order")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_create")
    private Date dateCreate;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private UsersEntity usersEntity;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customerOrder")
    private List<OrderedProduct> orderedProductList = new ArrayList<>();

    @Column(name = "is_done")
    private String isDone;

    public CustomerOrder() {
    }
    public CustomerOrder(Date dateCreate, UsersEntity usersEntity) {
        this.usersEntity =  usersEntity;
        this.dateCreate = dateCreate;
    }

    public List<OrderedProduct> getOrderedProductList() {
        return orderedProductList;
    }
    public Integer getId() {
        return id;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
    }

    public String getIsDone() {
        return isDone;
    }

    public void setIsDone(String isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", dateCreate=" + dateCreate +
                ", usersDetails=" + usersEntity +
                '}';
    }
}
