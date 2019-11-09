package iit.oop.cw.constant;

public class ResponseConstant {

    // Status Code
    public static final int NO_RESPONSE_CODE  = 0;
    public static final int SUCCESS_CODE  = 1;
    public static final int INFO_CODE  = 2;
    public static final int WARNING_CODE  = 3;
    public static final int ERROR_CODE  = 4;

    // Status Type
    public static final String NO_RESPONSE_TYPE = "NO RESPONSE"; // When Status Code is 0
    public static final String SUCCESS_TYPE = "SUCCESS"; // When Status Code is 1
    public static final String INFO_TYPE = "INFO"; // When Status Code is 2
    public static final String WARNING_TYPE = "WARNING"; // When Status Code is 3
    public static final String ERROR_TYPE = "ERROR"; // When Status Code is 4

    // Inserting Vehicle
    public static final String SUCCESSFUL_VEHICLE_CREATION = "Successfully created vehicle!";
    public static final String VEHICLE_EXISTS = "Vehicle already exists!";
    public static final String NO_AVAILABLE_PARKING_LOT = "Cannot add vehicle. All parking lots are full";

    // Deleting Vehicle
    public static final String SUCCESSFUL_VEHICLE_DELETION = "Successfully deleted vehicle!";
    public static final String NO_VEHICLE_FOUND = "No such vehicle available!";

    // Resetting Space
    public static final String SUCCESSFUL_SPACE_RESET = "Successfully reset the parking lot space!";

}
