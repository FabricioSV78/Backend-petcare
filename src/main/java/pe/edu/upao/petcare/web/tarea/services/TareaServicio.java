package pe.edu.upao.petcare.web.tarea.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upao.petcare.web.accion.models.Accion;
import pe.edu.upao.petcare.web.accion.repositories.RepositorioAccion;
import pe.edu.upao.petcare.web.categoria.models.Categoria;
import pe.edu.upao.petcare.web.categoria.repositories.RepositorioCategoria;
import pe.edu.upao.petcare.web.categoria.services.CategoriaConProgresoDTO;
import pe.edu.upao.petcare.web.categoria.services.CategoriaConTareasDTO;
import pe.edu.upao.petcare.web.categoria.services.CategoriaDTO;
import pe.edu.upao.petcare.web.logro.models.Logro;
import pe.edu.upao.petcare.web.logro.repositories.RepositorioLogro;
import pe.edu.upao.petcare.web.logro.services.LogroDTO;
import pe.edu.upao.petcare.web.logro.services.LogroServicio;
import pe.edu.upao.petcare.web.mascota.models.Mascota;
import pe.edu.upao.petcare.web.mascota.repositories.RepositorioMascota;
import pe.edu.upao.petcare.web.tarea.models.Tarea;
import pe.edu.upao.petcare.web.tarea.repositories.RepositorioTarea;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TareaServicio {

    @Autowired
    private RepositorioTarea repositorioTarea;

    @Autowired
    private RepositorioLogro repositorioLogro;

    @Autowired
    private LogroServicio logroServicio;

    @Autowired
    private RepositorioAccion repositorioAccion;

    @Autowired
    private RepositorioCategoria repositorioCategoria;

    @Autowired
    private RepositorioMascota repositorioMascota;



    // Método auxiliar para calcular el cambio en la felicidad
    private int calcularValorFelicidad(Tarea tarea) {
        // La lógica aquí dependerá de cómo quieres calcular la felicidad
        // Por ejemplo, podría ser un valor fijo o basado en el puntaje de la tarea
        return 5; // Supongamos que cada tarea aumenta la felicidad en 5 puntos
    }


    //----------------------NUEVO-----------------------------//

    public void completarTarea(Long tareaId) {
        Tarea tarea = repositorioTarea.findById(tareaId).orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada con id: " + tareaId));
        List<Accion> requisitos = repositorioAccion.findByTarea(tarea);

        for (Accion requisito : requisitos) {
            requisito.setVecesCompletadas(requisito.getVecesCompletadas() + 1);
            repositorioAccion.save(requisito);

            if (requisito.getVecesCompletadas() >= requisito.getVecesNecesarias()) {
                logroServicio.verificarYActualizarLogro(requisito.getLogro());
            }
        }
    }

    public List<String> completarTareasYVerificarLogros(List<Long> idsTareas) {
        Set<Logro> logrosPotencialmenteCompletados = new HashSet<>();

        for (Long idTarea : idsTareas) {
            Tarea tarea = repositorioTarea.findById(idTarea).orElseThrow(/* excepción */);
            List<Accion> requisitos = repositorioAccion.findByTarea(tarea);

            for (Accion requisito : requisitos) {
                requisito.incrementarVecesCompletadas();
                repositorioAccion.save(requisito);

                if (requisito.getVecesCompletadas() >= requisito.getVecesNecesarias()) {
                    logrosPotencialmenteCompletados.add(requisito.getLogro());
                }
            }
        }

        return logrosPotencialmenteCompletados.stream()
                .filter(logro -> logro.isCompleto())
                .map(Logro::getNombreLogro)
                .collect(Collectors.toList());
    }

    public List<TareaDTO> obtenerTareasPorIdCategoria(Long idCategoria) {
        List<Tarea> tareas = repositorioTarea.findByCategoria_IdCategoria(idCategoria);
        return tareas.stream()
                .map(this::convertirATareaDTO)
                .collect(Collectors.toList());
    }


    // Este método asume que has implementado una forma de convertir la entidad Tarea a un TareaDTO
    private TareaDTO convertirATareaDTO(Tarea tarea) {
        return new TareaDTO(
                tarea.getNombreTarea(),
                tarea.getPuntajeTarea()
                //tarea.getFrecuenciaEnDias()
        );
    }



    public TareaConProgresoDTO convertirATareaConProgresoDTO(Tarea tarea, Logro logro) {
        if (logro == null) {
            throw new IllegalArgumentException("El logro no puede ser nulo.");
        }
        int vecesCompletada = logro.getPuntajeActual(); // Esto asume que el puntaje actual del logro refleja las veces completadas
        return new TareaConProgresoDTO(
                tarea.getNombreTarea(),
                logro.getPuntajeActual(), // Este valor debería ser el incrementado por el servicio
                vecesCompletada, // Este debería ser calculado o incrementado adecuadamente
                logro.getPuntajeTotal(),
                logro.isCompletado()
        );
    }




}
