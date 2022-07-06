package com.example.Argo.controllers;

import com.example.Argo.models.Item;
import com.example.Argo.models.Itemgroup;
import com.example.Argo.models.User;
import com.example.Argo.service.GroupService;
import com.example.Argo.service.ItemService;
import com.example.Argo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;




    @GetMapping("/group/{id}/items")
    public ResponseEntity<?> getAllItems(@PathVariable int id, Principal principal) {
        if(groupService.getGroupById(id).isPresent()) {
            int boardId = groupService.getGroupById(id).get().getBoard().getId();
            if(userService.checkAccess(principal.getName(), boardId, false)) {
                return ResponseEntity.ok().body(itemService.getItemsByGroup(id));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        } else {
            return ResponseEntity.badRequest().body("Group Id does not exist");
        }
    }


    @PostMapping("/group/{id}/item")
    public ResponseEntity<?> addItem(@RequestBody Item item, @PathVariable int id, Principal principal) {
        if(groupService.getGroupById(id).isPresent()) {
            int boardId = groupService.getGroupById(id).get().getBoard().getId();
            if(userService.checkAccess(principal.getName(), boardId, false)) {
                item.setItemgroup(groupService.getGroupById(id).get());
                return ResponseEntity.ok().body(itemService.saveItem(item));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        } else {
            return ResponseEntity.badRequest().body("Group Id does not exist");
        }
    }



    @PutMapping("/item/{id}/update")
    public ResponseEntity<?> updateItem(@PathVariable int id, @RequestBody Item item, Principal principal) {
        if(itemService.getItemById(id).isPresent()) {
            int boardId = itemService.getItemById(id).get().getItemgroup().getBoard().getId();
            if(userService.checkAccess(principal.getName(), boardId, false)) {
                Item current = itemService.getItemById(id).get();
                current.setItem_name(item.getItem_name());
                current.setItem_desc(item.getItem_desc());
                current.setItem_date(item.getItem_date());
                current.setItemgroup(item.getItemgroup());
                current.setPriority(item.getPriority());
                current.setStatus(item.getStatus());
                return ResponseEntity.ok().body(itemService.saveItem(current));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        } else {
            return ResponseEntity.badRequest().body("Item Id does not exist");
        }
    }

    @RequestMapping("item/{id}/delete")
    public ResponseEntity<?> deleteItem(@PathVariable int id, Principal principal) {
        if(itemService.getItemById(id).isPresent()) {
            int boardId = itemService.getItemById(id).get().getItemgroup().getBoard().getId();
            if(userService.checkAccess(principal.getName(), boardId, false)) {
                Item current = itemService.getItemById(id).get();
                itemService.deleteItem(current);
                return ResponseEntity.ok().body("Item deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        } else {
            return ResponseEntity.badRequest().body("Item Id does not exist");
        }
    }

    @GetMapping("/test/items/getAll")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }



}
