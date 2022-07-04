package com.example.Argo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String board_name;

    private String board_desc;

    @ManyToOne
    @JoinColumn(name = "user_fk", referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy="board")
    private List<Itemgroup> itemGroups;

    @JsonIgnore
    @OneToMany(mappedBy="board", cascade = CascadeType.ALL)
    private List<BoardUsers> boardUsers;

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
