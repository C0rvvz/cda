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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MateriaServiceImplTest {

    @Mock
    private MateriaRepository materiaRepository;

    @InjectMocks
    private MateriaServiceImpl materiaService;

    @Test
    void testFindAllMaterias() throws IOException, SQLException {
        List<Materia> materias = new ArrayList<>();
        Materia materia1 = new Materia(1L, "Matemáticas");
        Materia materia2 = new Materia(2L, "Física");
        materias.add(materia1);
        materias.add(materia2);

        when(materiaRepository.findAll()).thenReturn(materias);

        List<Materia> resultado = materiaService.findAllMaterias();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Matemáticas", resultado.get(0).getNombre()); // corregido getNombre()
        assertEquals("Física", resultado.get(1).getNombre());
        verify(materiaRepository, times(1)).findAll();
        verifyNoMoreInteractions(materiaRepository);
    }

    @Test
    void testFindByIdMateria() throws IOException, SQLException {
        Materia materia = new Materia(1L, "Matemáticas");
        when(materiaRepository.findById(1)).thenReturn(Optional.of(materia));

        Optional<Materia> resultado = materiaService.findByIdMateria(1);

        assertTrue(resultado.isPresent());
        assertEquals("Matemáticas", resultado.get().getNombre());
        verify(materiaRepository, times(1)).findById(1);
        verifyNoMoreInteractions(materiaRepository);
    }

    @Test
    void testFindByIdMateriaNotFound() throws IOException, SQLException {
        when(materiaRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Materia> resultado = materiaService.findByIdMateria(99);

        assertFalse(resultado.isPresent());
        verify(materiaRepository, times(1)).findById(99);
        verifyNoMoreInteractions(materiaRepository);
    }

    @Test
    void testCreateMateria() throws IOException {
        Materia materia = new Materia();
        materia.setNombre("Matemáticas");

        Materia materiaGuardada = new Materia(1, "Matemáticas");

        when(materiaRepository.save(any(Materia.class))).thenReturn(materiaGuardada);

        Materia resultado = materiaService.createMateria(materia);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Matemáticas", resultado.getNombre());
        verify(materiaRepository, times(1)).save(materia);
        verifyNoMoreInteractions(materiaRepository);
    }

    @Test
    void testUpdateMateria() throws IOException {
        Materia materia = new Materia(1, "Matemáticas Avanzadas");

        when(materiaRepository.save(any(Materia.class))).thenReturn(materia);

        Materia resultado = materiaService.updateMateria(1L, materia);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Matemáticas Avanzadas", resultado.getNombre());
        verify(materiaRepository, times(1)).save(materia);
        verifyNoMoreInteractions(materiaRepository);
    }

    @Test
    void testDeleteMateria() throws IOException {
        Materia materia = new Materia(1L, "Matemáticas");

        // Supongo que el servicio busca la materia y luego la elimina, por eso mockeamos findById y delete
        when(materiaRepository.findById(1)).thenReturn(Optional.of(materia));
        doNothing().when(materiaRepository).delete(materia);

        materiaService.deleteMateria(1L);

        verify(materiaRepository, times(1)).findById(1);
        verify(materiaRepository, times(1)).delete(materia);
        verifyNoMoreInteractions(materiaRepository);
    }

    @Test
    void testDeleteMateriaNotFound() throws IOException {
        when(materiaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Aquí podrías lanzar excepción o manejarlo, depende del servicio
        assertThrows(RuntimeException.class, () -> {
            materiaService.deleteMateria(99);
        });

        verify(materiaRepository, times(1)).findById(99);
        verifyNoMoreInteractions(materiaRepository);
    }

    @Test
    void testHandleRepositoryException() {
        when(materiaRepository.findAll()).thenThrow(new RuntimeException("Error de base de datos"));

        assertThrows(RuntimeException.class, () -> {
            materiaService.findAllMaterias();
        });
        verify(materiaRepository, times(1)).findAll();
        verifyNoMoreInteractions(materiaRepository);
    }
}
