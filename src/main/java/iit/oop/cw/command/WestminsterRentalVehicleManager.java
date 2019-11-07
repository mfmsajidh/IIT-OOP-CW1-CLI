package iit.oop.cw.command;

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
    @ShellMethod("Displays greeting message to the user whose name is supplied")
    public void addVehicle(@ShellOption String numberPlate) {
        vehicleService.insertVehicle(numberPlate);
    }

    @Override
    @ShellMethod("Displays greeting message to the user whose name is supplied")
    public void deleteVehicle(@ShellOption String numberPlate) {
        vehicleService.deleteVehicle(numberPlate);
    }

    @Override
    @ShellMethod("Displays greeting message to the user whose name is supplied")
    public void vehiclesByModel() {
        vehicleService.viewVehiclesByModel();
    }
}
