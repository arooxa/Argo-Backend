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
    @JsonIgnore
    @JoinColumn(name = "user_fk", referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy="board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Itemgroup> itemGroups;

    @JsonIgnore
    @OneToMany(mappedBy="board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardUsers> boardUsers;

    private int numTasks;
}
