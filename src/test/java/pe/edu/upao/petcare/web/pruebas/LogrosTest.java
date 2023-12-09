package pe.edu.upao.petcare.web.pruebas;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upao.petcare.web.accion.models.Accion;
import pe.edu.upao.petcare.web.logro.models.Logro;
import pe.edu.upao.petcare.web.logro.repositories.RepositorioLogro;
import pe.edu.upao.petcare.web.logro.services.LogroDTO;
import pe.edu.upao.petcare.web.logro.services.LogroServicio;
import pe.edu.upao.petcare.web.mascota.repositories.RepositorioMascota;
import pe.edu.upao.petcare.web.tarea.repositories.RepositorioTarea;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LogrosTest {

    @Mock
    private RepositorioLogro repositorioLogro;


    @InjectMocks
    private LogroServicio logroServicio;

    // Escenario exitoso
    @Test
    public void cuandoActualizarPuntajeEntoncesRetornaLogroActualizado() {
        // Arrange
        Long idLogro = 1L;
        int puntajeTarea = 10;
        Logro logroExistente = new Logro();
        logroExistente.setPuntajeActual(20);
        logroExistente.setPuntajeTotal(100);
        logroExistente.setCompletado(false);

        when(repositorioLogro.findById(idLogro)).thenReturn(Optional.of(logroExistente));
        when(repositorioLogro.save(any(Logro.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        LogroDTO resultado = logroServicio.actualizarPuntaje(idLogro, puntajeTarea);

        // Assert
        assertNotNull(resultado);
        assertEquals(30, resultado.getPuntajeActual());
        assertFalse(resultado.isCompletado());
    }

    // Escenario fallido
    @Test
    public void cuandoLogroNoExisteEntoncesLanzaExcepcion() {
        // Arrange
        Long idLogroInexistente = 99L;
        int puntajeTarea = 10;

        when(repositorioLogro.findById(idLogroInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> {
            logroServicio.actualizarPuntaje(idLogroInexistente, puntajeTarea);
        });
    }



    // Escenario existoso

    @Test
    void verificarYActualizarLogro_TodosRequisitosCompletados_LogroActualizado() {
        // Configuración de datos ficticios
        Logro logro = mock(Logro.class);
        Accion accionCompleta = mock(Accion.class);
        when(accionCompleta.getVecesCompletadas()).thenReturn(5);
        when(accionCompleta.getVecesNecesarias()).thenReturn(5);
        List<Accion> requisitos = new ArrayList<>(List.of(accionCompleta));

        when(logro.getAccions()).thenReturn(requisitos);

        // Llamada al método a probar
        logroServicio.verificarYActualizarLogro(logro);

        // Verificaciones
        verify(logro).setCompletado(true);
        verify(repositorioLogro).save(logro);
    }


    // Escenario fallido
    @Test
    void verificarYActualizarLogro_RequisitosIncompletos_LogroNoActualizado() {
        // Configuración de datos ficticios
        Logro logro = mock(Logro.class);
        Accion accionIncompleta = mock(Accion.class);
        when(accionIncompleta.getVecesCompletadas()).thenReturn(3);
        when(accionIncompleta.getVecesNecesarias()).thenReturn(5);
        List<Accion> requisitos = new ArrayList<>(List.of(accionIncompleta));

        when(logro.getAccions()).thenReturn(requisitos);

        // Llamada al método a probar
        logroServicio.verificarYActualizarLogro(logro);

        // Verificaciones
        verify(logro, never()).setCompletado(true);
        verify(repositorioLogro, never()).save(logro);
    }


    //Escenario exitoso
    @Test
    void listarLogrosPorCategoria_RetornaListaDeLogroDTO() {
        // Configuración de datos ficticios
        Long idCategoria = 1L;
        Logro logro1 = new Logro();
        Logro logro2 = new Logro();
        List<Logro> listaLogros = Stream.of(logro1, logro2).collect(Collectors.toList());

        when(repositorioLogro.findByCategoriaIdCategoria(idCategoria)).thenReturn(listaLogros);

        // Llamada al método a probar
        List<LogroDTO> resultado = logroServicio.listarLogrosPorCategoria(idCategoria);

        // Verificaciones
        verify(repositorioLogro).findByCategoriaIdCategoria(idCategoria);
        assertEquals(listaLogros.size(), resultado.size());
        // Aquí se pueden añadir más aserciones para verificar el contenido de los DTOs si es necesario.
    }

    // Escenario fallido
    @Test
    void listarLogrosPorCategoria_RetornaListaVaciaCuandoNoSeEncuentranLogros() {
        // Configuración de un ID de categoría que no tiene logros asociados
        Long idCategoriaSinLogros = 99L;
        when(repositorioLogro.findByCategoriaIdCategoria(idCategoriaSinLogros)).thenReturn(List.of());

        // Llamada al método a probar
        List<LogroDTO> resultado = logroServicio.listarLogrosPorCategoria(idCategoriaSinLogros);

        // Verificaciones
        verify(repositorioLogro).findByCategoriaIdCategoria(idCategoriaSinLogros);
        assertTrue(resultado.isEmpty(), "La lista de LogroDTO debería estar vacía si no hay logros asociados con la categoría");
    }

}
