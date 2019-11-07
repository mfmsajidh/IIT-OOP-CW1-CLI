package iit.oop.cw.repository;

import iit.oop.cw.model.Vehicles;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicles, String> {
    Vehicles findBy_id (ObjectId _id);
}