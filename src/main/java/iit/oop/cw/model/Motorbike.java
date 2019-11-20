package iit.oop.cw.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehicle")
@TypeAlias("Motorbike")
public class Motorbike extends Vehicle {

    private String helmetProvided;
    private String bikeType;

    public String getHelmetProvided() {
        return helmetProvided;
    }

    public void setHelmetProvided(String helmetProvided) {
        this.helmetProvided = helmetProvided;
    }

    public String getBikeType() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }
}
