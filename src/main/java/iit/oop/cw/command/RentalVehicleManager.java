package iit.oop.cw.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public interface RentalVehicleManager {

    @ShellMethod
    public void addVehicle();

    @ShellMethod
    public void deleteVehicle(@ShellOption String numberPlate);

    @ShellMethod
    public void vehiclesByModel();
}
