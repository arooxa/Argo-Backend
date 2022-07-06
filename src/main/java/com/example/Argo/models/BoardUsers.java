package com.example.Argo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class BoardUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "user_fk", referencedColumnName = "id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "board_fk", referencedColumnName = "id")
    private Board board;

    private String role;
}
