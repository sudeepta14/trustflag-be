package zoo.hack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zoo.hack.biz.FlagBiz;
import zoo.hack.protocol.Flag;
import zoo.hack.protocol.OwnedFlag;

import java.util.List;

@RequestMapping("/flags")
@RestController
public class FlagService {

    @Autowired
    FlagBiz flagBiz;
    
    @CrossOrigin
    @RequestMapping("/{id}")
    public OwnedFlag findFlagById(@PathVariable("id") Long id) {
        return flagBiz.findById(id);
    }

    @CrossOrigin
    @RequestMapping("/search")
    public List<Flag> findFlagById(@RequestParam(value = "keywords") String keywords) {
        return flagBiz.searchByTerms(keywords);
    }

    @CrossOrigin
    @GetMapping("/all")
    public List<OwnedFlag> getFlags() {
        return flagBiz.getAllFlags();
    }

    @CrossOrigin
    @GetMapping("/user/{userId}")
    public List<OwnedFlag> getFlagsByUser(@PathVariable("userId") Long userId) {
        return flagBiz.getFlagsByUser(userId);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteFlagById(@PathVariable("id") Long id) {
        flagBiz.deleteById(id);
    }
}
