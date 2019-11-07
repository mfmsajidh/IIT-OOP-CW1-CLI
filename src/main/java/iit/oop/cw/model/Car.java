package iit.oop.cw.model;

public class Car extends Vehicle{

    private int numberOfDoors;
    private boolean airConditioning;

    public Car() {};

    public Car(int numberOfDoors, boolean airConditioning) {
        this.numberOfDoors = numberOfDoors;
        this.airConditioning = airConditioning;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }
}
