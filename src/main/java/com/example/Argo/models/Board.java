package com.example.Argo.models;

import javax.persistence.*;
import java.util.List;


@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String board_name;

    private String board_desc;

    @ManyToOne
    @JoinColumn(name = "user_fk", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy="board")
    private List<Itemgroup> itemGroups;

    public Board() {

    }

    public Board(int id, String board_name, String desc) {
        this.id = id;
        this.board_name = board_name;
        this.board_desc = desc;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBoard_desc() {
        return board_desc;
    }

    public void setBoard_desc(String desc) {
        this.board_desc = desc;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }
}
