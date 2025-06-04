package udem.edu.co.cda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import udem.edu.co.cda.entities.Materia;
import udem.edu.co.cda.service.MateriaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cda/materias")
@CrossOrigin(origins = "http://localhost:3000")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public List<Materia> getAllMaterias() {
        return materiaService.findAllMaterias();
    }

    @GetMapping("/{id}")
    public Optional<Materia> getMateriaById(@PathVariable Long id) {
        return materiaService.findByIdMateria(id);
    }

    @PostMapping
    public Materia createMateria(@RequestBody Materia materia) {
        return materiaService.createMateria(materia);
    }

    @PutMapping("/{id}")
    public Materia updateMateria(@PathVariable Long id, @RequestBody Materia materia) {
        return materiaService.updateMateria(id, materia);
    }

    @DeleteMapping("/{id}")
    public void deleteMateria(@PathVariable Long id) {
        materiaService.deleteMateria(id);
    }
}
