package com.shop.controller;

import com.shop.model.*;
import com.shop.repository.*;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    private ProducerRepository producerRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private VoteRepository voteRepository;
    private RoleRepository roleRepository;
    private AdressRepository adressRepository;
    private UserService userService;

    @Autowired
    public AdminController(OrderRepository orderRepository,ItemRepository itemRepository,
                            CategoryRepository categoryRepository,ProducerRepository producerRepository,
                            ProductRepository productRepository,UserRepository userRepository,
                           VoteRepository voteRepository, RoleRepository roleRepository,
                           AdressRepository adressRepository,UserService userService){
        this.orderRepository=orderRepository;
        this.itemRepository=itemRepository;
        this.categoryRepository=categoryRepository;
        this.producerRepository=producerRepository;
        this.productRepository=productRepository;
        this.userRepository=userRepository;
        this.voteRepository=voteRepository;
        this.userService=userService;
        this.adressRepository=adressRepository;
        this.roleRepository=roleRepository;
    }


    @GetMapping("")
    public String home(Model model){
        List<Order> orders= (List<Order>) orderRepository.findAll();
        List<Producer> producers= (List<Producer>) producerRepository.findAll();
        List<Product> products= (List<Product>) productRepository.findAll();
        List<Item> items= (List<Item>) itemRepository.findAll();
        List<Category> categories= (List<Category>) categoryRepository.findAll();
        List<User>users= (List<User>) userRepository.findAll();
        List<Vote> votes= (List<Vote>) voteRepository.findAll();
        List<Role> roles= (List<Role>) roleRepository.findAll();
        List<OrderStatus> orderStatuses=new ArrayList<>(Arrays.asList(OrderStatus.values()));
        model.addAttribute("product",new Product());
        model.addAttribute("item",new Item());
        model.addAttribute("producer",new Producer());
        model.addAttribute("user",new User());
        model.addAttribute("orders",orders);
        model.addAttribute("votes",votes);
        model.addAttribute("items",items);
        model.addAttribute("categories",categories);
        model.addAttribute("users",users);
        model.addAttribute("address", new Adress());
        model.addAttribute("producers",producers);
        model.addAttribute("products",products);
        model.addAttribute("orderStatuses",orderStatuses);
        model.addAttribute("roles",roles);
        return "adminPane";
    }

    @PostMapping("/add")
    public ModelAndView addWorker(@ModelAttribute @Valid User user, BindingResult result, @RequestParam("role") String role,
                                  ModelAndView mav,RedirectAttributes redirectAttributes){
        //userRepository.save(user);

        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("costam","duzo ich jest");
            mav.setViewName("redirect:/admin");
            return mav;
        }

        Adress address=user.getAdress();
        adressRepository.save(address);

        if(role.equals("ROLE_USER")){
            userService.addWithDefaultRole(user);
        }else if(role.equals("ROLE_WORKER")){
            userService.addWithWorkerRole(user);
        }else if(role.equals("ROLE_ADMIN")){
            userService.addWithAdminRole(user);
        }
        mav.setViewName("redirect:/admin");
        return mav;
    }


    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id,Model model){
        User user=userRepository.findById(id).get();
        model.addAttribute("userEdit",user);
        return "updateForm";
    }

    @PostMapping("edit/{id}")
    public String updateUser(@PathVariable("id") Long id,@ModelAttribute @Valid User user,
                             @RequestParam("new_password") String newPass,@RequestParam("old_password") String oldPass){


        User userToEdit=userRepository.findById(id).get();
        if(userService.checkPasswords(userToEdit,oldPass)){
            userToEdit.setFirstName(user.getFirstName());
            userToEdit.setLastName(user.getLastName());
            userToEdit.setEmail(user.getEmail());
            userToEdit.setLogin(user.getLogin());
            Adress adress=userToEdit.getAdress();
            adress.setCity(user.getAdress().getCity());
            adress.setCountry(user.getAdress().getCountry());
            adress.setStreet(user.getAdress().getStreet());
            adress.setPostCode(user.getAdress().getPostCode());
            adressRepository.save(adress);
            userService.updatePassword(userToEdit,newPass);
        }
        return "redirect:/admin/edit/"+userToEdit.getId();
    }

    //@GetMapping()


}
