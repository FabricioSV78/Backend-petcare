package pe.edu.upao.petcare.web.pruebas;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Inicializa los mocks
    }




    //escenario exitoso

    @Test
    void marcarTareaComoCompletada_TareaYLogroExisten_LogroCompleto() {
        // Configurar datos ficticios y comportamiento de los mocks
        Long tareaId = 1L;
        Long idMascota = 3L;
        Tarea tareaMock = mock(Tarea.class);
        Mascota mascotaMock = mock(Mascota.class);
        Accion accionMock = mock(Accion.class);
        Logro logroMock = mock(Logro.class);
        List<Accion> accionesMock = List.of(accionMock);

        when(repositorioTarea.findById(tareaId)).thenReturn(Optional.of(tareaMock));
        when(repositorioMascota.findById(idMascota)).thenReturn(Optional.of(mascotaMock));
        when(repositorioAccion.findByTarea(tareaMock)).thenReturn(accionesMock);

        // Configuración inicial de veces completadas y necesarias
        when(accionMock.getVecesNecesarias()).thenReturn(2);
        when(accionMock.getVecesCompletadas()).thenReturn(1);
        when(accionMock.getLogro()).thenReturn(logroMock);

        doAnswer(invocation -> {
            when(accionMock.getVecesCompletadas()).thenReturn(2);
            return null;
        }).when(accionMock).setVecesCompletadas(anyInt());

        // Llamada al método a probar
        tareaServicio.completarTarea(tareaId, idMascota);

        // Verificaciones
        verify(repositorioTarea).findById(tareaId);
        verify(repositorioMascota).findById(idMascota);
        verify(repositorioAccion).findByTarea(tareaMock);
        verify(accionMock).setVecesCompletadas(2); // Verifica que el contador de veces completadas se incrementa
        verify(repositorioAccion).save(accionMock); // Verifica que la acción se guarda después de actualizarla
        verify(logroServicio).verificarYActualizarLogro(logroMock, mascotaMock); // Verifica que se verifica y actualiza el logro
    }

    //escenario fallido
    @Test
    void marcarTareaComoCompletada_TareaExiste_LogroNoCompleto() {
        // Configurar datos ficticios y comportamiento de los mocks
        Long tareaId = 1L;
        Long idMascota = 3L;
        Tarea tareaMock = mock(Tarea.class);
        Mascota mascotaMock = mock(Mascota.class);
        Accion accionMock = mock(Accion.class);
        List<Accion> accionesMock = List.of(accionMock);

        when(repositorioTarea.findById(tareaId)).thenReturn(Optional.of(tareaMock));
        when(repositorioMascota.findById(idMascota)).thenReturn(Optional.of(mascotaMock));
        when(repositorioAccion.findByTarea(tareaMock)).thenReturn(accionesMock);
        when(accionMock.getVecesNecesarias()).thenReturn(3);
        when(accionMock.getVecesCompletadas()).thenReturn(1);

        // Llamada al método a probar
        tareaServicio.completarTarea(tareaId, idMascota);

        // Verificaciones
        verify(repositorioTarea).findById(tareaId);
        verify(repositorioMascota).findById(idMascota);
        verify(repositorioAccion).findByTarea(tareaMock);
        verify(accionMock).setVecesCompletadas(2);
        verify(repositorioAccion).save(accionMock);
        verify(logroServicio, never()).verificarYActualizarLogro(any(Logro.class), any(Mascota.class));
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
