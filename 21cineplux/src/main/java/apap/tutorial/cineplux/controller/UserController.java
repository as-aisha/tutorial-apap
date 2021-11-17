package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.RoleModel;
import apap.tutorial.cineplux.model.UserModel;
import apap.tutorial.cineplux.service.RoleService;
import apap.tutorial.cineplux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/add")
    private String addUserFormPage(Model model) {
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.getRoleList();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        return "form-add-user";
    }

    @PostMapping(value = "/add")
    private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
        userService.addUser(user);
        model.addAttribute("user", user);
        return "add-user";
    }

    @GetMapping("/viewall")
    public String listUser(Model model) {
        List<UserModel> listUser = userService.getUserList();
        model.addAttribute("listUser", listUser);
        return "viewall-user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id, Model model) {
        UserModel user = userService.getUserById(id);
        model.addAttribute("user", user);
        userService.deleteUser(user);
        return "delete-user";
    }

    @GetMapping("/update/{id}")
    public String updateUserPasswordForm(@PathVariable String id, Model model){
        UserModel user = userService.getUserById(id);
        if (user == null){
            return "error-notfound";
        }
        else {
            String passwordlama = "";
            String passwordbaru = "";
            String konfirmasi = "";
            model.addAttribute("user", user);
            model.addAttribute("konfirmasi", konfirmasi);
            model.addAttribute("passwordbaru", passwordbaru);
            model.addAttribute("passwordlama", passwordlama);
            return "form-update-password";
        }
    }
    @PostMapping("/update")
    public String updateUserPasswordSubmit(
            @ModelAttribute UserModel user,
            @RequestParam ("passwordlama") String passwordlama,
            @RequestParam ("passwordbaru") String passwordbaru,
            @RequestParam ("konfirmasi") String konfirmasi,
            Model model
    ){
        UserModel updatedUser= userService.updateUser(user, passwordlama, passwordbaru, konfirmasi);
        model.addAttribute("updatedUser", updatedUser);
        return "update-password";
    }

}

