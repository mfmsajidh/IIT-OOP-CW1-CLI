package iit.oop.cw.repository;

import iit.oop.cw.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {

    @Query("{{'vehicle_id': ?0}}")
    List<Schedule> findAllByVehicleId(String id);

}
