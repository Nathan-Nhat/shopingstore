package com.ttnhat.shop.Controller.ResponseObject;

public class UserDashBoardDTO {
    private Integer id;
    private String username;
    private String name;
    private long totalOrders;

    public UserDashBoardDTO(Integer id, String username, long totalOrders, String name) {
        this.id = id;
        this.username = username;
        this.totalOrders = totalOrders;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
