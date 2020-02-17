package com.ttnhat.shop.Entity;

import com.ttnhat.shop.Sercurity.Entity.UsersEntity;

import javax.persistence.*;
import java.util.Date;

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
    private UsersEntity usersEntity;

    public CustomerOrder() {
    }
    public CustomerOrder(Date dateCreate, UsersEntity usersDetails) {
        this.usersEntity =  usersDetails;
        this.dateCreate = dateCreate;
    }

    public Integer getId() {
        return id;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public UsersEntity getUsersDetails() {
        return usersEntity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setUsersDetails(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
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
