package com.example.Argo.repos;

import com.example.Argo.models.BoardUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardUsersRepo extends JpaRepository<BoardUsers, Long> {
}
