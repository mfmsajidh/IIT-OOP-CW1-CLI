package iit.oop.cw.repository;

import iit.oop.cw.model.SystemParameter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemParameterRepository extends MongoRepository<SystemParameter, String> {

    SystemParameter findByComment(String comment);

    SystemParameter queryByComment(String comment);

}
