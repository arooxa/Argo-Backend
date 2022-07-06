package com.example.Argo.service;

import com.example.Argo.models.Itemgroup;
import com.example.Argo.repos.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepo groupRepo;


    public Itemgroup saveGroup(Itemgroup itemgroup) { return groupRepo.save(itemgroup); }

    public void deleteGroup(Itemgroup itemgroup) { groupRepo.delete(itemgroup); }

    public Optional<Itemgroup> getGroupById(int id) { return groupRepo.findById(id); }

    public List<Itemgroup> getAllGroups() { return groupRepo.findAll(); }

    public List<Itemgroup> getGroupsByBoard(int boardId) { return groupRepo.findByBoardId(boardId); }
}
