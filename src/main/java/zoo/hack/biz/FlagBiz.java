package zoo.hack.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zoo.hack.dao.FlagEntity;
import zoo.hack.dao.UserEntity;
import zoo.hack.protocol.Flag;
import zoo.hack.protocol.User;
import zoo.hack.repository.FlagRepository;
import zoo.hack.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlagBiz {
    
    @Autowired
    FlagRepository flagRepository;
    
    @Autowired
    UserRepository userRepository;
    
    public List<Flag> getAllFlags(){
        Iterable<FlagEntity> flagEntities = flagRepository.findAll();
        
        ArrayList<Flag> flags = new ArrayList<>();
        for (FlagEntity flagEntity: flagEntities){
            Flag flag = new Flag();
            User user = new User();
            Optional<UserEntity> userEntityOpt = userRepository.findById(flagEntity.getUserId());
            if (userEntityOpt.isPresent()) {
                UserEntity userEntity = userEntityOpt.get();
                user.setEmail(userEntity.getEmail());
            }
            flag.setUser(user);
            flags.add(flag);
        }
        return flags;
    }

    public Flag findById(Long id) {
        Optional<FlagEntity> flagEntityOpt = flagRepository.findById(id);
        Flag flag = new Flag();
        if (flagEntityOpt.isPresent()) {
            FlagEntity flagEntity = flagEntityOpt.get();
            flag.setFlag(flagEntity.getFlag());
        } else {
            return null;
        }
        return flag;
    }
    
    public void deleteById(Long id){
        flagRepository.deleteById(id);
    }
}
