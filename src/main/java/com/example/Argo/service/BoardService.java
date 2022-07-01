package com.example.Argo.service;

import com.example.Argo.models.Board;
import com.example.Argo.repos.BoardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepo boardRepo;


    public Board saveBoard(Board board) { return boardRepo.save(board); }

    public Optional<Board> getBoardById(int id) { return boardRepo.findById(id); }


    public List<Board> getAllBoards() { return boardRepo.findAll(); }
}
