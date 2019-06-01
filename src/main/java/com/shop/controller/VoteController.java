package com.shop.controller;

import com.shop.model.Item;
import com.shop.model.User;
import com.shop.model.Vote;
import com.shop.repository.ItemRepository;
import com.shop.repository.UserRepository;
import com.shop.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;

@Controller
public class VoteController {
    private VoteRepository voteRepository;
    private ItemRepository itemRepository;
    private UserRepository userRepository;

    @Autowired
    public VoteController(VoteRepository voteRepository,ItemRepository itemRepository,UserRepository userRepository){
        this.voteRepository=voteRepository;
        this.itemRepository=itemRepository;
        this.userRepository=userRepository;
    }


    @PostMapping("/vote/{id_item}")
    public String addComment(@PathVariable(name="id_item") Long id_item,Principal principal, @ModelAttribute @Valid Vote vote){
        vote.setDate(new Date(System.currentTimeMillis()));
        User user=userRepository.findByLogin(principal.getName());
        vote.setUser(user);
        System.out.println(vote);
        Item item=itemRepository.findById(id_item).get();
        if(item.getVotes().size()>0) {
            item.setRating((item.getRating()+vote.getScore())/(item.getVotes().size()+1));
            itemRepository.save(item);
        }else{
            item.setRating(item.getRating()+vote.getScore()/1);
            itemRepository.save(item);
        }
        vote.setItem(item);
        voteRepository.save(vote);

        return "redirect:/item/"+item.getId();
}
}
