package zoo.hack.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zoo.hack.dao.FlagEntity;
import zoo.hack.dao.UserEntity;
import zoo.hack.protocol.Flag;
import zoo.hack.protocol.OwnedFlag;
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
    
    public List<OwnedFlag> getAllFlags(){
        Iterable<FlagEntity> flagEntities = flagRepository.findAll();
        ArrayList<OwnedFlag> flags = new ArrayList<>();
        for (FlagEntity flagEntity: flagEntities){
            flags.add(convertEntityToOwnedFlag(flagEntity));
        }
        return flags;
    }

    public List<OwnedFlag> getFlagsByUser(Long userId){
        Iterable<FlagEntity> flagEntities = flagRepository.findByUserId(userId);
        ArrayList<OwnedFlag> flags = new ArrayList<>();
        for (FlagEntity flagEntity: flagEntities){
            flags.add(convertEntityToOwnedFlag(flagEntity));
        }
        return flags;
    }

    public OwnedFlag findById(Long id) {
        Optional<FlagEntity> flagEntityOpt = flagRepository.findById(id);
        OwnedFlag flag;
        if (flagEntityOpt.isPresent()) {
            FlagEntity flagEntity = flagEntityOpt.get();
            flag = convertEntityToOwnedFlag(flagEntity);
        } else {
            return null;
        }
        return flag;
    }
    
    public void deleteById(Long id){
        flagRepository.deleteById(id);
    }
    
    private Flag convertEntityToFlag(FlagEntity flagEntity){
        Flag flag = new Flag();
        User user = new User();
        Optional<UserEntity> userEntityOpt = userRepository.findById(flagEntity.getUserId());
        if (userEntityOpt.isPresent()) {
            UserEntity userEntity = userEntityOpt.get();
            user.setEmail(userEntity.getEmail());
            user.setPhoneNumber(userEntity.getPhoneNumber());
        }
        flag.setUser(user);
        
        flag.setExpirationDate(flagEntity.getExpirationDate());
        return flag;
    }

    private OwnedFlag convertEntityToOwnedFlag(FlagEntity flagEntity){
        OwnedFlag flag = new OwnedFlag();
        User user = new User();
        Optional<UserEntity> userEntityOpt = userRepository.findById(flagEntity.getUserId());
        if (userEntityOpt.isPresent()) {
            UserEntity userEntity = userEntityOpt.get();
            user.setEmail(userEntity.getEmail());
        }
        flag.setId(flagEntity.getId());
        flag.setUser(user);
        flag.setExpirationDate(flagEntity.getExpirationDate());
        flag.setLicensePlateNumber(flagEntity.getLicensePlateNumber());
        flag.setLocation(flagEntity.getLocation());
        flag.setName(flagEntity.getName());
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
                flags.add(convertEntityToFlag(flagEntityOpt.get()));
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

    public void createFlag(OwnedFlag flag) {
        FlagEntity flagEntity = new FlagEntity();
        flagEntity.setName(flag.getName());
        flagEntity.setLicensePlateNumber(flag.getLicensePlateNumber());
        flagEntity.setLocation(flag.getLocation());
        flagEntity.setPhoneNumber(flag.getPhoneNumber());
        flagEntity.setUserId(flag.getUserId());
        flagEntity.setExpirationDate(flag.getExpirationDate());
        flagRepository.save(flagEntity);
    }
}
