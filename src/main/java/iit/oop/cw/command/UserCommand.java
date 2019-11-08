package iit.oop.cw.command;

import iit.oop.cw.model.User;
import iit.oop.cw.service.UserService;
import iit.oop.cw.constant.Gender;
import iit.oop.cw.shell.InputReader;
import iit.oop.cw.shell.ShellHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ShellComponent
public class UserCommand {

    @Autowired
    ShellHelper shellHelper;
    @Autowired
    UserService userService;
    @Autowired
    InputReader inputReader;

    @ShellMethod("Create new user with supplied username")
    public void createUser(@ShellOption({"-U", "--username"}) String username) {
        if (userService.exists(username)) {
            shellHelper.printError(
                    String.format("User with username='%s' already exists --> ABORTING", username)
            );
        }

        User user = new User();
        user.setUsername(username);

        // Read user's full name
        do {
            String fullname = inputReader.prompt("Full name");
            if (StringUtils.hasText(fullname)) {
                user.setFullName(fullname);
            } else {
                shellHelper.printWarning("User's full name CAN NOT be empty string! Please enter valid value!");
            }
        } while (user.getFullName() == null);

        // Read user's password
        do {
            String password = inputReader.prompt("Password", "secret", false);
            if (StringUtils.hasText(password)) {
                user.setPassword(password);
            } else {
                shellHelper.printWarning("Password CAN NOT be empty String! Please enter valid value!");
            }
        } while (user.getPassword() == null);

        // Read user's gender
        Map<String, String> options = new HashMap<>();
        options.put("M", Gender.MALE.name());
        options.put("F", Gender.FEMALE.name());
        options.put("D", Gender.DIVERSE.name());

        String genderValue = inputReader.selectFromList("Gender", "Please enter one of the [] values", options, true, null);
        Gender gender = Gender.valueOf(options.get(genderValue.toUpperCase()));
        user.setGender(gender);

        //Prompts for superuser attribute
        String superuserValue = inputReader.promptWithOptions("New user is suepruser", "N", Arrays.asList("Y", "N"));
        if ("Y".equals(superuserValue)) {
            user.setSuperuser(true);
        } else {
            user.setSuperuser(false);
        }

        // Print user's input
        shellHelper.printInfo("\nCreating new user:");
        shellHelper.print("\nUsername: " + user.getUsername());
        shellHelper.print("Password: " + user.getPassword());
        shellHelper.print("Full name: " + user.getFullName());
        shellHelper.print("Gender: " + user.getGender());
        shellHelper.print("Superuser: " + user.isSuperuser() + "\n");

        User createdUser = userService.create(user);
        shellHelper.printSuccess("Created user with id=" + createdUser.getId());

    }

    @ShellMethod("Update and synchronize all users in local database with external source")
    public void updateAllUsers() {
        shellHelper.printInfo("Starting local user db update");
        long numOfUsers = userService.updateAll();
        String successMessage = shellHelper.getSuccessMessage("SUCCESS >>");
        successMessage = successMessage + String.format(" Total of %d local db users updated!", numOfUsers);
        shellHelper.print(successMessage);
    }
}
