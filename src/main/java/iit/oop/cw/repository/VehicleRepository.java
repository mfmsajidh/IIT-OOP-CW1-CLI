package iit.oop.cw.repository;

import iit.oop.cw.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {

    Optional<Vehicle> findByNumberPlate(String numberPlate);

    List<Vehicle> findAllByOrderByModelDesc();

}