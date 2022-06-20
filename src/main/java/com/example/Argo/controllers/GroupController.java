package com.example.Argo.controllers;

import com.example.Argo.models.Board;
import com.example.Argo.models.Itemgroup;
import com.example.Argo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/boards/{id}/groups")
    public Itemgroup addGroup(@RequestBody Itemgroup itemgroup, @PathVariable int id) {
        itemgroup.setBoard(new Board(id, "", ""));
        return groupService.saveGroup(itemgroup);
    }

    @PutMapping("/groups/{id}/update")
    public Itemgroup updateGroup(@PathVariable int id, @RequestBody Itemgroup group) {
        Itemgroup current = groupService.getGroupById(id).get();
        current.setGroup_name(group.getGroup_name());
        current.setBoard(group.getBoard());
        return groupService.saveGroup(current);
    }

    @GetMapping("/groups/getAll")
    public List<Itemgroup> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/boards/{id}/groups")
    public List<Itemgroup> getAllGroups(@PathVariable int id) {
        return groupService.getGroupsByBoard(id);
    }



}
