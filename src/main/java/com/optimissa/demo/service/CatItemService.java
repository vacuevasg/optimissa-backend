package com.optimissa.demo.service;

import com.optimissa.demo.model.CatItem;
import com.optimissa.demo.repository.CatItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CatItemService {
    @Autowired
    private CatItemRepository itemRepository;



    public List<CatItem> getItems(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return itemRepository.findAll();
        } else {
            return itemRepository.findByNombreContainingIgnoreCase(nombre);
        }
    }
}
