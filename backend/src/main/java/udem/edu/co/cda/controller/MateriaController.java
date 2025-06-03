package udem.edu.co.cda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import udem.edu.co.cda.entities.Materia;
import udem.edu.co.cda.service.MateriaService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/cda")
public class MateriaController {

    @Autowired
    MateriaService materiaService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/materia")
    public List<Materia> findAllMaterias() {
        try {
            return this.materiaService.findAllMaterias();
        } catch (IOException e) {
            System.err.println("ERROR: informacion no valida:\n");
            return null;
        } catch (SQLException e) {
            System.err.println("ERROR: No existen materias:\n");
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/materia/{id}")
    public Optional<Materia> findMateriaById(@PathVariable("id") int id) {
        try {
            return this.materiaService.findByIdMateria(id);
        } catch (IOException e) {
            System.err.println("ERROR: informacion no valida:\n");
            return null;
        } catch (SQLException e) {
            System.err.println("ERROR: El ID no existe:\n");
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/materia")
    public Materia createMateria(@RequestBody Materia materia) {
        try {
            return this.materiaService.createMateria(materia);
        } catch (IOException e) {
            System.err.println("ERROR: informacion no valida:\n");
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/materia/{id}")
    public Materia updateMateria(@PathVariable("id") int id, @RequestBody Materia materia) {
        try {
            return this.materiaService.updateMateria(id, materia);
        } catch (IOException e) {
            System.err.println("ERROR: informacion no valida:\n");
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/materia/{id}")
    public void deleteMateria(@PathVariable("id") int id) {
        try {
            this.materiaService.deleteMateria(id);
        } catch (IOException e) {
            System.err.println("ERROR: informacion no valida:\n");
        }
    }
}