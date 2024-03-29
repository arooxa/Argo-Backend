package com.example.Argo.controllers;

import com.example.Argo.models.*;
import com.example.Argo.repos.BoardUsersRepo;
import com.example.Argo.service.BoardService;
import com.example.Argo.service.ItemService;
import com.example.Argo.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private ItemService itemService;

    @GetMapping("/board/getAll")
    public ResponseEntity<List<Board>> getBoardsPerUser(Principal principal) {
        List<Board> boards = boardService.getBoardsByUser(principal.getName());
        for(Board board: boards) {
            board.setNumTasks(itemService.getNumItemsPerBoard(board.getId()));
        }
        return ResponseEntity.ok().body(boards);
    }

//    @GetMapping("/board/{id}/getNumItems")
//    public ResponseEntity<?> getBoardsPerUser(@PathVariable int id, Principal principal) {
//        if(boardService.getBoardById(id).isPresent()) {
//            if(userService.checkAccess(principal.getName(), id, false)) {
//                Board current = boardService.getBoardById(id).get();
//                int count = 0;
//                for(Itemgroup group: current.getItemGroups()) {
//                    for(Item item: group.getItems()) {
//                        count++;
//                    }
//                }
//                return ResponseEntity.ok().body(itemService.getNumItemsPerBoard(id));
//            } else {
//                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
//            }
//        } else {
//            return ResponseEntity.badRequest().body("Board Id does not exist");
//        }
//    }

    @PostMapping("/board/add")
    public ResponseEntity<?> addBoard(@RequestBody Board board, Principal principal) {
        User currentUser = userService.findUser(principal.getName()).get();
        if(userService.findUser(principal.getName()).isPresent()) {
            board.setUser(currentUser);
            BoardUsers boardUsers = new BoardUsers();
            boardUsers.setBoard(board);
            boardUsers.setUser(currentUser);
            boardUsers.setRole("OWNER");
            boardService.saveBoard(board);
            boardUsersRepo.save(boardUsers);

            return ResponseEntity.ok().body("Board Added Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @PutMapping("/board/{id}/update")
    public ResponseEntity<?> updateBoard(@PathVariable int id, @RequestBody Board board, Principal principal) {
        if(boardService.getBoardById(id).isPresent()) {
            if(userService.checkAccess(principal.getName(), id, false)) {
                Board current = boardService.getBoardById(id).get();
                current.setBoard_name(board.getBoard_name());
                current.setBoard_desc(board.getBoard_desc());
                return ResponseEntity.ok().body(boardService.saveBoard(current));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        } else {
            return ResponseEntity.badRequest().body("Board Id does not exist");
        }
    }

    @DeleteMapping("/board/{id}/delete")
    public ResponseEntity<?> deleteBoard(@PathVariable int id, Principal principal) {
        if(boardService.getBoardById(id).isPresent()) {
            if(userService.checkAccess(principal.getName(), id, true)) {
                Board current = boardService.getBoardById(id).get();
                boardService.deleteBoard(current);
                return ResponseEntity.ok().body("Board Deleted Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        } else {
            return ResponseEntity.badRequest().body("Board Id does not exist");
        }
    }



    @GetMapping("/test/board/getAll")
    public ResponseEntity<List<Board>> getAllBoards() {
        return ResponseEntity.ok().body(boardService.getAllBoards());
    }

    @GetMapping("/test/boardUsers/getAll")
    public List<BoardUsers> getAllBoardUsers() {
        return boardUsersRepo.findAll();
    }




}
