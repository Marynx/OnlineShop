package com.shop.controller;

import com.shop.model.*;
import com.shop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    private OrderItemRepository orderItemRepository;
    private Environment environment;

    @Autowired
    ServletContext servletContext;


    private void saveFile(MultipartFile file, Path path) throws IOException {
        file.transferTo(path);
    }

    @Autowired
    public ItemController(ProductRepository productRepository,ItemRepository itemRepository,VoteRepository voteRepository,
                          UserRepository userRepository, ImageRepository imageRepository,CategoryRepository categoryRepository,
                          OrderItemRepository orderItemRepository,Environment environment){
        this.productRepository=productRepository;
        this.itemRepository=itemRepository;
        this.voteRepository=voteRepository;
        this.userRepository=userRepository;
        this.imageRepository=imageRepository;
        this.categoryRepository=categoryRepository;
        this.orderItemRepository=orderItemRepository;
        this.environment=environment;
    }

    @GetMapping("/{id}")
    public String showItem(Principal principal,@PathVariable("id") Long id, Model model){
        Item item=itemRepository.findById(id).get();
        Vote vote=new Vote();
        OrderItem orderItem=new OrderItem();
        List<Vote> votes= voteRepository.findByItem(item);
        String path="";
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
        if(item.getImages().size()!=0) {
             path= item.getImages().iterator().next().getPath();
        }
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
        Item item=itemRepository.findById(id).get();
        List<OrderItem> orderItems=orderItemRepository.findByItem(item);
        for(OrderItem oi:orderItems){
            if(oi.getOrder().getOrderStatus()==OrderStatus.IN_PROGRESS.getStatus() ||
                    oi.getOrder().getOrderStatus()==OrderStatus.SENT.getStatus()){
                return "redirect:/worker";
            }
        }

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

    @PostMapping("/edit/{id}")
    public String editItem(@PathVariable("id") Long id,@ModelAttribute @Valid Item item){
        Item item1=itemRepository.findById(id).get();
        item1.setDesc(item.getDesc());
        item1.setQuantity(item.getQuantity());
       // item1.setImages(item.getImages());
       // item1.setRating(item.getRating());
        item1.setPrice(item.getPrice());
        item1.setVersion(item.getVersion());
       // item1.setProduct(item.getProduct());
        itemRepository.save(item1);
        return "redirect:/worker";
    }

    //@DeleteMapping("/{id}/{id_image}")
    @GetMapping("/{id}/{id_image}")
    public String deleteImage(@PathVariable("id") Long id,@PathVariable("id_image") Long id_image){
        Item item=itemRepository.findById(id).get();
        Image image=imageRepository.findById(id_image).get();
        image.getItems().remove(item);
        //imageRepository.save(image);
        itemRepository.save(item);
        return "redirect:/item/edit/"+item.getId();
    }

    @PostMapping("/image/{id}")
    public String addImagetoItem(@PathVariable("id") Long id,@RequestParam("file")MultipartFile[] file) throws IOException {
        Item item=itemRepository.findById(id).get();
        for (int i=0;i<file.length;i++) {
            if(!file[i].isEmpty()) {
                saveFile(file[i],Paths.get(environment.getProperty("image.upload")+file[i].getOriginalFilename()));
//                String filename = UPLOADED_FOLDER
//                        + file[i].getOriginalFilename();
//                BufferedImage src = ImageIO.read(new ByteArrayInputStream(file[i].getBytes()));
//                // something like C:/Users/tom/Documents/nameBasedOnSomeId.png
//                File destination = new File(filename);
//                ImageIO.write(src, "jpg", destination);
//
//
                Image image = new Image();
                image.setPath("/images/" + file[i].getOriginalFilename());
                image.getItems().add(item);
                imageRepository.save(image);
            }
        }
        return "redirect:/item/edit/"+item.getId();
    }

}
