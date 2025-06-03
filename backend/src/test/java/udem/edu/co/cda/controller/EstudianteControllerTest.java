package udem.edu.co.cda.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import udem.edu.co.cda.entities.Estudiante;
import udem.edu.co.cda.service.EstudianteService;

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
class EstudianteControllerTest {

    @Mock
    private EstudianteService estudianteService;

    @InjectMocks
    private EstudianteController estudianteController;

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

        when(estudianteService.findAllEstudiantes()).thenReturn(estudiantes);

        // Act
        List<Estudiante> resultado = estudianteController.findAllEstudiantes();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Juan", resultado.get(0).getName());
        assertEquals("María", resultado.get(1).getName());
        verify(estudianteService, times(1)).findAllEstudiantes();
    }

    @Test
    void testFindAllEstudiantesWithIOException() throws IOException, SQLException {
        // Arrange
        when(estudianteService.findAllEstudiantes()).thenThrow(new IOException("Error de prueba"));

        // Act
        List<Estudiante> resultado = estudianteController.findAllEstudiantes();

        // Assert
        assertNull(resultado);
        verify(estudianteService, times(1)).findAllEstudiantes();
    }

    @Test
    void testFindAllEstudiantesWithSQLException() throws IOException, SQLException {
        // Arrange
        when(estudianteService.findAllEstudiantes()).thenThrow(new SQLException("Error de prueba"));

        // Act
        List<Estudiante> resultado = estudianteController.findAllEstudiantes();

        // Assert
        assertNull(resultado);
        verify(estudianteService, times(1)).findAllEstudiantes();
    }

    @Test
    void testFindEstudianteById() throws IOException, SQLException {
        // Arrange
        Estudiante estudiante = new Estudiante(1, "Juan");
        when(estudianteService.findByIdEstudiante(1)).thenReturn(Optional.of(estudiante));

        // Act
        Optional<Estudiante> resultado = estudianteController.findEstudianteById(1);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("Juan", resultado.get().getName());
        verify(estudianteService, times(1)).findByIdEstudiante(1);
    }

    @Test
    void testFindEstudianteByIdWithIOException() throws IOException, SQLException {
        // Arrange
        when(estudianteService.findByIdEstudiante(anyInt())).thenThrow(new IOException("Error de prueba"));

        // Act
        Optional<Estudiante> resultado = estudianteController.findEstudianteById(1);

        // Assert
        assertNull(resultado);
        verify(estudianteService, times(1)).findByIdEstudiante(1);
    }

    @Test
    void testFindEstudianteByIdWithSQLException() throws IOException, SQLException {
        // Arrange
        when(estudianteService.findByIdEstudiante(anyInt())).thenThrow(new SQLException("Error de prueba"));

        // Act
        Optional<Estudiante> resultado = estudianteController.findEstudianteById(1);

        // Assert
        assertNull(resultado);
        verify(estudianteService, times(1)).findByIdEstudiante(1);
    }

    @Test
    void testCreateEstudiante() throws IOException {
        // Arrange
        Estudiante estudiante = new Estudiante(1, "Juan");
        when(estudianteService.createEstudiante(any(Estudiante.class))).thenReturn(estudiante);

        // Act
        Estudiante resultado = estudianteController.createEstudiante(estudiante);

        // Assert
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getName());
        verify(estudianteService, times(1)).createEstudiante(estudiante);
    }

    @Test
    void testCreateEstudianteWithIOException() throws IOException {
        // Arrange
        Estudiante estudiante = new Estudiante(1, "Juan");
        when(estudianteService.createEstudiante(any(Estudiante.class))).thenThrow(new IOException("Error de prueba"));

        // Act
        Estudiante resultado = estudianteController.createEstudiante(estudiante);

        // Assert
        assertNull(resultado);
        verify(estudianteService, times(1)).createEstudiante(estudiante);
    }

    @Test
    void testUpdateEstudiante() throws IOException {
        // Arrange
        Estudiante estudiante = new Estudiante(1, "Juan Actualizado");
        when(estudianteService.updateEstudiante(anyInt(), any(Estudiante.class))).thenReturn(estudiante);

        // Act
        Estudiante resultado = estudianteController.updateEstudiante(1, estudiante);

        // Assert
        assertNotNull(resultado);
        assertEquals("Juan Actualizado", resultado.getName());
        verify(estudianteService, times(1)).updateEstudiante(1, estudiante);
    }

    @Test
    void testUpdateEstudianteWithIOException() throws IOException {
        // Arrange
        Estudiante estudiante = new Estudiante(1, "Juan");
        when(estudianteService.updateEstudiante(anyInt(), any(Estudiante.class))).thenThrow(new IOException("Error de prueba"));

        // Act
        Estudiante resultado = estudianteController.updateEstudiante(1, estudiante);

        // Assert
        assertNull(resultado);
        verify(estudianteService, times(1)).updateEstudiante(1, estudiante);
    }

    @Test
    void testDeleteEstudiante() throws IOException {
        // Arrange
        doNothing().when(estudianteService).deleteEstudiante(anyInt());

        // Act
        estudianteController.deleteEstudiante(1);

        // Assert
        verify(estudianteService, times(1)).deleteEstudiante(1);
    }

    @Test
    void testDeleteEstudianteWithIOException() throws IOException {
        // Arrange
        doThrow(new IOException("Error de prueba")).when(estudianteService).deleteEstudiante(anyInt());

        // Act
        estudianteController.deleteEstudiante(1);

        // Assert
        verify(estudianteService, times(1)).deleteEstudiante(1);
    }
}
