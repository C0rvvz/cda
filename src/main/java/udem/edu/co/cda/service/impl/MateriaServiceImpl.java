package udem.edu.co.cda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udem.edu.co.cda.entities.Materia;
import udem.edu.co.cda.repository.MateriaRepository;
import udem.edu.co.cda.service.MateriaService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    MateriaRepository materiaRepository;

    @Override
    public List<Materia> findAllMaterias() throws IOException, SQLException {
        return (List<Materia>) materiaRepository.findAll();
    }

    @Override
    public Optional<Materia> findByIdMateria(int id) throws IOException, SQLException {
        return (Optional<Materia>) materiaRepository.findById(id);
    }

    @Override
    public Materia createMateria(Materia materia) throws IOException {
        return (Materia) materiaRepository.save(materia);
    }

    @Override
    public Materia updateMateria(int id, Materia materia) throws IOException {
        return (Materia) materiaRepository.save(materia);
    }

    @Override
    public void deleteMateria(int id) throws IOException {
        Materia materiaEliminada = new Materia();
        materiaEliminada.setId(id);
        materiaRepository.delete(materiaEliminada);
    }
}