package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.RoleModel;
import apap.tutorial.cineplux.model.UserModel;
import apap.tutorial.cineplux.service.RoleService;
import apap.tutorial.cineplux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
/**
    @PostMapping(value = "/add")
    private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
        String keyword = userService.addUser(user);
        UserModel addeduser = userService.getUserByUsername(user.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("keyword", keyword);
        return "add-user";
    }
*/

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

/**
    //Latihan 4
    @GetMapping("/update/{id}")
    public String updateUserPassForm(@PathVariable String id, Model model){
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
    public String updateUserPassSubmit(
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
*/

    @GetMapping("/ubahPassword")
    public String changePasswordUserFormPage(){
    return "form-change-password";
}

    @PostMapping("/ubahPassword")
    @PreAuthorize("isAuthenticated()")
    public String changePasswordSubmit(Authentication auth,
                                       @RequestParam("passwordLama") String passwordLama,
                                       @RequestParam("passwordBaru") String passwordBaru,
                                       @RequestParam("konfirmasiPassword") String konfirmasiPassword,
                                       RedirectAttributes red){

        UserModel user = userService.getUserByUsername(auth.getName());
        if(!userService.validasiPassword(user, passwordLama)){
            red.addFlashAttribute("pesanError","Password Lama Salah, Harap Memasukkan Password yang Benar");
            return "redirect:/user/ubahPassword";
        }

        if(!passwordBaru.equals(konfirmasiPassword)){
            red.addFlashAttribute("pesanError", "Password Baru Tidak Sama dengan Konfirmasi Password");
            return "redirect:/user/ubahPassword";
        }

        userService.updatePassword(user, konfirmasiPassword);
        red.addFlashAttribute("pesan", "Password Berhasil Diubah");
        return "redirect:/";
    }

}

