package com.pgt.entity;

import java.io.Serializable;

/**
 * @program: pgtsdk
 * @description:
 * @author: LL
 * @create: 2018-03-23 11:29
 **/
public class DraftDealIdEntity implements Serializable {
    private String id;
    private int orderStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
}
