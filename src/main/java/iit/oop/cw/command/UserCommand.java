package iit.oop.cw.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import iit.oop.cw.model.User;
import iit.oop.cw.repository.UserRepository;
import iit.oop.cw.constant.Gender;
import iit.oop.cw.shell.InputReader;
import iit.oop.cw.shell.ShellHelper;
import iit.oop.cw.shell.table.BeanTableModelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;
import org.springframework.util.StringUtils;

import java.util.*;

@ShellComponent
public class UserCommand {

    @Autowired
    ShellHelper shellHelper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    InputReader inputReader;
    @Autowired
    private ObjectMapper objectMapper;

    @ShellMethod("Create new user with supplied username")
    public void createUser(@ShellOption({"-U", "--username"}) String username) {
        if (userRepository.exists(username)) {
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
        String superuserValue = inputReader.promptWithOptions("New user is superuser", "N", Arrays.asList("Y", "N"));
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

        User createdUser = userRepository.create(user);
        shellHelper.printSuccess("Created user with id=" + createdUser.getId());

    }

    @ShellMethod("Update and synchronize all users in local database with external source")
    public void updateAllUsers() {
        shellHelper.printInfo("Starting local user db update");
        long numOfUsers = userRepository.updateAll();
        String successMessage = shellHelper.getSuccessMessage("SUCCESS >>");
        successMessage = successMessage + String.format(" Total of %d local db users updated!", numOfUsers);
        shellHelper.print(successMessage);
    }

    @ShellMethod("Display list of users")
    public void userList() {
        List<User> users = userRepository.findAll();

        LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
        headers.put("id", "Id");
        headers.put("username", "Username");
        headers.put("fullName", "Full name");
        headers.put("gender", "Gender");
        headers.put("superuser", "Superuser");
        TableModel model = new BeanListTableModel<>(users, headers);

        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        shellHelper.print(tableBuilder.build().render(80));
    }

    @ShellMethod("Display details of user with supplied username")
    public void userDetails(@ShellOption({"-U", "--username"}) String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            shellHelper.printWarning("No user with the supplied username could be found?!");
            return;
        }
        displayUser(user);
    }

    private void displayUser(User user) {
        LinkedHashMap<String, Object> labels = new LinkedHashMap<>();
        labels.put("id", "Id");
        labels.put("username", "Username");
        labels.put("fullName", "Full name");
        labels.put("gender", "Gender");
        labels.put("superuser", "Superuser");
        labels.put("password", "Password");

        String[] header = new String[] {"Property", "Value"};
        BeanTableModelBuilder builder = new BeanTableModelBuilder(user, objectMapper);
        TableModel model = builder.withLabels(labels).withHeader(header).build();

        TableBuilder tableBuilder = new TableBuilder(model);

        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        tableBuilder.on(CellMatchers.column(0)).addSizer(new AbsoluteWidthSizeConstraints(20));
        tableBuilder.on(CellMatchers.column(1)).addSizer(new AbsoluteWidthSizeConstraints(30));
        shellHelper.print(tableBuilder.build().render(80));
    }
}
