package iit.oop.cw.model;

import iit.oop.cw.constant.AppConstant;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class SystemParameter {

    @Id
    private ObjectId _id;
    private int availableSpaceCount;
    private String comment;

    public SystemParameter() {
        this.availableSpaceCount = AppConstant.MAXIMUM_PARKING_LOTS;
        this.comment = AppConstant.PARKING_LOT_COMMENT;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public int getAvailableSpaceCount() {
        return availableSpaceCount;
    }

    public void setAvailableSpaceCount(int availableSpaceCount) {
        this.availableSpaceCount = availableSpaceCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
