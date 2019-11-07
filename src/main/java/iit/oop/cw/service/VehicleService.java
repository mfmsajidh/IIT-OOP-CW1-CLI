package iit.oop.cw.service;

import iit.oop.cw.model.Vehicle;
import iit.oop.cw.repository.VehicleRepository;
import iit.oop.cw.shell.InputReader;
import iit.oop.cw.shell.ShellHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class VehicleService {

    @Autowired
    private ShellHelper shellHelper;
    @Autowired
    private InputReader inputReader;
    @Autowired
    private VehicleRepository vehicleRepository;

    public void insertVehicle(String numberPlate) {

        Vehicle vehicle = new Vehicle();
        vehicle.setNumberPlate(numberPlate);

        // Read vehicle's type
        do {
            String type = inputReader.prompt("Vehicle Type");
            if (StringUtils.hasText(type)) {
                vehicle.setType(type);
            } else {
                shellHelper.printWarning("Vehicle type cannot be empty!");
            }
        } while (vehicle.getType().isEmpty());

        // Read vehicle's model
        do {
            String model = inputReader.prompt("Vehicle Model");
            if (StringUtils.hasText(model)) {
                vehicle.setModel(model);
            } else {
                shellHelper.printWarning("Vehicle model cannot be empty");
            }
        } while (vehicle.getModel().isEmpty());

        vehicleRepository.insert(vehicle);
        shellHelper.printSuccess("Successfully created vehicle!");

    }

    public void deleteVehicle(String numberPlate) {

        Vehicle vehicle = new Vehicle();
        vehicle = vehicleRepository.findByNumberPlate(numberPlate);
        vehicleRepository.deleteById(vehicle.get_id());

    }
}
