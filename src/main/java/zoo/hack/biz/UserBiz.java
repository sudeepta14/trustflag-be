package zoo.hack.biz;

import zoo.hack.dao.UserEntity;
import zoo.hack.protocol.User;
import zoo.hack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserBiz {
    
    @Autowired
    UserRepository userRepository;
    
    public List<User> getAllUsers(){
        Iterable<UserEntity> userEntities = userRepository.findAll();
        ArrayList<User> users = new ArrayList<>();
        for (UserEntity userEntity: userEntities){
            users.add(fromEntity(userEntity));
        }
        return users;
    }

    public User findById(Long id) {
        Optional<UserEntity> userEntityOpt = userRepository.findById(id);
        User user = new User();
        if (userEntityOpt.isPresent()) {
            UserEntity userEntity = userEntityOpt.get();
            userEntity.setUsername(userEntity.getUsername());
        } else {
            return null;
        }
        return user;
    }
    
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    private User fromEntity(UserEntity userEntity){
        User user = new User();
        user.setEmail(userEntity.getEmail());
        user.setUsername(userEntity.getUsername());
        return user;
    }
}
