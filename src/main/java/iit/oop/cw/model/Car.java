package iit.oop.cw.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehicle")
public class Car extends Vehicle{

    private String numberOfDoors;
    private String airConditioning;

    public String getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(String numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public String getAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(String airConditioning) {
        this.airConditioning = airConditioning;
    }
}
