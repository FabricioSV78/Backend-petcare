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
    private LogroServicio logroServicio;

    @Autowired
    private RepositorioAccion repositorioAccion;

    @Autowired
    private RepositorioMascota repositorioMascota;

    public TareaServicio(RepositorioTarea repositorioTarea, RepositorioMascota repositorioMascota, RepositorioAccion repositorioAccion, LogroServicio logroServicio) {
    }


    //----------------------NUEVO-----------------------------//

    /*public void completarTarea(Long tareaId) {
        Tarea tarea = repositorioTarea.findById(tareaId).orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada con id: " + tareaId));
        List<Accion> requisitos = repositorioAccion.findByTarea(tarea);

        for (Accion requisito : requisitos) {
            requisito.setVecesCompletadas(requisito.getVecesCompletadas() + 1);
            repositorioAccion.save(requisito);

            if (requisito.getVecesCompletadas() >= requisito.getVecesNecesarias()) {
                logroServicio.verificarYActualizarLogro(requisito.getLogro());
            }
        }
    }*/

    public void completarTarea(Long tareaId, Long idMascota) {
        Tarea tarea = repositorioTarea.findById(tareaId)
                .orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada con id: " + tareaId));

        Mascota mascota = repositorioMascota.findById(idMascota)
                .orElseThrow(() -> new EntityNotFoundException("Mascota no encontrada con id: " + idMascota));

                List<Accion> requisitos = repositorioAccion.findByTarea(tarea);

        for (Accion requisito : requisitos) {
            requisito.setVecesCompletadas(requisito.getVecesCompletadas() + 1);
            repositorioAccion.save(requisito);

            if (requisito.getVecesCompletadas() >= requisito.getVecesNecesarias()) {
                logroServicio.verificarYActualizarLogro(requisito.getLogro(), mascota);
            }
        }

        actualizarMascotaDespuesDeCompletarTarea(mascota);


    }

    private void actualizarMascotaDespuesDeCompletarTarea(Mascota mascota) {
        // Aquí iría la lógica para actualizar la mascota.
        // Por ejemplo, aumentar la felicidad:
        mascota.aumentarFelicidad(5); // Suponiendo que completar una tarea incrementa la felicidad en 5 puntos.
        repositorioMascota.save(mascota);
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








}
