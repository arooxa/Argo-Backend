package com.example.Argo.repos;

import com.example.Argo.models.BoardUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardUsersRepo extends JpaRepository<BoardUsers, Long> {

    Optional<BoardUsers> findByUserUsernameAndBoardId(String username, int id);

}
