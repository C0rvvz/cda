package udem.edu.co.cda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udem.edu.co.cda.entities.Materia;
import udem.edu.co.cda.repository.MateriaRepository;
import udem.edu.co.cda.service.MateriaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public List<Materia> findAllMaterias() {
        Iterable<Materia> iterable = materiaRepository.findAll();
        List<Materia> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    @Override
    public Optional<Materia> findByIdMateria(Long id) {
        return materiaRepository.findById(id);
    }

    @Override
    public Materia createMateria(Materia materia) {
        return materiaRepository.save(materia);
    }

    @Override
    public Materia updateMateria(Long id, Materia materia) {
        Materia existingMateria = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con id: " + id));

        existingMateria.setNombre(materia.getNombre());

        return materiaRepository.save(existingMateria);
    }

    @Override
    public void deleteMateria(Long id) {
        if (materiaRepository.existsById(id)) {
            materiaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Materia no encontrada con id: " + id);
        }
    }
}
