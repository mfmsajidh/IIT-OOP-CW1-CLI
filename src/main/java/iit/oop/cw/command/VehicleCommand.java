package iit.oop.cw.command;

import iit.oop.cw.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class VehicleCommand {

    @Autowired
    private VehicleService vehicleService;

    @ShellMethod("Create a new vehicle with the supplied details")
    public void addVehicle(@ShellOption String numberPlate) {
        vehicleService.insertVehicle(numberPlate);
    }
}
