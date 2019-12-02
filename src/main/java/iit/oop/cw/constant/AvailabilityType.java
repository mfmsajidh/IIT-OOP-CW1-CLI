package iit.oop.cw.constant;

public enum AvailabilityType {
    YES("Yes"),
    NO("No");

    // declaring private variable for getting values
    private String value;

    // getter method
    public String getValue()
    {
        return this.value;
    }

    // enum constructor - cannot be public or protected
    AvailabilityType(String value)
    {
        this.value = value;
    }
}
