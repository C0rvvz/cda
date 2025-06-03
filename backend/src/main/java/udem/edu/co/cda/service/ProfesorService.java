package udem.edu.co.cda.service;

import udem.edu.co.cda.entities.Profesor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProfesorService {
    List<Profesor> findAllProfesores() throws IOException, SQLException;
    Optional<Profesor> findByIdProfesor(int id) throws IOException, SQLException;
    Profesor createProfesor(Profesor profesor) throws IOException;
    Profesor updateProfesor(int id, Profesor profesor) throws IOException;
    void deleteProfesor(int id) throws IOException;
}