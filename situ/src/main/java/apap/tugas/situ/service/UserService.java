package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.UserModel;


public interface UserService {

    UserModel addUser(UserModel user);
    public String encrypt(String password);

    UserModel getUser(String username);

    Boolean validatePassword(String oldpass, String oldpasscoba);

    void updatePassword(String username, String newpass);
}
