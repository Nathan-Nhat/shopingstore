package com.ttnhat.shop.Sercurity.Entity;

import com.ttnhat.shop.Entity.UsersDetails;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UsersEntity {

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
//    @OneToOne(mappedBy = "userId", fetch = FetchType.LAZY)
//    private UsersDetails userDetails;
    @Column(name = "status")
    private String status;
    public UsersEntity(){};
    public UsersEntity(String username, String password, String roles, String status) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.status = status;
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

    public String getStatus() {
        return status;
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

//    public UsersDetails getUserDetails() {
//        return userDetails;
//    }

//    public void setUserDetails(UsersDetails userDetails) {
//        this.userDetails = userDetails;
//    }

    public void setStatus(String status) {
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
