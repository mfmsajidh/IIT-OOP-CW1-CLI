package iit.oop.cw.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Customer {

    @Id
    private ObjectId _id;

    private String customerName;
    private String phoneNumber;

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
