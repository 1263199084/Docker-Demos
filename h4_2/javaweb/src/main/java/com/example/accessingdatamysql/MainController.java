package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/demo")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewUser(@RequestParam String name
            , @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        n = userRepository.save(n);
        return "Saved. Your id is " + n.getId();
    }

    @PostMapping(path = "/update")
    public @ResponseBody
    String updateUser(@RequestParam Integer id,
                      @RequestParam String name
            , @RequestParam String email) {
        User n = userRepository.findById(id).orElse(null);
        if (n != null) {
            n.setName(name);
            n.setEmail(email);
            userRepository.save(n);
            return "Updated successfully.";
        } else {
            return "Id doesn't exist";
        }
    }

    @PostMapping(path = "/delete")
    public @ResponseBody
    String deleteUser(@RequestParam Integer id) {
        if (userRepository.findById(id).orElse(null) != null) {
            userRepository.deleteById(id);
            return "Deleted.";
        } else {
            return "Id doesn't exist";
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/who")
    public @ResponseBody
    String getPort(HttpServletRequest request) {
        return "hello from " + request.getLocalAddr();
    }
}
