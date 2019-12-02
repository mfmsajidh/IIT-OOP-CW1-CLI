package iit.oop.cw.constant;

public enum DoorCount {

    TWO("2"),
    FOUR("4"),
    SIX("6");

    // declaring private variable for getting values
    private String value;

    // getter method
    public String getValue()
    {
        return this.value;
    }

    // enum constructor - cannot be public or protected
    DoorCount(String value)
    {
        this.value = value;
    }

}
