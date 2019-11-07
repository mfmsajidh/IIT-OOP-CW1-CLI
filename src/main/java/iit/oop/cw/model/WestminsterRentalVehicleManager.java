package iit.oop.cw.model;

public class WestminsterRentalVehicleManager implements RentalVehicleManager {

    private int numberOfAvailableSlots = 50;

    public int getNumberOfAvailableSlots() {
        return numberOfAvailableSlots;
    }

    public void setNumberOfAvailableSlots(int numberOfAvailableSlots) {
        this.numberOfAvailableSlots = numberOfAvailableSlots;
    }

    @Override
    public void addVehicle() {};

    @Override
    public void deleteVehicle() {};

    @Override
    public String sortAvailableVehicleByModel() {
        return null;
    }

    public String listOfVehicles(){
        return "";
    }

}
