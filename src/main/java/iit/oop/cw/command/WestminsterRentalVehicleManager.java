package iit.oop.cw.command;

import iit.oop.cw.model.Response;
import iit.oop.cw.service.ResponseService;
import iit.oop.cw.service.VehicleService;
import iit.oop.cw.shell.ShellHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;

@Component
public class WestminsterRentalVehicleManager implements RentalVehicleManager {

    @Autowired
    ShellHelper shellHelper;
    @Autowired
    ResponseService responseService;
    @Autowired
    private VehicleService vehicleService;

    @Override
    @ShellMethod("Create a new vehicle with the supplied information")
    public void addVehicle() {
        Response response = vehicleService.insertVehicle();
        responseService.respond(response.getStatusCode(), response.getStatusMessage());
    }

    @Override
    @ShellMethod("Delete a vehicle by it's number plate")
    public void deleteVehicle() {
        Response response = vehicleService.deleteVehicle();
        responseService.respond(response.getStatusCode(), response.getStatusMessage());
    }

    @Override
    @ShellMethod("Display all vehicles with the supplied sort")
    public void viewVehicles() {
        vehicleService.viewVehiclesByModel();
    }

    @ShellMethod("Resets parking lot space")
    public void resetSpace() {
        Response response = vehicleService.resetSpace();
        responseService.respond(response.getStatusCode(), response.getStatusMessage());
    }
}
