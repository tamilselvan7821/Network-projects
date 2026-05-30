package org.example.service;

import org.example.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashPasswordService {
    List<User> users = new ArrayList<>();
    public String createBcryptPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
    public synchronized boolean addUser(String username, String password){
        if(ifUserAvailable(username) != null) return false;
        users.add(new User(username, createBcryptPassword(password)));
        return true;
    }
    public User ifUserAvailable(String username){
        for(User user : users){
            if(username.equals(user.getUsername())) return user;
        }
        return null;
    }
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> response = new HashMap<>();
        User user = ifUserAvailable(username);
        if(user == null){
            response.put("resp", "Please Create Account");
        }
        else{
            if(user.getAttempt() == 0){
                response.put("resp", "Password Attempt reached...Account blocked");
            }
            else{
                if(BCrypt.checkpw(password, user.getPassword())){
                    response.put("resp", "Successfully login");
                    user.setAttempt(3);
                }
                else{
                    user.setAttempt(user.getAttempt()-1);
                    response.put("resp", "User name and Password Incorrect");
                }
                response.put("remaining Attempts", ""+user.getAttempt());
            }
        }
        return response;
    }
}
