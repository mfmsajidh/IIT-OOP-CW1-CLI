package iit.oop.cw.constant;

public enum VehicleType {
    CAR("C"),
    MOTORBIKE("M");

    // declaring private variable for getting values
    private String value;

    // getter method
    public String getValue()
    {
        return this.value;
    }

    // enum constructor - cannot be public or protected
    VehicleType(String value)
    {
        this.value = value;
    }
}
