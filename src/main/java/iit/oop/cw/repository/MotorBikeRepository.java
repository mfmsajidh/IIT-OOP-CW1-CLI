package iit.oop.cw.repository;

import iit.oop.cw.model.Motorbike;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorBikeRepository extends MongoRepository<Motorbike, String> {
}
