package com.example.Argo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
