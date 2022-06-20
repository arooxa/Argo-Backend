package com.example.Argo.service;

import com.example.Argo.models.Item;
import com.example.Argo.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;


    public Item saveItem(Item item) { return itemRepo.save(item); }

    public void deleteItem(Item item) { itemRepo.delete(item); }

    public Optional<Item> getItemById(int id) { return itemRepo.findById(id); }


    public List<Item> getAllItems() { return itemRepo.findAll(); }

    public List<Item> getItemsByGroup(int groupId) { return itemRepo.findByItemgroupId(groupId); }
}
