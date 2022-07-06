package com.example.Argo.controllers;

import com.example.Argo.models.Board;
import com.example.Argo.models.BoardUsers;
import com.example.Argo.models.Itemgroup;
import com.example.Argo.repos.BoardUsersRepo;
import com.example.Argo.service.BoardService;
import com.example.Argo.service.GroupService;
import com.example.Argo.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userService;

    @GetMapping("/board/{id}/groups")
    public ResponseEntity<List<Itemgroup>> getAllGroups(@PathVariable int id, Principal principal) {
        if(userService.checkAccess(principal.getName(), id, false)) {
            return ResponseEntity.ok().body(groupService.getGroupsByBoard(id));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @PostMapping("/board/{id}/group")
    public ResponseEntity<?> addGroup(@RequestBody Itemgroup itemgroup, @PathVariable int id, Principal principal) {
        if(userService.checkAccess(principal.getName(), id, false)) {
            if(boardService.getBoardById(id).isPresent()) {
                itemgroup.setBoard(boardService.getBoardById(id).get());
                return ResponseEntity.ok().body(groupService.saveGroup(itemgroup));
            }
            return ResponseEntity.badRequest().body("Board Id does not exist");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

    }

    @PutMapping("/group/{id}/update")
    public ResponseEntity<?> updateGroup(@PathVariable int id, @RequestBody Itemgroup group, Principal principal) {
        if(groupService.getGroupById(id).isPresent()) {
            if(userService.checkAccess(principal.getName(), groupService.getGroupById(id).get().getBoard().getId(), false)) {
                    Itemgroup currentGroup = groupService.getGroupById(id).get();
                    currentGroup.setGroup_name(group.getGroup_name());
                    currentGroup.setBoard(group.getBoard());
                    return ResponseEntity.ok().body(groupService.saveGroup(currentGroup));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        } else {
            return ResponseEntity.badRequest().body("Group Id does not exist");
        }

    }

    @DeleteMapping("/group/{id}/delete")
    public ResponseEntity<?> deleteGroup(@PathVariable int id, Principal principal) {
        if(groupService.getGroupById(id).isPresent()) {
            if(userService.checkAccess(principal.getName(), groupService.getGroupById(id).get().getBoard().getId(), false)) {
                Itemgroup currentGroup = groupService.getGroupById(id).get();
                groupService.deleteGroup(currentGroup);
                return ResponseEntity.ok().body("Group Deleted Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        } else {
            return ResponseEntity.badRequest().body("Group Id does not exist");
        }
    }



    @GetMapping("/test/group/getAll")
    public List<Itemgroup> getAllGroups() {
        return groupService.getAllGroups();
    }



}
