package iit.oop.cw.service;

import iit.oop.cw.constant.AppConstant;
import iit.oop.cw.model.Vehicle;
import iit.oop.cw.repository.VehicleRepository;
import iit.oop.cw.shell.InputReader;
import iit.oop.cw.shell.ShellHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private ShellHelper shellHelper;
    @Autowired
    private InputReader inputReader;
    @Autowired
    private VehicleRepository vehicleRepository;

    private int availableParkingLot = AppConstant.MAXIMUM_PARKING_LOTS;

    public void insertVehicle() {
        if (availableParkingLot > 0) {
            Vehicle vehicle = new Vehicle();

            // Read vehicle's number plate
            do {
                String numberPlate = inputReader.prompt("Vehicle Number Plate");
                if (StringUtils.hasText(numberPlate)) {
                    vehicle.setNumberPlate(numberPlate);
                } else {
                    shellHelper.printWarning("Vehicle number plate cannot be empty!");
                }
            } while (vehicle.getNumberPlate() == null);

            // Read vehicle's type
            do {
                String type = inputReader.prompt("Vehicle Type");
                if (StringUtils.hasText(type)) {
                    vehicle.setType(type);
                } else {
                    shellHelper.printWarning("Vehicle type cannot be empty!");
                }
            } while (vehicle.getType() == null);

            // Read vehicle's model
            do {
                String model = inputReader.prompt("Vehicle Model");
                if (StringUtils.hasText(model)) {
                    vehicle.setModel(model);
                } else {
                    shellHelper.printWarning("Vehicle model cannot be empty!");
                }
            } while (vehicle.getModel() == null);

            vehicleRepository.insert(vehicle);
            shellHelper.printSuccess("Successfully created vehicle!");
        } else {
            shellHelper.printInfo(AppConstant.NO_AVAILABLE_PARKING_LOT);
        }
    }

    public void deleteVehicle(String numberPlate) {
        Vehicle vehicle = vehicleRepository.findByNumberPlate(numberPlate);
        shellHelper.printInfo("Deleting vehicle with number plate: " + vehicle.getNumberPlate());
        shellHelper.print("Vehicle Type: " + vehicle.getType());
        shellHelper.print("Vehicle Model: " + vehicle.getModel());

        vehicleRepository.deleteById(vehicle.get_id());
        shellHelper.printSuccess("Successfully Deleted Vehicle!");
    }

    public void viewVehiclesByModel() {

        List<Vehicle> vehicles = vehicleRepository.findAllByOrderByModelDesc();

        for (Vehicle vehicle: vehicles) {
            shellHelper.print("Model" + vehicle.getModel());
            shellHelper.print("_id" + vehicle.get_id());
            shellHelper.print("Number Plate" + vehicle.getNumberPlate());
            shellHelper.print("Type" + vehicle.getType());
        }
    }
}
