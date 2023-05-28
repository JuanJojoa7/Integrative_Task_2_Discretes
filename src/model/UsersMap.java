package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UsersMap {
    private HashMap<String, User> userMap;

    public UsersMap() {
        userMap = new HashMap<>();
    }

    public String addUser(User user) {
        String message = "";
        String email = user.getEmail();
        if (searchUserByEmail(email) != null) {
            message = "Error: El usuario con el email '" + email + "' ya existe.";
        }
        else{
            userMap.put(user.getName(), user);
            message = "added!";
        }

        return message;
    }

    public User searchUserByEmail(String email) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public User getUser(String email) {
        return userMap.get(email);
    }

    public void removeUser(String email) {
        userMap.remove(email);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    public boolean containsUser(String name) {
        return userMap.containsKey(name);
    }

    public int getUserCount() {
        return userMap.size();
    }

    public void updateUserName(String email, String newName) {
        User user = userMap.get(email);
        if (user != null) {
            user.setName(newName);
        }
    }

    public void updateUserPassword(String email, String newPassword) {
        User user = userMap.get(email);
        if (user != null) {
            user.setPassword(newPassword);
        }
    }
}
