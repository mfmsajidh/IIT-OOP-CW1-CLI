package iit.oop.cw.repository;

import iit.oop.cw.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {

    Vehicle findByNumberPlate(String numberPlate);

}