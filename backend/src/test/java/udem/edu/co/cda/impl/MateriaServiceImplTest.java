package udem.edu.co.cda.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import udem.edu.co.cda.entities.Materia;
import udem.edu.co.cda.repository.MateriaRepository;
import udem.edu.co.cda.service.impl.MateriaServiceImpl;

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
    void testFindAllMaterias() {
        List<Materia> materias = new ArrayList<>();
        materias.add(new Materia(1L, "Matemáticas"));
        materias.add(new Materia(2L, "Física"));

        when(materiaRepository.findAll()).thenReturn(materias);

        List<Materia> resultado = materiaService.findAllMaterias();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Matemáticas", resultado.get(0).getNombre());
        assertEquals("Física", resultado.get(1).getNombre());

        verify(materiaRepository, times(1)).findAll();
        verifyNoMoreInteractions(materiaRepository);
    }

    @Test
    void testFindByIdMateria() {
        Materia materia = new Materia(1L, "Matemáticas");
        when(materiaRepository.findById(1L)).thenReturn(Optional.of(materia));

        Optional<Materia> resultado = materiaService.findByIdMateria(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Matemáticas", resultado.get().getNombre());

        verify(materiaRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(materiaRepository);
    }

    @Test
    void testFindByIdMateriaNotFound() {
        when(materiaRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Materia> resultado = materiaService.findByIdMateria(99L);

        assertFalse(resultado.isPresent());

        verify(materiaRepository, times(1)).findById(99L);
        verifyNoMoreInteractions(materiaRepository);
    }

    @Test
    void testCreateMateria() {
        Materia materia = new Materia();
        materia.setNombre("Matemáticas");

        Materia materiaGuardada = new Materia(1L, "Matemáticas");

        when(materiaRepository.save(any(Materia.class))).thenReturn(materiaGuardada);

        Materia resultado = materiaService.createMateria(materia);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Matemáticas", resultado.getNombre());

        verify(materiaRepository, times(1)).save(any(Materia.class));
        verifyNoMoreInteractions(materiaRepository);
    }

    @Test
    void testUpdateMateria() {
        Materia materiaExistente = new Materia(1L, "Matemáticas");
        Materia materiaActualizada = new Materia(1L, "Matemáticas Avanzadas");

        when(materiaRepository.findById(1L)).thenReturn(Optional.of(materiaExistente));
        when(materiaRepository.save(any(Materia.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Materia resultado = materiaService.updateMateria(1L, materiaActualizada);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Matemáticas Avanzadas", resultado.getNombre());

        verify(materiaRepository, times(1)).findById(1L);
        verify(materiaRepository, times(1)).save(any(Materia.class));
        verifyNoMoreInteractions(materiaRepository);
    }

    @Test
    void testDeleteMateria() {
        when(materiaRepository.existsById(1L)).thenReturn(true);
        doNothing().when(materiaRepository).deleteById(1L);

        materiaService.deleteMateria(1L);

        verify(materiaRepository, times(1)).existsById(1L);
        verify(materiaRepository, times(1)).deleteById(1L);
        verifyNoMoreInteractions(materiaRepository);
    }

    @Test
    void testDeleteMateriaNotFound() {
        when(materiaRepository.existsById(anyLong())).thenReturn(false);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> materiaService.deleteMateria(99L));

        assertEquals("Materia no encontrada con id: 99", thrown.getMessage());

        verify(materiaRepository, times(1)).existsById(99L);
        verifyNoMoreInteractions(materiaRepository);
    }

    @Test
    void testFindAllMateriasException() {
        when(materiaRepository.findAll()).thenThrow(new RuntimeException("Error de base de datos"));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> materiaService.findAllMaterias());

        assertEquals("Error de base de datos", thrown.getMessage());

        verify(materiaRepository, times(1)).findAll();
        verifyNoMoreInteractions(materiaRepository);
    }
}
