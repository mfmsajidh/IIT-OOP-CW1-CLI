package iit.oop.cw.command;

import iit.oop.cw.shell.ShellHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class VehicleCommand {

    @Autowired
    ShellHelper shellHelper;

    @ShellMethod("Create a new vehicle with the supplied details")
    public void addVehicle(@ShellOption String vehiclePlateNumber) {
        shellHelper.printInfo(vehiclePlateNumber);
    }
}
