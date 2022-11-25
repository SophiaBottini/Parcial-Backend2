package com.msbills.models.Dto;

import java.util.Date;

public class BillUser {
    private String idBill;

    private Date billingDate;

    private Double totalPrice;

    private User user;

    public BillUser(String idBill, Date billingDate, Double totalPrice, User user) {
        this.idBill = idBill;
        this.billingDate = billingDate;
        this.totalPrice = totalPrice;
        this.user = user;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
//● billId
//        ● billingDate ● totalPrice ● user
//        ○ username ○ firstname ○ lastname ○ email