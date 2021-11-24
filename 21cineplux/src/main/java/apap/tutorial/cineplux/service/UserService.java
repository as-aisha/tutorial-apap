package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel getUserById(String id);
    UserModel getUserByUsername(String username);
    String addUser(UserModel user);
    String encrypt(String password);
    List<UserModel> getUserList();
    void deleteUser(UserModel user);
    UserModel updateUser(UserModel user, String passwordlama, String passwordbaru, String konfirmasi);
}
