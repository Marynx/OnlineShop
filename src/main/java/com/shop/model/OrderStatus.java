package com.shop.model;

public enum OrderStatus {
    NOT_ACCEPTED("Nie potwierdzono"),IN_PROGRESS("W trakcie realizacji"),CANCELED("Anulowano"),SENT("Wysłano"),DELIVERED("Dostarczono")
    ;

    private String status;

    OrderStatus(String status) {
        this.status=status;
    }

    public String getStatus(){
        return status;
    }
}
