package iit.oop.cw.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Schedule {

    @Id
    private ObjectId _id;

    private LocalDate pickUpDate;
    private LocalDate dropOffDate;
    private LocalDate bookedDate;

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDate getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(LocalDate dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public LocalDate getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(LocalDate bookedDate) {
        this.bookedDate = bookedDate;
    }
}
