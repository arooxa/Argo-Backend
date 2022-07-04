package com.example.Argo.controllers;

import com.example.Argo.models.Board;
import com.example.Argo.models.BoardUsers;
import com.example.Argo.models.User;
import com.example.Argo.repos.BoardUsersRepo;
import com.example.Argo.service.BoardService;
import com.example.Argo.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardUsersRepo boardUsersRepo;
    @Autowired
    private UserService userService;

    @PostMapping("/board/add")
    public Board addBoard(@RequestBody Board board, Principal principal) {
        Optional<User> currentUser = userService.findUser(principal.getName());
        board.setUser(currentUser.get());
        BoardUsers boardUsers = new BoardUsers();
        boardUsers.setBoard(board);
        boardUsers.setUser(currentUser.get());
        boardUsers.setRole("OWNER");
        boardUsersRepo.save(boardUsers);
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
    public List<Board> getBoardsPerUser(Principal principal) {
        return boardService.getBoardsByUser(principal.getName());
    }

    @GetMapping("/test/board/getAll")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/test/boardUsers/getAll")
    public List<BoardUsers> getAllBoardUsers() {
        return boardUsersRepo.findAll();
    }




}
