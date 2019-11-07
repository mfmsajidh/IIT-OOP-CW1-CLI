package iit.oop.cw.service;

import iit.oop.cw.model.Vehicles;
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

    public void saveVehicle(String numberPlate) {

        Vehicles vehicles = new Vehicles();
        vehicles.setNumberPlate(numberPlate);

        // Read vehicle's type
        do {
            String type = inputReader.prompt("Vehicle Type");
            if (StringUtils.hasText(type)) {
                vehicles.setType(type);
            } else {
                shellHelper.printWarning("Vehicle type cannot be empty!");
            }
        } while (vehicles.getType().isEmpty());

        // Read vehicle's model
        do {
            String model = inputReader.prompt("Vehicle Model");
            if (StringUtils.hasText(model)) {
                vehicles.setModel(model);
            } else {
                shellHelper.printWarning("Vehicle model cannot be empty");
            }
        } while (vehicles.getModel().isEmpty());

        Vehicles createdVehicle = vehicleRepository.save(vehicles);

        // Print saved details
        shellHelper.printSuccess("Created vehicle with Id = " + createdVehicle.get_id());
        shellHelper.print("Number Plate: " + createdVehicle.getNumberPlate());
        shellHelper.print("Type: " + createdVehicle.getType());
        shellHelper.print("Model: " + createdVehicle.getModel());
    }
}
