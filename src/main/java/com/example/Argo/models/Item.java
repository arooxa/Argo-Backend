package com.example.Argo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_id;

    private String item_name;

    private String item_desc;

    @Basic
    @Getter(AccessLevel.NONE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate item_date;

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

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public LocalDate getItem_date() {
        return item_date;
    }

    public void setItem_date(LocalDate item_date) {
        this.item_date = item_date;
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
