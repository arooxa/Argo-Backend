package com.example.Argo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Itemgroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String group_name;

    @ManyToOne
    @JoinColumn(name = "board_fk", referencedColumnName = "id")
    private Board board;

    @OneToMany(mappedBy="itemgroup")
    @JsonIgnore
    private List<Item> items;

    public Itemgroup() {

    }

    public Itemgroup(int id, String group_name) {
        this.id = id;
        this.group_name = group_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
