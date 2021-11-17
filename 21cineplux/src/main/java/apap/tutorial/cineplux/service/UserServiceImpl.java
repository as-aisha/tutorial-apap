package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.UserModel;
import apap.tutorial.cineplux.repository.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDB userDB;

    @Override
    public UserModel getUserById(String id) {
        Optional<UserModel> user = userDB.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public UserModel getUserByUsername(String username) {
        Optional<UserModel> user = Optional.ofNullable(userDB.findByUsername(username));
        return user.orElse(null);
    }

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDB.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public List<UserModel> getUserList() { return userDB.findAll(); }

    @Override
    public void deleteUser(UserModel user) {
        userDB.delete(user);
    }

    @Override
    public UserModel updateUser(UserModel user, String passwordlama, String passwordbaru, String konfirmasi) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Boolean isPasswordMatch = passwordEncoder.matches(passwordlama, user.getPassword());

        if (isPasswordMatch == true) {
            if (passwordbaru.equals(konfirmasi)) {
                user.setPassword(encrypt(passwordbaru));
                userDB.save(user);
            }
        }
        return user;
    }
}
