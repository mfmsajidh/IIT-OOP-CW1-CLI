package iit.oop.cw.repository;

import iit.oop.cw.model.User;

public interface UserRepository {

    boolean exists(String username);
    User create(User user);
    User update(User user);
}