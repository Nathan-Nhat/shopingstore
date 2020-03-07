package com.ttnhat.shop.Sercurity.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ttnhat.shop.Entity.CustomerOrder;
import com.ttnhat.shop.Entity.UsersDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UsersEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "roles")
    private String roles;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToOne(mappedBy = "usersEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private UsersDetails userDetails;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "usersEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomerOrder> customerOrderList = new ArrayList<>();
    public UsersEntity(){};
    public UsersEntity(String username, String password, String roles, Integer status, UsersDetails userDetails,
                       List<CustomerOrder> customerOrderList) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.status = status;
        this.customerOrderList = customerOrderList;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public Integer getStatus() {
        return status;
    }

    public List<CustomerOrder> getCustomerOrderList() {
        return customerOrderList;
    }

    public void setCustomerOrderList(List<CustomerOrder> customerOrderList) {
        this.customerOrderList = customerOrderList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public UsersDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UsersDetails userDetails) {
        this.userDetails = userDetails;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "UsersEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
