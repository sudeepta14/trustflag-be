package zoo.hack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zoo.hack.biz.FlagBiz;
import zoo.hack.protocol.Flag;

import java.util.List;

@RequestMapping("/flags")
@RestController
public class FlagService {

    @Autowired
    FlagBiz flagBiz;

    @RequestMapping("/{id}")
    public Flag findFlagById(@PathVariable("id") Long id) {
        return flagBiz.findById(id);
    }

    @CrossOrigin
    @GetMapping("/all")
    public List<Flag> getFlagsUsers() {
        return flagBiz.getAllFlags();
    }
}
