package iit.oop.cw.service;

import iit.oop.cw.constant.*;
import iit.oop.cw.model.*;
import iit.oop.cw.repository.CarRepository;
import iit.oop.cw.repository.MotorBikeRepository;
import iit.oop.cw.repository.SystemParameterRepository;
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
    @Autowired
    private SystemParameterRepository systemParameterRepository;

//    private int availableParkingLot = AppConstant.MAXIMUM_PARKING_LOTS;

    public Response insertVehicle() {

        Response response = new Response();

        SystemParameter systemParameter = systemParameterRepository.findByComment(AppConstant.PARKING_LOT_COMMENT);
        int availableSpaceCount = systemParameter.getAvailableSpaceCount();

        if (availableSpaceCount > 0) {

            Vehicle vehicle = new Vehicle();
            Car car = new Car();
            Motorbike motorbike = new Motorbike();

            // Read vehicle's type
            Map<String, String> vehicleTypeOptions = new HashMap<>();

            Arrays.asList( VehicleType.values())
                    .forEach( vehicleTypeOption ->
                            vehicleTypeOptions.put(vehicleTypeOption.getValue(), vehicleTypeOption.name())
                    );

            String vehicleTypeValue = inputReader.selectFromList(AppConstant.VEHICLE_TYPE_HEADING, InputReaderPrompt.VEHICLE_TYPE, vehicleTypeOptions, false, null);

            VehicleType vehicleType = VehicleType.valueOf(vehicleTypeOptions.get(vehicleTypeValue.toUpperCase()));
            switch (vehicleType) {
                case CAR:
                    car.setType(vehicleType.name());
                    break;
                case MOTORBIKE:
                    motorbike.setType(vehicleType.name());
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

            switch (vehicleType) {
                case CAR:
                    // Read car's number of doors
                    do {
                        // Read door count values
                        Map<String, String> doorCountOptions = new HashMap<>();
                        Arrays.asList( DoorCount.values())
                                .forEach( doorCount ->
                                        doorCountOptions.put(doorCount.getValue(), doorCount.name())
                                );

                        String numberOfDoors = inputReader.selectFromList(AppConstant.NUMBER_OF_DOORS, InputReaderPrompt.CAR_DOOR_COUNT, doorCountOptions, false, null);
                        if (StringUtils.hasText(numberOfDoors)) {
                                car.setNumberOfDoors(numberOfDoors);
                        } else {
                            shellHelper.printWarning(ValidationMessage.EMPTY_CAR_DOOR);
                        }
                    } while (car.getNumberOfDoors() == null);

                    // Read car's air conditioning
                    do {
                        // Read availability values
                        Map<String, String> availabilityTypeOptions = new HashMap<>();
                        Arrays.asList( AvailabilityType.values())
                                .forEach(availabilityType ->
                                        availabilityTypeOptions.put(availabilityType.getValue(), availabilityType.name())
                                );
                        String airConditioning = inputReader.selectFromList(AppConstant.AVAILABILITY_TYPE_HEADING, InputReaderPrompt.AIR_CONDITION, availabilityTypeOptions, false, null);
                        if (StringUtils.hasText(airConditioning)) {
                                car.setAirConditioning(airConditioning);
                        } else {
                            shellHelper.printWarning(ValidationMessage.EMPTY_CAR_AC);
                        }
                    } while (car.getAirConditioning() == null);
                    break;
                case MOTORBIKE:
                    // Read motobike's helmet availability
                    do {

                        // Read availability values
                        Map<String, String> availabilityTypeOptions = new HashMap<>();
                        Arrays.asList( AvailabilityType.values())
                                .forEach(availabilityType ->
                                        availabilityTypeOptions.put(availabilityType.getValue(), availabilityType.name())
                                );
                        String helmetProvided = inputReader.selectFromList(AppConstant.AVAILABILITY_TYPE_HEADING, InputReaderPrompt.HELMET_PROVIDED, availabilityTypeOptions, false, null);

//                        String helmetProvided = inputReader.prompt(InputReaderPrompt.HELMET_PROVIDED);
                        if (StringUtils.hasText(helmetProvided)) {
                            motorbike.setHelmetProvided(helmetProvided);
                        } else {
                            shellHelper.printWarning(ValidationMessage.EMPTY_BIKE_HELMET);
                        }
                    } while (motorbike.getHelmetProvided() == null);

                    // Read motobike's type
                    do {

                        Map<String, String> bikeTypeOptions = new HashMap<>();
                        Arrays.asList( BikeType.values())
                                .forEach( bikeType ->
                                        bikeTypeOptions.put(bikeType.getValue(), bikeType.name())
                                );

                        String bikeType = inputReader.selectFromList(AppConstant.BIKE_TYPE, InputReaderPrompt.BIKE_TYPE, bikeTypeOptions, false, null);

                        if (StringUtils.hasText(bikeType)) {
                            motorbike.setBikeType(bikeType);
                        } else {
                            shellHelper.printWarning(ValidationMessage.EMPTY_BIKE_TYPE);
                        }
                    } while (motorbike.getBikeType() == null);
                    break;
            }

            try {

                switch (vehicleType) {
                    case CAR:
                        carRepository.insert(car);
                        break;
                    case MOTORBIKE:
                        motorBikeRepository.insert(motorbike);
                }

                systemParameter.setAvailableSpaceCount(--availableSpaceCount);
                systemParameterRepository.save(systemParameter);

                response.setStatusCode(ResponseConstant.SUCCESS_CODE);
                response.setStatusMessage(ResponseConstant.SUCCESSFUL_VEHICLE_CREATION);

                shellHelper.printInfo(ShellHelperPrompt.AVAILABLE_SPACE + systemParameter.getAvailableSpaceCount());
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
                shellHelper.printInfo(ShellHelperPrompt.VEHICLE_NUMBERPLATE + vehicle.get().getNumberPlate());
                shellHelper.print(ShellHelperPrompt.VEHICLE_TYPE + vehicle.get().getType());
                shellHelper.print(ShellHelperPrompt.VEHICLE_MODEL + vehicle.get().getModel());
                try {
                    vehicleRepository.deleteById(vehicle.get().get_id());

                    response.setStatusCode(ResponseConstant.SUCCESS_CODE);
                    response.setStatusMessage(ResponseConstant.SUCCESSFUL_VEHICLE_DELETION);

                    SystemParameter systemParameter = systemParameterRepository.findByComment(AppConstant.PARKING_LOT_COMMENT);
                    int availableSpaceCount = systemParameter.getAvailableSpaceCount();
                    systemParameter.setAvailableSpaceCount(++availableSpaceCount);
                    systemParameterRepository.save(systemParameter);
                    shellHelper.printInfo(ShellHelperPrompt.AVAILABLE_SPACE + systemParameter.getAvailableSpaceCount());

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
            shellHelper.print(ShellHelperPrompt.VEHICLE_MODEL + vehicle.getModel());
            shellHelper.print(ShellHelperPrompt.VEHICLE_NUMBERPLATE + vehicle.getNumberPlate());
            shellHelper.print(ShellHelperPrompt.VEHICLE_TYPE + vehicle.getType());
        }
    }

    public Response resetSpace() {
        Response response = new Response();
        SystemParameter systemParameter = new SystemParameter();
        try {
            systemParameterRepository.save(systemParameter);
            response.setStatusCode(ResponseConstant.SUCCESS_CODE);
            response.setStatusMessage(ResponseConstant.SUCCESSFUL_SPACE_RESET);
        } catch (Exception e) {
            response.setStatusCode(ResponseConstant.ERROR_CODE);
            response.setStatusMessage(e.getMessage());
        }
        return response;
    }
}
