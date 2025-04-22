package udem.edu.co.cda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udem.edu.co.cda.entities.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor,Integer> {
}
