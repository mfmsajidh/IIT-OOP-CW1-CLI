package iit.oop.cw.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Vehicles {

    @Id
    private ObjectId _id;

    private String numberPlate;
    private String type;
    private String model;

    public Vehicles(){}

    public Vehicles(ObjectId _id, String numberPlate, String type, String model) {
        this._id = _id;
        this.numberPlate = numberPlate;
        this.type = type;
        this.model = model;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
