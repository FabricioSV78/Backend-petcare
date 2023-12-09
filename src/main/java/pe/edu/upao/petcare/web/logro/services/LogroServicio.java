package pe.edu.upao.petcare.web.logro.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upao.petcare.web.accion.models.Accion;
import pe.edu.upao.petcare.web.logro.models.Logro;
import pe.edu.upao.petcare.web.logro.repositories.RepositorioLogro;
import pe.edu.upao.petcare.web.mascota.repositories.RepositorioMascota;
import pe.edu.upao.petcare.web.tarea.repositories.RepositorioTarea;
import pe.edu.upao.petcare.web.tarea.services.TareaDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LogroServicio {

    @Autowired
    private RepositorioLogro repositorioLogro;

    @Autowired
    private RepositorioTarea repositorioTarea;

    @Autowired
    private RepositorioMascota repositorioMascota;


    public LogroDTO actualizarPuntaje(Long idLogro, int puntajeTarea) {
        Optional<Logro> optionalLogro = repositorioLogro.findById(idLogro);

        if (optionalLogro.isPresent()) {
            Logro logro = optionalLogro.get();
            logro.setPuntajeActual(logro.getPuntajeActual() + puntajeTarea);

            if (!logro.isCompletado() && esLogroCompletado(logro)) {
                logro.setCompletado(true);
            }

            Logro logroGuardado = repositorioLogro.save(logro);
            return convertirAlogroDTO(logroGuardado); // Asegúrate de tener implementado este método.
        } else {
            // lanzar una excepción aquí en lugar de retornar null.
            throw new EntityNotFoundException("Logro no encontrado con ID: " + idLogro);
        }
    }

    public List<LogroDTO> listarLogrosPorCategoria(Long idCategoria) {
        return repositorioLogro.findByCategoriaIdCategoria(idCategoria)
                .stream()
                .map(logro -> new LogroDTO(
                        logro.getNombreLogro(),
                        logro.getDescripcion(),
                        logro.isCompletado()))
                .collect(Collectors.toList());
    }



    public void verificarYActualizarLogro(Logro logro) {
        List<Accion> requisitos = logro.getAccions();
        boolean todosCompletados = requisitos.stream()
                .allMatch(requisito -> requisito.getVecesCompletadas() >= requisito.getVecesNecesarias());

        if (todosCompletados) {
            logro.setCompletado(true);
            repositorioLogro.save(logro);
        }
    }




    public LogroDTO convertirAlogroDTO(Logro logro) {
        LogroDTO logroDTO = new LogroDTO();
        logroDTO.setNombreLogro(logro.getNombreLogro());
        logroDTO.setDescripcion(logro.getDescripcion());
        logroDTO.setPuntajeTotal(logro.getPuntajeTotal());
        logroDTO.setPuntajeActual(logro.getPuntajeActual());
        logroDTO.setCompletado(logro.isCompletado());
        // Mapear más campos si es necesario
        return logroDTO;
    }


    private boolean esLogroCompletado(Logro logro) {
        int sumaPuntajeTareas = repositorioTarea.sumaPuntajePorLogro(logro.getIdLogro());
        return sumaPuntajeTareas >= logro.getPuntajeTotal();
    }







}
