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
import java.util.Set;

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
            flags.add(fromEntity(flagEntity));
        }
        return flags;
    }

    public Flag findById(Long id) {
        Optional<FlagEntity> flagEntityOpt = flagRepository.findById(id);
        Flag flag;
        if (flagEntityOpt.isPresent()) {
            FlagEntity flagEntity = flagEntityOpt.get();
            flag = fromEntity(flagEntity);
        } else {
            return null;
        }
        return flag;
    }
    
    public void deleteById(Long id){
        flagRepository.deleteById(id);
    }
    
    private Flag fromEntity(FlagEntity flagEntity){
        Flag flag = new Flag();
        User user = new User();
        Optional<UserEntity> userEntityOpt = userRepository.findById(flagEntity.getUserId());
        if (userEntityOpt.isPresent()) {
            UserEntity userEntity = userEntityOpt.get();
            user.setEmail(userEntity.getEmail());
        }
        flag.setUser(user);
        flag.setLicensePlateNumber(flagEntity.getLicensePlateNumer());
        flag.setName(flagEntity.getName());
        flag.setLocation(flagEntity.getLocation());
        flag.setPhoneNumber(flagEntity.getPhoneNumber());
        return flag;
    }
    
    public List<Flag> searchByTerms(String keywords){
        ArrayList<Flag> flags = new ArrayList<>();
        String[] keywordsArray = keywords.split(",");
        List<Long> matchingFlagIds = new ArrayList<>();
        for (String keyword : keywordsArray){
            List<FlagEntity> flagEntities = flagRepository.searchByKeywords(keyword);
            if (flagEntities.size() != 0){
                for (FlagEntity flagEntity: flagEntities){
                    matchingFlagIds.add(flagEntity.getId());
                }
            }
        }
        matchingFlagIds = removeDuplicates(matchingFlagIds);
        
        for (Long id : matchingFlagIds){
            Optional<FlagEntity> flagEntityOpt = flagRepository.findById(id);
            if (flagEntityOpt.isPresent()){
                flags.add(fromEntity(flagEntityOpt.get()));
            }
        }

        return flags;
    }
    
    private List<Long> removeDuplicates(List<Long> originalList){
        List<Long> dedupledList = new ArrayList<>();
        for (Long id : originalList){
            if (!dedupledList.contains(id)){
                dedupledList.add(id);
            }
        }
        return dedupledList;
    }
}
