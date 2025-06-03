package udem.edu.co.cda.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import udem.edu.co.cda.entities.Estudiante;
import udem.edu.co.cda.repository.EstudianteRepository;
import udem.edu.co.cda.service.impl.EstudianteServiceImpl;

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
public class EstudianteServiceImplTest {

    static {
        // Configuración para usar el modo inline de Mockito
        System.setProperty("mockito.mock-maker-inline", "true");
    }

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteServiceImpl estudianteService;

    // Ya no necesitamos el método setUp() con @BeforeEach porque
    // la extensión @ExtendWith(MockitoExtension.class) inicializa los mocks automáticamente

    @Test
    void testFindAllEstudiantes() throws IOException, SQLException {
        // Arrange
        List<Estudiante> estudiantes = new ArrayList<>();
        Estudiante estudiante1 = new Estudiante(1, "Juan");
        Estudiante estudiante2 = new Estudiante(2, "María");
        estudiantes.add(estudiante1);
        estudiantes.add(estudiante2);

        when(estudianteRepository.findAll()).thenReturn(estudiantes);

        // Act
        List<Estudiante> resultado = estudianteService.findAllEstudiantes();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Juan", resultado.get(0).getName());
        assertEquals("María", resultado.get(1).getName());
        verify(estudianteRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdEstudiante() throws IOException, SQLException {
        // Arrange
        Estudiante estudiante = new Estudiante(1, "Juan");
        when(estudianteRepository.findById(1)).thenReturn(Optional.of(estudiante));

        // Act
        Optional<Estudiante> resultado = estudianteService.findByIdEstudiante(1);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("Juan", resultado.get().getName());
        verify(estudianteRepository, times(1)).findById(1);
    }

    @Test
    void testFindByIdEstudianteNotFound() throws IOException, SQLException {
        // Arrange
        when(estudianteRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        Optional<Estudiante> resultado = estudianteService.findByIdEstudiante(99);

        // Assert
        assertFalse(resultado.isPresent());
        verify(estudianteRepository, times(1)).findById(99);
    }

    @Test
    void testCreateEstudiante() throws IOException {
        // Arrange
        Estudiante estudiante = new Estudiante("Juan");
        Estudiante estudianteGuardado = new Estudiante(1, "Juan");

        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudianteGuardado);

        // Act
        Estudiante resultado = estudianteService.createEstudiante(estudiante);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Juan", resultado.getName());
        verify(estudianteRepository, times(1)).save(estudiante);
    }

    @Test
    void testUpdateEstudiante() throws IOException {
        // Arrange
        Estudiante estudiante = new Estudiante(1, "Juan");

        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudiante);

        // Act
        Estudiante resultado = estudianteService.updateEstudiante(1, estudiante);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Juan", resultado.getName());
        verify(estudianteRepository, times(1)).save(estudiante);
    }

    @Test
    void testDeleteEstudiante() throws IOException {
        // Arrange
        doNothing().when(estudianteRepository).delete(any(Estudiante.class));

        // Act
        estudianteService.deleteEstudiante(1);

        // Assert
        verify(estudianteRepository, times(1)).delete(any(Estudiante.class));
    }

    @Test
    void testHandleRepositoryException() {
        // Arrange
        when(estudianteRepository.findAll()).thenThrow(new RuntimeException("Error de base de datos"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            estudianteService.findAllEstudiantes();
        });
    }
}
