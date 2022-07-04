package com.example.Argo.repos;

import com.example.Argo.models.Board;
import com.example.Argo.models.Itemgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepo extends JpaRepository<Board, Integer> {

    public List<Board> findByUserUsername(String username);
}
