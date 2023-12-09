package pe.edu.upao.petcare.web.pruebas;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upao.petcare.web.accion.models.Accion;
import pe.edu.upao.petcare.web.accion.repositories.RepositorioAccion;
import pe.edu.upao.petcare.web.accion.services.AccionServicio;
import pe.edu.upao.petcare.web.categoria.models.Categoria;
import pe.edu.upao.petcare.web.categoria.repositories.RepositorioCategoria;
import pe.edu.upao.petcare.web.categoria.services.CategoriaDTO;
import pe.edu.upao.petcare.web.logro.models.Logro;
import pe.edu.upao.petcare.web.logro.repositories.RepositorioLogro;
import pe.edu.upao.petcare.web.logro.services.LogroServicio;
import pe.edu.upao.petcare.web.mascota.models.Mascota;
import pe.edu.upao.petcare.web.mascota.repositories.RepositorioMascota;
import pe.edu.upao.petcare.web.tarea.models.Tarea;
import pe.edu.upao.petcare.web.tarea.repositories.RepositorioTarea;
import pe.edu.upao.petcare.web.tarea.services.TareaConProgresoDTO;
import pe.edu.upao.petcare.web.tarea.services.TareaDTO;
import pe.edu.upao.petcare.web.tarea.services.TareaServicio;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TareaTest {

    @InjectMocks
    private TareaServicio tareaServicio;

    @Mock
    private RepositorioTarea repositorioTarea;

    @Mock
    private RepositorioAccion repositorioAccion;

    @Mock
    private LogroServicio logroServicio;

    @Mock
    private RepositorioMascota repositorioMascota;




    //escenario exitoso

    @Test
    void marcarTareaComoCompletada_TareaExiste_LogroNoCompleto() {
        // Configurar datos ficticios y comportamiento de los mocks
        Long tareaId = 1L;
        Tarea tareaMock = mock(Tarea.class);
        Accion accionMock = mock(Accion.class);
        List<Accion> accionesMock = List.of(accionMock);

        when(repositorioTarea.findById(tareaId)).thenReturn(Optional.of(tareaMock));
        when(repositorioAccion.findByTarea(tareaMock)).thenReturn(accionesMock);
        when(accionMock.getVecesNecesarias()).thenReturn(3);
        when(accionMock.getVecesCompletadas()).thenReturn(1);

        // Llamada al método a probar
        tareaServicio.completarTarea(tareaId);

        // Verificaciones
        verify(repositorioTarea).findById(tareaId);
        verify(repositorioAccion).findByTarea(tareaMock);
        verify(accionMock).setVecesCompletadas(2); // Verifica que el puntaje se ha incrementado
        verify(repositorioAccion).save(accionMock);
        verify(logroServicio, never()).verificarYActualizarLogro(any(Logro.class)); // Verifica que el logro no se verifica/actualiza ya que no se ha alcanzado el umbral necesario
    }



    //escenario exitoso
    @Test
    void cuandoTareaNoExisteEntoncesLanzaExcepcion() {
        // Configura un ID de tarea que no existe
        Long tareaIdInvalido = 99L;
        when(repositorioTarea.findById(tareaIdInvalido)).thenReturn(Optional.empty());

        // Verifica que se lanza la excepción adecuada
        assertThrows(EntityNotFoundException.class, () -> {
            tareaServicio.completarTarea(tareaIdInvalido);
        });
    }


    //escenario exitoso
    @Test
    void obtenerTareasPorIdCategoria_RetornaListaDeTareaDTO() {
        // Configuración de datos ficticios
        Long idCategoria = 1L;
        Tarea tarea1 = new Tarea(/* parámetros necesarios */);
        Tarea tarea2 = new Tarea(/* parámetros necesarios */);
        List<Tarea> listaTareas = Stream.of(tarea1, tarea2).collect(Collectors.toList());

        when(repositorioTarea.findByCategoria_IdCategoria(idCategoria)).thenReturn(listaTareas);

        // Llamada al método a probar
        List<TareaDTO> resultado = tareaServicio.obtenerTareasPorIdCategoria(idCategoria);

        // Verificaciones
        verify(repositorioTarea).findByCategoria_IdCategoria(idCategoria);
        assertEquals(listaTareas.size(), resultado.size());
        // Aquí se pueden añadir más aserciones para verificar el contenido de los DTOs si es necesario.
    }

    //escenario fallido

    @Test
    void obtenerTareasPorIdCategoria_RetornaListaVaciaCuandoNoSeEncuentranTareas() {
        // Configuración de un ID de categoría que no tiene tareas asociadas
        Long idCategoriaSinTareas = 99L;
        when(repositorioTarea.findByCategoria_IdCategoria(idCategoriaSinTareas)).thenReturn(List.of());

        // Llamada al método a probar
        List<TareaDTO> resultado = tareaServicio.obtenerTareasPorIdCategoria(idCategoriaSinTareas);

        // Verificaciones
        verify(repositorioTarea).findByCategoria_IdCategoria(idCategoriaSinTareas);
        assertTrue(resultado.isEmpty(), "La lista de TareaDTO debería estar vacía si no hay tareas asociadas con la categoría");
    }





}
