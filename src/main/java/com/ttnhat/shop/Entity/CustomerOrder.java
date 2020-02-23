package com.ttnhat.shop.Entity;

import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.springframework.security.core.userdetails.UserDetails;

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
    @JoinColumn(name = "customer_id", referencedColumnName = "user_id")
    private UsersDetails usersDetails;

    public CustomerOrder() {
    }
    public CustomerOrder(Date dateCreate, UsersDetails usersDetails) {
        this.usersDetails =  usersDetails;
        this.dateCreate = dateCreate;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setUsersDetails(UsersDetails usersDetails) {
        this.usersDetails = usersDetails;
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
