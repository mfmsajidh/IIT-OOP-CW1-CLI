package iit.oop.cw.service;

import iit.oop.cw.model.User;
import iit.oop.cw.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserRepository {

    @Override
    public boolean exists(String username) {
        return "admin".equals(username);
    }

    @Override
    public User create(User user) {
        user.setId(10000L);
        return user;
    }

    @Override
    public User update(User user) {
        return user;
    }

    @Override
    public long updateAll() {
        long numberOfUsers = 2000;
        for (long i=1; i<=numberOfUsers; i++) {
            // do some operation ...
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return numberOfUsers;
    }

}
