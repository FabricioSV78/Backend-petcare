package pe.edu.upao.petcare.web.accion.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upao.petcare.web.accion.models.Accion;
import pe.edu.upao.petcare.web.accion.repositories.RepositorioAccion;
import pe.edu.upao.petcare.web.logro.models.Logro;
import pe.edu.upao.petcare.web.logro.services.LogroServicio;
import pe.edu.upao.petcare.web.mascota.models.Mascota;
import pe.edu.upao.petcare.web.mascota.repositories.RepositorioMascota;
import pe.edu.upao.petcare.web.tarea.models.Tarea;
import pe.edu.upao.petcare.web.tarea.repositories.RepositorioTarea;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccionServicio {

    @Autowired
    private RepositorioAccion repositorioAccion;

    public List<Accion.AccionEstado> obtenerEstadoPorIdTarea(Long idTarea) {
        List<Accion> acciones = repositorioAccion.findByTarea_IdTarea(idTarea);
        return acciones.stream().map(Accion::getEstado).collect(Collectors.toList());
    }


}
