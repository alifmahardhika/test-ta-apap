package apap.tugas.situ.service;


import apap.tugas.situ.model.UserModel;

import java.util.Optional;

public interface UserService {

    UserModel addUser(UserModel user);
    public String encrypt(String password);

    UserModel getUser(String username);
    Optional<UserModel> getUserById(Long uuid);

    Boolean validatePassword(String oldpass, String oldpasscoba);

    void updatePassword(String username, String newpass);
}
