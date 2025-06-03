package udem.edu.co.cda.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import udem.edu.co.cda.entities.Profesor;
import udem.edu.co.cda.repository.ProfesorRepository;
import udem.edu.co.cda.service.impl.ProfesorServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfesorServiceImplTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @InjectMocks
    private ProfesorServiceImpl profesorService;

    // Ya no necesitamos el método setUp() con @BeforeEach porque
    // la extensión @ExtendWith(MockitoExtension.class) inicializa los mocks automáticamente

    @Test
    void testFindAllProfesores() throws IOException, SQLException {
        // Arrange
        List<Profesor> profesores = new ArrayList<>();
        Profesor profesor1 = new Profesor(1, "Carlos");
        Profesor profesor2 = new Profesor(2, "Ana");
        profesores.add(profesor1);
        profesores.add(profesor2);

        when(profesorRepository.findAll()).thenReturn(profesores);

        // Act
        List<Profesor> resultado = profesorService.findAllProfesores();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Carlos", resultado.get(0).getName());
        assertEquals("Ana", resultado.get(1).getName());
        verify(profesorRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdProfesor() throws IOException, SQLException {
        // Arrange
        Profesor profesor = new Profesor(1, "Carlos");
        when(profesorRepository.findById(1)).thenReturn(Optional.of(profesor));

        // Act
        Optional<Profesor> resultado = profesorService.findByIdProfesor(1);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("Carlos", resultado.get().getName());
        verify(profesorRepository, times(1)).findById(1);
    }

    @Test
    void testFindByIdProfesorNotFound() throws IOException, SQLException {
        // Arrange
        when(profesorRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        Optional<Profesor> resultado = profesorService.findByIdProfesor(99);

        // Assert
        assertFalse(resultado.isPresent());
        verify(profesorRepository, times(1)).findById(99);
    }

    @Test
    void testCreateProfesor() throws IOException {
        // Arrange
        Profesor profesor = new Profesor();
        profesor.setName("Carlos");

        Profesor profesorGuardado = new Profesor(1, "Carlos");

        when(profesorRepository.save(any(Profesor.class))).thenReturn(profesorGuardado);

        // Act
        Profesor resultado = profesorService.createProfesor(profesor);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Carlos", resultado.getName());
        verify(profesorRepository, times(1)).save(profesor);
    }

    @Test
    void testUpdateProfesor() throws IOException {
        // Arrange
        Profesor profesor = new Profesor(1, "Carlos Actualizado");

        when(profesorRepository.save(any(Profesor.class))).thenReturn(profesor);

        // Act
        Profesor resultado = profesorService.updateProfesor(1, profesor);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Carlos Actualizado", resultado.getName());
        verify(profesorRepository, times(1)).save(profesor);
    }

    @Test
    void testDeleteProfesor() throws IOException {
        // Arrange
        doNothing().when(profesorRepository).delete(any(Profesor.class));

        // Act
        profesorService.deleteProfesor(1);

        // Assert
        verify(profesorRepository, times(1)).delete(any(Profesor.class));
    }

    @Test
    void testHandleRepositoryException() {
        // Arrange
        when(profesorRepository.findAll()).thenThrow(new RuntimeException("Error de base de datos"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            profesorService.findAllProfesores();
        });
    }
}