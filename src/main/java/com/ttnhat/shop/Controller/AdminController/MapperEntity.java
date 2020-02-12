package com.ttnhat.shop.Controller.AdminController;

public class MapperEntity {
    private String one;
    private String key;

    public MapperEntity(String one, String key) {
        this.one = one;
        this.key = key;
    }

    public String getOne() {
        return one;
    }

    public String getKey() {
        return key;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
