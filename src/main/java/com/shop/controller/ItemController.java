package com.shop.controller;

import com.shop.model.*;
import com.shop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/item")
public class ItemController {

    private static String UPLOADED_FOLDER = "D:\\springProjects\\online-shop\\src\\main\\resources\\static\\images\\";

    private ProductRepository productRepository;
    private ItemRepository itemRepository;
    private VoteRepository voteRepository;
    private UserRepository userRepository;
    private ImageRepository imageRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    ServletContext servletContext;

    @Autowired
    public ItemController(ProductRepository productRepository,ItemRepository itemRepository,VoteRepository voteRepository,
                          UserRepository userRepository, ImageRepository imageRepository,CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.itemRepository=itemRepository;
        this.voteRepository=voteRepository;
        this.userRepository=userRepository;
        this.imageRepository=imageRepository;
        this.categoryRepository=categoryRepository;
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

    @PostMapping("/add")
    public String addItem(@ModelAttribute @Valid Item item, @RequestParam("file")MultipartFile[] file) throws IOException {
        if(file[0].isEmpty()){
            return "redirect:/worker";
        }

        itemRepository.save(item);

      for (int i=0;i<file.length;i++) {
          if(!file[i].isEmpty()) {
              String filename = UPLOADED_FOLDER
                      + file[i].getOriginalFilename();
              BufferedImage src = ImageIO.read(new ByteArrayInputStream(file[i].getBytes()));
              // something like C:/Users/tom/Documents/nameBasedOnSomeId.png
              File destination = new File(filename);
              ImageIO.write(src, "jpg", destination);


              Image image = new Image();
              image.setPath("/images/" + file[i].getOriginalFilename());
              image.getItems().add(item);
              imageRepository.save(image);
          }
      }

        return"redirect:/worker";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") Long id){
        itemRepository.deleteById(id);
        return"redirect:/worker";
    }

    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable("id") Long id,Model model){
       Item item=itemRepository.findById(id).get();
       List<Category> categories= (List<Category>) categoryRepository.findAll();
       //item.getImages().remove();
       model.addAttribute("categories",categories);
       model.addAttribute("item",item);
       return "updateForm";
    }

}
