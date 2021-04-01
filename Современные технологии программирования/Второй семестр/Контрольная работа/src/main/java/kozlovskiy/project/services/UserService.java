package kozlovskiy.project.services;

import kozlovskiy.project.models.User;
import kozlovskiy.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User findUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public boolean deleteUserById(Long id) {
        if (findUserById(id) != null) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateUser(User user, Long id) {
        if (findUserById(id) != null) {
            user.setId(id);
            userRepo.save(user);
            return true;
        }
        return false;
    }
}
