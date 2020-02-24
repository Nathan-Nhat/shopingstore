package com.ttnhat.shop.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ttnhat.shop.Sercurity.Entity.UsersEntity;

import javax.persistence.*;

@Entity
@Table(name = "user_details")
public class UsersDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "avatar_url")
    private String avatarUrl;

    @JsonIgnore
    @Id
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UsersEntity usersEntity;
    @JsonIgnore
    @OneToMany(mappedBy = "usersDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomerOrder> customerOrderList;
    public UsersDetails() {
    }

    public UsersDetails(String name, String email, String phone, String address, String avatarUrl, UsersEntity usersEntity, List<CustomerOrder> customerOrderList){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.avatarUrl = avatarUrl;
        this.usersEntity = usersEntity;
        this.customerOrderList = customerOrderList;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public List<CustomerOrder> getCustomerOrderList() {
        return customerOrderList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
    }

    public void setCustomerOrderList(List<CustomerOrder> customerOrderList) {
        this.customerOrderList = customerOrderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersDetails that = (UsersDetails) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(address, that.address) &&
                Objects.equals(avatarUrl, that.avatarUrl) &&
                Objects.equals(usersEntity, that.usersEntity) &&
                Objects.equals(customerOrderList, that.customerOrderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phone, address, avatarUrl, usersEntity, customerOrderList);
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", userId=" + usersEntity +
                '}';
    }
}
