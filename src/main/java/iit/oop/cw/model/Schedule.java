package iit.oop.cw.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
public class Schedule {

    @Id
    private String _id;

    private LocalDate pickUpDate;
    private LocalDate dropOffDate;
    private String customer_id;
    private String vehicle_id;
}
