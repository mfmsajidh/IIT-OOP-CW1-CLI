package iit.oop.cw.model;

import iit.oop.cw.constant.VehicleType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Vehicle {

    @Id
    private ObjectId _id;

    private String numberPlate;
    private VehicleType type;
    private String model;

//    ObjectId needs to be converted to string
    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
