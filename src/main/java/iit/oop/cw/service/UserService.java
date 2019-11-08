package iit.oop.cw.service;

import iit.oop.cw.model.User;
import iit.oop.cw.observer.ProgressUpdateEvent;
import iit.oop.cw.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Observable;
import java.util.Observer;

//@Service
public class UserService extends Observable implements UserRepository {

    private Observer observer;

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

            // Notify observer of the change
            if (observer != null) {
                String message = "";
                if (i < numberOfUsers) {
                    message = ":: please WAIT update operation in progress";
                }
                observer.update(
                        this,
                        new ProgressUpdateEvent(i, numberOfUsers, message)
                );
            }
        }
        return numberOfUsers;
    }

    // Getters & Setters

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }
}
