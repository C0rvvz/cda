package udem.edu.co.cda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udem.edu.co.cda.entities.Profesor;
import udem.edu.co.cda.repository.ProfesorRepository;
import udem.edu.co.cda.service.ProfesorService;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServiceImpl implements ProfesorService {
    @Autowired
    ProfesorRepository profesorRepository;

    @Override
    public List<Profesor> findAllProfesores() throws IOException, SQLException{
        return (List<Profesor>) profesorRepository.findAll();
    }

    @Override
    public Optional<Profesor> findByIdProfesor(int id) throws IOException, SQLException {
        return (Optional<Profesor>) profesorRepository.findById(id);
    }

    @Override
    public Profesor createProfesor(Profesor profesor) throws IOException{
        return (Profesor) profesorRepository.save(profesor);
    }

    @Override
    public Profesor updateProfesor(int id, Profesor profesor) throws IOException{
        return (Profesor) profesorRepository.save(profesor);
    }

    @Override
    public void deleteProfesor(int id) throws IOException{
        Profesor profesorEliminado = new Profesor();
        profesorEliminado.setId(id);
        profesorRepository.delete(profesorEliminado);
    }
}