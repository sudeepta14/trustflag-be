package zoo.hack.service;

import zoo.hack.biz.UserBiz;
import zoo.hack.protocol.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserService {

    private static final String template = "Hello, %s!";

    @Autowired
    UserBiz userBiz;

    @CrossOrigin
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, name);
    }

    @CrossOrigin
    @RequestMapping("/{id}")
    public User findUserById(@PathVariable("id") Long id) {
        return userBiz.findById(id);
    }

    @CrossOrigin
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userBiz.getAllUsers();
    }
}
