package udem.edu.co.cda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import udem.edu.co.cda.entities.Profesor;
import udem.edu.co.cda.service.ProfesorService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/cda")
public class ProfesorController {

    @Autowired
    ProfesorService profesorService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/profesor")
    public List<Profesor> findAllProfesor(){
        try {
            return profesorService.findAllProfesores();
        } catch (IOException e) {
            System.err.println("ERROR: informacion no valida:\n");
            return null;
        } catch (SQLException e) {
            System.err.println("ERROR: No existen profesores:\n");
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/profesor/{id}")
    public Optional<Profesor> findProfesorById(@PathVariable("id") int id){
        try {
            return profesorService.findByIdProfesor(id);
        } catch (IOException e) {
            System.err.println("ERROR: informacion no valida:\n");
            return null;
        } catch (SQLException e) {
            System.err.println("ERROR: No existe ese profesor:\n");
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/profesor")
    public Profesor createProfesor(@RequestBody() Profesor profesor){
        try {
            return profesorService.createProfesor(profesor);
        } catch (IOException e) {
            System.err.println("ERROR: informacion no valida:\n");
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/profesor/{id}")
    public Profesor updateProfesor(@PathVariable("id") int id, @RequestBody() Profesor profesor){
        try {
            return profesorService.updateProfesor(id, profesor);
        } catch (IOException e) {
            System.err.println("ERROR: informacion no valida:\n");
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/profesor/{id}")
    public void deleteProfesor(@PathVariable("id") int id){
        try {
            profesorService.deleteProfesor(id);
        } catch (IOException e) {
            System.err.println("ERROR: informacion no valida:\n");
        }
    }
}
