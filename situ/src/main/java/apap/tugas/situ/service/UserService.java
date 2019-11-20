package apap.tugas.situ.service;


import apap.tugas.situ.model.UserModel;

public interface UserService {

    UserModel addUser(UserModel user);
    public String encrypt(String password);

    UserModel getUser(String username);
    UserModel getUserById(String uuid);

    Boolean validatePassword(String oldpass, String oldpasscoba);

    void updatePassword(String username, String newpass);
}
