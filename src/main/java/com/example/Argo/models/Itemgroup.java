package com.example.Argo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itemgroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String group_name;

    @ManyToOne
    @JoinColumn(name = "board_fk", referencedColumnName = "id")
    private Board board;

    @OneToMany(mappedBy="itemgroup", orphanRemoval = true)
    @JsonIgnore
    private List<Item> items;

}
