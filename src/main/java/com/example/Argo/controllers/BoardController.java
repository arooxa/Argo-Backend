package com.example.Argo.controllers;

import com.example.Argo.models.Board;
import com.example.Argo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/board/add")
    public Board addBoard(@RequestBody Board board) {
        return boardService.saveBoard(board);
    }

    @PutMapping("/board/{id}/update")
    public Board updateBoard(@PathVariable int id, @RequestBody Board board) {
        Board current = boardService.getBoardById(id).get();
        current.setBoard_name(board.getBoard_name());
        current.setBoard_desc(board.getBoard_desc());
        return boardService.saveBoard(current);
    }

    @GetMapping("/board/getAll")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }



}
