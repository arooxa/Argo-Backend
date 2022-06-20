package com.example.Argo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_id;

    private String item_name;

    private String item_desc;

    @Temporal(TemporalType.DATE)
    private Date item_date;

    /*
        status 0 - NOT STARTED
        status 1 - IN PROGRESS
        status 2 - FINISHED
     */
    private int status;

    /*
        priority 0 - LOW
        priority 1 - MEDIUM
        priority 2 - HIGH
     */
    private int priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_fk", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Itemgroup itemgroup;


    public Item() {

    }

    public Item(int item_id, String item_name, String item_desc, Date item_date, int status, int priority) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_desc = item_desc;
        this.item_date = item_date;
        this.status = status;
        this.priority = priority;
    }

    public int getItemID() {
        return item_id;
    }

    public void setItemID(int itemID) {
        this.item_id = itemID;
    }

    public Date getItem_date() {
        return item_date;
    }

    public void setItem_date(Date item_date) {
        this.item_date = item_date;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String itemName) {
        this.item_name = itemName;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String itemDesc) {
        this.item_desc = itemDesc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Itemgroup getItemgroup() {
        return itemgroup;
    }

    public void setItemgroup(Itemgroup itemgroup) {
        this.itemgroup = itemgroup;
    }
}
