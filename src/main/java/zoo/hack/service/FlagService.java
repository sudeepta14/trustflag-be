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

    @RequestMapping("/search")
    public List<Flag> findFlagById(@RequestParam(value = "keywords") String keywords) {
        return flagBiz.searchByTerms(keywords);
    }

    @CrossOrigin
    @GetMapping("/all")
    public List<Flag> getFlagsUsers() {
        return flagBiz.getAllFlags();
    }
}
