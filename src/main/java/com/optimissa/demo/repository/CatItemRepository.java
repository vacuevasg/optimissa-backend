package com.optimissa.demo.repository;

import com.optimissa.demo.model.CatItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CatItemRepository extends JpaRepository<CatItem,Integer>{
    List<CatItem> findByNombreContainingIgnoreCase(String nombre);

}