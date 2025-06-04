package udem.edu.co.cda.service;

import udem.edu.co.cda.entities.Materia;

import java.util.List;
import java.util.Optional;

public interface MateriaService {
    List<Materia> findAllMaterias();

    Optional<Materia> findByIdMateria(Long id);

    Materia createMateria(Materia materia);

    Materia updateMateria(Long id, Materia materia);

    void deleteMateria(Long id);
}
