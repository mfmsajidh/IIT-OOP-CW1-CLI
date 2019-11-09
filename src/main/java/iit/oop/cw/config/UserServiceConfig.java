package iit.oop.cw.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import iit.oop.cw.observer.ProgressUpdateObserver;
import iit.oop.cw.repository.UserRepository;
import iit.oop.cw.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class UserServiceConfig {

    @Bean
    public UserRepository userRepository(ProgressUpdateObserver observer, ObjectMapper objectMapper) throws IOException {
        UserService userService = new UserService();
        userService.setObserver(observer);
        userService.setObjectMapper(objectMapper);
        userService.init("cli-users.json");
        return userService;
    }
}
