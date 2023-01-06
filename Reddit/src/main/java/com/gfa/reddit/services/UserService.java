package com.gfa.reddit.services;

import com.gfa.reddit.models.Post;
import com.gfa.reddit.models.User;
import com.gfa.reddit.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean userValidation(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    public String login (String username, String password, Model model) {
        User.setIsLoginValid(userValidation(username, password));
        if (User.isIsLoginValid())
        {
            model.addAttribute("isUserLoggedIn", true);
            Post.setLoggedUser(userRepository.getUserByUsername(username));
            return "redirect:/";
        }
        model.addAttribute("failedLogin", true);
        return "login";
    }

}
