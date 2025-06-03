package udem.edu.co.cda.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import udem.edu.co.cda.entities.Profesor;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor, Integer> {
}
