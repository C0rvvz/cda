package udem.edu.co.cda.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="materia")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @OneToMany(mappedBy = "materia")
    private List<Clase> clases;

    public Materia(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Materia() {

    }

    // getters/setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }
}

