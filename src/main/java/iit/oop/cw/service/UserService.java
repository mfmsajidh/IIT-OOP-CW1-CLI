package iit.oop.cw.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import iit.oop.cw.model.User;
import iit.oop.cw.observer.ProgressUpdateEvent;
import iit.oop.cw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

//@Service
public class UserService extends Observable implements UserRepository {

    @Autowired
    private ObjectMapper objectMapper;

    private Observer observer;
    private List<User> users = new ArrayList<>();

    @Override
    public User findById(Long id) {
        for (User user: users) {
            if (id.equals(user.getId())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        for (User user: users) {
            if (username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public boolean exists(String username) {
//        return "admin".equals(username);
        for (User user: users) {
            if (username.equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User create(User user) {
//        user.setId(10000L);
        user.setId(new Long(getNextId()));
        users.add(user);
        return user;
    }

    @Override
    public User update(User user) {
        for(User u : users) {
            if (u.getId().equals(user.getId())) {
                u = user;
                return user;
            }
        }
        throw new IllegalArgumentException("No matching user found!");
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

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    //--- util methods --------------------------------------------------------

    public void init(String filePath) throws IOException {
        ClassPathResource cpr = new ClassPathResource("cli-users.json");
        users = objectMapper.readValue(cpr.getInputStream(), new TypeReference<List<User>>() { });
    }

    private long getNextId() {
        long maxId = 0;
        for(User user : users) {
            if (user.getId().longValue() > maxId) {
                maxId = user.getId().longValue();
            }
        }
        return maxId + 1;
    }

}
