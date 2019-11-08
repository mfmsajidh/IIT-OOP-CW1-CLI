package iit.oop.cw.command;

import iit.oop.cw.constant.CONSTANT;
import iit.oop.cw.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;

@Component
public class WestminsterRentalVehicleManager implements RentalVehicleManager {

    @Autowired
    private VehicleService vehicleService;

    @Override
    @ShellMethod("Create a new vehicle with the supplied information")
    public void addVehicle() {
        vehicleService.insertVehicle();
    }

    @Override
    @ShellMethod("Delete a vehicle by it's number plate")
    public void deleteVehicle(@ShellOption String numberPlate) {
        vehicleService.deleteVehicle(numberPlate);
    }

    @Override
    @ShellMethod("Display all vehicles with the specified sorting")
    public void vehiclesByModel() {
        vehicleService.viewVehiclesByModel();
    }
}
