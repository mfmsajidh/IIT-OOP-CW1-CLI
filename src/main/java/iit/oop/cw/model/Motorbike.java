package iit.oop.cw.model;

public class Motorbike extends Vehicle {

    private boolean helmetProvided;
    private String bikeType;

    public boolean isHelmetProvided() {
        return helmetProvided;
    }

    public void setHelmetProvided(boolean helmetProvided) {
        this.helmetProvided = helmetProvided;
    }

    public String getBikeType() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }
}
