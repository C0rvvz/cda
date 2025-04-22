package udem.edu.co.cda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import udem.edu.co.cda.entities.Estudiante;
import udem.edu.co.cda.service.EstudianteService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/cda")
public class EstudianteController {

    @Autowired
    EstudianteService estudianteService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/estudiante")
    public List<Estudiante> findAllEstudiantes() {
        try {
            return this.estudianteService.findAllEstudiantes();
        } catch (IOException e) {
            System.err.println("ERROR: informacion no valida:\n");
            return null;
        } catch (SQLException e) {
            System.err.println("ERROR: No existen estudiantes:\n");
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/estudiante/{id}")
    public Optional<Estudiante> findEstudianteById(@PathVariable("id") int id) {
        try {
            return this.estudianteService.findByIdEstudiante(id);
        } catch (IOException e) {
            System.err.println("ERROR: informacion no valida:\n");
            return null;
        } catch (SQLException e) {
            System.err.println("ERROR: El nombre no existe:\n");
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/estudiante")
    public Estudiante createEstudiante(@RequestBody Estudiante estudiante) {
        try {
            return this.estudianteService.createEstudiante(estudiante);
        } catch (IOException e) {
            System.err.println("ERROR: informacion no valida:\n");
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/estudiante/{id}")
    public Estudiante updateEstudiante(@PathVariable("id") int id, @RequestBody Estudiante estudiante) {
        try {
            return this.estudianteService.updateEstudiante(id, estudiante);
        } catch (IOException e) {
            System.err.println("ERROR: informacion no valida:\n");
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/estudiante/{id}")
    public void deleteEstudiante(@PathVariable("id") int id) {
        try {
            this.estudianteService.deleteEstudiante(id);
        } catch (IOException e) {
            System.err.println("ERROR: informacion no valida:\n");
        }
    }
}