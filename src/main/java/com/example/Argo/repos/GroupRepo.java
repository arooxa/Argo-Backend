package com.example.Argo.repos;

import com.example.Argo.models.Itemgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepo extends JpaRepository<Itemgroup, Integer> {

    public List<Itemgroup> findByBoardId(int boardId);
}
