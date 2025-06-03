package udem.edu.co.cda.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import udem.edu.co.cda.entities.Materia;
import udem.edu.co.cda.repository.MateriaRepository;
import udem.edu.co.cda.service.impl.MateriaServiceImpl;

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
public class MateriaServiceImplTest {

    @Mock
    private MateriaRepository materiaRepository;

    @InjectMocks
    private MateriaServiceImpl materiaService;

    // Ya no necesitamos el método setUp() con @BeforeEach porque
    // la extensión @ExtendWith(MockitoExtension.class) inicializa los mocks automáticamente

    @Test
    void testFindAllMaterias() throws IOException, SQLException {
        // Arrange
        List<Materia> materias = new ArrayList<>();
        Materia materia1 = new Materia(1, "Matemáticas");
        Materia materia2 = new Materia(2, "Física");
        materias.add(materia1);
        materias.add(materia2);

        when(materiaRepository.findAll()).thenReturn(materias);

        // Act
        List<Materia> resultado = materiaService.findAllMaterias();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Matemáticas", resultado.get(0).getName());
        assertEquals("Física", resultado.get(1).getName());
        verify(materiaRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdMateria() throws IOException, SQLException {
        // Arrange
        Materia materia = new Materia(1, "Matemáticas");
        when(materiaRepository.findById(1)).thenReturn(Optional.of(materia));

        // Act
        Optional<Materia> resultado = materiaService.findByIdMateria(1);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("Matemáticas", resultado.get().getName());
        verify(materiaRepository, times(1)).findById(1);
    }

    @Test
    void testFindByIdMateriaNotFound() throws IOException, SQLException {
        // Arrange
        when(materiaRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        Optional<Materia> resultado = materiaService.findByIdMateria(99);

        // Assert
        assertFalse(resultado.isPresent());
        verify(materiaRepository, times(1)).findById(99);
    }

    @Test
    void testCreateMateria() throws IOException {
        // Arrange
        Materia materia = new Materia();
        materia.setName("Matemáticas");

        Materia materiaGuardada = new Materia(1, "Matemáticas");

        when(materiaRepository.save(any(Materia.class))).thenReturn(materiaGuardada);

        // Act
        Materia resultado = materiaService.createMateria(materia);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Matemáticas", resultado.getName());
        verify(materiaRepository, times(1)).save(materia);
    }

    @Test
    void testUpdateMateria() throws IOException {
        // Arrange
        Materia materia = new Materia(1, "Matemáticas Avanzadas");

        when(materiaRepository.save(any(Materia.class))).thenReturn(materia);

        // Act
        Materia resultado = materiaService.updateMateria(1, materia);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Matemáticas Avanzadas", resultado.getName());
        verify(materiaRepository, times(1)).save(materia);
    }

    @Test
    void testDeleteMateria() throws IOException {
        // Arrange
        doNothing().when(materiaRepository).delete(any(Materia.class));

        // Act
        materiaService.deleteMateria(1);

        // Assert
        verify(materiaRepository, times(1)).delete(any(Materia.class));
    }

    @Test
    void testHandleRepositoryException() {
        // Arrange
        when(materiaRepository.findAll()).thenThrow(new RuntimeException("Error de base de datos"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            materiaService.findAllMaterias();
        });
    }
}
