package pe.edu.upao.petcare.web.accion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upao.petcare.web.accion.models.Accion;
import pe.edu.upao.petcare.web.mascota.models.Mascota;
import pe.edu.upao.petcare.web.tarea.models.Tarea;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface RepositorioAccion extends JpaRepository<Accion, Long> {


    List<Accion> findByTarea(Tarea tarea);

    List<Accion> findByTarea_IdTarea(Long idTarea);
}

