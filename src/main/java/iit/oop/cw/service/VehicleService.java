package iit.oop.cw.service;

import iit.oop.cw.constant.*;
import iit.oop.cw.model.Car;
import iit.oop.cw.model.Motorbike;
import iit.oop.cw.model.Response;
import iit.oop.cw.model.Vehicle;
import iit.oop.cw.repository.CarRepository;
import iit.oop.cw.repository.MotorBikeRepository;
import iit.oop.cw.repository.VehicleRepository;
import iit.oop.cw.shell.InputReader;
import iit.oop.cw.shell.ShellHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class VehicleService {

    @Autowired
    private ShellHelper shellHelper;
    @Autowired
    private InputReader inputReader;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private MotorBikeRepository motorBikeRepository;

    private int availableParkingLot = AppConstant.MAXIMUM_PARKING_LOTS;

    public Response insertVehicle() {

        Response response = new Response();

        if (availableParkingLot > 0) {
            Vehicle vehicle = new Vehicle();
            Car car = new Car();
            Motorbike motorbike = new Motorbike();

            // Read vehicle's type
            Map<String, String> vehicleTypeOptions = new HashMap<>();

            Arrays.asList( VehicleType.values())
                    .forEach( vehicleTypeOption ->
                            vehicleTypeOptions.put(vehicleTypeOption.getValue(), vehicleTypeOption.name())
                    );

            String vehicleTypeValue = inputReader.selectFromList(AppConstant.VEHICLE_TYPE_HEADING, InputReaderPrompt.VEHICLE_TYPE, vehicleTypeOptions, true, null);

            VehicleType vehicleType = VehicleType.valueOf(vehicleTypeOptions.get(vehicleTypeValue.toUpperCase()));
            switch (vehicleType) {
                case CAR:
                    car.setType(vehicleType);
                    break;
                case MOTORBIKE:
                    motorbike.setType(vehicleType);
                    break;
            }

            // Read vehicle's number plate
            do {
                String numberPlate = inputReader.prompt(InputReaderPrompt.VEHICLE_NUMBER_PLATE);
                if (StringUtils.hasText(numberPlate)) {
                    try {
                        Optional<Vehicle> vehicleExists = vehicleRepository.findByNumberPlate(numberPlate);
                        if (vehicleExists.isPresent()) {
                            response.setStatusCode(ResponseConstant.INFO_CODE);
                            response.setStatusMessage(ResponseConstant.VEHICLE_EXISTS);
                            return response;
                        } else {
                            vehicle.setNumberPlate(numberPlate);
                            switch (vehicleType) {
                                case CAR:
                                    car.setNumberPlate(numberPlate);
                                    break;
                                case MOTORBIKE:
                                    motorbike.setNumberPlate(numberPlate);
                                    break;
                            }
                        }
                    } catch (Exception e) {
                        response.setStatusCode(ResponseConstant.ERROR_CODE);
                        response.setStatusMessage(e.getMessage());
                        return response;
                    }
                } else {
                    shellHelper.printWarning(ValidationMessage.EMPTY_NUMBER_LATE);
                }
            } while (vehicle.getNumberPlate() == null);



            // Read vehicle's model
            do {
                String model = inputReader.prompt(InputReaderPrompt.VEHICLE_MODEL);
                if (StringUtils.hasText(model)) {
                    vehicle.setModel(model);
                    switch (vehicleType) {
                        case CAR:
                            car.setModel(model);
                            break;
                        case MOTORBIKE:
                            motorbike.setModel(model);
                            break;
                    }
                } else {
                    shellHelper.printWarning(ValidationMessage.EMPTY_VEHICLE_MODEL);
                }
            } while (vehicle.getModel() == null);

            switch (vehicle.getType()) {
                case CAR:
                    // Read car's number of doors
                    do {
                        String numberOfDoors = inputReader.prompt(InputReaderPrompt.CAR_DOOR_COUNT);
                        if (StringUtils.hasText(numberOfDoors)) {
                                car.setNumberOfDoors(numberOfDoors);
                        } else {
                            shellHelper.printWarning(ValidationMessage.EMPTY_CAR_DOOR);
                        }
                    } while (car.getNumberOfDoors() == null);

                    // Read car's air conditioning
                    do {
                        String airConditioning = inputReader.prompt(InputReaderPrompt.AIR_CONDITION);
                        if (StringUtils.hasText(airConditioning)) {
                                car.setAirConditioning(airConditioning);
                        } else {
                            shellHelper.printWarning(ValidationMessage.EMPTY_CAR_AC);
                        }
                    } while (car.getAirConditioning() == null);

                case MOTORBIKE:
                    // Read motobike's helmet availability
                    do {
                        String helmetProvided = inputReader.prompt(InputReaderPrompt.HELMET_PROVIDED);
                        if (StringUtils.hasText(helmetProvided)) {
                            motorbike.setHelmetProvided(helmetProvided);
                        } else {
                            shellHelper.printWarning(ValidationMessage.EMPTY_BIKE_HELMET);
                        }
                    } while (motorbike.getHelmetProvided() == null);

                    // Read motobike's type
                    do {
                        String bikeType = inputReader.prompt(InputReaderPrompt.BIKE_TYPE);
                        if (StringUtils.hasText(bikeType)) {
                            motorbike.setBikeType(bikeType);
                        } else {
                            shellHelper.printWarning(ValidationMessage.EMPTY_BIKE_TYPE);
                        }
                    } while (motorbike.getBikeType() == null);
            }

            try {

                switch (vehicleType) {
                    case CAR:
                        carRepository.insert(car);
                        break;
                    case MOTORBIKE:
                        motorBikeRepository.insert(motorbike);
                }

                response.setStatusCode(ResponseConstant.SUCCESS_CODE);
                response.setStatusMessage(ResponseConstant.SUCCESSFUL_VEHICLE_CREATION);

                availableParkingLot--;
                shellHelper.printInfo(ShellHelperPrompt.AVAILABLE_SPACE);
            } catch (Exception e) {
                response.setStatusCode(ResponseConstant.ERROR_CODE);
                response.setStatusMessage(e.getMessage());
            }
        } else {
            response.setStatusCode(ResponseConstant.INFO_CODE);
            response.setStatusMessage(ResponseConstant.NO_AVAILABLE_PARKING_LOT);
        }
        return response;
    }

    public Response deleteVehicle() {
        Response response = new Response();

        String numberPlate = inputReader.prompt(InputReaderPrompt.VEHICLE_NUMBER_PLATE);
        if (StringUtils.hasText(numberPlate)) {
            Optional<Vehicle> vehicle = vehicleRepository.findByNumberPlate(numberPlate);
            if (vehicle.isPresent()) {
                shellHelper.printInfo(ShellHelperPrompt.DELETE_VEHICLE_BY_NUMBERPLATE + vehicle.get().getNumberPlate());
                shellHelper.print(ShellHelperPrompt.VEHICLE_TYPE + vehicle.get().getType());
                shellHelper.print(ShellHelperPrompt.VEHICLE_MODEL + vehicle.get().getModel());
                try {
                    vehicleRepository.deleteById(vehicle.get().get_id());

                    response.setStatusCode(ResponseConstant.SUCCESS_CODE);
                    response.setStatusMessage(ResponseConstant.SUCCESSFUL_VEHICLE_DELETION);

                    availableParkingLot++;
                    shellHelper.printInfo(ShellHelperPrompt.AVAILABLE_SPACE);

                } catch (Exception e) {
                    response.setStatusCode(ResponseConstant.ERROR_CODE);
                    response.setStatusMessage(e.getMessage());
                }
            } else {
                response.setStatusCode(ResponseConstant.INFO_CODE);
                response.setStatusMessage(ResponseConstant.NO_VEHICLE_FOUND);
            }
        } else  {
            response.setStatusCode(ResponseConstant.WARNING_CODE);
            response.setStatusMessage(ValidationMessage.EMPTY_NUMBER_LATE);
        }
        return response;
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
