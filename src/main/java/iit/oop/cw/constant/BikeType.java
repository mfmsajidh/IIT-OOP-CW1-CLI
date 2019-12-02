package iit.oop.cw.constant;

public enum BikeType {
    SCOOTER("S"),
    TRAILER("T"),
    RACING("R");

    // declaring private variable for getting values
    private String value;

    // getter method
    public String getValue()
    {
        return this.value;
    }

    // enum constructor - cannot be public or protected
    BikeType(String value)
    {
        this.value = value;
    }
}
