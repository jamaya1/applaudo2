package com.jamaya.applaudo.controller;

import com.jamaya.applaudo.beans.ItemEJB;
import com.jamaya.applaudo.entity.Item;
import com.jamaya.applaudo.exception.BadRequestException;
import com.jamaya.applaudo.exception.NotFoundException;
import com.jamaya.applaudo.utilities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/item")
public class ItemController {

    private final ItemEJB itemEJB;

    @Autowired
    public ItemController(ItemEJB itemEJB) {
        this.itemEJB = itemEJB;
    }

    @PostMapping
    public ResponseEntity<Item> save(@RequestBody Item item)  {
        Item savedItem = null;
        try {
            savedItem = itemEJB.save(item);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getById(@PathVariable Integer itemId) {
        Item item = null;
        try {
            item = itemEJB.getById(itemId);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    @GetMapping()
    public ResponseEntity<List<Item>> getByNameAndStatus(@RequestParam Status itemStatus, @RequestParam String itemName) {
        List<Item> items = null;
        try {
            items = itemEJB.getByNameAndStatus(itemName, itemStatus);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

}
