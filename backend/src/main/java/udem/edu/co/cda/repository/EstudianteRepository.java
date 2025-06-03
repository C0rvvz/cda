package udem.edu.co.cda.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import udem.edu.co.cda.entities.Estudiante;

@Repository
public interface EstudianteRepository extends CrudRepository<Estudiante, Integer> {
}
