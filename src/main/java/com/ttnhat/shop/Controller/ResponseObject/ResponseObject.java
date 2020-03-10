package com.ttnhat.shop.Controller.ResponseObject;

public class ResponseObject<T, K> {
    private T type;
    private K message;

    public ResponseObject(T type, K message) {
        this.type = type;
        this.message = message;
    }

    public T getType() {
        return type;
    }

    public K getMessage() {
        return message;
    }

    public void setType(T type) {
        this.type = type;
    }

    public void setMessage(K message) {
        this.message = message;
    }
}
