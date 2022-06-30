package com.example.Argo.controllers;

import com.example.Argo.models.Item;
import com.example.Argo.models.Itemgroup;
import com.example.Argo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/groups/{id}/items")
    public Item addItem(@RequestBody Item item, @PathVariable int id) {
        item.setItemgroup(new Itemgroup(id, ""));
        return itemService.saveItem(item);
    }

    @GetMapping("/items/getAll")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/groups/{id}/items")
    public List<Item> getAllItems(@PathVariable int id) {
        return itemService.getItemsByGroup(id);
    }

    @PutMapping("/items/{id}/update")
    public Item updateItem(@PathVariable int id, @RequestBody Item item) {
        Item current = itemService.getItemById(id).get();
        current.setItem_name(item.getItem_name());
        current.setItem_desc(item.getItem_desc());
        current.setItem_date(item.getItem_date());
        current.setItemgroup(item.getItemgroup());
        current.setPriority(item.getPriority());
        current.setStatus(item.getStatus());
        return itemService.saveItem(current);
    }

    @RequestMapping("items/{id}/delete")
    public String deleteItem(@PathVariable int id) {
        Item current = itemService.getItemById(id).get();
        itemService.deleteItem(current);
        return "deleted";
    }



}
