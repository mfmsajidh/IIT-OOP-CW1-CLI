package iit.oop.cw.config;

import iit.oop.cw.observer.ProgressUpdateObserver;
import iit.oop.cw.repository.UserRepository;
import iit.oop.cw.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfig {

    @Bean
    public UserRepository userRepository(ProgressUpdateObserver observer) {
        UserService userService = new UserService();
        userService.setObserver(observer);
        return userService;
    }
}
