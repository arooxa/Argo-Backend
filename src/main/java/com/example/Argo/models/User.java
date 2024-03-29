package com.example.Argo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String first_name;
    private String last_name;
    private String username;
    @Column(length = 60)
    private String password;
    private boolean active = true;

    @JsonIgnore
    @OneToMany(mappedBy="user", orphanRemoval = true)
    private List<Board> boards;

    @JsonIgnore
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardUsers> boardUsers;

}
