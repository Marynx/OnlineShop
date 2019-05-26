package com.shop.repository;


import com.shop.model.Item;
import com.shop.model.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends CrudRepository<Vote,Long> {
    List<Vote> findByItem(Item item);
}
