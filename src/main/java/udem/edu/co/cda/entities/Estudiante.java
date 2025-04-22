package udem.edu.co.cda.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Estudiante(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @ManyToMany
    @JoinTable(
        name = "estudiante_materia",
        joinColumns = @JoinColumn(name = "estudiante_id"),
        inverseJoinColumns = @JoinColumn(name = "materia_id")
    )

    
    private List<Materia> materias;

    public Estudiante() {
    }

    public Estudiante(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
}