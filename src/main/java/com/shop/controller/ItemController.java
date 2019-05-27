package com.shop.controller;

import com.shop.model.Item;
import com.shop.model.OrderItem;
import com.shop.model.User;
import com.shop.model.Vote;
import com.shop.repository.ItemRepository;
import com.shop.repository.ProductRepository;
import com.shop.repository.UserRepository;
import com.shop.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/product")
public class ItemController {

    private ProductRepository productRepository;
    private ItemRepository itemRepository;
    private VoteRepository voteRepository;
    private UserRepository userRepository;

    @Autowired
    public ItemController(ProductRepository productRepository,ItemRepository itemRepository,VoteRepository voteRepository,
                          UserRepository userRepository){
        this.productRepository=productRepository;
        this.itemRepository=itemRepository;
        this.voteRepository=voteRepository;
        this.userRepository=userRepository;
    }

    @GetMapping("/{id}")
    public String showItem(Principal principal,@PathVariable("id") Long id, Model model){
        Item item=itemRepository.findById(id).get();
        Vote vote=new Vote();
        OrderItem orderItem=new OrderItem();
        List<Vote> votes= voteRepository.findByItem(item);
        boolean userVote=false;
        if(principal!=null){
            User user=userRepository.findByLogin(principal.getName());
            for(Vote v:votes){
                if(v.getUser()==user){
                    userVote=true;
                    break;
                }
            }
        }

        String path=item.getImages().iterator().next().getPath();
        model.addAttribute("item",item);
        model.addAttribute("path",path);
        model.addAttribute("vote",vote);
        model.addAttribute("votes",votes);
        model.addAttribute("userVote",userVote);
        model.addAttribute("orderItem",orderItem);
        return "product";
    }

}
