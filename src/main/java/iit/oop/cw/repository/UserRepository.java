package iit.oop.cw.repository;

import iit.oop.cw.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();

    boolean exists(String username);
    User create(User user);
    User update(User user);
    long updateAll();
}
