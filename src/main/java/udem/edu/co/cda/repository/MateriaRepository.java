package udem.edu.co.cda.repository;

import org.springframework.data.repository.CrudRepository;
import udem.edu.co.cda.entities.Materia;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends CrudRepository<Materia, Integer> {
}