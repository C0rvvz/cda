package udem.edu.co.cda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udem.edu.co.cda.entities.Estudiante;
import udem.edu.co.cda.repository.EstudianteRepository;
import udem.edu.co.cda.service.EstudianteService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> findAllEstudiantes() throws IOException, SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        estudianteRepository.findAll().forEach(estudiantes::add);
        return estudiantes;
    }

    @Override
    public Optional<Estudiante> findByIdEstudiante(int id) throws IOException, SQLException {
        return estudianteRepository.findById(id);
    }

    @Override
    public Estudiante createEstudiante(Estudiante estudiante) throws IOException {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante updateEstudiante(int id, Estudiante estudiante) throws IOException {
        estudiante.setId(id);
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void deleteEstudiante(int id) throws IOException {
        Estudiante estudianteEliminado = new Estudiante();
        estudianteEliminado.setId(id);
        estudianteRepository.delete(estudianteEliminado);
    }
}