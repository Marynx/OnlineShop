package com.shop.repository;

import com.shop.model.Image;
import com.shop.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Image,Long> {
    List<Image> findByItems(Item item);
}
