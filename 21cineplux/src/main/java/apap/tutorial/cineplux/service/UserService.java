package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel getUserById(String id);
    UserModel getUserByUsername(String username);
    UserModel addUser(UserModel user);
    String encrypt(String password);
    List<UserModel> getUserList();
    void deleteUser(UserModel user);
//    UserModel updatePass(UserModel user, String passwordlama, String passwordbaru, String konfirmasi);
    boolean validasiPassword(UserModel user,String password);
    UserModel updatePassword(UserModel user, String password);
}
