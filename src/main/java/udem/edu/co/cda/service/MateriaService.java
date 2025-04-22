package udem.edu.co.cda.service;

import udem.edu.co.cda.entities.Materia;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public interface MateriaService {
    List<Materia> findAllMaterias() throws IOException, SQLException;
    Optional<Materia> findByIdMateria(int id) throws IOException, SQLException;
    Materia createMateria(Materia materia) throws IOException;
    Materia updateMateria(int id, Materia materia) throws IOException;
    void deleteMateria(int id) throws IOException;
}